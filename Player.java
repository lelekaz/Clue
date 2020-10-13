/**
 * @author Leah Kazenmayer
 * @version 2
 */

import java.util.*;
public class Player
{
    Room currentRoom;
    ArrayList<Item> inventory = new ArrayList<Item>();
    String [] accused = new String[3];
    ArrayList<String> notepad = new ArrayList<String>();
    
    public Player()
    {
        currentRoom = new Room(null, null);
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room r)
    {
        currentRoom = r;
    }
    
    public String walk(String direction)
    {
        Room nextRoom = null;
        String print = "";
        if (direction.equalsIgnoreCase("north")) {
            nextRoom = currentRoom.getExit("north");
            print = "";
        }
        else if (direction.equalsIgnoreCase("south")) {
            nextRoom = currentRoom.getExit("south");
            print = "";
        }
        else if (direction.equalsIgnoreCase("east")) {
            nextRoom = currentRoom.getExit("east");
            print = "";
        }
        else if (direction.equalsIgnoreCase("west")) {
            nextRoom = currentRoom.getExit("west");
            print = "";
        }
        if(nextRoom.getHasLock() == true && keyInInventory(nextRoom.getDescription())==false) {
            print += "This room is locked. You can't get in.";
        }
        else
        {
            currentRoom = nextRoom;
        }
        
        if (nextRoom.getHasLock() == true && keyInInventory(nextRoom.getDescription())==true) {
            nextRoom.setHasLock(false);
            print += "You used the key to unlock the door.";
            print += "\n" + currentRoom.getLongDescription();
        }
        
        return print;
    }
    
    public String randomWalk()
    {
        Room nextRoom = null;
        String direction = currentRoom.getRandomExit();
        
        nextRoom = currentRoom.getExit(direction);
        
        if(nextRoom.getHasLock() == true && keyInInventory(nextRoom.getDescription())==false) {
            return "You rolled an odd. You tried going " + direction + ". However, that room is locked. You can't get in.";
        }
        else
        {
            currentRoom = nextRoom;
        }
        
        if (nextRoom.getHasLock() == true && keyInInventory(nextRoom.getDescription())==true) {
            nextRoom.setHasLock(false);
            return "You rolled an odd. You went " + direction + ".\nYou used the key to unlock the door.\n" + currentRoom.getLongDescription();
        }
        
        
        return "You rolled an odd. You went " + direction + ".\n" + currentRoom.getLongDescription();
    }
    
    public String look()
    {
        return currentRoom.getLongDescription();
    }
    
    public String take(Command command)
    {
        if (command.hasSecondWord()==false)
        {
            return "You need to type in a valid entry.";
        }
        else if (command.hasSecondWord()==true)
        {
            ArrayList<Item> itemList = new ArrayList(currentRoom.getItems());
            String itemTaken = command.getSecondWord();
            Item itemToTake = null;
            
            for (int x = 0; x<itemList.size(); x++)
            {
                itemToTake = (Item)itemList.get(x);
                if (itemToTake.getItemDescription().equals(itemTaken))
                {
                    inventory.add(itemToTake);
                    currentRoom.getItems().remove(itemToTake);
                    return "You've taken " + itemToTake.getItemDescription();
                }
            }
        }
        
        return "This item does not exist";
    }
    
    public String read()
    {
        String inventoryList = "";
        for (int i = 0; i<inventory.size(); i++)
        {
            if (inventory.get(i).getClue() != null)
            {
                inventoryList += ">" + inventory.get(i).getClue() + "\n";
            }
        }
        
        return inventoryList;
    }
    
    public String inventory()
    {
        String print = "";
        for (int i = 0; i<inventory.size(); i++)
        {
            print += "\n" + inventory.get(i).getItemDescription();
        }
        if (print.equals(""))
        {
            return "There's nothing in your inventory";
        }
        else
        {
            return print;
        }
        
    }
    
    public boolean cardInInventory()
    {
        for (int i = 0; i<inventory.size(); i++)
        {
            if (inventory.get(i).getItemDescription().substring(0,4).equals("card"))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean keyInInventory(String s)
    {
        for (int i = 0; i<inventory.size(); i++)
        {
            if (inventory.get(i).getItemDescription().equals(s+"key"))
            {
                return true;
            }
        }
        return false;
    }
    
    public int checkExtraAccusations()
    {
        int counter = 0;
        for (int i = 0; i<inventory.size(); i++)
        {
            if (inventory.get(i).getItemDescription().equals("accusePowerUp"))
            {
                counter++;
            }
        }
        
        return counter;
    }
    
    public void removeExtraAccusation()
    {
        for (int i = 0; i<inventory.size(); i++)
        {
            if (inventory.get(i).getItemDescription().equals("accusePowerUp"))
            {
                inventory.remove(inventory.get(i));
                break;
            }
        }
    }
    
    public String drop(Command command)
    {
        if (command.hasSecondWord()==false)
        {
            return "You need to type in a valid entry.";
        }
        else if (command.hasSecondWord()==true)
        {
            String itemDropped = command.getSecondWord();
            Item itemToDrop = null;
            
            for (int x = 0; x<inventory.size(); x++)
            {
                itemToDrop = (Item)inventory.get(x);
                if (itemToDrop.getItemDescription().equals(itemDropped))
                {
                    inventory.remove(itemToDrop);
                    currentRoom.addItem(itemToDrop);
                    return "You've dropped " + itemToDrop.getItemDescription();
                }
            }
        }
        
        return "This item does not exist in your inventory";
    }
    
    public String talk()
    {
        String print = "";
        Character character;
        for (int i = 0; i<currentRoom.getCharacters().size(); i++)
        {
            character = (Character) currentRoom.getCharacters().get(i);
            print += "Hello there. My name is " + character.getName() + ".\n";
            print += character.getCharacterDescription();
        }
        return print;
    }
    
    public String[] getAccusedList()
    {
        return accused;
    }
    
    public void setAccused(int index, String accusation)
    {
        accused[index] = accusation;
    }
    
    public ArrayList getNotepad()
    {
        return notepad;
    }
    
    public void addToNotepad(String s)
    {
        notepad.add(s);
    }
    
    public void eraseFromNotepad(String s)
    {
        for(int i = 0; i<notepad.size(); i++)
        {
            if (notepad.get(i).equalsIgnoreCase(s))
            {
                notepad.remove(notepad.get(i));
            }
        }
    }
    
    public String secret()
    {
        if (cardInInventory() == true)
        {
            return "You can't take the card(s) with you!";
        }
        currentRoom = currentRoom.getSecretPassageway();
        return "You went through the secret passageway.\n" + currentRoom.getLongDescription();
    }
}
