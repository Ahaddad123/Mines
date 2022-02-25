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
   private Room[][] map;
   private List<Item> weapons;
   private List<Item> treasures;

   /**
    * Constructor for map
    * @param weapons list of all weapons
    * @param treasures list of all treasures
    */
   Map(List<Item> weapons, List<Item> treasures)
   {
      this.weapons = weapons;
      this.treasures = treasures;
      // row, column, floor
      map = new Room[MAX_WIDTH][MAX_WIDTH];
      firstFloor();
   }

   /**
    * gets game map
    * @return game map
    */
   public Room[][] getMap() {
      return map;
   }

   /**
    * creates a room for each peice of the array
    * @param n north exit availability
    * @param s south exit availability
    * @param e east exit availability
    * @param w west exit availability
    * @param u up exit availability
    * @param d down exit availability
    * @param monster1 one monster blocking a path
    * @param direction1 direction that monster1 blocks
    * @param monster2 another monster blocking a path
    * @param direction2 direction that monster2 blocks
    * @param item1 an item in the room
    * @param item2 an item in the room
    * @param item3 an item in the room
    * @param i row index
    * @param j column index
    * @param room room description
    */
   private void createRoom(boolean n, boolean s, boolean e, boolean w,
                           boolean u, boolean d, Monster monster1,
                           Commands direction1, Monster monster2,
                           Commands direction2,
                           Item item1, Item item2, Item item3, int i, int j,
                           String room)
   {
      HashMap<Commands, Boolean> db= new HashMap<>();
      db.put(Commands.NORTH, n);
      db.put(Commands.SOUTH, s);
      db.put(Commands.EAST, e);
      db.put(Commands.WEST, w);
      db.put(Commands.UP, u);
      db.put(Commands.DOWN, d);
      HashMap<Commands, Monster> md = new HashMap<>();
      if(monster1 != null)
         md.put(direction1, monster1);
      if(monster2 != null)
         md.put(direction2, monster2);
      List<Item> items = new ArrayList<>();
      if(item1 != null)
         items.add(item1);
      if(item2 != null)
         items.add(item2);
      if(item3 != null)
         items.add(item3);
      map[i][j] = new Room(db, items, md, i, j, "You have entered the" +
              " " +
              room + ".");
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
    * builds first row of first floor
    */
   private void firstFloorFirstRow()
   {
      createRoom(false, true, false, false, false, false,
              new Monster("Code in Notepad"), Commands.EAST,
              null, null, null, null, null,
              0, 0, "Video Card");

      createRoom(false, false, false, false, false, false,
              new Monster("Code in Notepad"), Commands.WEST,
              null, null, treasures.get(0), treasures.get(1),
              treasures.get(2), 1, 0, "Power Supply");

      createRoom(false, false, true, false, false, false,
              new Monster("Missing Semicolon"), Commands.SOUTH,
              null, null, null, null,
              null, 2, 0, "Motherboard");

      createRoom(false, true, true, true, false, false,
              null, null, null, null, weapons.get(2),
              null, null, 3, 0, "RAM");

      createRoom(false, false, false, true, false, false,
              new Monster("Spelling Mistake"), Commands.SOUTH,
              null, null, null, null,
              null, 4, 0, "CPU");
   }

   /**
    * builds second row of first floor
    */
   private void firstFloorSecondRow()
   {
      createRoom(true, false, true, false, false, false,
              new Monster("Failed Build"), Commands.SOUTH,
              null, null, weapons.get(17), null, null,
              0, 1, "Hard Drive");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 1, 1, "Solid-State Drive");

      createRoom(false, true, false, true, false, false,
              new Monster("Missing Semicolon"), Commands.NORTH,
              null, null, null, null,
              null, 2, 1, "Optical Disk Drive");

      createRoom(true, true, false, false, false, false,
              null, null, null, null, null,
              null, null, 3, 1, "Monitor");

      createRoom(false, false, false, false, false, false,
              new Monster("Spelling Mistake"), Commands.NORTH,
              new Monster("Wrong Operator"), Commands.SOUTH,
              weapons.get(1), treasures.get(3),
              treasures.get(4), 4, 1, "Keyboard");
   }

   /**
    * builds third row of first floor
    */
   private void firstFloorThirdRow()
   {
      createRoom(false, true, false, false, false, false,
              new Monster("Failed Build"), Commands.NORTH,
              null, null, null, null, null,
              0, 2, "Mouse");

      createRoom(false, false, false, false, false, false,
              new Monster("Missing Method"), Commands.SOUTH,
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
              new Monster("Wrong Operator"), Commands.NORTH,
              null, null, null, null,
              null, 4, 2, "Floppy Disk");
   }

   /**
    * builds forth row of first floor
    */
   private void firstFloorForthRow()
   {
      createRoom(true, true, true, false, false, false,
              null, null,
              null, null, weapons.get(5), null, null,
              0, 3, "Webcam");

      createRoom(false, false, true, true, false, false,
              new Monster("Missing Method"), Commands.NORTH,
              null, null, null, null,
              null, 1, 3, "Microphone");

      createRoom(true, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 2, 3, "Modem");

      createRoom(false, false, false, true, false, false,
              new Monster("Magic Number"), Commands.SOUTH, null, null, treasures.get(7),
              null, null, 3, 3, "Router");

      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, weapons.get(4), null,
              null, 4, 3, "Network Switch");
   }

   /**
    * builds fifth row of first floor
    */
   private void firstFloorFifthRow()
   {
      createRoom(true, false, false, false, false, false,
              new Monster("Wrong Variable Type"), Commands.EAST,
              null, null, treasures.get(8), null, null,
              0, 4, "Firewall");

      createRoom(false, false, true, false, false, false,
              new Monster("Wrong Variable Type"), Commands.WEST,
              null, null, null, null,
              null, 1, 4, "Fan");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, weapons.get(13), null,
              null, 2, 4, "Battery");

      createRoom(false, false, false, true, false, false,
              new Monster("Magic Number"), Commands.NORTH, null, null,
              treasures.get(9),
              null, null, 3, 4, "Graphics Card");

      createRoom(true, false, false, false, false, false,
              null, null,
              null, null, null, null,
              null, 4, 4, "Operating System");
   }
}
