package javacodegeeks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseDragExample extends Component implements MouseListener, MouseMotionListener {

    int sX = -1, sY = -1;
    static Label stat;
    Image bImage;
    boolean dragging = false;
    int curX = -1, curY = -1;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mouse Dragger");
        Container cPane = frame.getContentPane();
        Image im = Toolkit.getDefaultToolkit().getImage("C:/User/arisa/Pictures/apple-icon.png");
        MouseDragExample ex = new MouseDragExample(im);
        cPane.setLayout( new BorderLayout() );
        cPane.add( BorderLayout.NORTH, new JLabel("") );
        cPane.add( BorderLayout.CENTER, ex );
        cPane.add( BorderLayout.SOUTH, stat = new Label() );
        stat.setSize( frame.getSize().width, stat.getSize().height );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible( true );
    }

    public MouseDragExample( Image i ){

        super();
        bImage = i;
        setSize( 300,200 );
        addMouseListener( this );
        addMouseMotionListener( this );
    }

    public void showStatus(String s){
        stat.setText(s);
    }

    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) {}

    @Override
    public void mousePressed(MouseEvent evt) {
        Point point = evt.getPoint();
        System.out.println("mouse Pressed at " + point);
        sX = point.x;
        sY = point.y;
        dragging = true;
    }
    @Override
    public void mouseReleased(MouseEvent evt) {
        dragging = false;
        System.out.println("Drawn rectangle area IS " + sX + ", " + sY + " to " + curX + ", " + curY);
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point p = evt.getPoint();
//        System.err.println("mouse drag to " + p);
        showStatus("mouse Dragged to " + p);
        curX = p.x;
        curY = p.y;
        if(dragging)
            repaint();
    }


    public void paint(Graphics g){
        int w = curX - sX, h = curY - sY;
        Dimension dims = getSize();
        g.drawImage(bImage, 0,0,dims.width, dims.height, this);
        if(sX < 0 || sY < 0)
            return;
        System.out.println("Rect[" + sX + ", " + sY + "] size: " + w + " x " + h);
        g.setColor(Color.RED);
        g.fillRect(sX,sY,w,h);
    }



    @Override
    public void mouseMoved(MouseEvent evt) {
        showStatus("Mouse to " + evt.getPoint() );

    }
}
