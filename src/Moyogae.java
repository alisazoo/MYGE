import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * A frame that displays
 * - floor size
 * - furniture name and size (possibly multiple pieces)
 * - squares that represents the floor and the furniture
 */
public class Moyogae extends JFrame {

    static int myFloorLength, myFloorWidth, myFurnitureLength, myFurnitureWidth;
    static String myFurnitureName, promptAns, inputInfo;
    static Floor myFloor;
    static ArrayList<Furniture> myFurniture = new ArrayList<>();

    /**
     * This main program creates a frame and makes it visible.
     * During step1(creating fundamentals),
     * this contains the main program to processing information from user.
     */
    public static void main(String[] args) {
        JFrame frame = new Moyogae();

        // ask the information about floor and furniture
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
        promptAns = prompt.nextLine();

        while(promptAns.equalsIgnoreCase("y")){
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
            promptAns= prompt.nextLine();
            /*
             * Fix later:
             * issue: enter the name of the next furniture instead of yes or no.
             * if so, the prompt automatically ends and no time to fix.
             * - NumberFormatException
             */
        }

        // Display the values of floor and furniture
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

        JPanel content = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString(inputInfo, 20, 30);
            }
        };
        content.setLayout(new BorderLayout());

        frame.setContentPane(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocation(20,20);
        frame.setVisible(true);
    }

    private DrawPanel drawPanel;

    /**
     * The constructor creates the frame, size it,
     * and centres it horizontally on the screen.
     */
    public Moyogae(){
        super("MYGE -Let's make sure the size-");
            // Specifies the string for the title bar of the window.
        JPanel content = new JPanel();  // To hold the content of the window.
        content.setBackground(Color.gray);
        content.setLayout(new BorderLayout());

//        JButton okButton = new JButton("OK");
        JLabel aLabel = new JLabel();
        aLabel.setText("Test");
//        content.add(okButton, BorderLayout.SOUTH);
        content.add(aLabel, BorderLayout.CENTER);

        setContentPane(content);

        // Create the DrawPanel that fills most of the window, and customize it.
        drawPanel = new DrawPanel();
        drawPanel.setName("SetName()");
        content.add(drawPanel, BorderLayout. NORTH);


    }
}
