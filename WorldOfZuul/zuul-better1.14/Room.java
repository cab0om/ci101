import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    ArrayList<Item> items = new ArrayList<Item>();
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        /**
         * Adding the items to the variable returnString.
         * @return = Items in the room (name and weight).
         */
        returnString += "\n Items in the room:\n";
        returnString += getRoomItems();
        
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Get items from the room.
     */
    public Item getItem(int i)
    {
            return items.get(i);
    }
    /**
     * Get items from the room.
     */
    public Item getItem(String ItemName)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getItemName().equals(ItemName))
            {
                return items.get(i);
            }
        }
        return null;
    }
    
    public void removeItem(String ItemName)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getItemName().equals(ItemName))
            {
                items.remove(i);
            }
        }
    }
    
    /**
     * Set an item in the room.
     */
    public void setItem(Item newItem)
    {
        items.add(newItem);
    }
    
    /**
     * An accessor to get the Name and Weight of the item.
     * @return Name and weight of item.
     */
    public String getRoomItems()
    {
        String outside = "";
        for (int i = 0; i < items.size(); i++)
        {
            outside += "\n" + items.get(i).getItemName() + " - ";
            outside += items.get(i).getItemWeight() + "\n";
        }
        
        return outside;
    }
    /**
     * An accessor to get the name and description of the items.
     * @return Name and description of item.
     */
    
    public String getExam()
    {
        String output = "";
        for (int i = 0; i < items.size(); i++)
        {
            output += items.get(i).getItemName() + "\n";
            output += items.get(i).getItemDesc();
        }
        
        return output;
    }
    
}

