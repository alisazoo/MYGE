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

        window.setContentPane(inputFloorSize());
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

    public static DrawPanel inputFloorSize(){

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

        Floor floor = new Floor();
        floor.setWidth( Integer.parseInt(floorWidthInput.getText()) );
        floor.setLength( Integer.parseInt(floorLengthInput.getText()) );

        DrawPanel content = new DrawPanel(floor);
        return content;

    }

}
