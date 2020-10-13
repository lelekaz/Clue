//Leah Kazenmayer

public class Character
{
    private String name;
    private String description;
    
    public Character(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCharacterDescription()
    {
        return description;
    }
}