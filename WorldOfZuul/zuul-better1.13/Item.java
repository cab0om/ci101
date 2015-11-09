
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String name, description, weight;
    
    /**
     * The mutator sets the name, the description and weight of item.
     * @param name = name of item
     * @param description = description of item
     * @param weight = weight of item
     */
    public Item(String name2, String desc, String weight2)
    {
        name = name2;
        description = desc;
        weight = weight2;
    }
    
    /**
     * @return name of item
     */
    public String getItemName()
    {
        return name;
    }
    
    /**
     * @return description of item
     */
    public String getItemDesc()
    {
        return description;
    }
    
    /**
     * @return weight of item
     */
    public String getItemWeight()
    {
        return weight;
    }
}
