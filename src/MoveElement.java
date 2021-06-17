import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

public class MoveElement extends JPanel implements MouseListener {
//public class MoveElement extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * This main routine allows this class to be run as a program.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("MoveElement");
        MoveElement content = new MoveElement();
        window.setContentPane(content);
        window.setSize(800, 600);
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /* The following variables are used when the user is moving the
        furniture while dragging a mouse. */
//    private int prevX, prevY;       // The previous location of the mouse.
//    private boolean dragging;       // This is set to true while the user is dragging.
    private Graphics graphicsForDragging; // A graphics context for the panel
                                            // that is used to move the furniture.

    /**
     * Constructor for MoveElement class sets:
     * - background colour to be white
     * - it to listen for mouse events on itself.
     */
    MoveElement(){
        setBackground(Color.WHITE);
        addMouseListener(this);
//        addMouseMotionListener(this);
    }

    /**
     * Draw the contents of the panel.
     * Since no information is saved about how the user dragged,
     * the user's movement is erased whenerver this routine is called.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);        // fill wiht background colour, white.
        int width = getWidth();         // Width of the panel.
        int length = getHeight();       // Height of the panel.

        g.setColor(Color.GRAY);
        g.fillRect(10,10,3600/10, 2400/10);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();               // x-coordinate where the user clicked.
        int y = e.getY();               // y-coordinate where the user clicked.

        graphicsForDragging.setColor(Color.MAGENTA);
        graphicsForDragging.fillRect(x,y,100,200);
//        if(dragging == true)
//            return;
//        dragging = true;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
//        if(dragging == false)
//            return;
//        dragging = false;
        graphicsForDragging.dispose();
        graphicsForDragging = null;
    }

//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if (dragging == false)
//            return;
//        int x = e.getX();
//        int y = e.getY();
//
//        graphicsForDragging.fillRect(x,y,100, 80);
//        prevX = x;
//        prevY = y;
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

}
