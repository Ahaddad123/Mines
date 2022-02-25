import java.util.ArrayList;
import java.util.List;

/**
 * @author Makenna Halvensleben
 * The Player class holds the information about the player of the game.
 * This includes their current location and their current inventory.
 */
public class Player {

    private Room location;
    private List<Item> inventory;
    private int xIndex;
    private int yIndex;
    private int zIndex;
    private int moveCount;

    /**
     * Constructor for Player that initializes instance variables
     * @param entrance starting location of the player
     */
    public Player(Room entrance, int xIndex, int yIndex, int zIndex) {
        this.location = entrance;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.zIndex = zIndex;
        this.moveCount = 0;
        inventory = new ArrayList<>();
    }

    /**
     * Getter method for current location
     * @return current Room
     */
    public Room getLocation() {
        return location;
    }

    /**
     * Setter method for the current location
     * @param location current Room
     */
    public void setLocation(Room location) {
        this.location = location;
    }

    /**
     * Getter method for the inventory
     * @return list of items
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Setter method for the inventory
     * @param inventory list of items
     */
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns the treasures in the inventory
     * @return list of treasures
     */
    public List<Item> getTreasures() {
        List<Item> treasures = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Treasure) {
                treasures.add(item);
            }
        }
        return treasures;
    }

    /**
     * Returns the weapons in the inventory
     * @return list of weapons
     */
    public List<Item> getWeapons() {
        List<Item> weapons = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Weapon) {
                weapons.add(item);
            }
        }
        return weapons;
    }

    /**
     * Getter method for the x index of the current location
     * @return x index
     */
    public int getXIndex() {
        return xIndex;
    }

    /**
     * Setter method for the x index of the current location
     * @param xIndex x index
     */
    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    /**
     * Getter method for the y index of the current location
     * @return y index
     */
    public int getYIndex() {
        return yIndex;
    }

    /**
     * Setter method for the y index of the current location
     * @param yIndex y index
     */
    public void setYIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    /**
     * Getter method for the z index of the current location
     * @return z index
     */
    public int getZIndex() {
        return zIndex;
    }

    /**
     * Setter method for the z index of the current location
     * @param zIndex z index
     */
    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * Getter method for the number of times moved
     * @return move count
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Setter method for the number of times moved
     * @param moveCount move count
     */
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    /**
     * Relocates the player
     * @param command user's input
     * @param map map of the game
     */
    public void move(Command command, Map map) {
        if (command == Command.NORTH) {
            this.yIndex--;
        } else if (command == Command.SOUTH) {
            this.yIndex++;
        } else if (command == Command.EAST) {
            this.xIndex++;
        } else if (command == Command.WEST) {
            this.xIndex--;
        } else if (command == Command.UP) {
            this.zIndex--;
        } else if (command == Command.DOWN) {
            this.zIndex++;
        }
        //TODO: change to 3D array
        this.location = map.getRooms()[this.xIndex][this.yIndex];
        this.moveCount++;
    }
}
