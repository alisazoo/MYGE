package headFirstJava.ch13;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutDemo {

    public static void main(String[] args) {
        BoxLayoutDemo gui = new BoxLayoutDemo();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame("BoxLayout");
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton btn1 = new JButton("shock me!");
        JButton btn2 = new JButton("bliss...");
        JButton btn3 = new JButton("Are you serious?");
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(250,200);
        frame.setVisible(true);

    }
}
