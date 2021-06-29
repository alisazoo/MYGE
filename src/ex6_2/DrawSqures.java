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
public class DrawSqures extends JPanel {

    public static void main(String[] args) {
        JFrame window = new JFrame("Draw Squares");
        DrawSqures content = new DrawSqures();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(400,300);
        window.setVisible(true);
    }

    private int x1, y1;     // Coords of top-left corner of the red square
    private int x2, y2;     // Coords of top-left corner of the blue square

    /**
     * This constructor places two squares in their initial positions and
     * sets up listening for mouse events and mouse motion events.
     */
    public DrawSqures(){

        // Set up initial position of the squares.
        x1 = 10;
        y1 = 10;
        x2 = 50;
        y2 = 50;

        // Set up the basic appearance of the panel.
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//        setPreferredSize(new Dimension(370, 150));

        // Listening object, belonging to nested class below.
        Dragger listener = new Dragger();

        // Set up listening
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    /**
     * paintComponent draws the two squares in their current position.
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);    // Fill with background colour.
        g.setColor(Color.BLUE);
        g.fillRect(x2,y2,50,50);
        g.setColor(Color.RED);
        g.fillRect(x1,y1,50,50);
    }

    /**
     * This private class is used to define the listener that listens for
     * mouse events and mouse motion on the panel.
     */
    private class Dragger implements MouseListener, MouseMotionListener{

        // Variables including what are used during dragging.
        boolean isDragging;         // Set to true when a drag is progress.
        boolean isRedSquare;        // True if red square is being dragged.
        int offsetX, offsetY;       // Offset of mouse-click coords from the top-left
                                    //  corner of the square that was clicked.

        @Override
        public void mousePressed(MouseEvent evt) {
            if( isDragging )        // Exit if a drag is already in progress.
                return;

            int x = evt.getX();
            int y = evt.getY();

            if( x2 <= x && x < x2+30 && y2 <= y && y < y2+30 ){ // Blue square
                isDragging = true;
                isRedSquare = false;
                offsetX = x - x2;   // Distance from corner of square to (x,y)
                offsetY = y - y2;
            }
            else if ( x1 <= x && x < x1+30 && y1 <= y && y < y1+30 ){ // Red square
                isDragging = true;
                isRedSquare = true;
                offsetX = x - x1;   // Distance from corner of square to (x,y)
                offsetY = y - y1;
            }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            isDragging = false;     // Dragging stops when user releases the mouse button.
        }

        @Override
        public void mouseDragged(MouseEvent evt) {

            if( !isDragging )
                return;

            int x = evt.getX();
            int y = evt.getY();

            if(isRedSquare){
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
        public void mouseMoved(MouseEvent e) { }

    }

}
