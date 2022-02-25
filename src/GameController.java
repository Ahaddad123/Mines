import Items.Item;
import Items.ItemController;

/**
 * @author Makenna Halvensleben
 * GameController holds the logic to run the game by handling user Commandsss.
 */
public class GameController {

    private static final int NUM_TREASURES = 24;
    private RoomController roomController;
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

        roomController = new RoomController(itemController.createWeapons(),itemController.createTreasures());
        map = roomController.getMap();
        player = new Player(roomController.getMap().getMap()[roomController.getStartRow()][roomController.getStartColumn()],
                roomController.getStartRow(), roomController.getStartColumn(), roomController.getStartFloor());
        quit = false;

        // game loop
        do {
            outputtable.outputRoom(player.getLocation());
            Commands Commands = inputtable.inputCommands(player);
            handleCommands(Commands);
        } while(!hasWon() && !quit);
        if (hasWon()) {
            outputtable.outputWinMessage();
        } else {
            outputtable.outputQuit();
        }
    }

    /**
     * Checks if there are any monsters that need to be killed when the player moves.
     * @param Commands Commandss to go
     */
    public void killMonster(Commands Commands) {
        Room location = player.getLocation();
        Room adjacentRoom;
        if (Commands == Commands.NORTH) {
            if (location.getCommandsss().get(Commandss.NORTH) == false) {
                location.getMonsters().remove(Commandss.NORTH);
                location.getCommandsss().put(Commandss.NORTH, true);
                adjacentRoom = map.getRooms()[player.getXIndex()][player.getYIndex() - 1];
                adjacentRoom.getMonsters().remove(Commandss.SOUTH);
                adjacentRoom.getCommandsss().put(Commandss.SOUTH, true);
            }
        } else if (Commands == Commands.SOUTH) {
            if (location.getCommandsss().get(Commandss.SOUTH) == false) {
                location.getMonsters().remove(Commandss.SOUTH);
                location.getCommandsss().put(Commandss.SOUTH, true);
                adjacentRoom = map.getRooms()[player.getXIndex()][player.getYIndex() + 1];
                adjacentRoom.getMonsters().remove(Commandss.NORTH);
                adjacentRoom.getCommandsss().put(Commandss.NORTH, true);
            }
        } else if (Commands == Commands.EAST) {
            if (location.getCommandsss().get(Commandss.EAST) == false) {
                location.getMonsters().remove(Commandss.EAST);
                location.getCommandsss().put(Commandss.EAST, true);
                adjacentRoom = map.getRooms()[player.getXIndex() + 1][player.getYIndex()];
                adjacentRoom.getMonsters().remove(Commandss.WEST);
                adjacentRoom.getCommandsss().put(Commandss.WEST, true);
            }
        } else if (Commands == Commands.WEST) {
            if (location.getCommandsss().get(Commandss.WEST) == false) {
                location.getMonsters().remove(Commandss.WEST);
                location.getCommandsss().put(Commandss.WEST, true);
                adjacentRoom = map.getRooms()[player.getXIndex() - 1][player.getYIndex()];
                adjacentRoom.getMonsters().remove(Commandss.EAST);
                adjacentRoom.getCommandsss().put(Commandss.EAST, true);
            }
        }
    }

    /**
     * Handles the input from the user and calls the needed methods for each Commands.
     * @param Commands user's input
     */
    public void handleCommands(Commandss Commands) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        if (Commands == Commandss.NORTH || Commands == Commandss.SOUTH || Commands == Commandss.EAST ||
                Commands == Commandss.WEST || Commands == Commandss.UP || Commands == Commandss.DOWN) {
            killMonster(Commands);
            player.move(Commands, map);
        } else if (Commands == Commands.CARRY) {
            for (Item item : player.getLocation().getItems()) {
                player.getInventory().add(item);
            }
            player.getLocation().getItems().clear();
        } else if (Commands == Commands.INVENTORY) {
            outputtable.outputInventory(player);
        } else if (Commands == Commands.LEAVE_TREASURES) {
            leaveTreasures();
        } else if (Commands == Commands.OUT) {
            if (player.getTreasures().size() > 0) {
                //TODO: implement out functionality
                boolean stoleTreasure = false;
                int i = 0;
                while(!stoleTreasure) {
                    if (player.getInventory().get(i) instanceof Treasure) {
                        player.getInventory().remove(i);
                        stoleTreasure = true;
                    }
                    i++;
                }
                outputtable.outputWayOut(findWayOut());
            }
        } else if (Commands == Commands.POINTS) {
            outputtable.outputPoints(player, moveCount);
        } else if (Commands == Commands.HELP) {
            outputtable.outputHelp();
        } else if (Commands == Commands.QUIT) {
            quit = true;
        } else {
            outputtable.outputInvalid();
        }
    }

    /**
     * Checks if the player has won by collecting all the treasures and returning to the exit.
     * @return if the player has won
     */
    public boolean hasWon() {
        return roomController.getEntrance().getItems().size() == NUM_TREASURES
                && player.getLocation() == roomController.getMap().getEntrance();
        return true;
    }

    /**
     * Leaves the players current treasures in the room they are in.
     */
    public void leaveTreasures() {
        for (Item item : player.getTreasures()) {
            player.getLocation().getItems().add(item);
        }
        for (Item item : player.getInventory()) {
            if (item instanceof Treasure) {
                player.getInventory().remove(item);
            }
        }
    }

    public String findWayOut() {
        String wayOut = "";
        return wayOut;
    }

}
