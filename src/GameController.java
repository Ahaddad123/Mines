/**
 * @author Makenna Halvensleben
 * GameController holds the logic to run the game by handling user commands.
 */
public class GameController {

    private static final int NUM_TREASURES = 24;
    private final RoomController roomController = new RoomController();
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
        roomController.createMap(roomController.createRooms(itemController.createItems(),itemController.createMonsters()));
        map = roomController.getMap();
        player = new Player(roomController.getEntrance(),
                roomController.getXIndex(), roomController.getYIndex(), roomController.getZIndex());
        quit = false;

        // game loop
        do {
            outputtable.outputRoom(player.getLocation());
            Command command = inputtable.inputCommand(player);
            handleCommand(command);
        } while(!hasWon() && !quit);
        if (hasWon()) {
            outputtable.outputWinMessage();
        } else {
            outputtable.outputQuit();
        }
    }

    /**
     * Checks if there are any monsters that need to be killed when the player moves.
     * @param command direction to go
     */
    public void killMonster(Command command) {
        Room location = player.getLocation();
        if (command == Command.NORTH) {
            if (location.getDirections().get(Direction.NORTH) == false) {
                location.getMonsters().remove(Direction.NORTH);
                map.getRooms()[player.getXIndex()][player.getYIndex() - 1].getMonsters().remove(Direction.SOUTH);
            }
        } else if (command == Command.SOUTH) {
            if (location.getDirections().get(Direction.SOUTH) == false) {
                location.getMonsters().remove(Direction.SOUTH);
                map.getRooms()[player.getXIndex()][player.getYIndex() + 1].getMonsters().remove(Direction.NORTH);
            }
        } else if (command == Command.EAST) {
            if (location.getDirections().get(Direction.EAST) == false) {
                location.getMonsters().remove(Direction.EAST);
                map.getRooms()[player.getXIndex() + 1][player.getYIndex()].getMonsters().remove(Direction.WEST);
            }
        } else if (command == Command.WEST) {
            if (location.getDirections().get(Direction.WEST) == false) {
                location.getMonsters().remove(Direction.WEST);
                map.getRooms()[player.getXIndex() - 1][player.getYIndex()].getMonsters().remove(Direction.EAST);
            }
        }
    }

    /**
     * Handles the input from the user and calls the needed methods for each command.
     * @param command user's input
     */
    public void handleCommand(Command command) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        if (command == Command.NORTH || command == Command.SOUTH || command == Command.EAST ||
                command == Command.WEST || command == Command.UP || command == Command.DOWN) {
            killMonster(command);
            player.move(command, map);
        } else if (command == Command.CARRY) {
            for (Item item : player.getLocation().getItems()) {
                player.getInventory().add(item);
            }
            player.getLocation().getItems().clear();
        } else if (command == Command.INVENTORY) {
            outputtable.outputInventory(player);
        } else if (command == Command.LEAVE_TREASURES) {
            leaveTreasures();
        } else if (command == Command.OUT) {
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
        } else if (command == Command.POINTS) {
            outputtable.outputPoints(player, moveCount);
        } else if (command == Command.HELP) {
            outputtable.outputHelp();
        } else if (command == Command.QUIT) {
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

    }

}
