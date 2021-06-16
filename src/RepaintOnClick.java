import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * An object of type RepaintOnClick is a MouseListener that
 * will respond to a mousePressed event by calling the repaint()
 * method of the source of the event.
 * That is, a RepaintOnClick object can be added as a mouse listener
 * to any Component; when the user clicks that component, the component
 * will be repainted.
 */
public class RepaintOnClick implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent evt){
        Component source = (Component)evt.getSource();
        source.repaint();   // Call repaint() on the Component that was clicked.
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
