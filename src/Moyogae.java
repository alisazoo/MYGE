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

    /**
     * This main program creates a frame and makes it visible.
     * During step1(creating fundamentals),
     * this contains the main program to processing information from user.
     */
    public static void main(String[] args) {
        JFrame window = new Moyogae();

        DrawPanel content = new DrawPanel();
        content.setLayout(new BorderLayout());

        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(900, 700);
        window.setLocation(20,20);
        window.setVisible(true);
    }

    /**
     * The constructor set the title of the application window.
     * Pending: creates the frame, size it,
     * and centres it horizontally on the screen.
     */
    public Moyogae(){
        super("MYGE -Let's make sure the size-");
    } // end for constructor Moyogae()
}
