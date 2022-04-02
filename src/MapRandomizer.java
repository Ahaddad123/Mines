import Items.Treasure;

import java.util.*;

public class MapRandomizer {
    private final Room[][][] map;
    private final Room entrance;
    private final Random rand;

    public MapRandomizer(Room[][][] map, int seed) {
        this.map = map;
        rand = new Random(seed);
        this.entrance = map[0][0][0];
        setUpWalls();
    }

    public void shuffleRoomDescriptions(ArrayList<String> roomDescriptions) {
        Collections.shuffle(roomDescriptions, rand);
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
                    //System.out.print(room.getColumn() + " " + room.getRow() + " " + room.getFloor() + ":");
                    //for(Item item : room.getItems()) {
                    //    System.out.print(item.getName() + " ");
                    //}
                    //System.out.println();
                    /*int i = room.getRow();
                    int j = room.getColumn();
                    int k = room.getFloor();
                    HashMap<Commands, Integer> dir = room.getDirections();
                    dir.put(Commands.DOWN, 2);
                    dir.put(Commands.UP, 2);
                    if(i == 0) {
                        dir.put(Commands.NORTH, 2);
                    } else if(i == 4) {
                        dir.put(Commands.SOUTH, 2);
                    } else {
                        dir.put
                    }
                    if(j == 0) {
                        dir.put(Commands.WEST, 2);
                    } else if()*/
                    room.getDirections().put(Commands.UP, 2);
                    room.getDirections().put(Commands.DOWN, 2);
                    room.getDirections().put(Commands.NORTH, 2);
                    room.getDirections().put(Commands.SOUTH, 2);
                    room.getDirections().put(Commands.EAST, 2);
                    room.getDirections().put(Commands.WEST, 2);
                }
            }
        }
    }

    public void randomizeFirstFloorStructure() {
        // setup stairs between first and second floor
        int numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomFirstFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }
        Room room = entrance;
        List<Room> reached = new ArrayList<>();
        reached.add(entrance);
        Commands direction;

        while(!allRoomsReached(reached)) {
            List<Commands> openPaths = getOpenPaths(room);
            direction = getRandomDirection(room);
            /*boolean canCreateNewOpening = false;
            if(isEdge(room) && isEdge(getAdjacentRoom(room, direction))) {
                if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                    canCreateNewOpening = true;
                }
            } else if (isEdge(room)){
                if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                    canCreateNewOpening = true;
                }
            } else if (isEdge(getAdjacentRoom(room, direction))) {
                if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                    canCreateNewOpening = true;
                }
            } else {
                if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                    canCreateNewOpening = true;
                }
            }*/
            if(!reached.contains(room)) {
                reached.add(room);
            }
            if (openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                room.getDirections().put(direction, 1);
                room = getAdjacentRoom(room, direction);
                room.getDirections().put(getOppositeDirection(direction), 1);
                //if(!reached.contains(room)) {
                //    reached.add(room);
                //}
            } else {
                room = getToNewRoom(room, reached);
            }
            //System.out.println(room.getRow() + " " + room.getColumn() + " " + room.getFloor());
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Room r = map[i][j][0];
                //System.out.print(room.getColumn() + " " + room.getRow() + " " + room.getFloor() + ":");
                if(isEdge(r)) {
                    if(getOpenPaths(r).size() > 2) {
                        for(Commands dir : getOpenPaths(r)) {
                            if(getOpenPaths(getAdjacentRoom(r, dir)).size() > 2) {
                                r.getDirections().put(dir, 2);
                                getAdjacentRoom(r,dir).getDirections().put(getOppositeDirection(dir), 2);
                                break;
                            }
                        }
                    }
                }
                /*if(getOpenPaths(r).size() > 2) {
                    for(Commands dir : getOpenPaths(r)) {
                        if(getOpenPaths(getAdjacentRoom(r, dir)).size() > 2) {
                            r.getDirections().put(dir, 2);
                            getAdjacentRoom(r,dir).getDirections().put(getOppositeDirection(dir), 2);
                            break;
                        }
                    }
                }*/
            }
        }

        /*for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Room room = map[i][j][0];

                boolean canCreateNewOpening = false;
                List<Commands> openPaths = getOpenPaths(room);
                Commands direction = getRandomDirection(room);
                if(isEdge(room) && isEdge(getAdjacentRoom(room, direction))) {
                    if(openPaths.size() < 2 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 2) {
                        canCreateNewOpening = true;
                    }
                } else if (isEdge(room)){
                    if(openPaths.size() < 2 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                        canCreateNewOpening = true;
                    }
                } else if (isEdge(getAdjacentRoom(room, direction))) {
                    if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 2) {
                        canCreateNewOpening = true;
                    }
                } else {
                    if(openPaths.size() < 3 && getOpenPaths(getAdjacentRoom(room, direction)).size() < 3) {
                        canCreateNewOpening = true;
                    }
                }

                if (canCreateNewOpening) {
                    room.getDirections().put(direction, 1);
                    room = getAdjacentRoom(room, direction);
                    room.getDirections().put(getOppositeDirection(direction), 1);
                }
            }
        }*/

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
            //openPaths.remove(nogo);
            int i = rand.nextInt(openPaths.size());
            newRoom = getAdjacentRoom(room, openPaths.get(i));
            //newRoom = getToNewRoom(newRoom, reached);
            //for(Commands commands : openPaths) {
            //    newRoom = getAdjacentRoom(room, commands);
            //}
            //newRoom = getToNewRoom(newRoom, reached);
        }
        return newRoom;
    }

    private boolean allRoomsReached(List<Room> reached) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Room room = map[i][j][0];
                if(!reached.contains(room)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void randomizeSecondFloorStructure() {
        // setup stairs between second and third floor
        int numStairs = rand.nextInt(2) + 1;
        for(int i = 0; i < numStairs; i++) {
            Room room = getRandomSecondFloorRoom();
            room.getDirections().put(Commands.UP, 1);
            getAdjacentRoom(room, Commands.UP).getDirections().put(Commands.DOWN, 1);
        }
    }

    public void randomizeThirdFloorStructure() {
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

    public Room getRandomThirdFloorRoom() {
        return map[rand.nextInt(5)][rand.nextInt(5)][2];
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
        List<Commands> list = new ArrayList<>();
        for(Commands commands : room.getDirections().keySet()) {
            if(room.getDirections().get(commands) == 1) {
                list.add(commands);
            }
        }
        return list;
    }
}
