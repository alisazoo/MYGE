import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel extends JPanel {

    private DataPanel dataPanel;
    private FloorPanel floorPanel;
    private JTextField floorWidthTxt, floorLengthTxt;
    private JLabel notificationLabel;

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
            add(new JLabel(" x "));
            add(floorLengthTxt);
            add(new JLabel(" mm "));
            add(setBtn);

        }

        public void actionPerformed(ActionEvent evt){
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set")) {
                String floorWidthText = floorWidthTxt.getText();
                String floorLengthText = floorLengthTxt.getText();
                String floorNotification = "The size of your floor is " + floorWidthText +
                        " mm (width) x " + floorLengthText + " mm (length).";
                System.out.println(floorNotification);
                notificationLabel.setText(floorNotification);
            }
            repaint();
        }
    } // end: nested-inner class DataPanel

    public class FloorPanel extends JPanel {

        public FloorPanel(){
            setBackground(Color.WHITE);
            setPreferredSize( new Dimension(400,300) );

            notificationLabel = new JLabel(
                    "Input the size of floor, then click set button.",
                    JLabel.CENTER);

            setLayout( new GridLayout(3,2) );
            add(notificationLabel);
        } // end: constructor

    } // end: nested class FloorPanel

}
