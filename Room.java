import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room
{
    private String description;
    private HashMap<String,Room> exits;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();

    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     *  The north exit.
     *  The east exit.
     *  The south exit.
     *  The west exit.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public void addItem(String name, String description) {
        // check if the item already exists in the map.
        // if exists just don´t add it.
        Set<String> keys = items.keySet();
        for (String item : items.keySet()) {
            if(item.equals(name))
                return;
        }
        Item newItem = new Item(name, description);
        items.put(name, newItem);
    }
    public void printItems() {
        if (items.isEmpty()) {
            System.out.println("No items in this room.");
        } else {
            System.out.println("Items in this room:");
            for (Item item : items.values()) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public Item delItem(String itemName) {
        return items.remove(itemName);
    }
    public Room getExit(String direction){
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public String getLongDescription(){
        return "You are " + description + ". \n" + getExitString();
    }

    public String getExitString(){
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit: keys){
            returnString += " " + exit;
        }
        return returnString;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }
}