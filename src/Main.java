public class Main {
    public static void main(String[] args) {

        // Ask the size of floor

        // Ask the size of a furniture

        // Set the size of floor
        Floor floor = new Floor(100,200);
        // Set the size of furniture
        Furniture furniture1 = new Furniture(10, 45, "shelf");

        // Display the values of floor and furniture
        System.out.println("The size of the floor: ");
        System.out.println(floor.getLength() + " x " + floor.getWidth()
                + " (length x width)");
        System.out.println("The size of " + furniture1.getName());
        System.out.println(furniture1.getLength() + " x " + furniture1.getWidth()
                + " (length x width)");
        System.out.println(furniture1.isAvailable);

    }
}
