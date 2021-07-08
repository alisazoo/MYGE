package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MoyogaeDemo extends JPanel {

    JFrame frame;
    JPanel mainPanel, furniturePanel;
    FloorPanel floorPanel;

    DefaultListModel<String> listModel;
    JList<String> furnitureList;

    static boolean isDuplicateFurniture = false;
    static String[] result = new String[3];

    /**
     * This main routine allow to use this program as an application.
     */
    public static void main(String[] args) {

        MoyogaeDemo main = new MoyogaeDemo();
        inputFloorData();
        main.buildGUI();

    }

    /**
     * This method creates a frame and makes it visible.
     */
    public void buildGUI(){

        frame = new JFrame("Moyogae Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();

        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.Y_AXIS) );
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setPreferredSize( new Dimension( 480, 500 ) );

        floorPanel = new FloorPanel();
        floorPanel.setPreferredSize(new Dimension(440,350));

        furniturePanel = new JPanel(layout);
        furniturePanel.setBackground(Color.GRAY);
        furniturePanel.setPreferredSize( new Dimension(400, 150));

        //------floorPanel-------------------------------------------
        JLabel floorWidth = new JLabel( Floor.getWidth() + " mm (width)" + " x " +
                Floor.getLength() + " mm (length)", JLabel.CENTER);
        Dragger dragListener = new Dragger();
        floorPanel.addMouseListener( dragListener );
        floorPanel.addMouseMotionListener( dragListener );
        floorPanel.add( BorderLayout.NORTH, floorWidth );

        //------furniturePanel----------------------------------------

        listModel = new DefaultListModel<>();
        for (Furniture item: Furniture.getFurnitureArrayList() ){
            String s = item.getName() + ": " + item.getWidth() + " mm x " + item.getLength() + " mm";
            listModel.addElement(s);
        }
        furnitureList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(furnitureList);
        furniturePanel.add( BorderLayout.CENTER, scrollPane);

        JButton addItem = new JButton("Add Furniture");
        addItem.addActionListener(new addItemListener() );
        furniturePanel.add( BorderLayout.EAST, addItem);

        JButton remItem = new JButton("Remove Furniture");
        remItem.addActionListener(new remItemListener() );
        furniturePanel.add( BorderLayout.SOUTH, remItem);

        mainPanel.add( floorPanel );
        mainPanel.add( furniturePanel );

        frame.setLocationRelativeTo(null);
        frame.setContentPane(mainPanel);
        frame.setBounds(50,50,500,650);
        frame.pack();
        frame.setVisible(true);
    } // end: buildGUI()



    //--------- input Dialog: floor ---------------------------------------

    /**
     * This inputFloorData method create the input dialog window, then
     * assign each values to the Floor class.
     */
    public static void inputFloorData(){

// TODO: uncomment out the following code to use dialog input
        // Input Dialog
//        JPanel panel = new JPanel(new BorderLayout(5,5));
//
//        JPanel label = new JPanel(new GridLayout(0,1,2,2));
//        label.add( new JLabel("Enter the size:") );
//        label.add( new JLabel("Floor Width (mm)", SwingConstants.RIGHT) );
//        label.add( new JLabel("Floor Length (mm)", SwingConstants.RIGHT) );
//        panel.add( label, BorderLayout.WEST );
//
//        JPanel control = new JPanel(new GridLayout(0,1,2,2));
//        control.add( new JLabel(""));   // to fill the empty space for layout
//        JTextField floorWidthInput = new JTextField();
//        control.add(floorWidthInput);
//        JTextField floorLengthInput = new JTextField();
//        control.add(floorLengthInput);
//        panel.add(control, BorderLayout.CENTER);
//
//        JOptionPane.showMessageDialog(null, panel,
//                "Set Floor Size", JOptionPane.QUESTION_MESSAGE);
//
//        // Set the input data into the floor object
//        Floor.setWidth( Integer.parseInt(floorWidthInput.getText()) );
//        Floor.setLength( Integer.parseInt(floorLengthInput.getText()) );

        // TODO: delete the following test data
        Floor.setWidth(3000);
        Floor.setLength(2100);

    }   // end inputFloorSize() method


    //--------- input Dialog: furniture -------------------------------------
    /**
     * This static inputFurnitureData method aims to set the new item in the ArrayList<Furniture>.
     * The data of the new item is sent from input dialog.
     */
    public static void inputFurnitureData(
            ArrayList<Furniture> arrayList, DefaultListModel<String> listModel){

        do{
            result = inputFurnitureDialog();
        } while (isDuplicateFurniture);

        // Set the input data into the local variables
        String name = result[0];
        int width, length;
        width = Integer.parseInt( result[1] );
        length = Integer.parseInt( result[2] );

        // new data is assigned to the ArrayList
        Furniture item = new Furniture(name, width, length);
        arrayList.add(item);

        // Set the input data into the DefaultListModel
        String newItemTxt = name + ": " + width + " mm x " + length + " mm";
        listModel.addElement(newItemTxt);

    }   // end: inputFurnitureData() method

    /**
     * This static method inputFurnitureDialog create the input dialog
     * for user to input the data of new furniture item. If the input name of the new item
     * is same as the existing one, this program requires to input again.
     * @return array with String value of name, width, and length of the new item.
     */
    public static String[] inputFurnitureDialog(){

        // Delete an invalid element if the array contains.
        if( isDuplicateFurniture ){
            //TODO: find more sophisticated way!.
            result = new String[3];
        }

        // set up the main panel for Input Dialog
        JPanel panel = new JPanel(new BorderLayout(5,5));

        // set up labelPanel
        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Name", SwingConstants.RIGHT) );
        label.add( new JLabel("Width (mm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (mm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        // set up controlPanel
        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        JTextField furnitureName = new JTextField();
        control.add( furnitureName );
        JTextField furnitureWidthInput = new JTextField();
        control.add(furnitureWidthInput);
        JTextField furnitureLengthInput = new JTextField();
        control.add(furnitureLengthInput);

        // add components onto the main panel
        if(isDuplicateFurniture) {
            panel.add( new JLabel("You cannot add duplicated item. " +
                    "Please make the new one with different name."), BorderLayout.NORTH );
        } else {
            panel.add( new JLabel("Enter the data of the new item."), BorderLayout.NORTH);
        }
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Add new furniture", JOptionPane.QUESTION_MESSAGE);

        // keep the input data into the String array, infoArray.
        String name = furnitureName.getText();
        String width = furnitureWidthInput.getText();
        String length = furnitureLengthInput.getText();

        String[] infoArray = new String[3];
        infoArray[0] = name;
        infoArray[1] = width;
        infoArray[2] = length;

        System.out.println("infoArray: " + infoArray[0] + " is " + infoArray[1] +
                " mm(w) " + infoArray[2] + "mm(l)");

        // If the name of the new input item is the same as one of the existing item,
        // isDuplicateFurniture set as true.
        for (Furniture newItem: Furniture.getFurnitureArrayList()){
            if(newItem.getName().equals(name)){
                isDuplicateFurniture = true;
                break;
            } else {
                isDuplicateFurniture = false;
            }
        }

        return infoArray;
    } // end: inputFurnitureDialog() method





    //-----Action Listener--------------------------------------------

    private class addItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            inputFurnitureData(Furniture.getFurnitureArrayList(), listModel);
            floorPanel.repaint();
        }
    }

    private class remItemListener implements ActionListener{

        public void actionPerformed(ActionEvent evt){

            if(!listModel.isEmpty()) {

                int index = furnitureList.getSelectedIndex();
                String str = furnitureList.getSelectedValue();

                // Delete the existing item from JList
                listModel.removeElementAt(index);

                // Delete the existing item from furnitureArrayList
                int strIndex = str.indexOf(":");
                String subtractText = str.substring(0, strIndex);

                for(Furniture item: Furniture.getFurnitureArrayList() ){
                    if( item.getName().equals(subtractText) ){
                        Furniture.getFurnitureArrayList().remove(item);
                        break;
                    }
                }
                frame.repaint();

            } else {
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before deleting!");
            }
        }
    }

    private class Dragger implements MouseListener, MouseMotionListener{

        boolean dragging;       // Set to true when a drag is in progress.
        int draggingIndex;      // Set the index of the current dragging item in furnitureArrayList
        int offsetX, offsetY;   // Offset of mouse-click coordinates from the top-left corner of
                                //  the square that was clicked.

        @Override
        public void mouseClicked(MouseEvent evt) {
            System.out.println("Clicked at (" + evt.getPoint().getX() + ", " +
                    evt.getPoint().getY() + ")" );
        }

        @Override
        public void mousePressed(MouseEvent evt) {

            // Exit if a drag is in progress.
            if( dragging )
                return;
            int locX = evt.getX();
            int locY = evt.getY();

            for(Furniture item: Furniture.getFurnitureArrayList() ){

            }



        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }   // end: nested-class Dragger


}
