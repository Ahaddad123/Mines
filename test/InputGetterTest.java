import Items.Monster;
import Items.Treasure;
import Items.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputGetterTest{
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
    public void testInputNorth{


        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.NORTH, CLOSED);
        currentRoom.getMonsters().put(Commands.NORTH, new Monster(""));
        adjacentRoom.getDirections().put(Commands.SOUTH, CLOSED);
        adjacentRoom.getMonsters().put(Commands.SOUTH, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "n");

        Assertions.assertFalse(commands);

    }

    @Test
    public void testInputSouth{

        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.NORTH, CLOSED);
        currentRoom.getMonsters().put(Commands.NORTH, new Monster(""));
        adjacentRoom.getDirections().put(Commands.SOUTH, CLOSED);
        adjacentRoom.getMonsters().put(Commands.SOUTH, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "s");

        Assertions.assertFalse(commands);
    }

    @Test
    public void testInputEast{

        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.EAST, CLOSED);
        currentRoom.getMonsters().put(Commands.EAST, new Monster(""));
        adjacentRoom.getDirections().put(Commands.WEST, CLOSED);
        adjacentRoom.getMonsters().put(Commands.WEST, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "e");

        Assertions.assertFalse(commands);
    }

    @Test
    public void testInputWest{

        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.WEST, CLOSED);
        currentRoom.getMonsters().put(Commands.WEST, new Monster(""));
        adjacentRoom.getDirections().put(Commands.EAST, CLOSED);
        adjacentRoom.getMonsters().put(Commands.EAST, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "w");

        Assertions.assertFalse(commands);
    }

    @Test
    public void testInputUp{

        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.UP, CLOSED);
        currentRoom.getMonsters().put(Commands.UP, new Monster(""));
        adjacentRoom.getDirections().put(Commands.DOWN, CLOSED);
        adjacentRoom.getMonsters().put(Commands.DOWN, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "u");

        Assertions.assertFalse(commands);
    }

    @Test
    public void testInputDown{

        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        Room adjacentRoom = map.getMap()[player.getXIndex()][player.getYIndex() - 1][player.getZIndex()];
        currentRoom.getDirections().put(Commands.DOWN, CLOSED);
        currentRoom.getMonsters().put(Commands.DOWN, new Monster(""));
        adjacentRoom.getDirections().put(Commands.UP, CLOSED);
        adjacentRoom.getMonsters().put(Commands.UP, new Monster(""));

        Commands commands = inputtable.inputCommand(player, "d");

        Assertions.assertFalse(commands);
    }


}