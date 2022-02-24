import java.util.HashMap;

public class Monster {
   private String name;
   private boolean defeat;

   Monster(String name)
   {
      this.name = name;
      this.defeat = false;
   }

   public String getName() {
      return name;
   }

   public boolean getDefeat() {
      return defeat;
   }


}
