package ex6_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A panel showing a red square and a blue square that the user can
 * drag with the mouse. The user can drag the squares off the panel
 * and drop them. There is no way of getting them back.
 */
public class DragTwoSquares extends JPanel {

    public static void main(String[] args) {
        JFrame window = new JFrame("Drag Either Square");
        DragTwoSquares content = new DragTwoSquares();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120, 70);
        window.setSize(400,300);
        window.setVisible(true);
    }

    //-------------------------------------------------------------

    private int x1, y1;     // Coords of top-left corner of the red square.
    private int x2, y2;     // Coords of top-left corner of the blue square.

    /**
     * The constructor places the two squares in their initial positions
     * and sets up listening for mouse events and mouse motion events.
     */
    public DragTwoSquares(){
        x1 = 10;    // Set up initial positions of the squares.
        y1 = 10;
        x2 = 50;
        y2 = 10;

        setBackground(Color.LIGHT_GRAY);    // Set up appearance of the panel.
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Dragger listener = new Dragger();   // Listening object, belonging to a nested class below.

        addMouseListener(listener);         // Set up listening.
        addMouseMotionListener(listener);
    }

    /**
     * paintComponent just draws the two squares in their current positions.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);            // Fill with background colour
        g.setColor(Color.RED);
        g.fillRect(x1, y1, 30, 30);
        g.setColor(Color.BLUE);
        g.fillRect(x2,y2,30,30);
    }

    /**
     * This private class is used to define the listener that listens for
     * mouse events and mouse motion on the panel.
     */
    private class Dragger implements MouseListener, MouseMotionListener{

        /* Some variables used during dragging */
        boolean dragging;       // Set to true when a drag is progress.
        boolean dragRedSquare;  // True if red square is being dragged.
                                //  False if blue square is being dragged.
        int offsetX, offsetY;   // Offset of mouse-click coordinates from
                                //  the top-left corner of the square that was clicked.

        public void mousePressed(MouseEvent evt){
            if( dragging )      // Exit if a drag is already in progress.
                return;

            int x = evt.getX();
            int y = evt.getY();

            if( x >= x2 && x < x2+30 && y >= y2 && y < y2+30){
                // It's the blue square (which should be checked first,
                //  since it's drawn on top of the red square.)
                dragging = true;
                dragRedSquare = false;
                offsetX = x - x2;       // Distance from corner of square to (x,y)
                offsetY = y - y2;
            }
            else if (x >= x1 && x < x1 + 30 && y >= y1 && y < y1 + 30){
                // It's the red square.
                dragging = true;
                dragRedSquare = true;
                offsetX = x - x1;       // Distance from corner of square to (x,y)
                offsetY = y - y1;
            }
        }

        /**
         * Dragging stops when user releases the mouse button.
         */
        public void mouseReleased(MouseEvent evt){
            dragging = false;
        }

        /**
         * Respond when the user drags the mouse. If a square is not being dragged,
         * then exit. Otherwise, change the position of the square that is being
         * dragged to match the position of the mouse.
         * Note: the corner of the square is placed in the same relative position
         * with respect to the mouse that I had when the user started dragging it.
         */
        public void mouseDragged(MouseEvent evt){

            if(dragging == false)
                return;
            int x = evt.getX();
            int y = evt.getY();
            if(dragRedSquare){
                x1 = x - offsetX;
                y1 = y - offsetY;
            }
            else{
                x2 = x - offsetX;
                y2 = y - offsetY;
            }
            repaint();
        }

        public void mouseClicked(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
        public void mouseMoved(MouseEvent e) {
        }

    }

}
