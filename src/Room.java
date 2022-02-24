import java.util.HashMap;
import java.util.List;

public class Room {
   private HashMap<Direction, Boolean> directions;
   private List<Item> items;
   private HashMap<Monster, Direction> monsters;

   Room(HashMap<Direction, Boolean> directions, List<Item> items,
        HashMap<Monster, Direction> monsters, String description)
   {
      this.items = items;
   }

   public void setOpenDirections() {
      for(Monster monster : monsters) {
         String passage = (String) monster.getBlocking().get(new Room());
      }
   }


}
