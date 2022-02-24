import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

   private int MAX_WIDTH = 5;
   private int MAX_HEIGHT = 3;
   private Room[][][] map;
   private List<Item> weapons;
   private List<Item> treasures;

   Map(List<Item> weapons, List<Item> treasures)
   {
      this.weapons = weapons;
      this.treasures = treasures;
      // row, column, floor
      map = new Room[MAX_WIDTH][MAX_WIDTH][MAX_HEIGHT];
      firstFloor();
   }

   private void firstFloor()
   {
      firstFloorFirstRow();
      firstFloorSecondRow();
      firstFloorThirdRow();
      firstFloorForthRow();
      firstFloorFifthRow();
   }

   private void firstFloorFirstRow()
   {
      createRoom(false, true, false, false, false, false,
              new Monster("Code in Notepad"), Direction.EAST,
              null, null, null, null, null,
              0, 0, "Video Card");

      createRoom(false, false, false, false, false, false,
              new Monster("Code in Notepad"), Direction.WEST,
              null, null, treasures.get(0), treasures.get(1),
              treasures.get(2), 1, 0, "Power Supply");

      createRoom(false, false, true, false, false, false,
              new Monster("Missing Semicolon"), Direction.SOUTH,
              null, null, null, null,
              null, 2, 0, "Motherboard");

      createRoom(false, true, true, true, false, false,
              null, null, null, null, weapons.get(2),
              null, null, 3, 1, "RAM");

      createRoom(false, false, false, true, false, false,
              new Monster("Spelling Mistake"), Direction.SOUTH,
              null, null, null, null,
              null, 4, 0, "CPU");
   }

   private void firstFloorSecondRow()
   {
      createRoom(true, false, true, false, false, false,
              new Monster("Failed Build"), Direction.SOUTH,
              null, null, weapons.get(17), null, null,
              0, 1, "Hard Drive");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 1, 1, "Solid-State Drive");

      createRoom(false, true, false, true, false, false,
              new Monster("Missing Semicolon"), Direction.NORTH,
              null, null, null, null,
              null, 2, 1, "Optical Disk Drive");

      createRoom(true, true, false, false, false, false,
              null, null, null, null, null,
              null, null, 3, 1, "Monitor");

      createRoom(false, false, false, false, false, false,
              new Monster("Spelling Mistake"), Direction.NORTH,
              new Monster("Wrong Operator"), Direction.SOUTH,
              weapons.get(1), treasures.get(3),
              treasures.get(4), 4, 1, "Keyboard");
   }

   private void firstFloorThirdRow()
   {
      createRoom(false, true, false, false, false, false,
              new Monster("Failed Build"), Direction.NORTH,
              null, null, null, null, null,
              0, 2, "Mouse");

      createRoom(false, false, false, false, false, false,
              new Monster("Missing Method"), Direction.SOUTH,
              null, null, treasures.get(5), treasures.get(6),
              weapons.get(3), 1, 2, "Flash Drive");

      createRoom(true, true, true, false, false, false,
              null, null,
              null, null, weapons.get(12), null,
              null, 2, 2, "Printer");

      createRoom(true, false, true, true, false, false,
              null, null, null, null, null,
              null, null, 3, 2, "Speakers");

      createRoom(false, true, false, true, false, false,
              new Monster("Wrong Operator"), Direction.NORTH,
              null, null, null, null,
              null, 4, 2, "Floppy Disk");
   }

   private void firstFloorForthRow()
   {
      createRoom(true, true, true, false, false, false,
              null, null,
              null, null, weapons.get(5), null, null,
              0, 3, "Webcam");

      createRoom(false, false, true, true, false, false,
              new Monster("Missing Method"), Direction.NORTH,
              null, null, null, null,
              null, 1, 3, "Microphone");

      createRoom(true, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 2, 3, "Modem");

      createRoom(false, false, false, true, false, false,
              new Monster("Magic Number"), Direction.SOUTH, null, null, treasures.get(7),
              null, null, 3, 3, "Router");

      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, weapons.get(4), null,
              null, 4, 3, "Network Switch");
   }

   private void firstFloorFifthRow()
   {
      createRoom(true, false, false, false, false, false,
              new Monster("Wrong Variable Type"), Direction.EAST,
              null, null, treasures.get(8), null, null,
              0, 4, "Firewall");

      createRoom(false, false, true, false, false, false,
              new Monster("Wrong Variable Type"), Direction.WEST,
              null, null, null, null,
              null, 1, 4, "Fan");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, weapons.get(13), null,
              null, 2, 4, "Battery");

      createRoom(false, false, false, true, false, false,
              new Monster("Magic Number"), Direction.NORTH, null, null,
              treasures.get(9),
              null, null, 3, 4, "Graphics Card");

      createRoom(true, false, false, false, false, false,
              null, null,
              null, null, null, null,
              null, 4, 4, "Operating System");
   }

   private void createRoom(boolean n, boolean s, boolean e, boolean w,
                           boolean u, boolean d, Monster monster1,
                      Direction direction1, Monster monster2,
                      Direction direction2,
                      Item item1, Item item2, Item item3, int i, int j,
                      String room)
   {
      HashMap<Direction, Boolean> db= new HashMap<>();
      db.put(Direction.NORTH, n);
      db.put(Direction.SOUTH, s);
      db.put(Direction.EAST, e);
      db.put(Direction.WEST, w);
      db.put(Direction.UP, u);
      db.put(Direction.DOWN, d);
      HashMap<Monster, Direction> md = new HashMap<>();
      if(monster1 != null)
         md.put(monster1, direction1);
      if(monster2 != null)
         md.put(monster2, direction2);
      List<Item> items = new ArrayList<>();
      if(item1 != null)
         items.add(item1);
      if(item2 != null)
         items.add(item2);
      if(item3 != null)
         items.add(item3);
      map[i][j][0] = new Room(db, items, md, "You have entered the " +
              room + ".");
   }

}
