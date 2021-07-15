package practice.headFirstJava.ch13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxLayoutDemo implements ActionListener {

    private JTextField field;
    JTextArea text;

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

        // Practice1: JTextField = user input in line
        JPanel panel2 = new JPanel();
        field = new JTextField("Your name");
        JTextField fieldEmpty = new JTextField(20); // 20 = 20 columns = default preferred width
        System.out.println(field.getText());    // get out of the input
        // field.setText("NAME");
        // field.setText(); // clear the field
        // field.addActionListener(MyActionListener);
        panel2.add(new JLabel("What's your name?"));
        panel2.add(field);
//        panel2.add(fieldEmpty);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);

        frame.setSize(450,400);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        text.append("Button Clicked \n");
    }

//    private class MyActionListener implements ActionListener{
//        public void actionPerformed(ActionEvent evt){
//            System.out.println(field.getText());    // get out of the input
//            field.selectAll();      // select/highlight the text in the field
//            field.requestFocus();   // put cursor back in the field
                                    // --> the user can just start typing
//        }

}
