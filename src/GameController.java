import Items.Item;
import Items.ItemController;
import Items.Treasure;
import Items.Weapon;

import java.util.List;

/**
 * @author Makenna Halvensleben
 * GameController holds the logic to run the game by handling user Commands.
 */
public class GameController {

    private final ItemController itemController = new ItemController();
    private Player player;
    private Map map;
    private boolean quit;
    private Room entrance;
    private final ShortestPath shortestPath = new ShortestPath();
    private final int CLOSED = 100;
    private final String command1 = null;

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
        RoomController roomController = new RoomController(itemController.createWeapons(), itemController.createTreasures(), itemController.createMonster());
        map = roomController.getMap();
        entrance = map.getMap()[0][0][0];
        roomController.setStartRoom(entrance);
        player = new Player(roomController.getMap().getMap()[roomController.getStartRow()][roomController.getStartColumn()][roomController.getStartFloor()],
                roomController.getStartRow(), roomController.getStartColumn(), roomController.getStartFloor());
        quit = false;
        MapRandomizer mapRandomizer = new MapRandomizer(map.getMap(), inputtable.inputRandomSeed());
        mapRandomizer.shuffleRoomDescriptions(new ItemController().createRoomDescriptions());
        mapRandomizer.randomizeTreasurePlacements(new ItemController().createTreasures());

        // game loop
        do {
            outputtable.outputRoomDescription(player.getLocation());
            Commands command = inputtable.inputCommand(player, command1);
            handleCommand(command);
        } while(!quit);
        outputtable.outputQuitMessage(player, entrance);
    }

    /**
     * Checks if there are any monsters that need to be killed when the player moves.
     * @param command Commands to go
     */
    public void killMonster(Commands command) {
        Room location = player.getLocation();
        Room adjacentRoom;
        if (command == Commands.NORTH) {
            if (location.getDirections().get(Commands.NORTH) == CLOSED) {
                removeWeapon(Commands.NORTH);
                location.getMonsters().remove(Commands.NORTH);
                location.getDirections().put(Commands.NORTH, 1);
                adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
                adjacentRoom.getMonsters().remove(Commands.SOUTH);
                adjacentRoom.getDirections().put(Commands.SOUTH, 1);
            }
        } else if (command == Commands.SOUTH) {
            if (location.getDirections().get(Commands.SOUTH) == CLOSED) {
                removeWeapon(Commands.SOUTH);
                location.getMonsters().remove(Commands.SOUTH);
                location.getDirections().put(Commands.SOUTH, 1);
                adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() + 1][player.getZIndex()];
                adjacentRoom.getMonsters().remove(Commands.NORTH);
                adjacentRoom.getDirections().put(Commands.NORTH, 1);
            }
        } else if (command == Commands.EAST) {
            if (location.getDirections().get(Commands.EAST) == CLOSED) {
                removeWeapon(Commands.EAST);
                location.getMonsters().remove(Commands.EAST);
                location.getDirections().put(Commands.EAST, 1);
                adjacentRoom = map.getMap()[player.getXIndex() + 1][player.getYIndex()][player.getZIndex()];
                adjacentRoom.getMonsters().remove(Commands.WEST);
                adjacentRoom.getDirections().put(Commands.WEST, 1);
            }
        } else if (command == Commands.WEST) {
            if (location.getDirections().get(Commands.WEST) == CLOSED) {
                removeWeapon(Commands.WEST);
                location.getMonsters().remove(Commands.WEST);
                location.getDirections().put(Commands.WEST, 1);
                adjacentRoom = map.getMap()[player.getXIndex() - 1][player.getYIndex()][player.getZIndex()];
                adjacentRoom.getMonsters().remove(Commands.EAST);
                adjacentRoom.getDirections().put(Commands.EAST, 1);
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
            boolean stoleTreasure = false;
            if (player.getTreasures().size() > 0) {
                int i = 0;
                while(!stoleTreasure) {
                    if (player.getInventory().get(i) instanceof Treasure) {
                        player.getInventory().remove(i);
                        stoleTreasure = true;
                    }
                    i++;
                }
            }
            outputtable.outputOut(outputPath(shortestPath.findWayOut(map, player.getLocation())), stoleTreasure);
        } else if (command == Commands.POINTS) {
            outputtable.outputPoints(player, entrance);
        } else if (command == Commands.HELP) {
            outputtable.outputHelp();
        } else if (command == Commands.QUIT) {
            quit = true;
        }
    }

    /**
     * Constructs a string of the commands needed to get to the direction
     * @param wayOut list of rooms that need to be traveled through
     * @return string of commands
     */
    private String outputPath(List<Room> wayOut) {
        String way = "";
        for(int i = wayOut.size()-1; i > 0; i--) {
            Room start = wayOut.get(i);
            int j = i - 1;
            Room finish = wayOut.get(j);
            way += getDirection(start, finish);
        }
        return way;
    }

    /**
     * Gets the command the player needs to enter to move between two rooms
     * @param start start room
     * @param finish end room
     * @return command
     */
    public String getDirection(Room start, Room finish) {
        if(start.getRow() > finish.getRow()) {
            return "W";
        } else if(start.getRow() < finish.getRow()) {
            return "E";
        } else if(start.getColumn() > finish.getColumn()) {
            return "N";
        } else if(start.getColumn() < finish.getColumn()) {
            return "S";
        } else if(start.getFloor() > finish.getFloor()) {
            return "D";
        } else {
            return "U";
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

    /**
     * Gets the player
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player
     * @param player player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Sets the map
     * @param map map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets the quit value
     * @return if the player has quit
     */
    public boolean isQuit() {
        return quit;
    }

    /**
     * Sets the quit value
     * @param quit if the player has quit
     */
    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
