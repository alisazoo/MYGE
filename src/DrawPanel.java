import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * A panel that can display the size and image of the floor and furniture.
 * The sizes are corresponded to the ones set by the user.
 *
 * Future plan: The ratio of the size of each element should correspond to what the user input.
 * User can move furniture as long as it can be set in the floor.
 */

public class DrawPanel extends JPanel {

    private int myFloorLength, myFloorWidth, myFurnitureLength, myFurnitureWidth;
    private String myFurnitureName, promptAns, inputInfo;
    private Floor myFloor;
    private ArrayList<Furniture> myFurniture = new ArrayList<>();

    public DrawPanel() {
        userPrompt();

        // Create the line to display the values of floor and furniture
        inputInfo = "++++++++Floor Size++++++++\n" +
                myFloor.getLength() + "mm x " + myFloor.getWidth()
                + "mm (length x width)\n\n";
        if (myFurniture.size() != 0) {
            inputInfo += "++++++++Furniture List++++++++\n";
            int itemNum = 1;
            for (Furniture item : myFurniture) {
                inputInfo = inputInfo.concat(itemNum + ". " + item.getName() +
                        ": " + item.getLength() + "mm x " + item.getWidth()
                        + "mm (length x width)\n");
                itemNum++;

            }
        }

    } // end constructor DrawPanel

    /**
     * Ask the information about floor and furniture.
     *
     *              * Fix later:
     *              * issue: enter the name of the next furniture instead of yes or no.
     *              * if so, the prompt automatically ends and no time to fix.
     *              * - NumberFormatException
     */
    private void userPrompt(){
        // Ask the size of floor
        Scanner prompt = new Scanner(System.in);

        System.out.println("Enter the width of your floor in millimeters (e.g. 3650)");
        myFloorWidth = parseInt(prompt.nextLine());
        System.out.println("Enter the length of your floor in millimeters (e.g. 2170)");
        myFloorLength = parseInt(prompt.nextLine());

        // Set the size of floor
        myFloor = new Floor(myFloorLength, myFloorWidth);

        // Ask the size of a furniture
        System.out.println("Do you have any furniture?: Y/N");
        promptAns = prompt.nextLine();

        while (promptAns.equalsIgnoreCase("y")) {
            System.out.println("Please enter the name of the furniture:");
            myFurnitureName = prompt.nextLine();
            System.out.println("Please enter the length of " + myFurnitureName + ":");
            myFurnitureLength = parseInt(prompt.nextLine());
            System.out.println("Please enter the width of " + myFurnitureName + ":");
            myFurnitureWidth = parseInt(prompt.nextLine());
            // Set the size of furniture
            myFurniture.add(new Furniture(myFurnitureLength, myFurnitureWidth, myFurnitureName));

            // ask again
            System.out.println("Do you have any furniture?: Y/N");
            promptAns = prompt.nextLine();
        }
    }

    /**
     * Display text based on the user input, generate the shapes corresponding to the sizes
     * the user input.
     * @param g Graphic context
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.drawString(inputInfo, 20, 30);
        g.setColor(Color.WHITE);
        g.fillRect(10,10,myFloor.getWidth()/10, myFloor.getLength()/10);
            // pending: put the name "floor" & size arrangement to fit the floor inside of
            // the application window area.
        if(myFurniture.size() >0){
            for(Furniture item: myFurniture){
                int locx = 15, locy = 15;
                g.setColor(Color.getHSBColor((float)Math.random(), 1.0F, 1.0F));
                g.fillRect(locx, locy, item.getWidth(), item.getLength());
                locx++;
                locy++;
                    // pending: the coordinates to set location are not worked.
            }
        }
    }
}
