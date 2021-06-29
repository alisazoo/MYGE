import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private ArrayList<Furniture> furnitureList;

    private DataPanel dataPanel;
    private FloorPanel floorPanel;
    private FurniturePanel furniturePanel;
    private JTextField floorWidthTxt, floorLengthTxt;
    private JLabel notificationLabel, floorWidth, floorLength;

    DrawPanel(Floor floor){
        setBackground(Color.DARK_GRAY);

        dataPanel = new DataPanel();
        floorPanel = new FloorPanel(floor);
        furniturePanel = new FurniturePanel();

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.NORTH);
        add(floorPanel,BorderLayout.CENTER);
        add(furniturePanel, BorderLayout.SOUTH);
    } // end: Constructor DrawPanel3

    public class FloorPanel extends JPanel {

        public FloorPanel(Floor floor){
            setBackground(Color.WHITE);
            setPreferredSize( new Dimension(400,300) );

            floorWidth = new JLabel(floor.getWidth() + " mm (width)", JLabel.CENTER);
            floorLength = new JLabel(floor.getLength() + "mm (length)", JLabel.CENTER);
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

            setBtn = new JButton("Set Floor Size");
            setBtn.addActionListener(this);
        }

        DataPanel(Floor floor){

//            // Layout
//            setLayout(new GridLayout(0,3));
//            // show only user input and xLabel, mmLabel, and setBtn.
//            //TODO change the layout with more components.

            String floorWidthText = floor.getWidth() + "mm (width)";
            System.out.println("size: "+floor.getWidth());
            add(new JLabel(floorWidthText));
            String floorLengthText = floor.getLength() + "mm (length)";
            add(new JLabel(floorLengthText));
            add(setBtn);
            repaint();
        }

            public void actionPerformed(ActionEvent evt){
            String cmd = evt.getActionCommand();
            if( cmd.equals("Set Floor Size")) {
//                Moyogae.inputFloorSize();
                repaint();

            }
        }
    } // end: nested-inner class DataPanel


    private class FurniturePanel extends JPanel implements ActionListener {

        JLabel furnitureName;
        JButton setBtn, addBtn, deleteBtn;
        JTextField furnitureNameTxt, furnitureWidthTxt, furnitureLengthTxt;
        JLabel furnitureNotice;
        JList<String> furnitureList;
        DefaultListModel<String> furnitureListText = new DefaultListModel<>();
        Furniture items = new Furniture();
        ArrayList<Furniture> furnitureArrayList = items.getFurnitureArrayList();
        String itemText = "";

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

            furnitureNotice = new JLabel("No furniture is registered.", JLabel.CENTER);

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
            add(furnitureNotice);
            furnitureList = new JList<>(furnitureListText);
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
                furnitureNotice.setEnabled(false);
                furnitureListText.addElement( furnitureName +
                        " (" + furnitureWidthNum + "mm x  " +
                        furnitureLengthNum + "mm)" );
                repaint();
            }

        }
    } // end: nested-inner class FurniturePanel

}
