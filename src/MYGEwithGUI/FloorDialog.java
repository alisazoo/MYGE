package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FloorDialog {

    static boolean isDuplicateFurniture = false;
    static String[] result = new String[3];
    static ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

    /**
     * This inputFloorData method create the input dialog window, then
     * assign each values to the Floor class.
     */
    public static void inputFloorDialog(){

        {   // TODO: uncomment out the following code to use dialog input
            JPanel panel = new JPanel(new BorderLayout(5, 5));

            JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
            label.add(new JLabel("Enter the size:"));
            label.add(new JLabel("Floor Width (cm)", SwingConstants.RIGHT));
            label.add(new JLabel("Floor Length (cm)", SwingConstants.RIGHT));
            panel.add(label, BorderLayout.WEST);

            JPanel control = new JPanel(new GridLayout(0, 1, 2, 2));
            control.add(new JLabel(""));//todo change layout manager and remove this unnecessary JLabel
            JTextField floorWidthField = new JTextField();
            control.add(floorWidthField);
            JTextField floorLengthField = new JTextField();
            control.add(floorLengthField);
            panel.add(control, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(null, panel,
                    "Set Floor Size", JOptionPane.QUESTION_MESSAGE);
            boolean isSetFloorData = false;
            while (!isSetFloorData) {
                try {
                    Floor.setWidth(Integer.parseInt(floorWidthField.getText()));
                    Floor.setLength(Integer.parseInt(floorLengthField.getText()));
                    isSetFloorData = true;
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter number for both width and length.");
                    JOptionPane.showMessageDialog(null, panel,
                            "Set Floor Size", JOptionPane.QUESTION_MESSAGE);
                }
            }
        }
//        // TODO: delete the following test data
//        Floor.setWidth(350);
//        Floor.setLength(210);

    }

}
