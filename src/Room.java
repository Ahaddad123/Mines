import java.util.HashMap;
import java.util.List;

public class Room {
   private HashMap<Direction, Boolean> directions;
   private List<Item> items;
   private HashMap<Direction, Monster> monsters;
   private String description;
   private int row;
   private int column;
   private int floor;

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

   public HashMap<Direction, Boolean> getDirections() {
      return directions;
   }

   public List<Item> getItems() {
      return items;
   }

   public HashMap<Direction, Monster> getMonsters() {
      return monsters;
   }

   public String getDescription() {
      return description;
   }

   public int getRow() {
      return row;
   }

   public int getColumn() {
      return column;
   }

   public int getFloor() {
      return floor;
   }
}
