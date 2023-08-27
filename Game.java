/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

    // Maria Fernanda da Mota Diniz - 202100045798
    // Carlos Eduardo Laurentino dos Santos - 202100045582
public class Game 
{
    private Parser parser;
    private Room currentRoom;



    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room portaria, ccet, didUm, didDois, dComp, moura, secretary, board;
      
        // create the rooms
        portaria = new Room("at the main entrance of the university");
        ccet = new Room("in the CCET");
        didUm = new Room("in Didactics 1 \nTeacher: Excuse me! You disrupted the class!");
        didDois = new Room("in Didactics 2");
        dComp = new Room("in DComp \nYou have won a cup of coffee to have the"+
                " energy to search for Moura for another minute \n");
        moura = new Room("in the Moura snack bar. \nYou found in time");
        secretary = new Room("in the secretary");
        board = new Room("int the board");

        /**
         *  initialise room exits
         */
        portaria.setExits("south", didUm);
        portaria.setExits("west", dComp);
        ccet.setExits("south", didDois);
        ccet.setExits("west", portaria);
        didUm.setExits("north", portaria);
        didUm.setExits("east", didDois);
        didDois.setExits("north", ccet);
        didDois.setExits("south", moura);
        didDois.setExits("west", didUm);
        didDois.setExits("upExit", secretary);
        dComp.setExits("east", portaria);
        secretary.setExits("north", board);
        board.setExits("south", secretary);
        secretary.setExits("downExit", didDois);

        currentRoom = portaria;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
            printLocationInfo();

        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getExit("west");
        }
        if(direction.equals("upExit")) {
            nextRoom = currentRoom.getExit("upExit");
        }
        if(direction.equals("downExit")) {
            nextRoom = currentRoom.getExit("downExit");
        }
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
        }
    }

    //P
    private void printLocationInfo() {
        System.out.println(currentRoom.getLocationDescription());
    }


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
