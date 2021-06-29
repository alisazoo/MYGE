/**
 * Represents an floor.
 */
public class Floor {

    private int length;
    private int width;

    public Floor(int length, int width){
        this.length = length;
        this.width = width;
    }

    public Floor(){
        this.length = 800;
        this.width = 600;
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
