import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A frame that displays
 * - floor size
 * - furniture name and size (possibly multiple pieces)
 * - squares that represents the floor and the furniture
 */
public class Moyogae extends JFrame {

    static JFrame window = new Moyogae();

    /**
     * This main program creates a frame and makes it visible.
     * During step1(creating fundamentals),
     * this contains the main program to processing information from user.
     */
    public static void main(String[] args) {

        window.setContentPane(inputFloorData());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocation(20,20);
        window.setVisible(true);
    }


    /**
     * The constructor set the title of the application window.
     * Pending: creates the frame, size it,
     * and centres it horizontally on the screen.
     */
    public Moyogae(){
        super("MYGE: measure your room");
    } // end for constructor Moyogae()

    /**
     * This inputFloorData method create the input dialog window, then
     * return DrawPanel instance.
     */
    public static DrawPanel inputFloorData(){

        // Input Dialog
        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Enter the size:") );
        label.add( new JLabel("Floor Width (mm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Floor Length (mm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        control.add( new JLabel(""));   // to fill the empty space for layout
        JTextField floorWidthInput = new JTextField();
        control.add(floorWidthInput);
        JTextField floorLengthInput = new JTextField();
        control.add(floorLengthInput);
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Set Floor Size", JOptionPane.QUESTION_MESSAGE);

        // Set the input data into the floor object
        Floor.setWidth( Integer.parseInt(floorWidthInput.getText()) );
        Floor.setLength( Integer.parseInt(floorLengthInput.getText()) );

        return new DrawPanel();

    }   // end inputFloorSize() method


    /**
     * This inputFurnitureData method set the new item in the ArrayList<Furniture>.
     */
    public static void inputFurnitureData(ArrayList<Furniture> arrayList, boolean isFurnitureAdd){

        // Input Dialog
        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Name", SwingConstants.RIGHT) );
        label.add( new JLabel("Width (mm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (mm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        JTextField furnitureName = new JTextField();
        control.add( furnitureName );
        JTextField furnitureWidthInput = new JTextField();
        control.add(furnitureWidthInput);
        JTextField furnitureLengthInput = new JTextField();
        control.add(furnitureLengthInput);
        panel.add(control, BorderLayout.CENTER);

        if(isFurnitureAdd) {
            JOptionPane.showMessageDialog(null, panel,
                    "Add new furniture", JOptionPane.QUESTION_MESSAGE);
        }
        // Set the input data into the ArrayList
        Furniture item = new Furniture();
        item.setName(furnitureName.getText());
        item.setWidth( Integer.parseInt(furnitureWidthInput.getText()) );
        item.setLength( Integer.parseInt(furnitureLengthInput.getText()) );
        arrayList.add(item);

    }   // end inputFurnitureData() method


}
