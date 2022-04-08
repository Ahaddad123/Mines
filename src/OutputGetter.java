
import Items.Item;
import Items.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Getter implements the outputtable interface to output all the results and such of the battle pets game
 * */
public class OutputGetter implements Outputtable{
    private static final int NUMBER_OF_ROOMS = 75;

    /**
     * outputHelp outputs the help screen and all the commands necessary to play the game.
     * Commands currently not working: O, L, and P
     *
     * */
    public void outputHelp(){
        System.out.println("You asked for help\n");
        System.out.println("Commands in the game are \"N\", \"S\" \"E\", \"W\", \"U\", \"D\".\n");
        System.out.println("These take you North, South, East, West, Up, and Down.\n");
        System.out.println("Other commands are \"C\" to pick things up, \"I\" to view your inventory,\n");
        System.out.println(" \"L\" to leave your treasures, \"P\" to get the amount of points you've scored,\n");
        System.out.println(" \"O\" for help getting out of the mine, and finally, \"Q\" to quit.");
        System.out.println("In a mine passages are straight. So, for example, if you go North to leave\n");
        System.out.println("a room, you can go South to reenter the room. The rooms are not evenly spaced\n");
        System.out.println("However, the distance between adjacent rooms is always a multiple of the minimum.\n");
        System.out.println("distance between adjacent rooms.\n");
    }

    /**
     * outputInventory outputs the items in the users inventory
     *
     * @param player gives information on the player which includes their items
     * */
    public void outputInventory(Player player){
        System.out.println("This is your list of items: ");
        for(Item item : player.getInventory()){
            System.out.println(item.getName());
        }
        System.out.println("\n");
    }

    /**
     * outputRoomDescription outputs the description of the room the user has entered
     * It also displays any monsters blocking any passages in the room, and any passages that are
     * just open
     *
     * @param room gives information on the room and monsters in the room
     * */
    public void outputRoomDescription(Room room)
    {
        System.out.println(room.getDescription());
        for(Item item: room.getItems()){
            System.out.println("A " + item.getName() + " is in this room. ");
        }
        room.getMonsters().forEach((k, v) -> System.out.println(v.getName() + " is blocking " + k + " passage."));

        for(Commands direction : room.getDirections().keySet()){
            if(room.getDirections().get(direction) == 1){
                System.out.println("There is a passage " + direction);
            }
        }
    }

    /**
     * outputPoints outputs the treasures and points the player has accumulated
     * This is not implemented in this iteration
     *
     * @param player this gives the player information
     * */
    public void outputPoints(Player player, Room entrance){
        int points;
        int roomMultiplier = 25;
        int carryMultiplier = 45;
        int recovMultiplier = 75;
        int moves = player.getMoveCount();
        int rooms = player.getNumberRooms();
        int roomsVisited = player.getRoomsVisited();
        int totalTreasures = player.getNumTreasures();
        int treasuresCarried = player.getTreasuresCarry();
        int treasuresRecovered = getTreasuresReturned(entrance);

        points = (roomMultiplier * roomsVisited / (rooms + 1)) +
                 (recovMultiplier * treasuresRecovered / totalTreasures) +
                 (carryMultiplier * treasuresCarried / totalTreasures);

        if (moves > 5 * rooms) {
            points = points - moves / (5 * rooms);

            if (points < 0) {
                points = 0;
            }
        }
        System.out.println("You have moved " + moves + " times to visit " + roomsVisited + " of " + NUMBER_OF_ROOMS + " locations.");
        System.out.println("You hold " + treasuresCarried + " of " + totalTreasures + " treasures.");
        System.out.println("You have returned " + treasuresRecovered + " of " + totalTreasures + " treasures to the entrance of the mine");
        System.out.println("You have scored " + points + " points.");
    }

    /**
     * Outputs a confirmation that the user is about to quit the game.
     */
    public void outputQuit(){
        System.out.println("Are you sure you want to quit?\n");
        System.out.println("Y to confirm, N to continue playing.\n");

    }

    public void outputOut(String wayOut){
        System.out.println("The pirate takes one of your treasures. As he leaves, he shouts the letters: ");
        System.out.println(wayOut);
    }

    public void outputQuitMessage(Player player, Room entrance){
        outputPoints(player, entrance);
        System.out.println("Thanks for playing the game!");
    }

    private int getTreasuresReturned(Room entrance) {
        List<Item> treasures = new ArrayList<>();
        for (Item item : entrance.getItems()) {
            if (item instanceof Treasure) {
                treasures.add(item);
            }
        }
        return treasures.size();
    }
}