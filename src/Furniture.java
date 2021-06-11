/**
 * Represents a furniture.
 * Premise:
 * The size, both the length and the width, should be same or less than floor's
 * to fit the floor.
 */
public class Furniture {

    private static int id;
    private String name;
    private int length;
    private int width;

    // Set the availability of the furniture
    boolean isAvailable = true;

    // Constructor
    public Furniture(int length, int width, String name){
        this.length = length;
        this.width = width;
        this.name = name;
        id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Furniture.id = id;
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
