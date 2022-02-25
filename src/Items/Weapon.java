package Items;

public class Weapon extends Item{
    // variables
    String name;
    Monster monster;

    /**
     * Returns the name of the weapon.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the monster object associated with the weapon.
     * @return
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     * Constructor for weapon, adds the name and monster associated with it.
     * @param name
     */
    public Weapon(String name, Monster monster) {
        this.name = name;
    }
}