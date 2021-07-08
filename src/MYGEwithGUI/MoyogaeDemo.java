package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

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

        // TODO: delete the following test data
        Furniture.addFurniture("desk", 120,60);
        Furniture.addFurniture("Shelf", 40,30);
        Furniture.addFurniture("Chair", 60,60);

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
        floorPanel.add( BorderLayout.NORTH, floorWidth );

        //------furniturePanel----------------------------------------

        listModel = new DefaultListModel<>();
        for (Furniture item: Furniture.furnitureArrayList){
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

    //------Panel Class-----------------------------------------------

    private class FloorPanel extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.GRAY);
            g.drawRect(20,20,420,290);

            int red, green, blue;
            Color randomColor;

            int x = 50, y = 50;
            for(Furniture item: Furniture.furnitureArrayList ){
                String itemName = item.getName();
                int itemW = item.getWidth();
                int itemH = item.getLength();
                red = (int) (Math.random() * 256);
                blue = (int) (Math.random() * 256);
                green = (int) (Math.random() * 256);
                randomColor = new Color(red, green, blue);
                g.setColor(randomColor);
                g.fillRect(x,y,itemW, itemH);
                x += 50;
                y += 50;
            }
        }

    }

    //-----Action Listener--------------------------------------------
    private class addItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            inputFurnitureData(Furniture.furnitureArrayList, listModel);
            floorPanel.repaint();
        }
    }

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

                for(Furniture item: Furniture.furnitureArrayList){
                    if( item.getName().equals(subtractText) ){
                        Furniture.furnitureArrayList.remove(item);
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
        Furniture item = new Furniture();
        String name = result[0];
        int width, length;
        width = Integer.parseInt( result[1] );
        length = Integer.parseInt( result[2] );

        // new data is assigned to the ArrayList
        item.setName(name);
        item.setWidth(width);
        item.setLength(length);
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

        // if any elements of invalid data in result, delete them
        if( isDuplicateFurniture ){
            //TODO: find more sophisticated way would be better.
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
            // The value is not trimmed considering the possibility to treat not only single word name.
            //TODO: trim the word?
        infoArray[1] = width;
        infoArray[2] = length;

        // If the name of the new input item is the same as one of the existing item,
        // isDuplicateFurniture set as true.
        for (Furniture newItem: Furniture.furnitureArrayList){
            if(newItem.getName().equals(name)){
                 isDuplicateFurniture = true;
                 break;
            } else {
                isDuplicateFurniture = false;
            }
        }

        return infoArray;
    } // end: inputFurnitureDialog() method

}
