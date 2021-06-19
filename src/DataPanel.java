import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {

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
//        setBtn.addActionListener();

        // Layout
//        setLayout(new GridLayout(0,3));
        // show only user input and xLabel, mmLabel, and setBtn.
        //TODO change the layout with more components.

        add(floorWidth);
        add(xLabel);
        add(floorLength);
        add(setBtn);

//        repaint();

    }
}
