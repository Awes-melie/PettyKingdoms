import java.io.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Random;
import java.util.ArrayDeque;
import java.util.Queue;
public class WorldState implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private Location[] locations;

    //WORLDGENCONSTS
    private static int NUMBER_OF_LOCATIONS = 10;
    private static int NUMBER_OF_EDGES = 15;
    //WORLDGENCONSTS

    public String getTitle() {
        return title;
    }

    public void setTitle(String s){
        title = s;
    }

    public void main(String[] args){
        WorldState world = WorldState.generateNewWorld();
        System.out.println(world.toString());
    }

    public String toString(){
        return "title: " + this.getTitle();
    }

    public static WorldState generateNewWorld() {
        WorldState world = new WorldState();
        Random generator = new Random();
        
        Queue<Location> unlinkedBorders = new ArrayDeque<Location>();

        for (int i = 0; i < NUMBER_OF_LOCATIONS; i++) {
            unlinkedBorders.add(new Location("i", TerrainType.PLAINS, new Location[0]));
        }
        while (!unlinkedBorders.isEmpty()){            
        }
        return world;
    }

    public static WorldState load(FileInputStream file){
        try {
            ObjectInputStream input = new ObjectInputStream(file);
            WorldState world = (WorldState) input.readObject();
            input.close();
            file.close();
            return world;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            return new WorldState();
        } catch (ClassNotFoundException e){
            System.out.println("Class not found.");
            return new WorldState();
        }
    }


    public void save(FileOutputStream file){
        try {
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(this);
			output.close();
			file.close();

		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
    }
}
