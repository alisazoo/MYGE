package practice.headFirstJava.ch12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoButtons {

    JFrame frame;
    JLabel label;

    public static void main(String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go(){
        frame = new JFrame("Two buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelBtn = new JButton("Change label");
        labelBtn.addActionListener(new LabelListener());

        JButton colorBtn = new JButton("Change colour");
        colorBtn.addActionListener(new ColorListener());

        label = new JLabel("I'm label");
        RandomColouredCircleGradient drawPanel = new RandomColouredCircleGradient();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, colorBtn);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.NORTH, labelBtn);

        frame.setSize(500,300);
        frame.setVisible(true);
    }

    class LabelListener implements ActionListener{

        public void actionPerformed(ActionEvent evt){
            label.setText("Ouch!");
        }
        // issue: the drawPanel will be repainted only for the first time when clicking this button.
        // Why?????
    }

    class ColorListener implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            frame.repaint();
        }
    }
}
