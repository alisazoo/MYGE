package headFirstJava.ch12;

import javax.swing.*;
import java.awt.*;

// Remove the implementation of ActionListener to combine with SimpleGUI3C.
//public class RandomColouredCircleGradient extends JPanel implements ActionListener {
public class RandomColouredCircleGradient extends JPanel {

    static RandomColouredCircleGradient content = new RandomColouredCircleGradient();
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

    public RandomColouredCircleGradient() {
        setSize(300,300);

        // Remove this button to combine with SimpleGUI3C.
//        btn.addActionListener(this);
//        add(btn);
    }

    public void paintComponent(Graphics g) {

        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        Color startColor = new Color(red, green, blue);

        red = (int) (Math.random() * 256);
        green = (int) (Math.random() * 256);
        blue = (int) (Math.random() * 256);
        Color endColor = new Color(red, green, blue);

        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(70,70,startColor,
                150,150,endColor);
        g2.setPaint(gradient);
        g2.fillOval(70,70,100,100);
//        g.setColor(randomColor);
//        g.fillOval(70, 70, 100, 100);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }
}
