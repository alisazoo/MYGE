import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static <numOfFurniture> void main(String[] args) {

        int myFloorLength, myFloorWidth, myFurnitureLength, myFurnitureWidth;
        String myFurnitureName;
        Floor myFloor;
        ArrayList<Furniture> myFurniture = new ArrayList<>();

        // Ask the size of floor
        Scanner prompt = new Scanner(System.in);
        System.out.println("Enter the length of your floor in millimeters (e.g. 3650)");
        myFloorLength = parseInt(prompt.nextLine());
        System.out.println("Enter the width of your floor in millimeters (e.g. 2170)");
        myFloorWidth = parseInt(prompt.nextLine());

        // Set the size of floor
        myFloor = new Floor(myFloorLength,myFloorWidth);

        // Ask the size of a furniture
        System.out.println("Do you have any furniture?: Y/N");
        String ans = prompt.nextLine();

        while(ans.equalsIgnoreCase("y")){
            System.out.println("Please enter the name of the furniture:");
            myFurnitureName = prompt.nextLine();
            System.out.println("Please enter the length of " + myFurnitureName + ":");
            myFurnitureLength = parseInt(prompt.nextLine());
            System.out.println("Please enter the width of " + myFurnitureName + ":");
            myFurnitureWidth = parseInt(prompt.nextLine());
            // Set the size of furniture
            myFurniture.add(new Furniture(myFurnitureLength, myFurnitureWidth, myFurnitureName));

            // ask again
            System.out.println("Do you habe any furniture?: Y/N");
            ans = prompt.nextLine();
        }

        // Display the values of floor and furniture
        System.out.println("++++++++Floor Size++++++++");
        System.out.println(myFloor.getLength() + "mm x " + myFloor.getWidth()
                + "mm (length x width)");
        if (myFurniture.size() != 0) {
            System.out.println("++++++++Furniture List++++++++");
            int itemNum = 1;
            for (Furniture item : myFurniture) {
                System.out.print(itemNum + ". " + item.getName());
                System.out.println(" " +
                        ": " + item.getLength() + "mm x " + item.getWidth()
                        + "mm (length x width)");
                itemNum++;

            }
        }
    }
}
