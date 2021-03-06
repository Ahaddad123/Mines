import Items.Weapon;

import java.util.Scanner;


/**
 * Input Getter implements the inputtable interface to take inputs for the game
 * */
public class InputGetter implements Inputtable {
    Scanner read = new Scanner(System.in);
    /**
     * inputCommand reads in the command and checks if the direction is valid, and if the command is valid.
     * If the direction is invalid the user is prompted to enter another direction, and if the command is
     * invalid the user is prompted to enter another command
     *
     * @param player this gives information on the user and their location on the map
     * */
    public Commands inputCommand(Player player, String command){
        System.out.println("Command? ");
        boolean valid = false;
        while(command == null){
            command = read.nextLine();
        }

        switch(command){
            case "n":
                if(player.getLocation().getDirections().get(Commands.NORTH) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.NORTH)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.NORTH).getName())){

                                valid = true;
                                command = "NORTH";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "NORTH";
                }
                break;
            case "s":
                if(player.getLocation().getDirections().get(Commands.SOUTH) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.SOUTH)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.SOUTH).getName())){
                                valid = true;
                                command = "SOUTH";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "SOUTH";
                }
                break;
            case "e":
                if(player.getLocation().getDirections().get(Commands.EAST) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.EAST)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.EAST).getName())){
                                valid = true;
                                command = "EAST";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "EAST";
                }
                break;
            case "w":
                if(player.getLocation().getDirections().get(Commands.WEST) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.WEST)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.WEST).getName())){
                                valid = true;
                                command = "WEST";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "WEST";
                }
                break;
            case "u":
                if(player.getLocation().getDirections().get(Commands.UP) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.UP)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.UP).getName())){
                                valid = true;
                                command = "UP";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "UP";
                }
                break;
            case "d":
                if(player.getLocation().getDirections().get(Commands.DOWN) == 100){
                    if(player.getLocation().getMonsters().containsKey(Commands.DOWN)){
                        for(int i = 0; i < player.getWeapons().size(); i++){
                            Weapon weapon = (Weapon)player.getWeapons().get(i);
                            if(weapon.getMonster().getName().equals(player.getLocation().getMonsters().get(Commands.DOWN).getName())){
                                valid = true;
                                command = "DOWN";
                            }
                        }
                        if (!valid) {
                            System.out.println("Passageway is blocked.");
                            command = "INVALID";
                        }
                    }
                    else{
                        System.out.println("You can't go that way.");
                        command = "INVALID";
                        valid = false;
                    }
                } else {
                    valid = true;
                    command = "DOWN";
                }
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
                command = "LEAVE_TREASURES";
                break;
            case "o":
                valid = true;
                command = "OUT";
                break;
            case "q":
                valid = true;
                command = "QUIT";
                break;
            case "p":
                valid = true;
                command = "POINTS";
                break;
            default:
                valid = false;
                command = "INVALID";
                break;
        }

        if(valid)
            return Commands.valueOf(command);
        else {
            System.out.println("Invalid input");
            return Commands.INVALID;
        }
    }

    public int inputRandomSeed(){
        int randomSeed = 0;
        boolean valid;
        do {
            valid = true;
            System.out.print("Enter a number for the random seed: ");
            String seed = read.nextLine();
            try{
                randomSeed = Integer.parseInt(seed);
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
                valid = false;
            }
        } while (!valid);

        return randomSeed;
    }
}