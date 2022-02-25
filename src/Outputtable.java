import java.util.List;
 /**
  * Outputtable is an interface for OutputGetter
  * */
public interface Outputtable {

    public void outputHelp();

    public void outputInventory(Player player);

    public void outputRoomDescription(Room room);

    public void outputPoints(Player player, int movementNumber);
}