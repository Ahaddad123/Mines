import java.util.HashMap;
import java.util.List;
import Items.Item;
import Items.Monster;

/**
 * Creates a room with monsters, weapons, and treasures
 * @author Jasmine Krahn
 */
public class Room {
   private HashMap<Commands, Boolean> directions;
   private List<Item> items;
   private HashMap<Commands, Monster> monsters;
   private String description;
   private int row;
   private int column;
   private int floor;

   /**
    * Constructor to set up a room
    * @param directions open exits
    * @param items items in the room
    * @param monsters monsters blocking room exits
    * @param row row of map array
    * @param column column of map array
    */
   Room(HashMap<Commands, Boolean> directions, List<Item> items,
        HashMap<Commands, Monster> monsters, int row,
        int column, int floor)
   {
      this.items = items;
      this.monsters = monsters;
      this.description = "";
      this.directions = directions;
      this.row = row;
      this.column = column;
      this.floor = floor;
      this.floor = 0;
   }

   /**
    * Getter for open exits
    * @return open exits
    */
   public HashMap<Commands, Boolean> getDirections() {
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
    * setter for room description
    */
   public void setDescription(String description) {
      this.description = description;
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
}
