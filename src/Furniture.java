import java.util.ArrayList;

/**
 * Represents a furniture.
 * Premise:
 * The size, both the length and the width, should be same or less than floor's
 * to fit the floor.
 */
public class Furniture {

    private String name;
    private int length;
    private int width;
    private static ArrayList<Furniture> furnitureArrayList = new ArrayList<>();

    // Set the availability of the furniture
    boolean isAvailable = true;

    // Constructor
    public Furniture(){

    }
    public Furniture(String name, int width, int length){
        this.length = length;
        this.width = width;
        this.name = name;
    }

    // add new furniture to the furnitureArrayList
    public void addFurniture(String name, int width, int length){
        Furniture item = new Furniture(name, width, length);
        furnitureArrayList.add(item);

        displayFurniturelist();
    }

    // For debugging: make sure items are set in the ArrayList
    public void displayFurniturelist(){
        for(Furniture item: furnitureArrayList){
            String itemN = item.getName();
            int w = item.getLength();
            int l = item.getWidth();
            int id = item.hashCode();
            System.out.println(id + ": " + itemN +" (" + w + "mm x  " + l + "mm)");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
