/*
 * This class holds information about items in the zuul-world game.
 */
public class Item {
    private String description;
    private String name;

    /**
     * Constructor that initializes the item's name and description
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Description access method
    public String getDescription() {
        return description;
    }

    // Description mutation method
    public String getName() {
        return name;
    }
}
