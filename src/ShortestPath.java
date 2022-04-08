import java.util.ArrayList;
import java.util.List;

/**
 * source: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 */
public class ShortestPath {

    private final int MAX_WIDTH = 5;
    private final int MAX_HEIGHT = 3;
    private List<String> wayOut;

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V = 5*5*3;
    int minDistance(int[] dist, Boolean[] sptSet, int dest)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int[] dist, int dest)
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            if(dist[i] == dest) {
                System.out.println(i + " \t\t " + dist[i]);
            }
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int[][] graph, int src, int dest)
    {
        int[] dist = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean[] sptSet = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, dest);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist, dest);
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
    public List<String> findWayOut(Map map, Room currentRoom)
    {
        wayOut = new ArrayList<>();
        // Let us create the example graph discussed above
        int[][] graph2 = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

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

        return wayOut;
    }
}
// This code is contributed by Aakash Hasija

