
import Items.Item;
import javafx.scene.control.skin.TextInputControlSkin;

import java.util.List;

/**
 * Output Getter implements the outputtable interface to output all the results and such of the battle pets game
 * */
public class OutputGetter implements Outputtable{
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

    public void outputQuitMessage(Player player, int movementNumber){

    }

    public void outputInventory(Player player){
        System.out.println("This is your list of items: ");
        for(Item item : player.getInventory()){
            System.out.println(item.getName());
        }
        System.out.println("\n");
    }

    public void outputRejectionMessage(){

    }

    public void outputRoomDescription(Room room)
    {
        System.out.println(room.getDescription());
        for(Item item: room.getItems()){
            System.out.println("A " + item.getName() + " is in this room. ");
        }
        room.getMonsters().forEach((k, v) -> System.out.println(v.getName() + " is blocking " + k + " passage."));

        for(Commands direction : room.getDirections().keySet()){
            if(room.getDirections().get(direction)){
                System.out.println("There is a passage " + direction);
            }
        }
    }

    public void outputPoints(Player player, int movementNumber){}


}