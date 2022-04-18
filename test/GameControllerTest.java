import Items.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {
    private final Map map = new Map();
    private final GameController gameController = new GameController();
    private final int CLOSED = 100;
    private final int OPEN = 1;

    @BeforeEach
    public void setup() {
        gameController.setMap(map);
        gameController.setPlayer(new Player(map.getMap()[1][1][1], 1, 1 ,1));
    }

    @Test
    public void testKillMonster_NORTH(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.NORTH, CLOSED);
        currentRoom.getMonsters().put(Commands.NORTH, new Monster(""));
        adjacentRoom.getDirections().put(Commands.SOUTH, CLOSED);
        adjacentRoom.getMonsters().put(Commands.SOUTH, new Monster(""));

        gameController.killMonster(Commands.NORTH);

        Assertions.assertFalse(currentRoom.getMonsters().containsKey(Commands.NORTH));
        Assertions.assertEquals(OPEN, currentRoom.getDirections().get(Commands.NORTH));
        Assertions.assertFalse(adjacentRoom.getMonsters().containsKey(Commands.SOUTH));
        Assertions.assertEquals(OPEN, adjacentRoom.getDirections().get(Commands.SOUTH));
    }

    @Test
    public void testKillMonster_SOUTH(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() + 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.SOUTH, CLOSED);
        currentRoom.getMonsters().put(Commands.SOUTH, new Monster(""));
        adjacentRoom.getDirections().put(Commands.NORTH, CLOSED);
        adjacentRoom.getMonsters().put(Commands.NORTH, new Monster(""));

        gameController.killMonster(Commands.SOUTH);

        Assertions.assertFalse(currentRoom.getMonsters().containsKey(Commands.SOUTH));
        Assertions.assertEquals(OPEN, currentRoom.getDirections().get(Commands.SOUTH));
        Assertions.assertFalse(adjacentRoom.getMonsters().containsKey(Commands.NORTH));
        Assertions.assertEquals(OPEN, adjacentRoom.getDirections().get(Commands.NORTH));
    }

    @Test
    public void testKillMonster_EAST(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex() + 1][player.getYIndex()][player.getZIndex()];
        currentRoom.getDirections().put(Commands.EAST, CLOSED);
        currentRoom.getMonsters().put(Commands.EAST, new Monster(""));
        adjacentRoom.getDirections().put(Commands.WEST, CLOSED);
        adjacentRoom.getMonsters().put(Commands.WEST, new Monster(""));

        gameController.killMonster(Commands.EAST);

        Assertions.assertFalse(currentRoom.getMonsters().containsKey(Commands.EAST));
        Assertions.assertEquals(OPEN, currentRoom.getDirections().get(Commands.EAST));
        Assertions.assertFalse(adjacentRoom.getMonsters().containsKey(Commands.WEST));
        Assertions.assertEquals(OPEN, adjacentRoom.getDirections().get(Commands.WEST));
    }

    @Test
    public void testKillMonster_WEST(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex() - 1][player.getYIndex()][player.getZIndex()];
        currentRoom.getDirections().put(Commands.WEST, CLOSED);
        currentRoom.getMonsters().put(Commands.WEST, new Monster(""));
        adjacentRoom.getDirections().put(Commands.EAST, CLOSED);
        adjacentRoom.getMonsters().put(Commands.EAST, new Monster(""));

        gameController.killMonster(Commands.WEST);

        Assertions.assertFalse(currentRoom.getMonsters().containsKey(Commands.WEST));
        Assertions.assertEquals(OPEN, currentRoom.getDirections().get(Commands.WEST));
        Assertions.assertFalse(adjacentRoom.getMonsters().containsKey(Commands.EAST));
        Assertions.assertEquals(OPEN, adjacentRoom.getDirections().get(Commands.EAST));
    }
}
