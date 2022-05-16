import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {
    private final Player player = new Player(new Room(1, 1, 1), 1, 1, 1);

    @Test
    public void testMove_NORTH() {
        player.move(Commands.NORTH, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(0, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void testMove_SOUTH() {
        player.move(Commands.SOUTH, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(2, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void testMove_EAST() {
        player.move(Commands.EAST, new Map());
        Assertions.assertEquals(2, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void testMove_WEST() {
        player.move(Commands.WEST, new Map());
        Assertions.assertEquals(0, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void testMove_UP() {
        player.move(Commands.UP, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(2, player.getZIndex());
    }

    @Test
    public void testMove_DOWN() {
        player.move(Commands.DOWN, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(0, player.getZIndex());
    }

    /**
     * Tests if a room is added to the visited list once the player enter
     * that room.
     */
    @Test
    public void testAddVisitedRoom(){
        Room testRoom = new Room(-1, -1, -1);
        player.addVisitedRoom(testRoom);
        List<Room> visited = player.getRoomsVisited();
        Assertions.assertEquals(-1, visited.get(1).getRow());
        Assertions.assertEquals(-1, visited.get(1).getColumn());
        Assertions.assertEquals(-1, visited.get(1).getFloor());
    }
}
