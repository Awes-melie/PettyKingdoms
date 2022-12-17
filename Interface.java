import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    onStart(world);
    
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
            System.out.println(String.format("... my %s?",world.getTitle()));
        }
    }
    System.out.println(String.format("Goodbye my %s",world.getTitle()));
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
    public static void saveWorld(WorldState world){
        try {
            FileOutputStream file = new FileOutputStream(new File("file.txt"));
            world.save(file);
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void onStart(WorldState world)
        throws IOException
    {
        System.out.println("Do you have a realm already? y|n");
        Boolean inputInvalid = true;
        while (inputInvalid){
            String command = input.readLine();

            if(command.toUpperCase().compareTo("Y") == 0) {
                world = loadWorld();
                return;
            } else if (command.toUpperCase().compareTo("N") == 0){
                world = WorldState.generateWorld();
                System.out.println("Are you a lord or a lady my leige? lord|lady|neither");
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
                return;
            } else if (command.toUpperCase().compareTo("LADY") == 0){
                world.setTitle("Lady");
                return;
            } else if (command.toUpperCase().compareTo("NEITHER") == 0){
                world.setTitle("Leige");
                return;
            } else {
                System.out.println("... My leige? Are you a lord or a lady my leige? lord|lady|neither");
            }
        }
    }
}