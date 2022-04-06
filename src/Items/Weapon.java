package Items;

public class Weapon extends Item{
    Monster monster;

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
        super(name);
        this.monster = monster;
    }
}