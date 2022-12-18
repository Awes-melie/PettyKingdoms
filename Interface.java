import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.SocketImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Interface {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
        throws IOException
    {

    WorldState world = new WorldState();

    Boolean gameActive = true;

    world = onStart(world);

    while (gameActive){
        String command = input.readLine();
        String[] operations = command.split(" ");

        if(operations.length>0){
            if (command.toUpperCase().compareTo("QUIT") == 0){
                gameActive = false;
            } else if (command.toUpperCase().compareTo("SAVE") == 0){
                saveWorld(world);
            }
        } else {
            puts("... my %s?",world);
        }
    }
    puts("Goodbye my %s?",world);
    }

    public static WorldState loadWorld(){
        try {

            FileInputStream file = new FileInputStream(new File("file.txt"));
            return WorldState.load(file);

        } catch(FileNotFoundException e) {

            System.out.println("File not found");
            return new WorldState();

        }
    }

    public static void puts(String text, WorldState world){
        System.out.println(String.format(text, world.getTitle()));
    }

    public static void saveWorld(WorldState world){
        try {
            FileOutputStream file = new FileOutputStream(new File("file.txt"));
            world.save(file);
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static WorldState onStart(WorldState world)
        throws IOException 
    {
        System.out.println("Do you have a realm already? y|n");
        Boolean inputInvalid = true;

        while (inputInvalid){

            String command = input.readLine();

            if(command.toUpperCase().compareTo("Y") == 0) {

                world = loadWorld();
                return world;

            } else if (command.toUpperCase().compareTo("N") == 0){

                world = WorldState.generateNewWorld();
                System.out.println("Are you a lord or a lady? lord|lady|neither");
                inputInvalid = false;

            } else {

                System.out.println("... My leige? Do you have a realm already? y|n");

            }
        }

        inputInvalid = true;

        while (inputInvalid){

            String command = input.readLine();
            if(command.toUpperCase().compareTo("LORD") == 0) {

                world.setTitle("Lord");
                inputInvalid = false;

            } else if (command.toUpperCase().compareTo("LADY") == 0){

                world.setTitle("Lady");
                inputInvalid = false;

            } else if (command.toUpperCase().compareTo("NEITHER") == 0){

                world.setTitle("Leige");
                inputInvalid = false;

            } else {

                System.out.println("... Are you a lord or a lady? lord|lady|neither");

            }
        }
        return world;
    }
}