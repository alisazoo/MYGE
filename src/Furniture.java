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
    static ArrayList<Furniture> furnitureArrayList = new ArrayList<>();

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
    }

    // private JList<Furniture> createFurnitureList(){
    protected static String[] createFurnitureList(){
        String[] itemList = new String[furnitureArrayList.size()];
        for(int i = 0; i < furnitureArrayList.size(); i++){
            itemList[i] = furnitureArrayList.get(i).getName() + " (" +
                    furnitureArrayList.get(i).getWidth() + " mm x " +
                    furnitureArrayList.get(i).getWidth() + " mm)";
        }
        return itemList;
    }


//    // For debugging: make sure items are set in the ArrayList
//    /**
//     * Return the string
//     * @return String of item
//     */
//    public String getFurniturelistText(){
//        String itemTxt = "";
//        for(Furniture item: furnitureArrayList){
//            String itemN = item.getName();
//            int w = item.getLength();
//            int l = item.getWidth();
//            itemTxt = itemTxt.concat( itemN +" (" + w + "mm x  " + l + "mm)\n" );
//        }
//        return itemTxt;
//    }

    public ArrayList<Furniture> getFurnitureArrayList(){
        return furnitureArrayList;
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
