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
                    "",
                    JLabel.CENTER);

            setLayout( new BorderLayout(5,5) );
            //TODO check how the gaps above are reflected.
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
            setBtn = new JButton("Set Floor Size");
            setBtn.addActionListener(this);

//            // Layout
//            setLayout(new GridLayout(0,3));
//            // show only user input and xLabel, mmLabel, and setBtn.
//            //TODO change the layout with more components.

            add(floorWidthTxt);
            add(new JLabel("mm (width) x "));
            add(floorLengthTxt);
            add(new JLabel("mm (length)"));
            add(setBtn);

        }

            public void actionPerformed(ActionEvent evt){
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set Floor Size")) {
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
        JButton setBtn, addBtn, deleteBtn;
        JTextField furnitureNameTxt, furnitureWidthTxt, furnitureLengthTxt;
        JLabel furnitureList;
        String list;
        Furniture items = new Furniture();

        FurniturePanel() {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            furnitureNameTxt = new JTextField("e.g. desk", 15);
                //TODO if the input is empty should be handled as exception.
            furnitureWidthTxt = new JTextField("0", 5);
            furnitureLengthTxt = new JTextField("0", 5);

            addBtn = new JButton("Add Furniture");
            addBtn.addActionListener(this);
            setBtn = new JButton("Set Floor Size");
            setBtn.addActionListener(this);
            // this delete button only appears after the data is set
            deleteBtn = new JButton("Delete this furniture");
            deleteBtn.addActionListener(this);

            furnitureList = new JLabel("No furniture is registered.", JLabel.CENTER);

            // Layout
            setLayout(new GridLayout(0,2));
            // show only user input and xLabel, mmLabel, and setBtn.
            //TODO change the layout with more components.

            add(new JLabel("Furniture name:"));
            add(furnitureNameTxt);
            add(new JLabel("width (mm)"));
            add(furnitureWidthTxt);
            add(new JLabel("length (mm)"));
            add(furnitureLengthTxt);
            add(addBtn);
            add(new JLabel("")); // to adjust components
            add(furnitureList);
        }

        private void addFurnitureInputArea(){

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            int furnitureWidthNum = Integer.parseInt(furnitureWidthTxt.getText());
            int furnitureLengthNum = Integer.parseInt(furnitureLengthTxt.getText());
            String furnitureName = furnitureNameTxt.getText();
            if(cmd.equals("Add Furniture")){
                items.addFurniture(furnitureName, furnitureWidthNum, furnitureLengthNum);
                furnitureList.setText(items.getFurniturelistText());
                repaint();
            }

        }
    } // end: nested-inner class FurniturePanel

}
