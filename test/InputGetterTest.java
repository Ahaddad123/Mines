import Items.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;

class InputGetterTest {

    private final Map map = new Map();
    private final GameController gameController = new GameController();
    private final int CLOSED = 100;
    private final int OPEN = 1;
    private final InputGetter inputGetter = new InputGetter();

    //---------------------------------------------------------------------
    // Sets up the map and the player for each test
    // v1: Created the test condition - Nathan S, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gameController.setMap(map);
        gameController.setPlayer(new Player(map.getMap()[1][1][1], 1, 1 ,1));
    }

    //---------------------------------------------------------------------
    // Tests if the input North is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputNorthInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.NORTH, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "n");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input South is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputSouthInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.SOUTH, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "s");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input West is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputWestInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.WEST, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "w");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input East is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputEastInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.EAST, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "e");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input Up is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputUpInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.UP, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "u");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input down is valid or invalid, should be invalid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputDownInvalid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.DOWN, CLOSED);

        Commands commands = inputGetter.inputCommand(player, "d");

        Assertions.assertEquals(Commands.INVALID, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input North is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputNorthValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.NORTH, OPEN);

        Commands commands = inputGetter.inputCommand(player, "n");

        Assertions.assertEquals(Commands.NORTH, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input South is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputSouthValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.SOUTH, OPEN);

        Commands commands = inputGetter.inputCommand(player, "s");

        Assertions.assertEquals(Commands.SOUTH, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input West is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputWestValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.WEST, OPEN);

        Commands commands = inputGetter.inputCommand(player, "w");

        Assertions.assertEquals(Commands.WEST, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input East is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputEastValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.EAST, OPEN);

        Commands commands = inputGetter.inputCommand(player, "e");

        Assertions.assertEquals(Commands.EAST, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input Up is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputUpValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.UP, OPEN);

        Commands commands = inputGetter.inputCommand(player, "u");

        Assertions.assertEquals(Commands.UP, commands);
    }

    //---------------------------------------------------------------------
    // Tests if the input Down is valid or invalid, should be valid in this case
    // v1: Created the test condition - Andrew H, 4-22-22
    //---------------------------------------------------------------------
    @org.junit.jupiter.api.Test
    public void testInputDownValid(){
        Player player = gameController.getPlayer();
        Room currentRoom = player.getLocation();
        currentRoom.getDirections().put(Commands.DOWN, OPEN);

        Commands commands = inputGetter.inputCommand(player, "d");

        Assertions.assertEquals(Commands.DOWN, commands);
    }
}
