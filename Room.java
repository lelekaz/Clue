/**
 * @author  Leah Kazenmayer
 * @version 2
 */

import java.util.*;
public class Room 
{
    private String description;
    private boolean hasLock;
    private Room secretPassageway;
    private HashMap <String, Room> exits;
    private ArrayList <Item> items = new ArrayList <Item>();
    private ArrayList <Character> characters = new ArrayList <Character>();
    private String imageName;

    public Room()
    {
    }
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String image) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        imageName = image;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public boolean getHasLock()
    {
        return hasLock;
    }
    
    public void setHasLock(boolean b)
    {
        hasLock = b;
    }
    
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }
    
    public ArrayList getAllExits()
    {
        Set<String> keySet = exits.keySet();
        ArrayList<String> listOfExits = new ArrayList<String>(keySet);
        return listOfExits;
    }
    
    public String getRandomExit()
    {
        Random random = new Random();
        int number = random.nextInt(getAllExits().size());
        String direction = (String) getAllExits().get(number);
        return direction;
    }
    
    public String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    public String getLongDescription()
    {
        String resultString = "You are in the " + description + "\n" + getExitString() +"\n";
        
        for (int i = 0; i<getAllExits().size(); i++)
        {
            if (getExit( (String) getAllExits().get(i) ).getHasLock() == true)
            {
                resultString += "The " + (String) getAllExits().get(i) + " exit is locked.";
            }
        }
        
        if (items.size() !=0)
        {
            resultString += "This room contains: ";
            
            Iterator iter = items.iterator();
            while (iter.hasNext())
            {
                Item nextItem = (Item) iter.next();
                resultString += "\n" + "\t" + nextItem.getItemDescription();
            }
        }
        
        if (characters.size() !=0)
        {
            resultString += "\nThis room also has:";
            Iterator iter = characters.iterator();
            while (iter.hasNext())
            {
                Character nextCharacter= (Character) iter.next();
                resultString += "\n" + "\t" + nextCharacter.getName();
            }
        }
                
        if (getSecretPassageway() !=null)
        {
            resultString += "\nThere's also a secret passageway....";
            resultString += "\nIf you want to go through the passageway, type in 'secret'";
        }
        
        return resultString;
    }
    
    public ArrayList getItems()
    {
        return items;
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void addCharacter(Character character)
    {
        characters.add(character);
    }
    
    public ArrayList getCharacters()
    {
        return characters;
    }
    
    public void setSecretPassageway(Room r)
    {
        secretPassageway = r;
    }
    
    public Room getSecretPassageway()
    {
        return secretPassageway;
    }
    
    public String getImageName()
    {
        return imageName;
    }
}
