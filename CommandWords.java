/**
 * @author  Leah Kazenmayer
 * @version 2
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "roll", "quit", "help", "look", "take", "drop", "talk", "read", "secret", "accusePerson",
        "accusePlace", "accuseWeapon", "submit", "readNotepad", "addNotepad", "eraseNotepad",
        "suspects", "instructions", "inventory", "north", "south", "east", "west"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    public String showAll()
    {
        String returnString = "";
        for (int i = 0; i<validCommands.length; i++)
        {
            returnString += validCommands[i] + " ";
        }
        return returnString;
    }
}
