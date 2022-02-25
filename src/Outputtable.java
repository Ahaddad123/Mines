import java.util.List;

public interface Outputtable {
    public void outputHelp();
    public void outputQuitMessage(Player player, int movementNumber);
    public void outputInventory();
    public void outputRejectionMessage();
    public void outputRoomDescription(Room room);
    public void outputPoints(Player player, int movementNumber);
}