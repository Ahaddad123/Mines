import Items.Item;
import Items.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates the game map
 * @author Jasmine Krahn
 */
public class Map {

    private int MAX_WIDTH = 5;
    private int MAX_HEIGHT = 3;
    private Room[][][] map;
    private List<Item> weapons;
    private List<Item> treasures;
    private List<Monster> monsters;

    /**
     * Constructor for map
     * @param weapons list of all weapons
     * @param treasures list of all treasures
     */
    Map(List<Item> weapons, List<Item> treasures, List<Monster> monsters)
    {
        this.weapons = weapons;
        this.treasures = treasures;
        this.monsters = monsters;
        // row, column, floor
        map = new Room[MAX_WIDTH][MAX_WIDTH][MAX_HEIGHT];
        firstFloor();
        secondFloor();
    }

    public Map(Room[][][] map) {
        this.map = map;
    }

    /**
     * gets game map
     * @return game map
     */
    public Room[][][] getMap() {
        return map;
    }

    /**
     * creates a room for each piece of the array
     * @param monster1 one monster blocking a path
     * @param direction1 direction that monster1 blocks
     * @param monster2 another monster blocking a path
     * @param direction2 direction that monster2 blocks
     * @param item1 an item in the room
     * @param i row index
     * @param j column index
     */
    private void createRoom(Monster monster1,
                            Commands direction1, Monster monster2,
                            Commands direction2,
                            Item item1, int i, int j, int k)
    {
        HashMap<Commands, Integer> db= new HashMap<>();
        HashMap<Commands, Monster> md = new HashMap<>();
        if(monster1 != null)
            md.put(direction1, monster1);
        if(monster2 != null)
            md.put(direction2, monster2);
        List<Item> items = new ArrayList<>();
        if(item1 != null)
            items.add(item1);
        map[i][j][k] = new Room(db, items, md, i, j, k);
    }

    /**
     * builds first floor of the game map
     */
    private void firstFloor()
    {
        firstFloorFirstRow();
        firstFloorSecondRow();
        firstFloorThirdRow();
        firstFloorForthRow();
        firstFloorFifthRow();
    }

    /**
     * builds first floor of the game map
     */
    private void secondFloor()
    {
        secondFloorFirstRow();
        secondFloorSecondRow();
        secondFloorThirdRow();
        secondFloorForthRow();
        secondFloorFifthRow();
    }

    /**
     * builds first row of first floor
     */
    private void firstFloorFirstRow()
    {
        createRoom(monsters.get(0), Commands.EAST, null, null,
                null, 0, 0, 0);

        createRoom(monsters.get(0), Commands.WEST, null, null,
                null, 1, 0, 0);

        createRoom(monsters.get(1), Commands.SOUTH, null, null,
                null,2, 0, 0);

        createRoom(null, null, null, null,
                weapons.get(2), 3, 0, 0);

        createRoom(monsters.get(2), Commands.SOUTH, null, null,
                null, 4, 0, 0);
    }

    /**
     * builds second row of first floor
     */
    private void firstFloorSecondRow()
    {
        createRoom(monsters.get(7), Commands.SOUTH, null, null,
                weapons.get(0), 0, 1, 0);

        createRoom(null, null, null, null,
                null, 1, 1, 0);

        createRoom(monsters.get(1), Commands.NORTH, null, null,
                null, 2, 1, 0);

        createRoom(null, null, null,
                null, null, 3, 1, 0);

        createRoom(monsters.get(2), Commands.NORTH, monsters.get(3),
                Commands.SOUTH, weapons.get(1), 4, 1, 0);
    }

    /**
     * builds third row of first floor
     */
    private void firstFloorThirdRow()
    {
        createRoom(monsters.get(7), Commands.NORTH, null, null,
                null, 0, 2, 0);

        createRoom(monsters.get(6), Commands.SOUTH, null, null,
                weapons.get(3), 1, 2, 0);

        createRoom(null, null, null, null,
                weapons.get(12), 2, 2, 0);

        createRoom(null, null, null,
                null, null, 3, 2, 0);

        createRoom(monsters.get(3), Commands.NORTH, null, null,
                null, 4, 2, 0);
    }

    /**
     * builds forth row of first floor
     */
    private void firstFloorForthRow()
    {
        createRoom(null, null, null, null,
                weapons.get(5), 0, 3, 0);

        createRoom(monsters.get(6), Commands.NORTH, null, null,
                null, 1, 3, 0);

        createRoom(null, null, null, null,
                null, 2, 3, 0);

        createRoom(monsters.get(4), Commands.SOUTH, null, null,
                null, 3, 3, 0);

        createRoom(null, null,
                null, null, weapons.get(4),4, 3, 0);
    }

    /**
     * builds fifth row of first floor
     */
    private void firstFloorFifthRow()
    {
        createRoom(monsters.get(5), Commands.EAST, null, null,
                null, 0, 4, 0);

        createRoom(monsters.get(5), Commands.WEST, null, null,
                null, 1, 4, 0);

        createRoom(null, null, null, null,
                weapons.get(13),2, 4, 0);

        createRoom(monsters.get(4), Commands.NORTH, null, null,
                null, 3, 4, 0);

        createRoom(null, null, null, null,
                null, 4, 4, 0);
    }

    /**
     * builds first row of second floor
     */
    private void secondFloorFirstRow()
    {
        createRoom(monsters.get(14), Commands.SOUTH, null,
                null, weapons.get(7), 0, 0, 1);

        createRoom(monsters.get(13), Commands.EAST, null, null,
                null, 1, 0, 1);

        createRoom(monsters.get(13), Commands.WEST, null, null,
                null, 2, 0, 1);

        createRoom(null, null, null, null,
                null, 3, 0, 1);

        createRoom(null, null, null, null,
                weapons.get(15), 4, 0, 1);
    }

    /**
     * builds second row of second floor
     */
    private void secondFloorSecondRow() {
        createRoom(monsters.get(14), Commands.NORTH, null,
                null, null, 0, 1, 1);

        createRoom(null, null, null, null,
                weapons.get(23), 1, 1, 1);

        createRoom(monsters.get(10), Commands.EAST, null, null,
                weapons.get(9), 2, 1, 1);

        createRoom(monsters.get(10), Commands.WEST, null, null,
                null, 3, 1, 1);

        createRoom(monsters.get(9), Commands.SOUTH, null, null,
                null, 4, 1, 1);
    }

    /**
     * builds third row of second floor
     */
    private void secondFloorThirdRow()
    {
        createRoom(null, null, null, null,
                null, 0, 2, 1);

        createRoom(monsters.get(11), Commands.SOUTH, null,
                null, null, 1, 2, 1);

        createRoom(null, null, null, null,
                null, 2, 2, 1);

        createRoom(null, null, null, null,
                weapons.get(8), 3, 2, 1);

        createRoom(monsters.get(9), Commands.NORTH, null,
                null, weapons.get(16),4, 2, 1);
    }

    /**
     * builds forth row of second floor
     */
    private void secondFloorForthRow()
    {
        createRoom(null, null, null,
                null, null, 0, 3, 1);

        createRoom(monsters.get(11), Commands.NORTH, monsters.get(15), Commands.SOUTH,
                null, 1, 3, 1);

        createRoom(null, null, null, null,
                null, 2, 3, 1);

        createRoom(null, null, null, null,
                null, 3, 3, 1);

        createRoom(monsters.get(7), Commands.SOUTH,
                null, null, weapons.get(21),4, 3, 1);
    }


    /**
     * builds fifth row of second floor
     */
    private void secondFloorFifthRow()
    {
        createRoom(null, null,
                null, null, weapons.get(6), 0, 4, 1);

        createRoom(monsters.get(12), Commands.EAST,
                monsters.get(15), Commands.NORTH, null, 1, 4, 1);

        createRoom(monsters.get(12), Commands.WEST,
                null, null, null, 2, 4, 1);

        createRoom(null, null, null, null,
                weapons.get(10), 3, 4, 1);

        createRoom(monsters.get(7), Commands.NORTH,
                null, null, null, 4, 4, 1);
    }

    /**
     * builds first row of third floor
     */
    private void thirdFloorFirstRow()
    {
        createRoom(null, null, null, null,
                weapons.get(18), 0, 1, 2);

        createRoom(monsters.get(20), Commands.SOUTH,
                null, null, null, 1, 1, 2);

        createRoom(null, null, null, null,
                null, 2, 1, 2);

        createRoom(monsters.get(17), Commands.SOUTH, null,
                null, null, 3, 1, 2);

        createRoom(null, null, null, null,
                null, 4, 1, 2);
    }

    /**
     * builds second row of third floor
     */
    private void thirdFloorSecondRow()
    {
        createRoom(monsters.get(21), Commands.EAST,
                null, null, null, 0, 2, 2);

        createRoom(monsters.get(20), Commands.NORTH,
                monsters.get(21), Commands.WEST, null, 1, 2, 2);

        createRoom(null, null,
                null, null, weapons.get(22),2, 2, 2);

        createRoom(monsters.get(17), Commands.NORTH, null,
                null, null, 3, 2, 2);

        createRoom(monsters.get(23), Commands.SOUTH,
                null, null, null, 4, 2, 2);
    }

    /**
     * builds third row of third floor
     */
    private void thirdFloorThirdRow()
    {
        createRoom(null, null, null, null,
                null, 0, 3, 2);

        createRoom(null, null,
                null, null, weapons.get(11), 1, 3, 2);

        createRoom(null, null, null, null,
                null, 2, 3, 2);

        createRoom(null, null, null,
                null, null, 3, 3, 2);

        createRoom(monsters.get(23), Commands.NORTH, monsters.get(22),
                Commands.SOUTH, weapons.get(19),4, 3, 2);
    }

    /**
     * builds forth row of third floor
     */
    private void thirdFloorForthRow()
    {
        createRoom(monsters.get(18), Commands.EAST,
                null, null, null, 0, 3, 2);

        createRoom(monsters.get(18), Commands.WEST, null,
                null, null, 1, 3, 2);

        createRoom(monsters.get(19), Commands.SOUTH,
                null, null, null, 2, 3, 2);

        createRoom(monsters.get(16), Commands.EAST, null, null,
                weapons.get(17), 3, 3, 2);

        createRoom(monsters.get(22), Commands.NORTH,
                null, null, null, 4, 3, 2);
    }

    /**
     * builds fifth row of third floor
     */
    private void thirdFloorFifthRow()
    {
        createRoom(null, null,
                null, null, weapons.get(14), 0, 4, 2);

        createRoom(null, null, null, null,
                null, 1, 4, 2);

        createRoom(monsters.get(19), Commands.NORTH,
                null, null, null, 2, 4, 2);

        createRoom(null, null, null,
                null, null, 3, 4, 2);

        createRoom(null, null,
                null, null, weapons.get(20),4, 4, 2);
    }
}
