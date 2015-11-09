import java.util.ArrayList;
import java.util.Stack;
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
 * @author  Mate Barna
 * @version 1.13 - 2014.04.11.
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private boolean debug_mode = false;
    private Room description;
    private Item exam;
    private Stack<Room> previousRooms;
    ArrayList<Item> inventory = new ArrayList<Item>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previousRooms = new Stack<Room>();
    }
    /**
     * In case debug_mode is true, the display method handles all output to terminal window.
     */
   public void display(String x)
   {
       if (debug_mode = true)
       {
           System.out.println(x);
       }
   }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, lobby, stockroom, stairs, upstairs, balcony, ladder;
      
        // create the rooms
        outside = new Room("outside the main entrance of the abandoned warehouse");
        lobby = new Room("in the main lobby, huge place!");
        stockroom = new Room("in a big stockroom. Can you find the key to my heart? ;)");
        stairs = new Room("at the stairs, please note there are no lights upstairs!");
        upstairs = new Room("upstairs, there is no light though...");
        balcony = new Room("standing on the balcony, you can either 'jump' or go down the ladder!");
        ladder = new Room("standing at a ladder!");
       
                
        // initialise room exits
        outside.setExit("east", lobby);
        outside.setExit("south", ladder);

        lobby.setExit("west", outside);
        lobby.setExit("north", stockroom);
        lobby.setExit("south", stairs);
        
        stockroom.setExit("south", lobby);
                
        stairs.setExit("north", lobby);
        stairs.setExit("east", upstairs);

        upstairs.setExit("west", stairs);
        upstairs.setExit("north", balcony);
        
        balcony.setExit("south", upstairs);
        balcony.setExit("west", ladder);
        balcony.setExit("jump", outside);
        
        ladder.setExit("east", balcony);
        ladder.setExit("north", outside);
        
        //testing if inventory command works
        //inventory.add(new Item("lol", "aaa", "kkk"));
        
        
        currentRoom = outside;  // start game outside
        /**
         * Adding an item to the 'lobby' room.
         */
        lobby.setItem(new Item("Trophy", "A nice bronze trophy", "20 kg"));
        // Adding an extra item to test task 6.
        lobby.setItem(new Item("Hat", "A big black hat", "1 kg"));
    }
    /**
     * Calls a main method to perform a new Game() method and start playing the game.
     * It's necessary to be able to play the game outside of BlueJ environment.
     */
    public static void main(String[] args)
    {
        Game start = new Game();
        start.play();
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
        System.out.println(currentRoom.getLongDescription());
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
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        /**
         * Defines a new 'look' command which lets us re-display the contents and description of the current room.
         */
        else if (commandWord.equals("look"))  {
           System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("examine"))
        {
            goExam(command);
        }
        /**
         * Putting in 'back' command, the goBack() method gets called.
         */
        else if (commandWord.equals("back")) {
            goBack();
        }
        /**
         * Define a command 'inventory' to show what we picked up already.
         */
        else if (commandWord.equals("inventory")) {
            getInventory();
        }
        /**
         * define a command 'take' to pick up an item from a room.
         */
        else if (commandWord.equals("take"))
        {
            getItem(command);
        }
        //else if (commandWord.equals("drop"))
        //{
         //   dropItem(command);
        //}
       
        // else command not recognised.
        return wantToQuit;
    }
    
    /**
     * A method to pick up items.
     * @param command - The 2nd word of the command containing the items exact name.
     */
     private void getItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up...
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = currentRoom.getItem(item);

        if (newItem == null) {
            System.out.println("There is no such item!");
        }
        else {
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up:" + item);
        }
    }
    /**
     * A method originally designed to drop the items.
     */
     private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = null;
        int index = 0;
        for (int i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).getItemName().equals(item))
            {
                newItem = inventory.get(i);
                index = i;
            }
        }

        if (newItem == null) {
            System.out.println("There is no such item in your inventory!");
        }
        else {
            inventory.remove(index);
            //currentRoom.setItem(new Item(item));
            System.out.println("Dropped:" + item);
        }
    }
    /**
     * A method to list all items in our inventory.
     * @param output - a String to contain the name of items
     * @return output 
     */
    public void getInventory()
    {       
        String output = "";
        for (int i = 0; i < inventory.size(); i++)
        {
           output += inventory.get(i).getItemName() + " , ";
        }
        System.out.println("You are carrying:");
        System.out.println(output);
    }
    
    /**
      * A method to go back to the last stored room.
      * If the stack named previousRooms is NOT empty, it adds the current room to our Stack and 
      * displays our current room.
    */
    public void goBack()
    {
        if (!previousRooms.empty())
        {
            currentRoom = previousRooms.pop();
        }
        System.out.println(currentRoom.getLongDescription());
    
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
        System.out.println("around at the warehouse.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // push the current room into our stack in order to be able to use back command.
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    public void goExam(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to examine...
            System.out.println("Examine what?");
            return;
        }

        String item = command.getSecondWord();
        String name = exam.getItemName();
        if (item != name)
         System.out.println(currentRoom.getExam());     
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

