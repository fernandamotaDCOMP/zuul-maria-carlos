/*
 * This class holds information about items in the zuul-world game.
 */
public class Item {
    private String description;
    private String name;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
