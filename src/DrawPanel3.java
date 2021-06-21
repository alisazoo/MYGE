import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel3 extends JPanel {

    DataPanel dataPanel;
    FloorPanel floorPanel;

    DrawPanel3(){
        setBackground(Color.DARK_GRAY);

        dataPanel = new DataPanel();
        floorPanel = new FloorPanel();

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.SOUTH);
        add(floorPanel,BorderLayout.CENTER);
//        repaint();
    } // end: Constructor DrawPanel3

    private class DataPanel extends JPanel implements ActionListener {

        Floor floor;
        JLabel floorName, xLabel;
        JTextField floorWidth, floorLength;
        JButton setBtn;

        DataPanel(){
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            xLabel = new JLabel(" x ");

            floorName = new JLabel("Floor");

            floorWidth = new JTextField(" (width: mm)", 10);
            floorLength = new JTextField(" (length: mm)", 10);

            setBtn = new JButton("Set");
//            setBtn.addActionListener(floorPanel);
            setBtn.addActionListener(this);
            // Layout
//        setLayout(new GridLayout(0,3));
            // show only user input and xLabel, mmLabel, and setBtn.
            //TODO change the layout with more components.

            add(floorWidth);
            add(xLabel);
            add(floorLength);
            add(setBtn);

            repaint();

        }

        public void actionPerformed(ActionEvent evt){
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set"))
                setBtn.setText("I am listenig!");
            repaint();
        }
    }// end: nested-inner class DataPanel

    public class FloorPanel extends JPanel implements ActionListener {

        Floor floor;
        JLabel floorWidth, floorLength, notificationLabel;

        public FloorPanel(){
            setBackground(Color.WHITE);
            setPreferredSize( new Dimension(400,300) );

            notificationLabel = new JLabel("Input the size of floor, then click set button.");

            setLayout( new GridLayout(3,2) );
            add(notificationLabel);

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set")) {
                //TODO set the values for floor
                JLabel floorData = new JLabel();
                String floorWidthText = floorWidth.getText();
                String floorLengthText = floorLength.getText();
                String floorText = "The size of your floor is " + floorWidthText +
                        " mm (width) x " + floorLengthText + " mm (length).";
                floorData.setText(floorText);
                add(floorData);

                repaint();
            }
        }
    } // end: nested class FloorPanel
}
