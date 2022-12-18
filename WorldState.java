import java.io.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WorldState implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private Location locations;

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
