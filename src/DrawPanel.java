import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel extends JPanel {

    private DataPanel dataPanel;
    private FloorPanel floorPanel;
    private JTextField floorWidthTxt, floorLengthTxt;
    private JLabel notificationLabel, floorWidth, floorLength;

    DrawPanel(){
        setBackground(Color.DARK_GRAY);

        dataPanel = new DataPanel();
        floorPanel = new FloorPanel();

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.SOUTH);
        add(floorPanel,BorderLayout.CENTER);
    } // end: Constructor DrawPanel3

    private class DataPanel extends JPanel implements ActionListener {

        JLabel floorName;
        JButton setBtn;

        DataPanel(){
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            floorName = new JLabel("Floor");
            floorWidthTxt = new JTextField("0", 10);
            floorLengthTxt = new JTextField("0", 10);
            setBtn = new JButton("Set");
            setBtn.addActionListener(this);

//            // Layout
//            setLayout(new GridLayout(0,3));
//            // show only user input and xLabel, mmLabel, and setBtn.
//            //TODO change the layout with more components.

            add(floorWidthTxt);
            add(new JLabel("(width) x "));
            add(floorLengthTxt);
            add(new JLabel("(length) mm "));
            add(setBtn);

        }

        public void actionPerformed(ActionEvent evt){
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set")) {
                String floorWidthText = floorWidthTxt.getText() + "mm (width)";
                String floorLengthText = floorLengthTxt.getText() + " mm (length)";
                String floorNotification = "The size of your floor: " + floorWidthText +
                        " x " + floorLengthText + ".";
                notificationLabel.setText(floorNotification);
                floorWidth.setText(floorWidthText);
                floorLength.setText(floorLengthText);
            }
            repaint();
        }
    } // end: nested-inner class DataPanel

    public class FloorPanel extends JPanel {

        public FloorPanel(){
            setBackground(Color.WHITE);
            setPreferredSize( new Dimension(400,300) );

            floorWidth = new JLabel("0 mm (width)", JLabel.CENTER);
            floorLength = new JLabel("0 mm (length)", JLabel.CENTER);
            floorLength.setUI(new VerticalLabelUI(true));
            notificationLabel = new JLabel(
                    "Input the size of floor, then click set button.",
                    JLabel.CENTER);

            setLayout( new BorderLayout(10,10) );
            add(floorWidth, BorderLayout.NORTH);
            add(notificationLabel, BorderLayout.SOUTH);
            add(floorLength, BorderLayout.EAST);
        } // end: constructor

        /**
         * This method draws the floor image in FloorPanel.
         * @param g Graphic context
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setForeground(Color.GRAY);
            g.fillRect(50,50,675,400);
        }

    } // end: nested class FloorPanel

}
