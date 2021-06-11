public class Main {
    public static void main(String[] args) {
        Floor floor = new Floor(100,200);
        System.out.println("The size of the floor: ");
        System.out.println(floor.getLength() + " x " + floor.getWidth()
                + " (length x width)");

        Furniture furniture1 = new Furniture(10, 45, "shelf");
        System.out.println("The size of " + furniture1.getName());
        System.out.println(furniture1.getLength() + " x " + furniture1.getWidth()
                + " (length x width)");

    }
}
