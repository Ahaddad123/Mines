import java.util.HashMap;
import java.util.List;

/**
 * Creates a room with monsters, weapons, and treasures
 * @author Jasmine Krahn
 */
public class Room {
   private HashMap<Direction, Boolean> directions;
   private List<Item> items;
   private HashMap<Direction, Monster> monsters;
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
    * @param description description of the room
    */
   Room(HashMap<Direction, Boolean> directions, List<Item> items,
        HashMap<Direction, Monster> monsters, int row,
        int column, String description)
   {
      this.items = items;
      this.monsters = monsters;
      this.description = description;
      this.directions = directions;
      this.row = row;
      this.column = column;
      this.floor = 0;
   }

   /**
    * Getter for open exits
    * @return open exits
    */
   public HashMap<Direction, Boolean> getDirections() {
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
   public HashMap<Direction, Monster> getMonsters() {
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
}
