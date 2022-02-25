import Items.Item;
import Items.ItemController;
import Items.Treasure;
import Items.Weapon;

/**
 * @author Makenna Halvensleben
 * GameController holds the logic to run the game by handling user Commands.
 */
public class GameController {

    private static final int NUM_TREASURES = 24;
    private final ItemController itemController = new ItemController();
    private Player player;
    private Map map;
    private boolean quit;

    /**
     * Constructor for the GameController
     */
    public GameController() {
    }

    /**
     * The method called in Main that runs the game.
     * Contains the game loop that outputs the room information and handles input.
     */
    public void run() {
        // initialize map and player
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        Inputtable inputtable = IOManager.getInstance().getInputtable();
        RoomController roomController = new RoomController(itemController.createWeapons(), itemController.createTreasures());
        map = roomController.getMap();
        player = new Player(roomController.getMap().getMap()[roomController.getStartRow()][roomController.getStartColumn()],
                roomController.getStartRow(), roomController.getStartColumn(), roomController.getStartFloor());
        quit = false;

        // game loop
        do {
            outputtable.outputRoomDescription(player.getLocation());
            Commands command = inputtable.inputCommand(player);
            handleCommand(command);
        } while(!quit);
        outputtable.outputQuitMessage(player, player.getMoveCount());
    }

    /**
     * Checks if there are any monsters that need to be killed when the player moves.
     * @param command Commands to go
     */
    public void killMonster(Commands command) {
        Room location = player.getLocation();
        Room adjacentRoom;
        if (command == Commands.NORTH) {
            if (!location.getDirections().get(Commands.NORTH)) {
                removeWeapon(Commands.NORTH);
                location.getMonsters().remove(Commands.NORTH);
                location.getDirections().put(Commands.NORTH, true);
                adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1];
                adjacentRoom.getMonsters().remove(Commands.SOUTH);
                adjacentRoom.getDirections().put(Commands.SOUTH, true);
            }
        } else if (command == Commands.SOUTH) {
            if (!location.getDirections().get(Commands.SOUTH)) {
                removeWeapon(Commands.SOUTH);
                location.getMonsters().remove(Commands.SOUTH);
                location.getDirections().put(Commands.SOUTH, true);
                adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() + 1];
                adjacentRoom.getMonsters().remove(Commands.NORTH);
                adjacentRoom.getDirections().put(Commands.NORTH, true);
            }
        } else if (command == Commands.EAST) {
            if (!location.getDirections().get(Commands.EAST)) {
                removeWeapon(Commands.EAST);
                location.getMonsters().remove(Commands.EAST);
                location.getDirections().put(Commands.EAST, true);
                adjacentRoom = map.getMap()[player.getXIndex() + 1][player.getYIndex()];
                adjacentRoom.getMonsters().remove(Commands.WEST);
                adjacentRoom.getDirections().put(Commands.WEST, true);
            }
        } else if (command == Commands.WEST) {
            if (!location.getDirections().get(Commands.WEST)) {
                removeWeapon(Commands.WEST);
                location.getMonsters().remove(Commands.WEST);
                location.getDirections().put(Commands.WEST, true);
                adjacentRoom = map.getMap()[player.getXIndex() - 1][player.getYIndex()];
                adjacentRoom.getMonsters().remove(Commands.EAST);
                adjacentRoom.getDirections().put(Commands.EAST, true);
            }
        }
    }

    /**
     * Handles the input from the user and calls the needed methods for each Commands.
     * @param command user's input
     */
    public void handleCommand(Commands command) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        if (command == Commands.NORTH || command == Commands.SOUTH || command == Commands.EAST ||
                command == Commands.WEST || command == Commands.UP || command == Commands.DOWN) {
            killMonster(command);
            player.move(command, map);
        } else if (command == Commands.CARRY) {
            for (Item item : player.getLocation().getItems()) {
                player.getInventory().add(item);
            }
            player.getLocation().getItems().clear();
        } else if (command == Commands.INVENTORY) {
            outputtable.outputInventory(player);
        } else if (command == Commands.LEAVE_TREASURES) {
            leaveTreasures();
        } else if (command == Commands.OUT) {
            //TODO: implement out functionality
            /*
            if (player.getTreasures().size() > 0) {
                boolean stoleTreasure = false;
                int i = 0;
                while(!stoleTreasure) {
                    if (player.getInventory().get(i) instanceof Treasure) {
                        player.getInventory().remove(i);
                        stoleTreasure = true;
                    }
                    i++;
                }
            }*/
        } else if (command == Commands.POINTS) {
            outputtable.outputPoints(player, player.getMoveCount());
        } else if (command == Commands.HELP) {
            outputtable.outputHelp();
        } else if (command == Commands.QUIT) {
            quit = true;
        }
    }

    /**
     * Leaves the players current treasures in the room they are in.
     */
    public void leaveTreasures() {
        for (Item item : player.getTreasures()) {
            player.getLocation().getItems().add(item);
        }
        player.getInventory().removeIf(item -> item instanceof Treasure);
    }

    /**
     * Removes the weapon needed to kill the monster from the player's inventory
     * @param command the direction the player is going
     */
    public void removeWeapon(Commands command) {
        for (Item item : player.getWeapons()) {
            Weapon weapon = (Weapon)item;
            if (weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(command).getName())) {
                player.getInventory().remove(item);
            }
        }
    }

}
