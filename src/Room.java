import java.util.HashMap;
import java.util.List;

public class Room {
   private HashMap<Direction, Boolean> directions;
   private List<Item> items;
   private HashMap<Monster, Direction> monsters;
   private String description;

   Room(HashMap<Direction, Boolean> directions, List<Item> items,
        HashMap<Monster, Direction> monsters, String description)
   {
      this.items = items;
      this.monsters = monsters;
      this.description = description;
      this.directions = directions;
   }

   public HashMap<Direction, Boolean> getDirections() {
      return directions;
   }

   public List<Item> getItems() {
      return items;
   }

   public HashMap<Monster, Direction> getMonsters() {
      return monsters;
   }

   public String getDescription() {
      return description;
   }
}
