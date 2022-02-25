import Items.Weapon;

import java.util.Scanner;


/**
 * Input Getter implements the inputtable interface to output all the results and such of the battle pets game
 * */
public class InputGetter implements Inputtable {
    Scanner read = new Scanner(System.in);

    public Commands inputCommand(Player player){
        String command;
        System.out.println("Command? ");
        boolean valid = false;

        command = read.nextLine();
        switch(command){
            case "n":
                if(!player.getLocation().getDirections().get(Commands.NORTH)){
                    if(player.getLocation().getMonsters().containsKey(Commands.NORTH)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster() == player.getLocation().getMonsters().get(Commands.NORTH)){
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
                } else {
                    valid = true;
                    command = "NORTH";
                }
                break;
            case "s":
                if(!player.getLocation().getDirections().get(Commands.SOUTH)){
                    if(player.getLocation().getMonsters().containsKey(Commands.SOUTH)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster() == player.getLocation().getMonsters().get(Commands.SOUTH)){
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
                } else {
                    valid = true;
                    command = "SOUTH";
                }
                break;
            case "e":
                if(!player.getLocation().getDirections().get(Commands.EAST)){
                    if(player.getLocation().getMonsters().containsKey(Commands.EAST)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster() == player.getLocation().getMonsters().get(Commands.EAST)){
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
                } else {
                    valid = true;
                    command = "EAST";
                }
                break;
            case "w":
                if(!player.getLocation().getDirections().get(Commands.WEST)){
                    if(player.getLocation().getMonsters().containsKey(Commands.WEST)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster() == player.getLocation().getMonsters().get(Commands.WEST)){
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
                } else {
                    valid = true;
                    command = "WEST";
                }
                break;
            case "u":
                // Not used yet
                valid = false;
                command = "UP";
                break;
            case "d":
                // Not used yet
                valid = false;
                command = "DOWN";
                break;
            case "h":
                valid = true;
                command = "HELP";
                break;
            case "i":
                valid = true;
                command = "INVENTORY";
                break;
            case "c":
                valid = true;
                command = "CARRY";
                break;
            case "l":
                valid = true;
                command = "LEAVE";
                break;
            case "o":
                valid = true;
                command = "OUT";
                break;
            case "q":
                valid = true;
                command = "QUIT";
                break;
            default:
                valid = false;
                command = "INVALID";
                break;
        }

        if(valid)
            return Commands.valueOf(command);
        else
            return Commands.INVALID;
    }
}