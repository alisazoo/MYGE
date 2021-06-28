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

    /**
     * This constructor sets
     */
    public DrawSqures(){
        setBackground(Color.WHITE);
        setForeground(Color.GREEN);
        setPreferredSize(new Dimension(370, 150));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(10,70,50,50);
        g.setColor(Color.RED);
        g.fillRect(10,10,50,50);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) { }
}
