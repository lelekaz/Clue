//Leah Kazenmayer

public class Item
{
    private String description;
    private String clue;
    
    public Item(String description)
    {
        this.description = description;
    }
    
    public String getItemDescription()
    {
        return description;
    }
    
    public void setClue(String c)
    {
        clue = c;
    }
    
    public String getClue()
    {
        return clue;
    }
}