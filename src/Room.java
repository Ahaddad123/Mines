import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Items.Item;
import Items.Monster;

/**
 * Creates a room with monsters, weapons, and treasures
 * @author Jasmine Krahn
 */
public class Room {
   private HashMap<Commands, Integer> directions;
   private List<Item> items;
   private HashMap<Commands, Monster> monsters;
   private String description;
   private int row;
   private int column;
   private int floor;

   /**
    * Constructor to set up a room
    * @param items items in the room
    * @param monsters monsters blocking room exits
    * @param row row of map array
    * @param column column of map array
    * @param description description of the room
    */
   Room(List<Item> items,
        HashMap<Commands, Monster> monsters, int row,
        int column, int floor, String description)
   {
      this.items = items;
      this.monsters = monsters;
      this.description = description;
      this.row = row;
      this.column = column;
      this.floor = floor;
      this.floor = 0;
   }

   Room(int i, int j, int k) {
      this.items = new ArrayList<>();
      this.directions = new HashMap<>();
      this.monsters = new HashMap<>();
      this.row = i;
      this.column = j;
      this.floor = k;
   }

   /**
    * Getter for open exits
    * @return open exits
    */
   public HashMap<Commands, Integer> getDirections() {
      return directions;
   }

   /**
    * getting for room items
    * @return list of room items
    */
   public List<Item> getItems() {
      return items;
   }

   /**
    * getter for monsters blocking passageways
    * @return monsters in the room
    */
   public HashMap<Commands, Monster> getMonsters() {
      return monsters;
   }

   /**
    * getter for room description
    * @return room description
    */
   public String getDescription() {
      return description;
   }

   /**
    * getter for row
    * @return row number of room
    */
   public int getRow() {
      return row;
   }

   /**
    * getter for column
    * @return column number of room
    */
   public int getColumn() {
      return column;
   }

   /**
    * getter for floor
    * @return floor number of room
    */
   public int getFloor() {
      return floor;
   }

   /**
    * setter for room description
    * @param description room description
    */
   public void setDescription(String description) {
      this.description = description;
   }
}
