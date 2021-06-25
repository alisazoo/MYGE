package ex6_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SimpleStamper2 extends JPanel implements MouseListener, MouseMotionListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Stamper");
        SimpleStamper2 content = new SimpleStamper2();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(400,300);
        window.setVisible(true);
    }

    private boolean dragging;

    public SimpleStamper2(){
        setBackground(Color.WHITE);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragging = false)
            return;

        int x = e.getX();
        int y = e.getY();
        Graphics g = getGraphics();

        if( SwingUtilities.isRightMouseButton(e) ){
            // if (e.getButton() = MouseEvent.BUTTON3) does not work!
            g.setColor(Color.BLUE);
            g.fillRect(x-30,y-15, 60,30);
            g.setColor(Color.BLACK);
            g.drawRect(x-30, y-15, 60,30);
        } else{
            g.setColor(Color.RED);
            g.fillRect(x-30,y-15, 60,30);
            g.setColor(Color.BLACK);
            g.drawRect(x-30, y-15, 60,30);
        }
        g.dispose();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isShiftDown()){
            dragging = false;
            repaint();
            return;
        }
        dragging = true;
        int x = e.getX();
        int y = e.getY();

        Graphics g = getGraphics();

        if(e.getButton() == MouseEvent.BUTTON3){
            g.setColor(Color.BLUE);
            g.fillRect(x-30,y-15, 60,30);
            g.setColor(Color.BLACK);
            g.drawRect(x-30, y-15, 60,30);
        } else{
            g.setColor(Color.RED);
            g.fillRect(x-30,y-15, 60,30);
            g.setColor(Color.BLACK);
            g.drawRect(x-30, y-15, 60,30);
        }
        g.dispose();
    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

    public void mouseMoved(MouseEvent e) { }
}
