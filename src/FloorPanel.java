import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloorPanel extends JPanel implements ActionListener {

    Floor floor;
    JLabel floorWidth, floorLength, notificationLabel;


    public FloorPanel(){
        setBackground(Color.WHITE);
        setLayout( new GridLayout(3,2) );
        setPreferredSize( new Dimension(400,300) );

        notificationLabel = new JLabel("Input the size of floor, then click set button.");
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
}
