package practice.headFirstJava.ch12;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI1B implements ActionListener {

    JButton button;

    public static void main(String[] args){
        SimpleGUI1B gui = new SimpleGUI1B();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("Click me");

        button.addActionListener(this);

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt){
        button.setText("I've been clicked.");
    }
}
