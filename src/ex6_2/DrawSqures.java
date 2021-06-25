package ex6_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawSqures extends JPanel implements MouseMotionListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("Draw Squares");
        DrawSqures content = new DrawSqures();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120,70);
        window.setSize(400,300);
        window.setVisible(true);
    }

    int colorCode; // 0 = blue, 1 = red
    Square red, blue;

    /**
     * This constructor sets
     */
    public DrawSqures(){
        setBackground(Color.WHITE);
        setForeground(Color.GREEN);
        setPreferredSize(new Dimension(370, 150));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(10,10,100,100);
        g.setColor(Color.BLUE);
        g.fillRect(100,100,100,100);
    }


    private class Square extends JPanel{

        private Square() {

        }


    }
}
