import java.util.*;
/**
 * @author  Leah Kazenmayer
 * @version 2
 */

public class GameEngine 
{
    private Parser parser;
    private Player player;
    private UserInterface gui;
    ArrayList<Item> Items = new ArrayList<Item>();
    ArrayList<Item> answers = new ArrayList<Item>();
    
    private boolean rolled;
    private boolean accused1;
    private boolean accused2;
    private boolean accused3;
    private boolean addNotepad;
    private boolean eraseNotepad;
    
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        parser = new Parser();
        player = new Player();
        createDeck();
        createRooms();
    }
    
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }
    
    // public static void main (String[] args)
    // {
        // GameEngine game = new GameEngine();
        // game.play();
    // }
    
    private void createDeck()
    {
        //creating characters
        Item scarlet = new Item("card1");
        scarlet.setClue("Miss Scarlet");
        Item plum = new Item("card2");
        plum.setClue("Professor Plum");
        Item peacock = new Item("card3");
        peacock.setClue("Mrs. Peacock");
        Item mustard = new Item("card4");
        mustard.setClue("Colonel Mustard");
        Item white = new Item("card5");
        white.setClue("Mrs. White");
        Item green = new Item("card6");
        green.setClue("Mr. Green");
        
        //adding character Items to the deck
        Items.add(scarlet);
        Items.add(plum);
        Items.add(peacock);
        Items.add(mustard);
        Items.add(white);
        Items.add(green);
        
        //Getting a random character Item
        //Adding the random character Item to the confidential envelope
        //Removing said random character Item from the regular deck of Items
        Item random = Items.get((int) (Math.random()*6));
        answers.add(random);
        Items.remove(random);
        
        //creating locations
        Item ballroom = new Item("card7");
        ballroom.setClue("ballroom");
        Item bathroom = new Item("card8");
        bathroom.setClue("bathroom");
        Item billiards = new Item("card9");
        billiards.setClue("billiards room");
        Item conservatory = new Item ("card10");
        conservatory.setClue("conservatory");
        Item dining = new Item("card11");
        dining.setClue("dining room");
        Item hall = new Item("card12");
        hall.setClue("hall");
        Item kitchen = new Item("card13");
        kitchen.setClue("kitchen");
        Item laundry = new Item("card14");
        laundry.setClue("laundry");
        Item library = new Item("card15");
        library.setClue("library");
        Item lounge = new Item("card16");
        lounge.setClue("lounge");
        Item mudroom = new Item("card17");
        mudroom.setClue("mudroom");
        Item study = new Item("card18");
        study.setClue("study");
        
        //Adding locations to the deck
        Items.add(ballroom);
        Items.add(bathroom);
        Items.add(billiards);
        Items.add(conservatory);
        Items.add(dining);
        Items.add(hall);
        Items.add(kitchen);
        Items.add(laundry);
        Items.add(library);
        Items.add(lounge);
        Items.add(mudroom);
        Items.add(study);
        
        //Getting a random location Item
        //Adding the random location Item to the confidential envelope
        //Removing said random location Item from the regular deck of Items
        random = Items.get((int) (Math.random()*12) + 5);
        answers.add(random);
        Items.remove(random);
        
        //creating weapons
        Item candlestick = new Item("card19");
        candlestick.setClue("candlestick");
        Item dagger = new Item("card20");
        dagger.setClue("dagger");
        Item pipe = new Item("card21");
        pipe.setClue("lead pipe");
        Item revolver = new Item("card22");
        revolver.setClue("revolver");
        Item rope = new Item("card23");
        rope.setClue("rope");
        Item wrench = new Item("card24");
        wrench.setClue("wrench");
        
        //Adding weapons to the deck
        Items.add(candlestick);
        Items.add(dagger);
        Items.add(pipe);
        Items.add(revolver);
        Items.add(rope);
        Items.add(wrench);
        
        //Getting a random weapon Item
        //Adding the random weapon Item to the confidential envelope
        //Removing said random weapon Item from the regular deck of Items
        random = Items.get((int) (Math.random()*6) + 16);
        answers.add(random);
        Items.remove(random);
        
        //Shuffles the Items
        Collections.shuffle(Items);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Creating rooms in the game
        Room ballroom, bathroom, billiards, conservatory, dining, hall, kitchen, laundry;
        Room library, lounge, main, mudroom, study;
        Room space1, space2, space3, space4, space5;
        
        //Initializing the descriptions of the rooms
        ballroom = new Room("ballroom", "ballroom.gif");
        bathroom = new Room("bathroom", "bathroom.gif");
        billiards = new Room("billiards room", "billiards_room.gif");
        conservatory = new Room("conservatory", "conservatory.gif");
        dining =  new Room("dining room", "dining_room.gif");
        hall = new Room("hall", "hall.gif");
        kitchen = new Room("kitchen", "kitchen.gif");
        laundry = new Room("laundry room", "laundry_room.gif");
        library = new Room("library", "library.gif");
        lounge = new Room("lounge", "lounge.gif");
        main = new Room("main starting point", "main.gif");
        mudroom = new Room("mudroom", "mudroom.gif");
        study = new Room("study", "study.gif");
        space1 = new Room("space tile", "space_tile.gif");
        space2 = new Room("space tile", "space_tile.gif");
        space3 = new Room("space tile", "space_tile.gif");
        space4 = new Room("space tile", "space_tile.gif");
        space5 = new Room("space tile", "space_tile.gif");
        
        //Setting the secret passageways
        conservatory.setSecretPassageway(library);
        library.setSecretPassageway(conservatory);
        mudroom.setSecretPassageway(bathroom);
        bathroom.setSecretPassageway(mudroom);
        lounge.setSecretPassageway(laundry);
        laundry.setSecretPassageway(lounge);
        
        ballroom.setExit("west", mudroom);
        ballroom.setExit("south", laundry);
        
        bathroom.setExit("east", hall);
        
        billiards.setExit("west", space1);
        billiards.setExit("east", space2);
        
        conservatory.setExit("north", space1);
        conservatory.setExit("east", mudroom);
        conservatory.setExit("south", main);
        
        dining.setExit("west", main);
        dining.setExit("east", space3);
        
        hall.setExit("west", bathroom);
        hall.setExit("north", space4);
        hall.setExit("east", space5);
        
        kitchen.setExit("north", main);
        kitchen.setExit("west", space4);
        
        laundry.setExit("west", space3);
        laundry.setExit("north", ballroom);
        
        library.setExit("south", space5);
        library.setExit("east", study);
        
        lounge.setExit("south", space4);
        
        main.setExit("north", conservatory);
        main.setExit("east", dining);
        main.setExit("south", kitchen);
        
        mudroom.setExit("west", conservatory);
        mudroom.setExit("north", space2);
        mudroom.setExit("east", ballroom);
        
        study.setExit("west", library);
        study.setExit("north", space3);
        
        space1.setExit("south", conservatory);
        space1.setExit("east", billiards);
        
        space2.setExit("west", billiards);
        space2.setExit("south", mudroom);
        
        space3.setExit("west", dining);
        space3.setExit("south", study);
        space3.setExit("east", laundry);
        
        space4.setExit("north",lounge);
        space4.setExit("east", kitchen);
        space4.setExit("south", hall);
        
        space5.setExit("west", hall);
        space5.setExit("north", library);

        player.setCurrentRoom(main);
        
        ballroom.addItem(Items.get(0));
        ballroom.addItem(Items.get(1));
        bathroom.addItem(Items.get(2));
        bathroom.addItem(Items.get(3));
        billiards.addItem(Items.get(4));
        billiards.addItem(Items.get(5));
        conservatory.addItem(Items.get(6));
        conservatory.addItem(Items.get(7));
        dining.addItem(Items.get(8));
        dining.addItem(Items.get(9));
        hall.addItem(Items.get(10));
        hall.addItem(Items.get(11));
        kitchen.addItem(Items.get(12));
        kitchen.addItem(Items.get(13));
        laundry.addItem(Items.get(14));
        laundry.addItem(Items.get(15));
        library.addItem(Items.get(16));
        library.addItem(Items.get(17));
        lounge.addItem(Items.get(18));
        mudroom.addItem(Items.get(19));
        study.addItem(Items.get(20));
        
        ballroom.setHasLock(false);
        bathroom.setHasLock(false);
        billiards.setHasLock(false);
        conservatory.setHasLock(false);
        dining.setHasLock(false);
        hall.setHasLock(true);
        kitchen.setHasLock(true);
        laundry.setHasLock(false);
        library.setHasLock(false);
        lounge.setHasLock(false);
        mudroom.setHasLock(false);
        study.setHasLock(false);
        
        Item hallKey = new Item("hallkey");
        billiards.addItem(hallKey);
        
        Item kitchenKey = new Item("kitchenkey");
        laundry.addItem(kitchenKey);
        
        Item accuseItem = new Item("accusePowerUp");
        conservatory.addItem(accuseItem);
        Item accuseItem2 = new Item("accusePowerUp");
        hall.addItem(accuseItem2);
        
        space4.addCharacter(
            new Character(
                            "\nMiss Scarlet",
                            "\nHere's a hint for this confusing maze: The mudroom has 3 exits.\nWest: conservatory | North: Space tile | East: Ballroom\n"
                         )
        );
        study.addCharacter(
            new Character(
                            "\nColonel Mustard",
                            "\nHere's a hint for this confusing maze: The kitchen has 2 exits.\nWest: Space tile | North: Main Starting Point\n"
                         )
        );
        hall.addCharacter(
            new Character(
                            "\nMrs. Peacock",
                            "\nHere's a hint for this confusing maze: The laundry has 2 exits.\nWest: Space tile | North: Ballroom\n"
                         )
        );
    }
    
    private void printWelcome()
    {
        gui.println("Welcome to the game Clue!");
        gui.println("Clue is a mysterious game, designed to figure out whodunnit.");
        gui.println("Mr. Boddy was murdered last night. Can you figure out who did it?");
        gui.println("With what weapon?");
        gui.println("And where the murder occurred?");
        gui.println("Type 'help' if you need help.");
        gui.println("Type 'instructions' if you need instructions for the game.");
        gui.println(player.getCurrentRoom().getLongDescription() + "\n");
        gui.showImage(player.getCurrentRoom().getImageName());
    }
    
    public boolean interpretCommand(String commandLine) 
    {
        boolean wantToQuit = false;
        
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.print("\nI don't know what you mean...\n");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("roll")) {
            if (player.cardInInventory() == false)
            {
                if (command.getSecondWord() == null)
                {
                    Di di = new Di();
                    int rolledNumber = di.roll();
                    gui.println("You rolled a " + rolledNumber + ".");
                    
                    if (rolledNumber%2 == 0)
                    {
                        String inputString;
                        String string = "";
                        gui.println("You get to choose your exit.");
                        gui.println("Which exit do you like to take?\n");
                        rolled = true;
                    }
                    else if (rolledNumber % 2 == 1)
                    {
                        gui.println(player.randomWalk());
                        gui.showImage(player.getCurrentRoom().getImageName());
                    }
                }
                else
                {
                    gui.println("Type only 'roll'");
                }
            }
            else
            {
                gui.println("You can't take the cards with you.");
            }
        }
        else if (commandWord.equals("north") && rolled == true)
        {
            makeSureTheyRolled("north");
            rolled = false;
            gui.showImage(player.getCurrentRoom().getImageName());
        }
        else if (commandWord.equals("south") && rolled == true)
        {
            makeSureTheyRolled("south");
            rolled = false;
            gui.showImage(player.getCurrentRoom().getImageName());
        }
        else if (commandWord.equals("east") && rolled == true)
        {
            makeSureTheyRolled("east");
            rolled = false;
            gui.showImage(player.getCurrentRoom().getImageName());
        }
        else if (commandWord.equals("west") && rolled == true)
        {
            makeSureTheyRolled("west");
            rolled = false;
            gui.showImage(player.getCurrentRoom().getImageName());
        }
        else if (
                    (commandWord.equals("north") ||
                    commandWord.equals("south") ||
                    commandWord.equals("east") ||
                    commandWord.equals("west")
                    ) &&
                    rolled==false
                )
                    {
                        gui.println("Please roll the die first.");
                    }
        else if (commandWord.equals("look")) {
            gui.println(player.look());
        }
        else if (commandWord.equals("take"))
        {
            gui.println(player.take(command));
        }
        else if (commandWord.equals("read"))
        {
            if (player.inventory().equals("There's nothing in your inventory"))
            {
                gui.println("There's nothing to read.");
            }
            else
            {
                gui.println(player.read());
            }
        }
        else if (commandWord.equals("inventory"))
        {
            gui.println(player.inventory());
        }
        else if (commandWord.equals("drop"))
        {
            gui.println(player.drop(command));
        }
        else if (commandWord.equals("talk"))
        {
            gui.println(player.talk());
        }
        else if (commandWord.equals("accusePerson"))
        {
            accused1 = true;
            gui.println("I accuse...(Enter a character you would like to accuse)");
            gui.println("Tip: Remember to spell out their whole name including titles");
        }
        else if (commandWord.equals("accusePlace"))
        {
            accused2 = true;
            gui.println("of committing the crime in the...(Enter a location you think it took place in)");
        }
        else if (commandWord.equals("accuseWeapon"))
        {
            accused3 = true;
            gui.println("with the...(Enter the weapon you think (s)he used)");
        }
        else if (commandWord.equals("submit"))
        {
            if (answers.get(0).getClue().equalsIgnoreCase((String)player.getAccusedList()[0])
            && answers.get(1).getClue().equalsIgnoreCase((String)player.getAccusedList()[1])
            && answers.get(2).getClue().equalsIgnoreCase((String)player.getAccusedList()[2]))
            {
                gui.println("You guessed correctly! You win!");
                gui.enable(false);
            }
            else
            {
                if (player.checkExtraAccusations() == 0)
                {
                    gui.println("You guessed incorrectly. You lose :(");
                    gui.println("The murderer was: " + answers.get(0).getClue());
                    gui.println("The crime scene was: " + answers.get(1).getClue());
                    gui.println("The murder weapon was: " + answers.get(2).getClue());
                    gui.enable(false);
                }
                else if (player.checkExtraAccusations()>0)
                {
                    player.removeExtraAccusation();
                    gui.println("You guessed incorrectly. However, you had accusation");
                    gui.println("powerup(s) in your inventory. That means you get more");
                    gui.println("chance(s) to get the answer correctly.");
                }
            }
        }
        else if (commandWord.equals("readNotepad"))
        {
            String print = "";
            for (int i = 0; i<player.getNotepad().size(); i++)
            {
                print += ("\n>" + (String) player.getNotepad().get(i));
            }
            if (print.equals(""))
            {
                gui.println("There's nothing to read.");
            }
            else
            {
                gui.println(print);
            }
        }
        else if (commandWord.equals("addNotepad"))
        {
            addNotepad = true;
            gui.println("What would you like to add to your notepad?");
        }
        else if (commandWord.equals("eraseNotepad"))
        {
            eraseNotepad = true;
            gui.println("What would you like to erase from your notepad?");
        }
        else if (commandWord.equals("suspects"))
        {
            suspects();
        }
        else if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("instructions"))
        {
            printInstructions();
        }
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
        else if (commandWord.equals("secret"))
        {
            gui.println(player.secret());
            gui.showImage(player.getCurrentRoom().getImageName());
        }

        return wantToQuit;
    }
    
    private void suspects()
    {
        gui.println("Suspects: Mrs. White, Mr. Green, Miss Scarlet, Professor Plum, Colonel Mustard, Mrs. Peacock");
        gui.println("Possible murder weapons: dagger, lead pipe, revolver, candlestick, rope, wrench");
        gui.println("Possible crime scenes: conservatory, billiards room, mudroom, lounge, dining room,");
        gui.println("laundry room, ballroom, kitchen, study, bathroom, hall, library");
    }
    
    private void printHelp() 
    {
        gui.println("Your command words are:");
        gui.println("   roll look take read inventory drop talk accusePerson accusePlace");
        gui.println("   accuseWeapon submit readNotepad addNotepad deleteNotepad suspects");
        gui.println("   help instructions quit");
        parser.showCommands();
    }
    
    private void printInstructions()
    {
        gui.println("This game is all about finding out whodunnit.");
        gui.println("To figure out the murder of Mr. Boddy, you need to visit every single room.");
        gui.println("From there, you will find cards that have clues on them.");
        gui.println("All the cards you take and read are clues to who DIDN'T commit the murder,");
        gui.println("what they DIDN'T use, and where they DIDN'T kill Mr. Boddy.\n");
        
        gui.println("'roll' means your character will roll a die.");
        gui.println("If you roll an even number, you get to choose which exit you go through.");
        gui.println("If you roll an odd number, an exit is decided for you.\n");
        
        gui.println("'look' means that your character will look around the current room (s)he's in.\n");
        
        gui.println("'take' means to take an item when you reach a room.");
        gui.println("For instance, if you see 'card17' in a room, you can");
        gui.println("type in 'take card17' to take the item.\n");
        
        gui.println("'read' let's your character read the cards once you take them.\n");
        
        gui.println("'inventory' let's your character see what's in its inventory.\n");
        
        gui.println("'drop' means to drop an item(s) in the room you're in.");
        gui.println("You can't take cards with you to other rooms.");
        gui.println("For instance, if you took a item called 'card17' and want to leave the room,");
        gui.println("type in 'drop card17', and your character will drop it.\n");
        
        gui.println("'accusePerson' means that you can accuse someone of committing the murder");
        gui.println("'accuseWeapon' means you can guess what weapon they used");
        gui.println("'accusePlace' means you can guess where the crime took place");
        gui.println("'submit' menas you will officially submit all three of your guesses");
        gui.println("to determine whether you guessed correctly or not");
        gui.println("However, be careful, you may only accuse once in the whole game.");
        gui.println("If you are right with the accusation, you win!");
        gui.println("If you are wrong, you lose.\n");
        
        gui.println("'suspects' will tell you all the murder suspects, all the weapons");
        gui.println("that could have possibly killed Mr. Boddy, and all the possible");
        gui.println("crime scenes.\n");
        
        gui.println("'readNotepad' means that you may read your notepad");
        gui.println("'addNotepad' means that you may add your own notes to your notepad");
        gui.println("'eraseNotepad' means that you may erase a note from your notepad");
        gui.println("This notepad is to help keep track of everything you find.\n");    

    }

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }
    
    private void makeSureTheyRolled(String direction)
    {
        ArrayList listOfExits = player.getCurrentRoom().getAllExits();
        String print = "initial";
        for (int i = 0; i<listOfExits.size(); i++)
        {
            if (direction.equalsIgnoreCase((String) listOfExits.get(i)))
            {
                print = player.walk(direction);
            }
        }
        if (print.equals("initial"))
        {
            gui.println("Type in a valid exit");
        }
        else if (print == "")
        {
            gui.println(player.getCurrentRoom().getLongDescription());
        }
        else
        {
            gui.println(print);
        }
    }
    
    public boolean getAccused1()
    {
        return accused1;
    }
    
    public void setAccused1(boolean b)
    {
        accused1 = b;
    }
    
    public boolean getAccused2()
    {
        return accused2;
    }
    
    public void setAccused2(boolean b)
    {
        accused2 = b;
    }
    
    public boolean getAccused3()
    {
        return accused3;
    }
    
    public void setAccused3(boolean b)
    {
        accused3 = b;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public void setAddNotepad(boolean b)
    {
        addNotepad = b;
    }
    
    public boolean getAddNotepad()
    {
        return addNotepad;
    }
    
    public void setEraseNotepad(boolean b)
    {
        eraseNotepad = b;
    }
    
    public boolean getEraseNotepad()
    {
        return eraseNotepad;
    }
    
    
    
    public void print(String s)
    {
        gui.println(s);
    }
}
