package practice.headFirstJava.ch12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomColouredCircle extends JPanel implements ActionListener {

    static RandomColouredCircle content = new RandomColouredCircle();
    JButton btn = new JButton("click");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Random Coloured Circle");
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(120,70);
        frame.setSize(360,360);
        frame.setBackground(Color.BLACK);
//        frame.pack();
        frame.setVisible(true);
    }

    public RandomColouredCircle() {
        setSize(300,300);
        btn.addActionListener(this);
        add(btn);
    }

    public void paintComponent(Graphics g) {
        int red = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        Color randomColor = new Color(red, green, blue);
        g.setColor(randomColor);
        g.fillOval(70, 70, 100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
