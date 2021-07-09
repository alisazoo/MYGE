package MYGEwithGUI;

import jdk.swing.interop.SwingInterOpUtils;

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

    double adjustRatioWidth, adjustRatioLength;
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

        int flrWidth = Floor.getWidth();
        int flrLength = Floor.getLength();

        adjustRatioWidth = 420.0/flrWidth;
        adjustRatioLength = 330.0/flrLength;

        floorPanel = new FloorPanel(adjustRatioWidth, adjustRatioLength);
        floorPanel.setPreferredSize(new Dimension(440,350));
        floorPanel.setBorder( BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        furniturePanel = new JPanel(layout);
        furniturePanel.setPreferredSize( new Dimension(400, 150));

        //------floorPanel-------------------------------------------
        JLabel floorWidth = new JLabel( Floor.getWidth() + " cm (width)" + " x " +
                Floor.getLength() + " cm (length)", JLabel.CENTER);

// Comment out during checking out sizeAdjustment brunch
        Dragger dragListener = new Dragger();
        floorPanel.addMouseListener( dragListener );
        floorPanel.addMouseMotionListener( dragListener );
        floorPanel.add( BorderLayout.NORTH, floorWidth );
        // TODO: fix the layout issue with BorderLayout manager?
        // floorPanel.add(BorderLayout.SOUTH, floorWidth);
        //  this above line also work but the location is the SAME as NORTH ver.

        //------furniturePanel----------------------------------------

        listModel = new DefaultListModel<>();
        for (Furniture item: Furniture.getFurnitureArrayList() ){
            String s = item.getName() + ": " + item.getWidth() + " cm x " + item.getLength() + " cm";
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
//        label.add( new JLabel("Floor Width (cm)", SwingConstants.RIGHT) );
//        label.add( new JLabel("Floor Length (cm)", SwingConstants.RIGHT) );
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
        Floor.setWidth(300);
        Floor.setLength(210);

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
        String newItemTxt = name + ": " + width + " cm x " + length + " cm";
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
        label.add( new JLabel("Width (cm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (cm)", SwingConstants.RIGHT) );
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
                " cm(w) " + infoArray[2] + "cm(l)");

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

        boolean dragging;           // Set to true when a drag is in progress.
        Furniture target = null;    // Set the instance of the current dragging item (in furnitureArrayList)
        int offsetX, offsetY;       // Offset of mouse-click coordinates from the top-left corner of
                                    //  the square that was clicked.
        int topLeftX = 0;           // the top-left x-coords of the target furniture
        int topLeftY = 0;           // the top-left y-coords of the target furniture
        int buttomRightX;           // the buttom-right x-coords of the target furniture
        int buttomRightY;           // the buttom-right y-coods of the target furniture
        int itemWidth;
        int itemLength;

        @Override
        public void mousePressed(MouseEvent evt) {

            System.out.println("clicked: (" + evt.getX() + ", " + evt.getY() + ")");

            // Exit if a drag is in progress.
            if( dragging )
                return;
            int locX = evt.getX();
            int locY = evt.getY();

            ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
            ArrayList<Furniture> targetList = new ArrayList<>();

            for (Furniture item : itemList) {

                itemWidth = (int)(item.getWidth() * adjustRatioWidth);
                itemLength = (int)(item.getLength() * adjustRatioLength);

                topLeftX = item.getCurX();
                topLeftY = item.getCurY();
                System.out.println("topLeft: " + topLeftX + ", "+ topLeftY);

                buttomRightX = topLeftX + itemWidth;
                buttomRightY = topLeftY + itemLength;
                System.out.println("buttomRight: " + buttomRightX + ", " + buttomRightY);

                offsetX = locX - topLeftX;
                offsetY = locY - topLeftY;
                System.out.println("offset (" + offsetX + ", " + offsetY + ")");

                // Check whether the area of this item contains the clicked position.
                // And add the item to targetList as a potential item to detect the clicked item.
                if (topLeftX <= locX && locX <= buttomRightX
                        && topLeftY <= locY && locY <= buttomRightY) {
                    targetList.add(item);
                }
            } // end for-loop

            //TODO the following process can be improved with Stream! try later.
            int latestItemId = 0;
            for(Furniture item: targetList){
                if( item.getId() >= latestItemId ) {
                    latestItemId = item.getId();
                    target = item;
                }
            }

            //For Debugging
            //TODO: delete the following block later
            if( target != null ) {
                System.out.println("your target is " + target.getName());
            } else {
                System.out.println("nothing is clicked.");
            }

            dragging = true;
        }

        //        public int detectLatestItem(ArrayList<Furniture> list){
//            // Detect the latest item in the furnitureArrayList
//            int latestItemId = 0; // id represents the latest created item
//            for( Furniture item: list ){
//                if(latestItemId < item.getId() )
//                    latestItemId = item.getId();
//            }
//            return latestItemId;
//        }

        /**
         * Respond when the user drags the mouse.
         * If a square, representing each furniture, is not being dragged, then exit.
         * Otherwise, change the position of the mouse.
         * NOTE: the corner of the square is placed in the same relative position with
         * respect to the mouse that I had when the user started dragging it.
         */
        @Override
        public void mouseDragged(MouseEvent evt) {

            if(!dragging)
                return;
            int x = evt.getX();
            int y = evt.getY();

            int targetId = target.getId();
            ArrayList<Furniture> originalAryList = Furniture.getFurnitureArrayList();
            for( Furniture item: originalAryList ){
                if( item.getId() == targetId ){

                    item.setPreX( item.getCurX() );
                    item.setPreY( item.getCurY() );
                    item.setCurX( x - offsetX );
                    item.setCurY( y - offsetY );
                    System.out.println( "The dragging item is " + item.getName() +
                            "(" + item.getCurX() + ", " + item.getCurY() + ")" );

                }

            }
            frame.repaint();

            System.out.println("\n");

        }

        /**
         * Dragging stops when user releases the mouse button.
         */
        @Override
        public void mouseReleased(MouseEvent evt) {

            int afterReleaseX = evt.getX();
            int afterReleaseY = evt.getY();

            // If the bottom-right corner of the item will out of the floor,
            // notification will be appear
            //TODO: additionally, the image repaint based on the previous location (= topLeftX & topLeftY)
            if( (afterReleaseX - offsetX + itemWidth) > 445 || ( afterReleaseY - offsetY + itemLength ) > 310 ){
                //panel size: 440 x 305!
                JOptionPane.showMessageDialog(null,
                        "You cannot move the item out side your room. " +
                                "Please put it in the room");
                //TODO: add a function. Set the item along to the right bottom corner of the floor.

                return;
//            } else if (afterReleaseX < 0 || afterReleaseY < 0){
            } else if ( (afterReleaseX-offsetX) < 15 || (afterReleaseY-offsetY ) < 15){
                JOptionPane.showMessageDialog(null,
                        "You cannot move the item out side your room. " +
                                "Please put it in the room, please!");
                //TODO: add a function. Set the item along to the top left corner of the floor.
                return;
            }

            System.out.println("\nafterRelease: " + afterReleaseX + ", " + afterReleaseY);
            dragging = false;
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }




    }   // end: nested-class Dragger


}
