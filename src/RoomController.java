import java.util.List;

public class RoomController {
   private Room startRoom;
   private int startRow;
   private int startColumn;
   private int startFloor;

   private Room currentRoom;
   private int currentRow;
   private int currentColumn;
   private int currentFloor;

   private Map map;
   private Room[][][] rooms;

   RoomController()
   {
      rooms = map.getMap();
   }

   private void setStartRoom(Room start)
   {
      startRoom = start;
      startRow = start.getRow();
      startColumn = start.getColumn();
      startFloor = start.getFloor();
   }

   private void setCurrentRoom(Room current)
   {
      currentRoom = current;
      currentRow = current.getRow();
      currentColumn = current.getColumn();
      currentFloor = current.getFloor();
   }

   public Room getStartRoom() {
      return startRoom;
   }

   public Room getCurrentRoom() {
      return currentRoom;
   }
}
