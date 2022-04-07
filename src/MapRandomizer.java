import Items.Treasure;

import java.util.*;

import static java.lang.Math.abs;

/**
 * @author Makenna Halvensleben
 * MapRandomizer holds the logic to randomize a map
 */
public class MapRandomizer {
    private Room[][][] map;
    private final Random rand;
    private final int MAX_WIDTH = 5;

    /**
     * Constructor for MapRandomizer that creates the map
     * @param map empty map
     * @param seed random seed
     */
    public MapRandomizer(Room[][][] map, int seed) {
        this.map = map;
        rand = new Random(abs(seed%9));
        setUpWalls();
        addMonsters();
    }

    /**
     * Shuffles the list of room descriptions and assigns them to each room
     * @param roomDescriptions list of room descriptions
     */
    public void shuffleRoomDescriptions(ArrayList<String> roomDescriptions) {
        Collections.shuffle(roomDescriptions, rand);
        int i = 0;
        for(Room[][] floor : map) {
            for(Room[] row : floor) {
                for(Room room : row) {
                    room.setDescription(roomDescriptions.get(i));
                    i++;
                }
            }
        }
    }

    /**
     * Randomizes the locations of the treasures
     * @param treasures list of treasures
     */
    public void randomizeTreasurePlacements(ArrayList<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            getRandomRoom().getItems().add(treasure);
        }
    }

    /**
     * Sets up the passages for each room that are blocked by walls
     */
    private void setUpWalls() {
        // initializes all passages as blocked
        for(Room[][] floor : map) {
            for(Room[] row : floor) {
                for(Room room : row) {
                    room.getDirections().put(Commands.UP, 2);
                    room.getDirections().put(Commands.DOWN, 2);
                    room.getDirections().put(Commands.NORTH, 2);
                    room.getDirections().put(Commands.SOUTH, 2);
                    room.getDirections().put(Commands.EAST, 2);
                    room.getDirections().put(Commands.WEST, 2);
                }
            }
        }

        // creates the stairs between the first and second floors
        int numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomFirstFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }

        // open passages on the first floor
        setupRoomStructure(0);

        // creates the stairs between the second and third floors
        numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomSecondFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }

        // open passages on the second floor
        setupRoomStructure(1);

        // open passages on the third floor
        setupRoomStructure(2);
    }

    /**
     * Opens passages for the given floor index
     * @param floor floor index
     */
    private void setupRoomStructure(int floor) {
        Room room = map[0][0][floor];
        List<Room> reached = new ArrayList<>();
        Commands direction;

        // travels through all the rooms on the given floor
        // opens at most 3 passages for each room
        while(!allRoomsReached(reached, floor)) {
            List<Commands> openPaths = getOpenPaths(room);
            direction = getRandomDirection(room);
            if(!reached.contains(room)) {
                reached.add(room);
            }
            if (openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                room.getDirections().put(direction, 1);
                room = getAdjacentRoom(room, direction);
                room.getDirections().put(getOppositeDirection(direction), 1);
            } else {
                room = getToNewRoom(room, reached);
            }
        }

        // adds additional walls to minimize the amount of free space in the floor
        for(int i = 0; i < MAX_WIDTH; i++) {
            for(int j = 0; j < MAX_WIDTH; j++) {
                Room r = map[i][j][floor];
                if(isEdge(r) && getOpenPaths(r).size() > 2) {
                    boolean added = false;
                    for(int x = 0; x < getOpenPaths(r).size() && !added; x++) {
                        Commands dir = getOpenPaths(r).get(x);
                        if(getOpenPaths(getAdjacentRoom(r, dir)).size() > 2 && !isEdge(getAdjacentRoom(r, dir))) {
                            r.getDirections().put(dir, 2);
                            getAdjacentRoom(r,dir).getDirections().put(getOppositeDirection(dir), 2);
                            added = true;
                        }
                    }
                }
            }
        }

        // checks to see if any rooms are unreachable
        checkForInvalid(floor);
    }

    /**
     * Checks to see if any rooms on the given floor are unreachable
     * @param floor floor index
     */
    private void checkForInvalid(int floor) {
        // checks for a horizontal wall across the entire floor
        for(int j = 0; j < MAX_WIDTH - 1; j++) {
            int num = 0;
            for(int i = 0; i < MAX_WIDTH; i++) {
                num += map[i][j][floor].getDirections().get(Commands.SOUTH);
            }
            if(num == 10) {
                Room room = map[rand.nextInt(MAX_WIDTH-1)][j][floor];
                room.getDirections().put(Commands.SOUTH, 1);
                getAdjacentRoom(room, Commands.SOUTH).getDirections().put(Commands.NORTH, 1);
            }
        }

        // checks for a vertical wall across the entire floor
        for(int i = 0; i < MAX_WIDTH - 1; i++) {
            int num = 0;
            for(int j = 0; j < MAX_WIDTH; j++) {
                num += map[i][j][floor].getDirections().get(Commands.EAST);
            }
            if(num == 10) {
                Room room = map[i][rand.nextInt(MAX_WIDTH-1)][floor];
                room.getDirections().put(Commands.EAST, 1);
                getAdjacentRoom(room, Commands.EAST).getDirections().put(Commands.WEST, 1);
            }
        }
    }

    /**
     * Returns a room that has not been reached yet when opening passages
     * @param room current room
     * @param reached list of rooms that have been reached
     * @return a room the has not been reached or an adjacent room
     */
    private Room getToNewRoom(Room room, List<Room> reached) {
        boolean isNew = false;
        List<Commands> openPaths = getOpenPaths(room);
        Room newRoom = null;
        for(Commands open : openPaths) {
            if(!reached.contains(getAdjacentRoom(room, open)) && !isNew) {
                newRoom = getAdjacentRoom(room, open);
                isNew = true;
            }
        }
        if(!isNew) {
            int i = rand.nextInt(openPaths.size());
            newRoom = getAdjacentRoom(room, openPaths.get(i));
        }
        return newRoom;
    }

    /**
     * Checks to see if all rooms of a given floor have been reached
     * @param reached list of rooms that have been reached
     * @param floor floor index
     * @return if all the rooms have been reached
     */
    private boolean allRoomsReached(List<Room> reached, int floor) {
        for(int i = 0; i < MAX_WIDTH; i++) {
            for(int j = 0; j < MAX_WIDTH; j++) {
                Room room = map[i][j][floor];
                if(!reached.contains(room)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets a random room from the entire map
     * @return a random room
     */
    public Room getRandomRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][rand.nextInt(3)];
    }

    /**
     * Gets a random room from the first floor
     * @return a random room from the first floor
     */
    public Room getRandomFirstFloorRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][0];
    }

    /**
     * Gets a random room from the second floor
     * @return a random room from the second floor
     */
    public Room getRandomSecondFloorRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][1];
    }

    /**
     * Gets the room adjacent to the current room in the given direction
     * @param room current room
     * @param command direction
     * @return adjacent room
     */
    public Room getAdjacentRoom(Room room, Commands command) {
        if (command == Commands.NORTH) {
            return map[room.getRow()][room.getColumn() - 1][room.getFloor()];
        } else if (command == Commands.SOUTH) {
            return map[room.getRow()][room.getColumn() + 1][room.getFloor()];
        } else if (command == Commands.EAST) {
            return map[room.getRow() + 1][room.getColumn()][room.getFloor()];
        } else if (command == Commands.WEST) {
            return map[room.getRow() - 1][room.getColumn()][room.getFloor()];
        } else if (command == Commands.UP) {
            return map[room.getRow()][room.getColumn()][room.getFloor() + 1];
        } else {
            return map[room.getRow()][room.getColumn()][room.getFloor() - 1];
        }
    }

    /**
     * Gets a random direction that is valid to the room
     * @param room current room
     * @return a random valid direction
     */
    public Commands getRandomDirection(Room room) {
        List<Commands> validDirections = new ArrayList<>();
        if(room.getColumn() != 0) {
            validDirections.add(Commands.NORTH);
        }
        if(room.getColumn() != 4) {
            validDirections.add(Commands.SOUTH);
        }
        if (room.getRow() != 4) {
            validDirections.add(Commands.EAST);
        }
        if (room.getRow() != 0) {
            validDirections.add(Commands.WEST);
        }
        int i = rand.nextInt(validDirections.size());
        return validDirections.get(i);
    }

    /**
     * Returns if a room is at the edge of the map
     * @param room current room
     * @return if it is an edge room
     */
    public boolean isEdge(Room room) {
        return room.getColumn() == 0 || room.getColumn() == 4 || room.getRow() == 0 || room.getRow() == 4;
    }

    /**
     * Returns the opposite direction of the given direction
     * @param command given direction
     * @return opposite direction
     */
    public Commands getOppositeDirection(Commands command) {
        if (command == Commands.NORTH) {
            return Commands.SOUTH;
        } else if (command == Commands.SOUTH) {
            return Commands.NORTH;
        } else if (command == Commands.EAST) {
            return Commands.WEST;
        } else if (command == Commands.WEST) {
            return Commands.EAST;
        } else if (command == Commands.UP) {
            return Commands.DOWN;
        } else {
            return Commands.UP;
        }
    }

    /**
     * Gets a list of open passages for a given room
     * @param room current room
     * @return list of open passages
     */
    public List<Commands> getOpenPaths(Room room) {
        List<Commands> lateralDirections = new ArrayList<>();
        lateralDirections.add(Commands.NORTH);
        lateralDirections.add(Commands.SOUTH);
        lateralDirections.add(Commands.EAST);
        lateralDirections.add(Commands.WEST);
        List<Commands> list = new ArrayList<>();
        for(Commands commands : lateralDirections) {
            if(room.getDirections().get(commands) == 1) {
                list.add(commands);
            }
        }
        return list;
    }

    private void addMonsters() {
        for(Room[][] floor : map) {
            for(Room[] row : floor) {
                for(Room room : row) {
                    for(Commands blocked : room.getMonsters().keySet()) {
                        room.getDirections().put(blocked, 2);
                    }
                }
            }
        }
    }
}
