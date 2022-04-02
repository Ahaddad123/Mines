import Items.Item;
import Items.ItemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Makenna Halvensleben
 * Main class that runs the main method.
 */
public class Main {
    public static void main(String[] args) {
        //GameController gameController = new GameController();
        //gameController.run();
        //int i = 0;
        Room[][][] map = new Room[5][5][3];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 3; k++) {
                    map[i][j][k] = new Room(i, j, k);
                }
            }
        }
        List<Commands> directions = new ArrayList<>();
        directions.add(Commands.NORTH);
        directions.add(Commands.SOUTH);
        directions.add(Commands.EAST);
        directions.add(Commands.WEST);
        directions.add(Commands.UP);
        directions.add(Commands.DOWN);
        Random random = new Random();
        //int x = random.nextInt();
        for(int x = 0; x < 10; x++) {


            MapRandomizer mapRandomizer = new MapRandomizer(map, x); //75438
            //MapRandomizer mapRandomizer = new MapRandomizer(map, 75438);
            mapRandomizer.randomizeFirstFloorStructure();
        /*for(Room[][] floor : map) {
            for(Room[] row : floor) {
                for(Room room : row) {
                    System.out.print(room.getColumn() + " " + room.getRow() + " " + room.getFloor() + ":");
                    //for(Item item : room.getItems()) {
                    //    System.out.print(item.getName() + " ");
                    //}
                    //System.out.println();
                }
            }
        }*/
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Room room = map[i][j][0];
                    System.out.print(room.getColumn() + " " + room.getRow() + " " + room.getFloor() + ":");
                    for (Commands direction : directions) {
                        if (room.getDirections().get(direction) == 1) {
                            System.out.print(direction);
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}
