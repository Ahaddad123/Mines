import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private final Player player = new Player(new Room(1, 1, 1), 1, 1, 1);

    @Test
    public void move_NORTH() {
        player.move(Commands.NORTH, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(0, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void move_SOUTH() {
        player.move(Commands.SOUTH, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(2, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void move_EAST() {
        player.move(Commands.EAST, new Map());
        Assertions.assertEquals(2, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void move_WEST() {
        player.move(Commands.WEST, new Map());
        Assertions.assertEquals(0, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(1, player.getZIndex());
    }

    @Test
    public void move_UP() {
        player.move(Commands.UP, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(2, player.getZIndex());
    }

    @Test
    public void move_DOWN() {
        player.move(Commands.DOWN, new Map());
        Assertions.assertEquals(1, player.getXIndex());
        Assertions.assertEquals(1, player.getYIndex());
        Assertions.assertEquals(0, player.getZIndex());
    }
}
