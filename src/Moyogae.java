import javax.swing.*;

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

        window.setContentPane(content);
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
}
