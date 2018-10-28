package worldofzuul;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }


    private void createRooms()
    { //Jeg har ændret en anden fil
        Room bedroom, hallway, sistersRoom, livingRoom, lobby, wc, outside, window;
      
        bedroom = new Room("in your smokefilled bedroom and you hear the fire cracking");
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs");
        sistersRoom = new Room("in your sisters room");
        livingRoom = new Room("in the living room");
        lobby = new Room("in the lobby facing the front door");
        wc = new Room("on the toilet, the room is filled with smoke and fire - GET OUT!");
        outside = new Room("Outside");
        window = new Room("you jumped out the window! \n Did you forget you lived on 8th floor?! \n Anyway, you are dead! Restart the game?");
        
        
        bedroom.setExit("door", hallway);
        bedroom.setExit("window", window);

        hallway.setExit("door", sistersRoom);
        hallway.setExit("stairs", livingRoom);
        hallway.setExit("toilet", wc);
        
        sistersRoom.setExit("door", hallway);   
        
        wc.setExit("door", hallway);

        livingRoom.setExit("stairs", hallway);
        livingRoom.setExit("lobby", lobby);
        
 
        lobby.setExit("livingroom", livingRoom);
        lobby.setExit("outside", outside);

        currentRoom = bedroom;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Fire Escape!");
        System.out.println("In Fire Escape, your goal is to escape your home.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
            player.addStep();
            
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at you own home.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("you have walked " + player.getStepCount() + " steps so far");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("your health is: " + player.getHealth());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
