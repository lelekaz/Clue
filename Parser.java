import java.util.Scanner;

/**
 * @author  Leah Kazenmayer
 * @version 2
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {
        //String inputLine = "";   // will hold the full input line
        String word1 = null;
        String word2 = null;
        
        String [] arrOfStr = inputLine.split(" ");
        
        if (arrOfStr.length == 1){
            word1 = arrOfStr[0];
            word2 = null;
        }
        else{
            word1 = arrOfStr[0];
            word2 = arrOfStr[1];
        }

        if(commands.isCommand(word1))
            return new Command(word1, word2);
        else
            return new Command(null, word2);
    }
    
    public void showCommands()
    {
        commands.showAll();
    }
}
