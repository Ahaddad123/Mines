import Items.Treasure;

import java.util.*;

public class MapRandomizer {
    private final Room[][][] map;
    private final Random rand;
    private final int MAX_WIDTH = 5;

    public MapRandomizer(Room[][][] map, int seed) {
        this.map = map;
        rand = new Random(seed);
        setUpWalls();
    }

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

    public void randomizeTreasurePlacements(ArrayList<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            getRandomRoom().getItems().add(treasure);
        }
    }

    private void setUpWalls() {
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
        int numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomFirstFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }
        setupRoomStructure(0);
        numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomSecondFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }
        setupRoomStructure(1);
        setupRoomStructure(2);
    }

    private void setupRoomStructure(int floor) {
        Room room = map[0][0][floor];
        List<Room> reached = new ArrayList<>();
        Commands direction;
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
        for(int i = 0; i < MAX_WIDTH; i++) {
            for(int j = 0; j < MAX_WIDTH; j++) {
                Room r = map[i][j][floor];
                if(isEdge(r) && getOpenPaths(r).size() > 2) {
                    boolean added = false;
                    for(int x = 0; x < getOpenPaths(r).size() && !added; x++) {
                        Commands dir = getOpenPaths(r).get(x);
                        if(getOpenPaths(getAdjacentRoom(r, dir)).size() > 2) {
                            r.getDirections().put(dir, 2);
                            getAdjacentRoom(r,dir).getDirections().put(getOppositeDirection(dir), 2);
                            added = true;
                        }
                    }
                }
            }
        }
        checkForInvalid(floor);
    }

    private void checkForInvalid(int floor) {
        for(int j = 0; j < MAX_WIDTH - 1; j++) {
            int num = 0;
            for(int i = 0; i < MAX_WIDTH; i++) {
                num += map[i][j][floor].getDirections().get(Commands.SOUTH);
            }
            if(num == 10) {
                map[rand.nextInt(MAX_WIDTH-1)][j][floor].getDirections().put(Commands.SOUTH, 1);
            }
        }
        for(int i = 0; i < MAX_WIDTH - 1; i++) {
            int num = 0;
            for(int j = 0; j < MAX_WIDTH; j++) {
                num += map[i][j][floor].getDirections().get(Commands.EAST);
            }
            if(num == 10) {
                map[i][rand.nextInt(MAX_WIDTH-1)][floor].getDirections().put(Commands.EAST, 1);
            }
        }
    }

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

    public Room getRandomRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][rand.nextInt(3)];
    }

    public Room getRandomFirstFloorRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][0];
    }

    public Room getRandomSecondFloorRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][1];
    }

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

    public boolean isEdge(Room room) {
        return room.getColumn() == 0 || room.getColumn() == 4 || room.getRow() == 0 || room.getRow() == 4;
    }

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
}
