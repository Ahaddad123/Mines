import Player;
import OutputGetter;
import Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutputGetterTest {

    @Test
    public void testOutputPoints() {
        Player player;
        Room entrance;
        player.setMoveCount(32);
        player.setRoomsVisited(18);

        Assertions.assertEquals(176, outputPoints(player, entrance));
    }

    @Test
    public void testGetTreasuresReturned() {

    }


}