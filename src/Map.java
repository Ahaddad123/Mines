import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

   private int MAX_WIDTH = 5;
   private int MAX_HEIGHT = 3;
   private Room[][] map;
   private List<Item> weapons;
   private List<Item> treasures;

   Map(List<Item> weapons, List<Item> treasures)
   {
      this.weapons = weapons;
      this.treasures = treasures;
      map = new Room[MAX_WIDTH][MAX_WIDTH];
      firstFloorFirstRow();
   }

   public void printMap()
   {
      for(int i = 0; i < MAX_WIDTH; i++)
      {
         map[0][i].printDirections();
      }
   }

   private void firstFloorFirstRow()
   {
      // row, column, floor
      HashMap<Direction, Boolean> m = new HashMap<>();
      m.put(Direction.NORTH, false);
      HashMap<Monster, Direction> map1 = new HashMap<>();
      map1.put(new Monster("name"), Direction.WEST);
      List<Item> items = new ArrayList<>();
      items.add(treasures.get(0));
      map[0][0] = new Room(m, items, map1, "");

   }

}
