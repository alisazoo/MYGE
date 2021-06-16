import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A simple demonstration of MouseEvents.
 * Shapes are drawn on a black background when the user clicks the panel.
 * If the user Shift-clicks, the panel is cleared.
 * If the user right-clicks the panel, a blue oval is drawn.
 * Otherwise, when the user clicks, a red rectangle is drawn.
 * The contents of the panel are not persistent. For example, they might
 * disappear if the panel is resized.
 * This class has main() routine to allow it to be run as an application.
 */
public class SimpleStamper extends JPanel implements MouseListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Stamper");
        SimpleStamper content = new SimpleStamper();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(450,350);
        window.setVisible(true);
    }

    /**
     * This constructor simply sets the background colour of the panel to be black
     * and sets the panel to listen for mouse events on itself.
     */
    public SimpleStamper(){
        setBackground(Color.BLACK);
        addMouseListener(this);
    }


    /**
     * Since this panel has been set to listen for mouse events on itself,
     * this method will be called when the user clicks the mouse on the panel.
     * This method is part of the MouserListner intereface.
     * @param e MouseEvent generated when it is pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if(e.isShiftDown()){
            // The user was holding down the Shift key. Just repaint the panel.
            // Since this class does not define a paintComponent() method,
            // the method from the superclass, JPanel, is called.
            // That method simply fills the panel with the background colour, black.
            // The effect is to clear the panel.
            repaint();
            return;
        }
        int x = e.getX();   // x-coordinate where user clicked.
        int y = e.getY();   // y-coordinate where user clicked.

        Graphics g = getGraphics(); // Graphics context for drawing directly.
                                    // Note: this is considered to be bad style!!!

//        if(e.isMetaDown()){ // does not work in my env, so replaced by the following.
        if(e.getButton() == MouseEvent.BUTTON3){
            // User right-clicked at the point(x,y). Draw a blue oval centered at the point(x,y).
            // (A white outline around the oval will make it more distinct when shapes overlap)
            g.setColor(Color.BLUE);
            g.fillOval(x-30, y-15, 60,30);
            g.setColor(Color.WHITE);    // White outline
            g.drawOval(x-30, y-15, 60, 30);
        } else {
            // User left-clicked (or middle-clicked) at (x,y).
            // Draw the red rectangle centered at (x,y).
            g.setColor(Color.MAGENTA);
            g.fillRect(x-30, y-15, 60,30);
            g.setColor(Color.WHITE);    // White outline
            g.drawRect(x-30, y-15, 60, 30);
        }

        g.dispose();    // We are finished with the graphics context, so dispose of it.
    }   // end mousePressed.

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}   // end class SimpleStamper.
