import Items.Monster;
import Items.Treasure;
import Items.Weapon;
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

    @Test
    public void testRemoveWeapon() {
        Player player = gameController.getPlayer();
        String monsterName = "testMonsterName";
        Weapon testWeapon = new Weapon("", new Monster(monsterName));
        player.getLocation().getMonsters().put(Commands.NORTH, new Monster(monsterName));
        player.getInventory().add(testWeapon);

        gameController.removeWeapon(Commands.NORTH);

        Assertions.assertFalse(player.getInventory().contains(testWeapon));
    }

    @Test
    public void testLeaveTreasures() {
        Player player = gameController.getPlayer();
        Treasure treasure1 = new Treasure("name1");
        Treasure treasure2 = new Treasure("name2");
        Treasure treasure3 = new Treasure("name3");
        Weapon weapon = new Weapon("weapon", new Monster(""));
        player.getInventory().add(treasure1);
        player.getInventory().add(treasure2);
        player.getInventory().add(treasure3);
        player.getInventory().add(weapon);

        gameController.leaveTreasures();

        Assertions.assertTrue(player.getLocation().getItems().contains(treasure1));
        Assertions.assertTrue(player.getLocation().getItems().contains(treasure2));
        Assertions.assertTrue(player.getLocation().getItems().contains(treasure3));
        Assertions.assertFalse(player.getLocation().getItems().contains(weapon));

        Assertions.assertFalse(player.getInventory().contains(treasure1));
        Assertions.assertFalse(player.getInventory().contains(treasure2));
        Assertions.assertFalse(player.getInventory().contains(treasure3));
        Assertions.assertTrue(player.getInventory().contains(weapon));
    }

    @Test
    public void testGetDirection_NORTH() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(1, 0, 1);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("N", actual);
    }

    @Test
    public void testGetDirection_SOUTH() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(1, 2, 1);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("S", actual);
    }

    @Test
    public void testGetDirection_EAST() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(2, 1, 1);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("E", actual);
    }

    @Test
    public void testGetDirection_WEST() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(0, 1, 1);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("W", actual);
    }

    @Test
    public void testGetDirection_UP() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(1, 1, 2);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("U", actual);
    }

    @Test
    public void testGetDirection_DOWN() {
        Room start = new Room(1, 1, 1);
        Room finish = new Room(1, 1, 0);

        String actual = gameController.getDirection(start, finish);

        Assertions.assertEquals("D", actual);
    }

    @Test
    public void testHandleCommand_NORTH(){
        Player player = gameController.getPlayer();
        testCommand = NORTH;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(0, player.yIndex);
    }

    @Test
    public void testHandleCommand_SOUTH(){
        Player player = gameController.getPlayer();
        testCommand = SOUTH;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(2, player.yIndex);
    }

    @Test
    public void testHandleCommand_EAST(){
        Player player = gameController.getPlayer();
        testCommand = EAST;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(2, player.xIndex);
    }

    @Test
    public void testHandleCommand_WEST(){
        Player player = gameController.getPlayer();
        testCommand = WEST;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(0, player.xIndex);
    }

    @Test
    public void testHandleCommand_UP(){
        Player player = gameController.getPlayer();
        testCommand = UP;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(2, player.zIndex);
    }

    @Test
    public void testHandleCommand_DOWN(){
        Player player = gameController.getPlayer();
        testCommand = DOWN;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(0, player.zIndex);
    }

    @Test
    public void testHandleCommand_CARRY(){
        List<Item> testItems;
        testItems.add(new Item("testItem"));
        Room testRoom = new Room(null, testItems, null, 1, 1, 1);
        Player testPlayer = new Player(testRoom, 1, 1 ,1)
        Player player = gameController.getPlayer();
        testCommand = CARRY;
        gameController.handleCommand(testCommand);
        Assertions.assertEquals(1, player.getTreasuresCarry());
        Assertions.assertEquals("testItem", player.inventory[0]);
    }

    @Test
    public void testHandleCommand_LEAVE_TREASURERS(){
        Player player = gameController.getPlayer();
        player.getInventory().add(treasure1);
        player.getInventory().add(treasure2);
        player.getInventory().add(treasure3);
        testCommand = LEAVE_TREASURERS;
        gameController.handleCommand(testCommand);
        Assertions.assertFalse(player.getInventory().contains(treasure1));
        Assertions.assertFalse(player.getInventory().contains(treasure2));
        Assertions.assertFalse(player.getInventory().contains(treasure3));
    }

    @Test
    public void testHandleCommand_OUT(){
        Player player = gameController.getPlayer();
        player.getInventory().add(treasure1);
        player.getInventory().add(treasure2);
        player.getInventory().add(treasure3);
        testCommand = OUT;
        gameController.handleCommand(testCommand);
        Assertions.assertFalse(player.getInventory().contains(treasure1));
        Assertions.assertFalse(player.getInventory().contains(treasure2));
        Assertions.assertFalse(player.getInventory().contains(treasure3));
    }

    @Test
    public void testHandleCommand_QUIT(){
        Player player = gameController.getPlayer();
        testCommand = QUIT;
        gameController.handleCommand(testCommand);
        Assertions.assertTrue(gameController.quit);
    }
}
