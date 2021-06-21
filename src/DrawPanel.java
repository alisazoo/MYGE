import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private Floor floor = new Floor();
    private ArrayList<Furniture> furnitureList;

    private DataPanel dataPanel;
    private FloorPanel floorPanel;
    private FurniturePanel furniturePanel;
    private JTextField floorWidthTxt, floorLengthTxt;
    private JLabel notificationLabel, floorWidth, floorLength;

    DrawPanel(){
        setBackground(Color.DARK_GRAY);

        dataPanel = new DataPanel();
        floorPanel = new FloorPanel();
        furniturePanel = new FurniturePanel();

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.NORTH);
        add(floorPanel,BorderLayout.CENTER);
        add(furniturePanel, BorderLayout.SOUTH);
    } // end: Constructor DrawPanel3

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
            //TODO check how the gaps above are reflected.
            add(floorWidth, BorderLayout.SOUTH);
            add(notificationLabel, BorderLayout.NORTH);
            add(floorLength, BorderLayout.EAST);
        } // end: constructor

        /**
         * This method draws the floor image in FloorPanel.
         * @param g Graphic context
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setForeground(Color.LIGHT_GRAY);
            g.fillRect(50,50,675,400);
        }

    } // end: nested class FloorPanel

    private class DataPanel extends JPanel implements ActionListener {

        JLabel floorName;
        JButton setBtn, addBtn;

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
                int floorWidthNum = Integer.parseInt(floorWidthTxt.getText());
                int floorLengthNum = Integer.parseInt(floorLengthTxt.getText());

                // Set the width and length of the floor
                floor.setWidth(floorWidthNum);
                floor.setLength(floorLengthNum);

                // Display the information about the floor in the DataPanel.
                String floorWidthText = floorWidthNum + "mm (width)";
                String floorLengthText = floorLengthNum + " mm (length)";
                String floorNotification = "The size of your floor: " + floorWidthText +
                        " x " + floorLengthText + ".";
                notificationLabel.setText(floorNotification);
                floorWidth.setText(floorWidthText);
                floorLength.setText(floorLengthText);
            }
            repaint();
        }
    } // end: nested-inner class DataPanel

    private class FurniturePanel extends JPanel implements ActionListener {

        JLabel furnitureName;
        JButton setBtn, addBtn;

        FurniturePanel() {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            JTextField furnitureName = new JTextField("(Furtniture Name)", 20);
            JTextField furniturWidthTxt = new JTextField("0", 10);
            JTextField furniturLengthTxt = new JTextField("0", 10);

            addBtn = new JButton("Add Furniture");
//            addBtn.addActionListener(this);
            setBtn = new JButton("Set");
//            setBtn.addActionListener(this);

//            // Layout
//            setLayout(new GridLayout(0,3));
//            // show only user input and xLabel, mmLabel, and setBtn.
//            //TODO change the layout with more components.

            add(addBtn);
            add(furnitureName);
        }

        private void addFurnitureInputArea(){

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            if(cmd.equals("Add Furniture")){
                furnitureName.setText("Hello");

                repaint();
            }

        }
    } // end: nested-inner class FurniturePanel

}
