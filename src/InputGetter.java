import java.util.List;
import java.util.Scanner;


/**
 * Input Getter implements the inputtable interface to output all the results and such of the battle pets game
 * */
public class InputGetter implements Inputtable {
    Scanner read = new Scanner(System.in);

    public commands inputCommand(Player player){
        String command;
        System.out.println("Command? ");
        boolean valid = false;
        Room room = new Room();

        command = read.nextLine();
        switch(command){
            case 'n':
                if(player.getLocation().getDirection().get(Direction.NORTH) == false){
                    if(player.getMonsters().contains(Direction.NORTH) == true){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            if(player.getWeapons().get(i).getMonster() == player.getMonsters().get(Direction.NORTH)){
                                valid = true;
                                command = "NORTH";
                            }
                            else{
                                valid = false;
                                command = "INVALID";
                            }
                        }
                    }
                    else{
                        command = "INVALID";
                        valid = false;
                    }
                }
            case 's':
                if(player.getLocation().getDirection().get(Direction.SOUTH) == false){
                    if(player.getMonsters().contains(Direction.SOUTH) == true){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            if(player.getWeapons().get(i).getMonster() == player.getMonsters().get(Direction.SOUTH)){
                                valid = true;
                                command = "SOUTH";
                            }
                            else{
                                command = "INVALID";
                                valid = false;
                            }
                        }
                    }
                    else{
                        command = "INVALID";
                        valid = false;
                    }
                }
            case 'e':
                if(player.getLocation().getDirection().get(Direction.EAST) == false){
                    if(player.getMonsters().contains(Direction.EAST) == true){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            if(player.getWeapons().get(i).getMonster() == player.getMonsters().get(Direction.EAST)){
                                valid = true;
                                command = "EAST";
                            }
                            else{
                                valid = false;
                                command = "INVALID";
                            }
                        }
                    }
                    else{
                        command = "INVALID";
                        valid = false;
                    }
                }
            case 'w':
                if(player.getLocation().getDirection().get(Direction.WEST) == false){
                    if(player.getMonsters().contains(Direction.WEST)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            if(player.getWeapons().get(i).getMonster() == player.getMonsters().get(Direction.WEST)){
                                valid = true;
                                command = "WEST";
                            }
                            else {
                                command = "INVALID";
                                valid = false;
                            }
                        }
                    }
                    else{
                        command = "INVALID";
                        valid = false;
                    }
                }
            case 'u':
                // Not used yet
                valid = false;
                command = "UP";
            case 'd':
                // Not used yet
                valid = false;
                command = "DOWN";
            case 'h':
                valid = true;
                command = "HELP";
            case 'i':
                valid = true;
                command = "INVENTORY";
            case 'c':
                valid = true;
                command = "CARRY";
            case 'l':
                valid = true;
                command = "LEAVE";
            case 'o':
                valid = true;
                command = "OUT";
            case 'q':
                valid = true;
                command = "QUIT";
        }

        if(valid)
            return commands.valueOf(command);
        else
            return commands.INVALID;
    }
}