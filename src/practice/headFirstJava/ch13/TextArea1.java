// Practice to use JTextArea (p.414)

package practice.headFirstJava.ch13;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextArea1 implements ActionListener, ListSelectionListener {

    JTextArea text;

    JList<String> list;
    String selected;

    public static void main(String[] args) {
        TextArea1 gui = new TextArea1();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton btn = new JButton("Just Click it");
        btn.addActionListener(this);
        text = new JTextArea(10,20);
        text.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroller);

        // Practice for JList
        JPanel panel2 = new JPanel();
        String[] listEntries = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta"};
        list = new JList<>(listEntries);
        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        JScrollPane scroller2 = new JScrollPane(list);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel2.add(scroller2);


        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, btn);
        frame.getContentPane().add(BorderLayout.NORTH, panel2);

        frame.setSize(350,300);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        text.append("Bottun Clicked! \n");
        text.append(selected + "\n");
    }

    public void valueChanged(ListSelectionEvent lse){
        if( !lse.getValueIsAdjusting() ){
//            selected = (String) list.getSelectedValue();    // redundant
            selected = list.getSelectedValue();
            System.out.println("Selected text is " + selected);
        }
    }
}
