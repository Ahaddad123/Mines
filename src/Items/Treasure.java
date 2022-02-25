package Items;

public class Treasure {
    // variables
    String name;

    /**
     * Returns the name of the treasure.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor for treasure, adds the name
     * @param name
     */
    public Treasure(String name) {
        this.name = name;
    }
}