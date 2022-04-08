import java.util.ArrayList;
import java.util.List;

// A Java program for Dijkstra's
// single source shortest path
// algorithm. The program is for
// adjacency matrix representation
// of the graph.
// source: https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
class ShortestPath {

    private static final int NO_PARENT = -1;
    private final int MAX_WIDTH = 5;
    private final int MAX_HEIGHT = 3;
    private List<Room> wayOut;
    private List<Integer> path;

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private void dijkstra(int[][] adjacencyMatrix,
                                 int startVertex, int destination)
    {
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }
        printSolution(startVertex, shortestDistances, parents, destination);
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private void printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents, int destination)
    {
        int nVertices = distances.length;
        //System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                if(vertexIndex == destination) {
                    //System.out.print("\n" + startVertex + " -> ");
                    //System.out.print(vertexIndex + " \t\t ");
                    //System.out.print(distances[vertexIndex] + "\t\t");
                    printPath(vertexIndex, parents);
                }
            }
        }
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private void printPath(int currentVertex,
                                  int[] parents)
    {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        //System.out.print(currentVertex + " ");
        path.add(currentVertex);
    }

    /**
     * source: https://stackoverflow.com/questions/7367770/how-to-flatten-or-index-3d-array-in-1d-array
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int to1D( int x, int y, int z ) {
        return (z * MAX_WIDTH * MAX_WIDTH) + (y * MAX_WIDTH) + x;
    }

    //source : https://coderwall.com/p/fzni3g/bidirectional-translation-between-1d-and-3d-arrays
    public int[] to3D(int i) {
        int x = i % MAX_WIDTH;
        int y = ( i / MAX_WIDTH ) % MAX_WIDTH;
        int z = i / ( MAX_WIDTH * MAX_WIDTH );
        return new int[]{x, y, z};
    }

    /**
     * Gets the room adjacent to the current room in the given direction if valid
     * @param room current room
     * @param command direction
     * @return adjacent room
     */
    public Room getAdjacentRoom(Map map, Room room, Commands command) {
        if (command == Commands.NORTH && room.getColumn() != 0) {
            return map.getMap()[room.getRow()][room.getColumn() - 1][room.getFloor()];
        } else if (command == Commands.SOUTH && room.getColumn() != 4) {
            return map.getMap()[room.getRow()][room.getColumn() + 1][room.getFloor()];
        } else if (command == Commands.EAST && room.getRow() != 4) {
            return map.getMap()[room.getRow() + 1][room.getColumn()][room.getFloor()];
        } else if (command == Commands.WEST && room.getRow() != 0) {
            return map.getMap()[room.getRow() - 1][room.getColumn()][room.getFloor()];
        } else if (command == Commands.UP && room.getFloor() != 2) {
            return map.getMap()[room.getRow()][room.getColumn()][room.getFloor() + 1];
        } else if (command == Commands.DOWN && room.getFloor() != 0){
            return map.getMap()[room.getRow()][room.getColumn()][room.getFloor() - 1];
        } else {
            return null;
        }
    }

    // Driver method
    public List<Room> findWayOut(Map map, Room currentRoom)
    {
        wayOut = new ArrayList<>();
        path = new ArrayList<>();

        int numberOfRooms = MAX_HEIGHT * MAX_WIDTH * MAX_WIDTH;
        int[][] graph = new int[numberOfRooms][numberOfRooms];

        for(int k = 0; k < MAX_HEIGHT; k++) {
            for(int i = 0; i < MAX_WIDTH; i++) {
                for(int j = 0; j < MAX_WIDTH; j++) {
                    Room room = map.getMap()[i][j][k];
                    int index = to1D(i,j,k);
                    for(Commands commands : room.getDirections().keySet()) {
                        Room adjacentRoom = getAdjacentRoom(map, room, commands);
                        if(adjacentRoom != null) {
                            int adjacentIndex = to1D(adjacentRoom.getRow(), adjacentRoom.getColumn(), adjacentRoom.getFloor());
                            graph[index][adjacentIndex] = room.getDirections().get(commands);
                        }
                    }
                }
            }
        }

        dijkstra(graph, 0, to1D(currentRoom.getRow(), currentRoom.getColumn(), currentRoom.getFloor()));

        for(int i : path) {
            int[] index = to3D(i);
            wayOut.add(map.getMap()[index[0]][index[1]][index[2]]);
        }

        return wayOut;
    }
}

// This code is contributed by Harikrishnan Rajan
