import Items.Item;
import Items.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class OutputTest {
    /**
     * This test creates a fake game scenario and tests if the points are correct
     */
    @Test
    public void testOutputPoints() {
        // creates fake test treasures that have been returned
        OutputGetter outputGetter = new OutputGetter();
        List<Item> testTreasures = new ArrayList<>();
        testTreasures.add(new Treasure("testTreasure1"));
        testTreasures.add(new Treasure("testTreasure2"));
        testTreasures.add(new Treasure("testTreasure3"));
        testTreasures.add(new Treasure("testTreasure4"));
        testTreasures.add(new Treasure("testTreasure5"));
        testTreasures.add(new Treasure("testTreasure6"));
        Room entrance = new Room(null, testTreasures, null,0, 0, 0);

        // creates a player with some dummy info
        Player player = new Player(entrance, 0, 0, 0);
        player.setMoveCount(32);

        // creates a list of rooms that have been visited
        List<Room> testVisited = new ArrayList<>();
        testVisited.add(new Room(1,1,1));
        testVisited.add(new Room(1,2,1));
        testVisited.add(new Room(1,3,1));
        testVisited.add(new Room(2,1,1));
        testVisited.add(new Room(2,2,1));
        testVisited.add(new Room(2,3,1));
        testVisited.add(new Room(3,1,1));
        testVisited.add(new Room(3,2,1));
        testVisited.add(new Room(3,3,1));
        testVisited.add(new Room(1,1,2));
        testVisited.add(new Room(1,2,2));
        testVisited.add(new Room(1,3,2));
        testVisited.add(new Room(2,1,2));
        player.setRoomsVisited(testVisited);

        // adds treasures that the player is currently carrying
        testTreasures.add(new Treasure("testTreasure7"));
        testTreasures.add(new Treasure("testTreasure8"));
        testTreasures.add(new Treasure("testTreasure9"));
        player.setInventory(testTreasures);

        // calls the test
        outputGetter.outputPoints(player, entrance);
        Assertions.assertEquals(48, outputGetter.getPoints());
    }

    /**
     * This tests the getTreasuresReturned function and checks that all the treasures
     * at the entrance are being accounted for.
     */
    @Test
    public void testGetTreasuresReturned() {
        // creating the variables
        OutputGetter outputGetter = new OutputGetter();
        List<Item> testTreasures = new ArrayList<>();

        // adding some dummy treasures
        testTreasures.add(new Treasure("testTreasure1"));
        testTreasures.add(new Treasure("testTreasure2"));
        testTreasures.add(new Treasure("testTreasure3"));
        testTreasures.add(new Treasure("testTreasure4"));

        // creating the room entrance
        Room entrance = new Room(null, testTreasures, null,0, 0, 0);

        // calling the test
        Assertions.assertEquals(4, outputGetter.getTreasuresReturned(entrance));
    }
}
