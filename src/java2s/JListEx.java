package java2s;

// http://www.java2s.com/Code/JavaAPI/java.awt.event/MouseEventgetClickCount.htm
// A bit better version than above one!

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JListEx {

    public static void main(final String[] args) {
        final String[] labels = { "A", "B", "C", "D", "E" };
        JFrame frame = new JFrame("Selecting JList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList<String> jlist;
        jlist = new JList<>(labels);
        JScrollPane scrollPane1 = new JScrollPane(jlist);
        frame.add(scrollPane1);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                System.out.println("getSource(): " + theList);
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    System.out.println("index: " + index);
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        System.out.println("Double-clicked on: " + o.toString());
                    }
                }
            }
        };
        jlist.addMouseListener(mouseListener);

        frame.setSize(350, 200);
        frame.setVisible(true);
    }
}