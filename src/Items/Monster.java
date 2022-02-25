package Items;

public class Monster {
    // variables
    String name;

    /**
     * Returns the name of the monster.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor for monster, adds the name
     * @param name
     */
    public Monster(String name) {
        this.name = name;
    }
}