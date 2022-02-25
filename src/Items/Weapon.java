package Items;

public class Weapon {
    // variables
    String name;
    String monster;

    /**
     * Returns the name of the weapon.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the monster associated with the weapon.
     * @return
     */
    public String getMonster() {
        return monster;
    }

    /**
     * Constructor for weapon, adds the name and monster associated with it.
     * @param name
     */
    public Weapon(String name, String monster) {
        this.name = name;
    }
}
