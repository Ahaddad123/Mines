package Items;

import java.util.ArrayList;

public class ItemController {

    //variables
    Map weaponsAndMonsters = new map;

    public void itemController() {

    }

    public boolean killMonsters(Room location, Player player) {
        return true;
    }

    /**
     * Adds all the various items that are in the game into an array list.
     */
    public void createItems() {
        // creates the treasures
        ArrayList<String> treasures = new ArrayList<String>();
        treasures.add("a Magical Unicorn");
        treasures.add("Doug's Scooter");
        treasures.add("a Code Converter Online");
        treasures.add("Snack Money");
        treasures.add("a Computer Charger");
        treasures.add("a Wireless Mouse");
        treasures.add("an Extended Deadline");
        treasures.add("a day off");
        treasures.add("a Correct Build");
        treasures.add("passed unit tests");
        treasures.add("a Project Turned in on Time");
        treasures.add("a Team Celebration");
        treasures.add("a Fully Fuctioning Code");
        treasures.add("Learning a new Language");
        treasures.add("No Merge Conflicts");
        treasures.add("Finished Issues");
        treasures.add("an Included Library");
        treasures.add("Solitare");
        treasures.add("Doug's Flashdrive");
        treasures.add("Agreeable Teammates");
        treasures.add("Duke Nukem");
        treasures.add("No Final Exam");
        treasures.add("a Passing Grade");
        treasures.add("a Completed Project");

        // creates the weapons
        ArrayList<String> weapons = new ArrayList<String>();
        weapons.add("Copy & Paste");
        weapons.add("Semicolon");
        weapons.add("Missing Letter");
        weapons.add("Correct Operator");
        weapons.add("Constant Declaration");
        weapons.add("Correct Variable Type");
        weapons.add("New Method");
        weapons.add("Compiler");
        weapons.add("Lower Case First Letter");
        weapons.add("Bracket");
        weapons.add("Larger Bound");
        weapons.add("Terminating condition");
        weapons.add("Different Name");
        weapons.add("Variable Declaration");
        weapons.add("Energy Drink");
        weapons.add("Alarm Clock");
        weapons.add("Extra Parameter");
        weapons.add("Trip to the Library");
        weapons.add("Update Download");
        weapons.add("File in the Trash Can");
        weapons.add("Pull and Merge Request");
        weapons.add("Team Meeting");
        weapons.add("Google Link");
        weapons.add("Dinosaur Game");
    }

    /**
     * Adds all the various monsters that are in the game into an array list.
     */
    public void createMonster() {
        // creates the monsters
        ArrayList<String> monsters = new ArrayList<String>();
        monsters.add("Code in Notepad");
        monstaers.add("Missing Semicolon");
        2. Spelling Mistake		Missing Letter
        3. Wrong Operator		Correct Operator
        4. Magic Number			Constant Declaration
        5. Wrong Variable Type		Correct Variable Type
        6. Missing Method		New Method
        7. Failed Build			Compiler
        8. Bad Naming Convention	Lower Case First Letter
        9. Missing Bracket		Bracket
        10. Out of Bounds Index		Larger Bound
        11. Infinite Loop		Terminating condition
        12. Duplicate Variable		Different Name
        13. Undefined Variable		Variable Declaration
        14. Lack of Energy		Energy Drink
        15. Sleeping Coder		Alarm Clock
        16. Lack of Parameters		Extra Parameter
        17. Broken Computer		Trip to the Library
        18. Old Version of IDE		Update Download
        19. Lost Code			File in the Trash Can
        20. Failed Push			Pull and Merge Request
        21. Brain Block			Team Meeting
        22. Unknown Error		Google Link
        23. 404 Error			Dinosaur Game
    }

    public Map getWeaponsAndMonsters() {
        return weaponsAndMonsters;
    }

    public void setWeaponsAndMonsters(Map weaponsAndMonsters) {
        this.weaponsAndMonsters = weaponsAndMonsters;
    }
}
