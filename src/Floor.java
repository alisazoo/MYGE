/**
 * Represents an floor.
 */
public class Floor {

    private static int length;
    private static int width;

    public Floor(int length, int width){
        Floor.width = width;
        Floor.length = length;
    }

    public Floor(){
        Floor.length = 800;
        Floor.width = 600;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        Floor.length = length;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Floor.width = width;
    }
}
