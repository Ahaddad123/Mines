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
   private List<Item> monsters;

   /**
    * Constructor for map
    * @param weapons list of all weapons
    * @param treasures list of all treasures
    */
   Map(List<Item> weapons, List<Item> treasures, List<Item> monsters)
   {
      this.weapons = weapons;
      this.treasures = treasures;
      this.monsters = monsters;
      // row, column, floor
      map = new Room[MAX_WIDTH][MAX_WIDTH][MAX_HEIGHT];
      firstFloor();
      secondFloor();
   }

   /**
    * gets game map
    * @return game map
    */
   public Room[][][] getMap() {
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
                           boolean u, boolean d, Item monster1,
                           Commands direction1, Item monster2,
                           Commands direction2,
                           Item item1, Item item2, Item item3, int i, int j
           , int k,
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
      map[i][j][k] = new Room(db, items, md, i, j, k, "You have entered the" +
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
      createRoom(false, true, false, false, true, false,
              monsters.get(0), Commands.EAST,
              null, null, null, null, null,
              0, 0, 0,"Video Card");

      createRoom(false, false, false, false, false, false,
              monsters.get(0), Commands.WEST,
              null, null, treasures.get(0), treasures.get(1),
              treasures.get(2), 1, 0, 0,"Power Supply");

      createRoom(false, false, true, false, false, false,
              monsters.get(1), Commands.SOUTH,
              null, null, null, null,
              null, 2, 0, 0,"Motherboard");

      createRoom(false, true, true, true, false, false,
              null, null, null, null, weapons.get(2),
              null, null, 3, 0, 0, "RAM");

      createRoom(false, false, false, true, false, false,
              monsters.get(2), Commands.SOUTH,
              null, null, null, null,
              null, 4, 0, 0,"CPU");
   }

   /**
    * builds second row of first floor
    */
   private void firstFloorSecondRow()
   {
      createRoom(true, false, true, false, false, false,
              monsters.get(7), Commands.SOUTH,
              null, null, weapons.get(17), null, null,
              0, 1, 0,"Hard Drive");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 1, 1, 0,"Solid-State Drive");

      createRoom(false, true, false, true, false, false,
              monsters.get(1), Commands.NORTH,
              null, null, null, null,
              null, 2, 1, 0,"Optical Disk Drive");

      createRoom(true, true, false, false, false, false,
              null, null, null, null, null,
              null, null, 3, 1, 0,"Monitor");

      createRoom(false, false, false, false, false, false,
              monsters.get(2), Commands.NORTH,
              monsters.get(3), Commands.SOUTH,
              weapons.get(1), treasures.get(3),
              treasures.get(4), 4, 1, 0,"Keyboard");
   }

   /**
    * builds third row of first floor
    */
   private void firstFloorThirdRow()
   {
      createRoom(false, true, false, false, false, false,
              monsters.get(7), Commands.NORTH,
              null, null, null, null, null,
              0, 2, 0,"Mouse");

      createRoom(false, false, false, false, false, false,
              monsters.get(6), Commands.SOUTH,
              null, null, treasures.get(5), treasures.get(6),
              weapons.get(3), 1, 2, 0,"Flash Drive");

      createRoom(true, true, true, false, false, false,
              null, null,
              null, null, weapons.get(12), null,
              null, 2, 2, 0,"Printer");

      createRoom(true, false, true, true, false, false,
              null, null, null, null, null,
              null, null, 3, 2, 0,"Speakers");

      createRoom(false, true, false, true, false, false,
              monsters.get(3), Commands.NORTH,
              null, null, null, null,
              null, 4, 2, 0,"Floppy Disk");
   }

   /**
    * builds forth row of first floor
    */
   private void firstFloorForthRow()
   {
      createRoom(true, true, true, false, false, false,
              null, null,
              null, null, weapons.get(5), null, null,
              0, 3, 0,"Webcam");

      createRoom(false, false, true, true, false, false,
              monsters.get(6), Commands.NORTH,
              null, null, null, null,
              null, 1, 3, 0,"Microphone");

      createRoom(true, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 2, 3, 0,"Modem");

      createRoom(false, false, false, true, false, false,
              monsters.get(4), Commands.SOUTH, null, null, treasures.get(7),
              null, null, 3, 3, 0,"Router");

      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, weapons.get(4), null,
              null, 4, 3, 0,"Network Switch");
   }

   /**
    * builds fifth row of first floor
    */
   private void firstFloorFifthRow()
   {
      createRoom(true, false, false, false, false, false,
              monsters.get(5), Commands.EAST,
              null, null, treasures.get(8), null, null,
              0, 4, 0,"Firewall");

      createRoom(false, false, true, false, false, false,
              monsters.get(5), Commands.WEST,
              null, null, null, null,
              null, 1, 4, 0,"Fan");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, weapons.get(13), null,
              null, 2, 4, 0,"Battery");

      createRoom(false, false, false, true, false, false,
              monsters.get(4), Commands.NORTH, null, null,
              treasures.get(9),
              null, null, 3, 4, 0,"Graphics Card");

      createRoom(true, false, false, false, true, false,
              null, null,
              null, null, null, null,
              null, 4, 4, 0,"Operating System");
   }

   /**
    * builds first row of second floor
    */
   private void secondFloorFirstRow()
   {
      createRoom(false, false, true, false, false, true,
              monsters.get(14), Commands.SOUTH,
              null, null, weapons.get(7), null, null,
              0, 0, 1,"");

      createRoom(false, false, true, false,false, false,
              monsters.get(13), Commands.EAST,
              null, null, null, null,
              null, 1, 0, 1,"");

      createRoom(false, false, false, true, false, false,
              monsters.get(13), Commands.WEST,
              null, null, treasures.get(10), null,
              null, 2, 0, 1,"");

      createRoom(false, false, true, true, false, false,
              null, null, null, null,
              null,
              null, null, 3, 0, 1,"");

      createRoom(false, true, false, true, false, false,
              null, null,
              null, null, weapons.get(8), null,
              null, 4, 0, 1,"");
   }

   /**
    * builds second row of second floor
    */
   private void secondFloorSecondRow()
   {
      createRoom(false, true, false, false, false, false,
              monsters.get(14), Commands.NORTH,
              null, null, null, null, null,
              0, 1, 1,"");

      createRoom(false, true, true, false,false, false,
              null, null,
              null, null, weapons.get(23), null,
              null, 1, 1, 1,"");

      createRoom(false, true, false, true, false, false,
              monsters.get(10), Commands.EAST,
              null, null, null, null,
              null, 2, 1, 1,"");

      createRoom(false, true, true, false, false, false,
              monsters.get(10), Commands.WEST, null, null,
              null,
              null, null, 3, 1, 1,"");

      createRoom(true, false, false, true, false, false,
              monsters.get(9), Commands.SOUTH,
              null, null, null, null,
              null, 4, 1, 1,"");
   }

   /**
    * builds third row of second floor
    */
   private void secondFloorThirdRow()
   {
      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, weapons.get(9), null, null,
              0, 2, 1,"");

      createRoom(true, false, false, false,false, false,
              monsters.get(11), Commands.SOUTH,
              null, null, null, null,
              null, 1, 2, 1,"");

      createRoom(true, false, false, false, true, false,
              null, null,
              null, null, treasures.get(11), null,
              null, 2, 2, 1,"");

      createRoom(true, true, false, false, false, false,
              null, null, null, null,
              weapons.get(15),
              null, null, 3, 2, 1,"");

      createRoom(false, true, false, false, false, false,
              monsters.get(9), Commands.NORTH,
              null, null, weapons.get(16), treasures.get(12),
              null, 4, 2, 1,"");
   }

   /**
    * builds forth row of second floor
    */
   private void secondFloorForthRow()
   {
      createRoom(true, false, false, false, false, false,
              monsters.get(15), Commands.SOUTH,
              null, null, treasures.get(13), null, null,
              0, 3, 1,"");

      createRoom(false, true, true, false,false, false,
              monsters.get(11), Commands.NORTH,
              null, null, null, null,
              null, 1, 3, 1,"");

      createRoom(false, false, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 2, 3, 1,"");

      createRoom(true, false, false, true, false, false,
              null, null, null, null,
              treasures.get(14),
              null, null, 3, 3, 1,"");

      createRoom(true, false, false, false, false, false,
              monsters.get(7), Commands.SOUTH,
              null, null, weapons.get(21), null,
              null, 4, 3, 1,"");
   }


   /**
    * builds fifth row of second floor
    */
   private void secondFloorFifthRow()
   {
      createRoom(false, false, true, false, false, false,
              monsters.get(15), Commands.NORTH,
              null, null, weapons.get(6), null, null,
              0, 4, 1,"");

      createRoom(true, false, false, true,false, false,
              monsters.get(12), Commands.EAST,
              null, null, null, null,
              null, 1, 4, 1,"");

      createRoom(false, false, true, true, false, false,
              monsters.get(12), Commands.WEST,
              null, null, null, null,
              null, 2, 4, 1,"");

      createRoom(false, false, true, true, false, false,
              null, null, null, null,
              weapons.get(10),
              null, null, 3, 4, 1,"");

      createRoom(false, false, false, true, false, true,
              monsters.get(7), Commands.NORTH,
              null, null, null, null,
              null, 4, 4, 1,"");
   }

   /**
    * builds first row of third floor
    */
   private void thirdFloorFirstRow()
   {
      createRoom(false, true, false, false, false, false,
              null, null,
              null, null, weapons.get(18), null, null,
              0, 1, 2,"");

      createRoom(false, false, true, false,false, false,
              monsters.get(20), Commands.SOUTH,
              null, null, null, null,
              null, 1, 1, 2,"");

      createRoom(false, true, true, true, false, false,
              null, null,
              null, null, null, null,
              null, 2, 1, 2,"");

      createRoom(false, false, true, true, false, false,
              monsters.get(17), Commands.SOUTH, null, null,
              null,
              null, null, 3, 1, 2,"");

      createRoom(false, true, false, true, false, false,
              null, null,
              null, null, treasures.get(15), null,
              null, 4, 1, 2,"");
   }

   /**
    * builds second row of third floor
    */
   private void thirdFloorSecondRow()
   {
      createRoom(true, true, false, false, false, false,
              monsters.get(21), Commands.EAST,
              null, null, null, null, null,
              0, 2, 2,"");

      createRoom(false, false, false, false,false, false,
              monsters.get(20), Commands.NORTH,
              monsters.get(21), Commands.WEST, treasures.get(16),
              treasures.get(17),
              null, 1, 2, 2,"");

      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, weapons.get(22), null,
              null, 2, 2, 2,"");

      createRoom(false, false, false, false, false, false,
              monsters.get(17), Commands.NORTH, null, null,
              treasures.get(18),
              treasures.get(19), null, 3, 2, 2,"");

      createRoom(true, false, false, false, false, false,
              monsters.get(23), Commands.SOUTH,
              null, null, null, null,
              null, 4, 2, 2,"");
   }

   /**
    * builds third row of third floor
    */
   private void thirdFloorThirdRow()
   {
      createRoom(true, true, false, false, false, false,
              null, null,
              null, null, treasures.get(20), null, null,
              0, 3, 2,"");

      createRoom(false, false, false, true,false, false,
              null, null,
              null, null, weapons.get(11),
              null,
              null, 1, 3, 2,"");

      createRoom(true, true, true, true, false, true,
              null, null,
              null, null, null, null,
              null, 2, 3, 2,"");

      createRoom(false, false, true, true, false, false,
              null, null, null, null,
              null,
              null, null, 3, 3, 2,"");

      createRoom(false, false, false, true, false, false,
              monsters.get(23), Commands.NORTH,
              monsters.get(22), Commands.SOUTH, weapons.get(19), null,
              null, 4, 3, 2,"");
   }

   /**
    * builds forth row of third floor
    */
   private void thirdFloorForthRow()
   {
      createRoom(true, true, false, false, false, false,
              monsters.get(18), Commands.EAST,
              null, null, null, null, null,
              0, 3, 2,"");

      createRoom(false, true, false, false,false, false,
              monsters.get(18), Commands.WEST,
              null, null, treasures.get(21),
              null,
              null, 1, 3, 2,"");

      createRoom(true, false, false, false, false, false,
              monsters.get(19), Commands.SOUTH,
              null, null, null, null,
              null, 2, 3, 2,"");

      createRoom(false, false, false, false, false, false,
              monsters.get(16), Commands.EAST, null, null,
              weapons.get(0),
              treasures.get(22), null, 3, 3, 2,"");

      createRoom(false, true, false, false, false, false,
              monsters.get(22), Commands.NORTH,
              null, null, null, null,
              null, 4, 3, 2,"");
   }

   /**
    * builds fifth row of third floor
    */
   private void thirdFloorFifthRow()
   {
      createRoom(true, false, true, false, false, false,
              null, null,
              null, null, weapons.get(14), null, null,
              0, 4, 2,"");

      createRoom(true, false, false, true,false, false,
              null, null,
              null, null, null,
              null,
              null, 1, 4, 2,"");

      createRoom(false, false, true, false, false, false,
              monsters.get(19), Commands.NORTH,
              null, null, null, null,
              null, 2, 4, 2,"");

      createRoom(false, false, true, true, false, false,
              null, null, null, null,
              null,
              null, null, 3, 4, 2,"");

      createRoom(true, false, false, true, false, false,
              null, null,
              null, null, weapons.get(20), null,
              null, 4, 4, 2,"");
   }
}
