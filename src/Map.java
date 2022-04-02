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
   private List<Monster> monsters;

   /**
    * Constructor for map
    * @param weapons list of all weapons
    * @param treasures list of all treasures
    */
   Map(List<Item> weapons, List<Item> treasures, List<Monster> monsters)
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

   private void createRoom(int n, int s, int e, int w,
                           int u, int d, Monster monster1,
                           Commands direction1, Monster monster2,
                           Commands direction2,
                           Item item1, Item item2, Item item3, int i, int j, int k,
                           String room)
   {
      HashMap<Commands, Integer> db= new HashMap<>();
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
       createRoom(2, 1, 2, 2, 1, 2,
               monsters.get(0), Commands.EAST,
               null, null, null, null, null,
               0, 0, 0,"Video Card");

       createRoom(2, 2, 2, 2, 2, 2,
               monsters.get(0), Commands.WEST,
               null, null, treasures.get(0), treasures.get(1),
               treasures.get(2), 1, 0, 0,"Power Supply");

       createRoom(2, 2, 1, 2, 2, 2,
               monsters.get(1), Commands.SOUTH,
               null, null, null, null,
               null, 2, 0, 0,"Motherboard");

       createRoom(2, 1, 1, 1, 2, 2,
               null, null, null, null, weapons.get(2),
               null, null, 3, 0, 0, "RAM");

       createRoom(2, 2, 2, 1, 2, 2,
               monsters.get(2), Commands.SOUTH,
               null, null, null, null,
               null, 4, 0, 0,"CPU");

   }

   /**
    * builds second row of first floor
    */
   private void firstFloorSecondRow()
   {
       createRoom(1, 2, 1, 2, 2, 2,
               monsters.get(7), Commands.SOUTH,
               null, null, weapons.get(17), null, null,
               0, 1, 0,"Hard Drive");

       createRoom(2, 2, 1, 1, 2, 2,
               null, null,
               null, null, null, null,
               null, 1, 1, 0,"Solid-State Drive");

       createRoom(2, 1, 2, 1, 2, 2,
               monsters.get(1), Commands.NORTH,
               null, null, null, null,
               null, 2, 1, 0,"Optical Disk Drive");

       createRoom(1, 1, 2, 2, 2, 2,
               null, null, null, null, null,
               null, null, 3, 1, 0,"Monitor");

       createRoom(2, 2, 2, 2, 2, 2,
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
       createRoom(2, 1, 2, 2, 2, 2,
               monsters.get(7), Commands.NORTH,
               null, null, null, null, null,
               0, 2, 0,"Mouse");

       createRoom(2, 2, 2, 2, 2, 2,
               monsters.get(6), Commands.SOUTH,
               null, null, treasures.get(5), treasures.get(6),
               weapons.get(3), 1, 2, 0,"Flash Drive");

       createRoom(1, 1, 1, 2, 2, 2,
               null, null,
               null, null, weapons.get(12), null,
               null, 2, 2, 0,"Printer");

       createRoom(1, 2, 1, 1, 2, 2,
               null, null, null, null, null,
               null, null, 3, 2, 0,"Speakers");

       createRoom(2, 1, 2, 1, 2, 2,
               monsters.get(3), Commands.NORTH,
               null, null, null, null,
               null, 4, 2, 0,"Floppy Disk");
   }

   /**
    * builds forth row of first floor
    */
   private void firstFloorForthRow()
   {
       createRoom(1, 1, 1, 2, 2, 2,
               null, null,
               null, null, weapons.get(5), null, null,
               0, 3, 0,"Webcam");

       createRoom(2, 2, 1, 1, 2, 2,
               monsters.get(6), Commands.NORTH,
               null, null, null, null,
               null, 1, 3, 0,"Microphone");

       createRoom(1, 2, 1, 1, 2, 2,
               null, null,
               null, null, null, null,
               null, 2, 3, 0,"Modem");

       createRoom(2, 2, 2, 1, 2, 2,
               monsters.get(4), Commands.SOUTH, null, null, treasures.get(7),
               null, null, 3, 3, 0,"Router");

       createRoom(1, 1, 2, 2, 2, 2,
               null, null,
               null, null, weapons.get(4), null,
               null, 4, 3, 0,"Network Switch");
   }

   /**
    * builds fifth row of first floor
    */
   private void firstFloorFifthRow()
   {
       createRoom(1, 2, 2, 2, 2, 2,
               monsters.get(5), Commands.EAST,
               null, null, treasures.get(8), null, null,
               0, 4, 0,"Firewall");

       createRoom(2, 2, 1, 2, 2, 2,
               monsters.get(5), Commands.WEST,
               null, null, null, null,
               null, 1, 4, 0,"Fan");

       createRoom(2, 2, 1, 1, 2, 2,
               null, null,
               null, null, weapons.get(13), null,
               null, 2, 4, 0,"Battery");

       createRoom(2, 2, 2, 1, 2, 2,
               monsters.get(4), Commands.NORTH, null, null,
               treasures.get(9),
               null, null, 3, 4, 0,"Graphics Card");

       createRoom(1, 2, 2, 2, 1, 2,
               null, null,
               null, null, null, null,
               null, 4, 4, 0,"Operating System");

   }

   /**
    * builds first row of second floor
    */
   private void secondFloorFirstRow()
   {
      createRoom(2, 2, 1, 2, 2, 1,
              monsters.get(14), Commands.SOUTH,
              null, null, weapons.get(7), null, null,
              0, 0, 1,"");

      createRoom(2, 2, 1, 2,2, 2,
              monsters.get(13), Commands.EAST,
              null, null, null, null,
              null, 1, 0, 1,"");

      createRoom(2, 2, 2, 1, 2, 2,
              monsters.get(13), Commands.WEST,
              null, null, treasures.get(10), null,
              null, 2, 0, 1,"");

      createRoom(2, 2, 1, 1, 2, 2,
              null, null, null, null,
              null,
              null, null, 3, 0, 1,"");

      createRoom(2, 1, 2, 1, 2, 2,
              null, null,
              null, null, weapons.get(8), null,
              null, 4, 0, 1,"");
   }

   /**
    * builds second row of second floor
    */
   private void secondFloorSecondRow()
   {
      createRoom(2, 1, 2, 2, 2, 2,
              monsters.get(14), Commands.NORTH,
              null, null, null, null, null,
              0, 1, 1,"");

      createRoom(2, 1, 1, 2,2, 2,
              null, null,
              null, null, weapons.get(23), null,
              null, 1, 1, 1,"");

      createRoom(2, 1, 2, 1, 2, 2,
              monsters.get(10), Commands.EAST,
              null, null, null, null,
              null, 2, 1, 1,"");

      createRoom(2, 1, 1, 2, 2, 2,
              monsters.get(10), Commands.WEST, null, null,
              null,
              null, null, 3, 1, 1,"");

      createRoom(1, 2, 2, 1, 2, 2,
              monsters.get(9), Commands.SOUTH,
              null, null, null, null,
              null, 4, 1, 1,"");
   }

   /**
    * builds third row of second floor
    */
   private void secondFloorThirdRow()
   {
      createRoom(1, 1, 2, 2, 2, 2,
              null, null,
              null, null, weapons.get(9), null, null,
              0, 2, 1,"");

      createRoom(1, 2, 2, 2,2, 2,
              monsters.get(11), Commands.SOUTH,
              null, null, null, null,
              null, 1, 2, 1,"");

      createRoom(1, 2, 2, 2, 1, 2,
              null, null,
              null, null, treasures.get(11), null,
              null, 2, 2, 1,"");

      createRoom(1, 1, 2, 2, 2, 2,
              null, null, null, null,
              weapons.get(15),
              null, null, 3, 2, 1,"");

      createRoom(2, 1, 2, 2, 2, 2,
              monsters.get(9), Commands.NORTH,
              null, null, weapons.get(16), treasures.get(12),
              null, 4, 2, 1,"");
   }

   /**
    * builds forth row of second floor
    */
   private void secondFloorForthRow()
   {
      createRoom(1, 2, 2, 2, 2, 2,
              monsters.get(15), Commands.SOUTH,
              null, null, treasures.get(13), null, null,
              0, 3, 1,"");

      createRoom(2, 1, 1, 2,2, 2,
              monsters.get(11), Commands.NORTH,
              null, null, null, null,
              null, 1, 3, 1,"");

      createRoom(2, 2, 1, 1, 2, 2,
              null, null,
              null, null, null, null,
              null, 2, 3, 1,"");

      createRoom(1, 2, 2, 1, 2, 2,
              null, null, null, null,
              treasures.get(14),
              null, null, 3, 3, 1,"");

      createRoom(1, 2, 2, 2, 2, 2,
              monsters.get(7), Commands.SOUTH,
              null, null, weapons.get(21), null,
              null, 4, 3, 1,"");
   }


   /**
    * builds fifth row of second floor
    */
   private void secondFloorFifthRow()
   {
      createRoom(2, 2, 1, 2, 2, 2,
              monsters.get(15), Commands.NORTH,
              null, null, weapons.get(6), null, null,
              0, 4, 1,"");

      createRoom(1, 2, 2, 1,2, 2,
              monsters.get(12), Commands.EAST,
              null, null, null, null,
              null, 1, 4, 1,"");

      createRoom(2, 2, 1, 1, 2, 2,
              monsters.get(12), Commands.WEST,
              null, null, null, null,
              null, 2, 4, 1,"");

      createRoom(2, 2, 1, 1, 2, 2,
              null, null, null, null,
              weapons.get(10),
              null, null, 3, 4, 1,"");

      createRoom(2, 2, 2, 1, 2, 1,
              monsters.get(7), Commands.NORTH,
              null, null, null, null,
              null, 4, 4, 1,"");
   }

   /**
    * builds first row of third floor
    */
   private void thirdFloorFirstRow()
   {
      createRoom(2, 1, 2, 2, 2, 2,
              null, null,
              null, null, weapons.get(18), null, null,
              0, 1, 2,"");

      createRoom(2, 2, 1, 2,2, 2,
              monsters.get(20), Commands.SOUTH,
              null, null, null, null,
              null, 1, 1, 2,"");

      createRoom(2, 1, 1, 1, 2, 2,
              null, null,
              null, null, null, null,
              null, 2, 1, 2,"");

      createRoom(2, 2, 1, 1, 2, 2,
              monsters.get(17), Commands.SOUTH, null, null,
              null,
              null, null, 3, 1, 2,"");

      createRoom(2, 1, 2, 1, 2, 2,
              null, null,
              null, null, treasures.get(15), null,
              null, 4, 1, 2,"");
   }

   /**
    * builds second row of third floor
    */
   private void thirdFloorSecondRow()
   {
      createRoom(1, 1, 2, 2, 2, 2,
              monsters.get(21), Commands.EAST,
              null, null, null, null, null,
              0, 2, 2,"");

      createRoom(2, 2, 2, 2,2, 2,
              monsters.get(20), Commands.NORTH,
              monsters.get(21), Commands.WEST, treasures.get(16),
              treasures.get(17),
              null, 1, 2, 2,"");

      createRoom(1, 1, 2, 2, 2, 2,
              null, null,
              null, null, weapons.get(22), null,
              null, 2, 2, 2,"");

      createRoom(2, 2, 2, 2, 2, 2,
              monsters.get(17), Commands.NORTH, null, null,
              treasures.get(18),
              treasures.get(19), null, 3, 2, 2,"");

      createRoom(1, 2, 2, 2, 2, 2,
              monsters.get(23), Commands.SOUTH,
              null, null, null, null,
              null, 4, 2, 2,"");
   }

   /**
    * builds third row of third floor
    */
   private void thirdFloorThirdRow()
   {
      createRoom(1, 1, 2, 2, 2, 2,
              null, null,
              null, null, treasures.get(20), null, null,
              0, 3, 2,"");

      createRoom(2, 2, 2, 1,2, 2,
              null, null,
              null, null, weapons.get(11),
              null,
              null, 1, 3, 2,"");

      createRoom(1, 1, 1, 1, 2, 1,
              null, null,
              null, null, null, null,
              null, 2, 3, 2,"");

      createRoom(2, 2, 1, 1, 2, 2,
              null, null, null, null,
              null,
              null, null, 3, 3, 2,"");

      createRoom(2, 2, 2, 1, 2, 2,
              monsters.get(23), Commands.NORTH,
              monsters.get(22), Commands.SOUTH, weapons.get(19), null,
              null, 4, 3, 2,"");
   }

   /**
    * builds forth row of third floor
    */
   private void thirdFloorForthRow()
   {
      createRoom(1, 1, 2, 2, 2, 2,
              monsters.get(18), Commands.EAST,
              null, null, null, null, null,
              0, 3, 2,"");

      createRoom(2, 1, 2, 2,2, 2,
              monsters.get(18), Commands.WEST,
              null, null, treasures.get(21),
              null,
              null, 1, 3, 2,"");

      createRoom(1, 2, 2, 2, 2, 2,
              monsters.get(19), Commands.SOUTH,
              null, null, null, null,
              null, 2, 3, 2,"");

      createRoom(2, 2, 2, 2, 2, 2,
              monsters.get(16), Commands.EAST, null, null,
              weapons.get(0),
              treasures.get(22), null, 3, 3, 2,"");

      createRoom(2, 1, 2, 2, 2, 2,
              monsters.get(22), Commands.NORTH,
              null, null, null, null,
              null, 4, 3, 2,"");
   }

   /**
    * builds fifth row of third floor
    */
   private void thirdFloorFifthRow()
   {
      createRoom(1, 2, 1, 2, 2, 2,
              null, null,
              null, null, weapons.get(14), null, null,
              0, 4, 2,"");

      createRoom(1, 2, 2, 1,2, 2,
              null, null,
              null, null, null,
              null,
              null, 1, 4, 2,"");

      createRoom(2, 2, 1, 2, 2, 2,
              monsters.get(19), Commands.NORTH,
              null, null, null, null,
              null, 2, 4, 2,"");

      createRoom(2, 2, 1, 1, 2, 2,
              null, null, null, null,
              null,
              null, null, 3, 4, 2,"");

      createRoom(1, 2, 2, 1, 2, 2,
              null, null,
              null, null, weapons.get(20), null,
              null, 4, 4, 2,"");
   }
}
