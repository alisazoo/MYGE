package practice.java2s;

// Practice to tries around JList and ListModel
// Based on...
// Java Swing, 2nd Edition
// By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
// ISBN: 0-596-00408-7
// Publisher: O'Reilly

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListModelExample2 extends JPanel {

    JList list;
    DefaultListModel model;
    int counter = 15;

    public ListModelExample2(){
        setLayout(new BorderLayout());
        model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        JButton addBtn = new JButton("Add Element");
        JButton removeBtn = new JButton("Remove Element");
        for( int i = 0; i < 15; i++ )
            model.addElement( "Element " + i );

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addElement("Element " + counter);
                counter++;
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if( model.getSize() > 0 )
//                    model.removeElementAt(0);

            }
        });

        add( pane, BorderLayout.NORTH );
        add( addBtn, BorderLayout.WEST );
        add( removeBtn, BorderLayout.EAST );
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("List Model Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane( new ListModelExample() );
        frame.setSize( 260, 200 );
        frame.setVisible(true);
    }
}
