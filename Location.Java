import java.io.Serializable;

public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;
    private TerrainType terrainType;
    private Location[] borders;

    private int noOfBorders;

    //Name methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Terrain type methods
    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    //Border methods
    public void appendBorders(Location l){
        Location[] temp = new Location[noOfBorders +1];
        int index = 0;
        for (Location location : this.borders) {
            temp[index] = location;
            index++;
        }
        temp[index] = l;
        borders = temp;
    }

    public Location[] getBorders() {
        return borders;
    }

    //Constructor method
    public Location(String n, TerrainType t, Location[] b){
        name = n;
        terrainType = t;
        borders = b;
    }

    public void main(String[] args){
        
    }
    
}