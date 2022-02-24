import java.util.List;

/**
 * Holds information about starting room and current room
 * @author Jasmine Krahn
 */
public class RoomController {
   // starting room information
   private Room startRoom;
   private int startRow;
   private int startColumn;
   private int startFloor;

   // current room information
   private Room currentRoom;
   private int currentRow;
   private int currentColumn;
   private int currentFloor;

   // map information
   private Map map;
   private Room[][][] rooms;

   /**
    * Constructor - make sure its the same reference map
    */
   RoomController()
   {
      rooms = map.getMap();
   }

   /**
    * sets start to the room the player is started in
    * @param start room player started in
    */
   private void setStartRoom(Room start)
   {
      startRoom = start;
      startRow = start.getRow();
      startColumn = start.getColumn();
      startFloor = start.getFloor();
   }

   /**
    * sets current to the room the player is currently in
    * @param current current room player is in
    */
   private void setCurrentRoom(Room current)
   {
      currentRoom = current;
      currentRow = current.getRow();
      currentColumn = current.getColumn();
      currentFloor = current.getFloor();
   }

   /**
    * get start
    * @return starting room
    */
   public Room getStartRoom() {
      return startRoom;
   }

   /**
    * get current
    * @return current room
    */
   public Room getCurrentRoom() {
      return currentRoom;
   }
}
