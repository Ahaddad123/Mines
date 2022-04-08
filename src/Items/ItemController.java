package Items;

import java.util.ArrayList;

public class ItemController {

    public ItemController() {

    }

    /**
     * Adds all the various items that are in the game into an array list.
     */
    public ArrayList createTreasures() {
        // creates the treasures
        ArrayList<Item> treasures = new ArrayList<Item>();
        treasures.add(new Treasure("a Magical Unicorn"));
        treasures.add(new Treasure("Doug's Scooter"));
        treasures.add(new Treasure("a Code Converter Online"));
        treasures.add(new Treasure("Snack Money"));
        treasures.add(new Treasure("a Computer Charger"));
        treasures.add(new Treasure("a Wireless Mouse"));
        treasures.add(new Treasure("an Extended Deadline"));
        treasures.add(new Treasure("a day off"));
        treasures.add(new Treasure("a Correct Build"));
        treasures.add(new Treasure("passed unit tests"));
        treasures.add(new Treasure("a Project Turned in on Time"));
        treasures.add(new Treasure("a Team Celebration"));
        treasures.add(new Treasure("a Fully Fuctioning Code"));
        treasures.add(new Treasure("Learning a new Language"));
        treasures.add(new Treasure("No Merge Conflicts"));
        treasures.add(new Treasure("Finished Issues"));
        treasures.add(new Treasure("an Included Library"));
        treasures.add(new Treasure("Solitare"));
        treasures.add(new Treasure("Doug's Flashdrive"));
        treasures.add(new Treasure("Agreeable Teammates"));
        treasures.add(new Treasure("Duke Nukem"));
        treasures.add(new Treasure("No Final Exam"));
        treasures.add(new Treasure("a Passing Grade"));
        treasures.add(new Treasure("a Completed Project"));

        return treasures;
    }

    /**
     * Adds all the various monsters that are in the game into an array list.
     */
    public ArrayList createWeapons() {
        // creates the weapons
        ArrayList<Item> weapons = new ArrayList<Item>();
        weapons.add(new Weapon("Copy & Paste", new Monster("Code in Notepad")));
        weapons.add(new Weapon("Semicolon", new Monster("Missing Semicolon")));
        weapons.add(new Weapon("Missing Letter", new Monster("Spelling Mistake")));
        weapons.add(new Weapon("Correct Operator", new Monster("Wrong Operator")));
        weapons.add(new Weapon("Constant Declaration", new Monster("Magic Number")));
        weapons.add(new Weapon("Correct Variable Type", new Monster("Wrong Variable Type")));
        weapons.add(new Weapon("New Method", new Monster("Missing Method")));
        weapons.add(new Weapon("Compiler", new Monster("Failed Build")));
        weapons.add(new Weapon("Lower Case First Letter", new Monster("Bad Naming Convention")));
        weapons.add(new Weapon("Bracket", new Monster("Missing Bracket")));
        weapons.add(new Weapon("Larger Bound", new Monster("Out of Bounds Index")));
        weapons.add(new Weapon("Terminating condition", new Monster("Infinite Loop")));
        weapons.add(new Weapon("Different Name", new Monster("Duplicate Variable")));
        weapons.add(new Weapon("Variable Declaration", new Monster("Undefined Variable")));
        weapons.add(new Weapon("Energy Drink", new Monster("Lack of Energy")));
        weapons.add(new Weapon("Alarm Clock", new Monster("Sleeping Coder")));
        weapons.add(new Weapon("Extra Parameter", new Monster("Lack of Parameters")));
        weapons.add(new Weapon("Trip to the Library", new Monster("Broken Computer")));
        weapons.add(new Weapon("Update Download", new Monster("Old Version of IDE")));
        weapons.add(new Weapon("File in the Trash Can", new Monster("Lost Code")));
        weapons.add(new Weapon("Pull and Merge Request", new Monster("Failed Push")));
        weapons.add(new Weapon("Team Meeting", new Monster("Brain Block")));
        weapons.add(new Weapon("Google Link", new Monster("Unknown Error")));
        weapons.add(new Weapon("Dinosaur Game", new Monster("404 Error")));

        return weapons;
    }

    /**
     * Adds all the various monsters that are in the game into an array list.
     */
    public ArrayList createMonster() {
        // creates the monsters
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        monsters.add(new Monster("Code in Notepad"));
        monsters.add(new Monster("Missing Semicolon"));
        monsters.add(new Monster("Spelling Mistake"));
        monsters.add(new Monster("Wrong Operator"));
        monsters.add(new Monster("Magic Number"));
        monsters.add(new Monster("Wrong Variable Type"));
        monsters.add(new Monster("Missing Method"));
        monsters.add(new Monster("Failed Build"));
        monsters.add(new Monster("Bad Naming Convention"));
        monsters.add(new Monster("Missing Bracket"));
        monsters.add(new Monster("Out of Bounds Index"));
        monsters.add(new Monster("Infinite Loop"));
        monsters.add(new Monster("Duplicate Variable"));
        monsters.add(new Monster("Undefined Variable"));
        monsters.add(new Monster("Lack of Energy"));
        monsters.add(new Monster("Sleeping Coder"));
        monsters.add(new Monster("Lack of Parameters"));
        monsters.add(new Monster("Broken Computer"));
        monsters.add(new Monster("Old Version of IDE"));
        monsters.add(new Monster("Lost Code"));
        monsters.add(new Monster("Failed Push"));
        monsters.add(new Monster("Brain Block"));
        monsters.add(new Monster("Unknown Error"));
        monsters.add(new Monster("404 Error"));

        return monsters;
    }

    /**
     * Adds all the various room descriptions that are in the game into an array list.
     */
    public ArrayList<String> createRoomDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        //computer parts
        descriptions.add("Video Card");
        descriptions.add("Power Supply");
        descriptions.add("Motherboard");
        descriptions.add("RAM");
        descriptions.add("CPU");
        descriptions.add("Hard Drive");
        descriptions.add("Solid-State Drive");
        descriptions.add("Optical Disk Drive");
        descriptions.add("Monitor");
        descriptions.add("Keyboard");
        descriptions.add("Mouse");
        descriptions.add("Flash Drive");
        descriptions.add("Printer");
        descriptions.add("Speakers");
        descriptions.add("Floppy Disk");
        descriptions.add("Webcam");
        descriptions.add("Microphone");
        descriptions.add("Modem");
        descriptions.add("Router");
        descriptions.add("Network Switch");
        descriptions.add("Firewall");
        descriptions.add("Fan");
        descriptions.add("Battery");
        descriptions.add("Graphics Card");
        descriptions.add("Operating System");
        //programming languages
        descriptions.add("C#");
        descriptions.add("C++");
        descriptions.add("COBOL");
        descriptions.add("DCL");
        descriptions.add("Emerald");
        descriptions.add("Java");
        descriptions.add("JavaFX");
        descriptions.add("JavaScript");
        descriptions.add("Machine Code");
        descriptions.add("MATLAB");
        descriptions.add("Pascal");
        descriptions.add("Prolog");
        descriptions.add("Python");
        descriptions.add("RPG");
        descriptions.add("Rust");
        descriptions.add("Scratch");
        descriptions.add("SQL");
        descriptions.add("TypeScript");
        descriptions.add("UNITY");
        descriptions.add("Viper");
        descriptions.add("WebAssembly");
        descriptions.add("ActionScript");
        descriptions.add("AngelScript");
        descriptions.add("Assembly");
        descriptions.add("BASIC");
        //other computer related things
        descriptions.add("Windows");
        descriptions.add("Mac");
        descriptions.add("Android");
        descriptions.add("Ubuntu");
        descriptions.add("iOS");
        descriptions.add("Blackberry");
        descriptions.add("Playstation");
        descriptions.add("Xbox");
        descriptions.add("Nintendo");
        descriptions.add("Chrome");
        descriptions.add("Photoshop");
        descriptions.add("Facebook");
        descriptions.add("Netflix");
        descriptions.add("Firmware");
        descriptions.add("Utility Programs");
        descriptions.add("Device Drivers");
        descriptions.add("Malware");
        descriptions.add("Spyware");
        descriptions.add("Adobe");
        descriptions.add("Notepad++");
        descriptions.add("Git Bash");
        descriptions.add("Discord");
        descriptions.add("Zoom");
        descriptions.add("Microsoft Solitaire");
        descriptions.add("File Explorer");
        return descriptions;
    }
}
