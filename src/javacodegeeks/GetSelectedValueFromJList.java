package javacodegeeks;

// https://examples.javacodegeeks.com/desktop-java/swing/jlist/get-selected-value-from-jlist/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetSelectedValueFromJList extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JList<Object> list;
    private JButton btn;

    public GetSelectedValueFromJList(){

        // Set flow layout for the frame
        this.getContentPane().setLayout( new FlowLayout() );
        Object[] data = {"Value 1", "Value 2", "Value 3", "Value 4", "Value 5"};
        list = new JList<>( data );
        btn = new JButton( "Check" );
        btn.addActionListener(this);

        add(list);
        add(btn);
    } // end: constructor

    public void actionPerformed(ActionEvent evt ){
        if(evt.getActionCommand().equals("Check") ){
            int index = list.getSelectedIndex(); // the index of the selected item in the list
            System.out.println("Index selected: " + index );
            String s = (String)list.getSelectedValue(); // the value of the selected item
            System.out.println("Value selected: " + s);
        }
    } // end: actionPerformed

    private static void createAndShowGUI(){
        JFrame frame = new GetSelectedValueFromJList();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
