package headFirstJava.ch12;

import javax.swing.*;

public class SimpleGUI1 {

    public static void main (String[] arg){

        JFrame frame = new JFrame();
        JButton button = new JButton("Click me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);

    }

}