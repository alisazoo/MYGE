package headFirstJava.ch12;
// from "Be the Compiler" 2/7/2021

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerButton {
    JFrame frame;
    JButton b;

    public static void main(String[] args) {
        InnerButton gui = new InnerButton();
        gui.go();
    }

    public void go(){
        frame = new JFrame("Inner Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b = new JButton("A");
        b.addActionListener(new BListener());

        frame.getContentPane().add(BorderLayout.SOUTH, b);
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    class BListener implements ActionListener {
        public void actionPerformed(ActionEvent evt){
            if(b.getText().equals("A"))
                b.setText("B");
            else
                b.setText("A");
        }
    }
}
