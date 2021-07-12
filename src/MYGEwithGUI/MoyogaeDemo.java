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

        floorPanel = new FloorPanel();
        adjustRatioWidth = 440.0/Floor.getWidth();
        adjustRatioLength = 300.0/Floor.getLength();
        floorPanel.setFloorRatio(adjustRatioWidth, adjustRatioLength);

        floorPanel.setPreferredSize(new Dimension(440,350));

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
        frame.setBounds(10,10,500,650);
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

//        //Input Dialog
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
        Floor.setWidth(350);
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

    private class Dragger implements MouseListener, MouseMotionListener {

        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
                                    // used to manage the final result of
                                    // the mouse motions.
        boolean dragging;           // Set to true when a drag is in progress.
        Furniture target = null;    // Set the instance of the current dragging item (in furnitureArrayList)

        int topLeftX = 0;           // the top-left x-coords of the target furniture
        int topLeftY = 0;           // the top-left y-coords of the target furniture
        int bottomRightX;           // the buttom-right x-coords of the target furniture
        int bottomRightY;           // the buttom-right y-coods of the target furniture

        int itemWidth;
        int itemLength;

        @Override
        public void mousePressed(MouseEvent evt) {

            System.out.println("\n------- mousePressed()  (" + evt.getX() + ", " + evt.getY() + ")" +
                    "----------------------------");

            // Exit if a drag is in progress.
            if (dragging)
                return;
            if( target != null )
                target = null;
            int locX = evt.getX();
            int locY = evt.getY();

            ArrayList<Furniture> targetList = new ArrayList<>();
                                    // save the temporary item(s) that could
                                    // be targeted during dragging.

            for (Furniture item : itemList) {

                itemWidth = (int) (item.getWidth() * adjustRatioWidth);
                itemLength = (int) (item.getLength() * adjustRatioLength);

                // topLeft coords before dragging
                topLeftX = item.getCurX();
                topLeftY = item.getCurY();
                System.out.println("topLeft: " + topLeftX + ", " + topLeftY);

                // buttomRight coords before dragging
                bottomRightX = topLeftX + itemWidth;
                bottomRightY = topLeftY + itemLength;
                System.out.println("bottomRight: " + bottomRightX + ", " + bottomRightY);

                // the length of the gap between the mouse-clicked position and the
                // top-left corner of the item at the very starting point.
                item.setOffsetX( locX - topLeftX );
                item.setOffsetY( locY - topLeftY );
//                System.out.println("offset (" + item.getOffsetX() + ", " + item.getOffsetY() + ")");

                // Check whether the area of this item contains the clicked position.
                // And add the item to targetList as a potential item to detect the clicked item.
                if (topLeftX <= locX && locX <= bottomRightX
                        && topLeftY <= locY && locY <= bottomRightY) {
                    targetList.add(item);
                }
            } // end for-loop

            // assingn the item with the one containing the latest id. (= shown on the top
            // layer)
            int latestItemId = 0;
            //TODO the following process can be improved with Stream! try later.
            for (Furniture item : targetList) {
                if (item.getId() >= latestItemId) {
                    latestItemId = item.getId();
                    target = item;
                }
            }
            dragging = true;

            //For debugging
            System.out.println("\n------- mousePressed() ---------------------");
        }

        /**
         * Respond when the user drags the mouse.
         * If a square, representing each furniture, is not being dragged, then exit.
         * Otherwise, change the position of the mouse.
         * NOTE: the corner of the square is placed in the same relative position with
         * respect to the mouse that I had when the user started dragging it.
         */
        @Override
        public void mouseDragged(MouseEvent evt) {
//            System.out.println("\n====== mouseDragged() ===========================");
            if (!dragging)
                return;
            int x = evt.getX();
            int y = evt.getY();

            // set the temporary location in the target object
            target.setCurX( x );
            target.setCurY( y );

            System.out.println("MouseDragged(): " +
                    "cur(" + x + "," + y + ") " +
                    "top (" + topLeftX + "," + topLeftY+ ") " +
                    "bottom (" + bottomRightX + "," + bottomRightY +")");

            frame.repaint();

//            System.out.println("====== mouseDragged() ===========================");

        }

        /**
         * Dragging stops when user releases the mouse button.
         */
        @Override
        public void mouseReleased(MouseEvent evt) {
            System.out.println("\n***** mouseReleased! ****************************");
            int afterReleaseX = evt.getX();
            int afterReleaseY = evt.getY();

            // If the bottom-right corner of the item will out of the floor,
            // repaint the image in one of the edge of the floor.
            //panel size: 440 x 305!
            boolean showNotification = false;
            if ( (afterReleaseX - target.getOffsetX() + itemWidth) > 440) {
                int bottomXReset = 440 - itemWidth;
                target.setCurX(bottomXReset);
                System.out.println("bottom X reset.");
                showNotification = true;
            }
            if ( ( afterReleaseY - target.getOffsetY() + itemLength) > 300) {
                int bottomYReset = 300 - itemLength;
                target.setCurY(bottomYReset);
                System.out.println("bottom Y reset.");
                showNotification = true;
            }
            if( ( afterReleaseX- target.getOffsetX() ) < 10 ){
                target.setCurX(10);
                System.out.println("top x reset.");
                showNotification = true;
            }
            if ( ( afterReleaseY - target.getOffsetY() ) < 10){
                target.setCurY(10);
                System.out.println("top y reset.");
                showNotification = true;
            }

            if( showNotification ){
                JOptionPane.showMessageDialog(null,
                        "You cannot move the item out side your room. " +
                                "Please put it in the room, please!");
            }

            System.out.println("Current cords: " + target.getCurX() + ", " + target.getCurY());

            // set the location information in furnitureArrayList, and
            // the location information of starting point is stored as previous coords in the furnitureArraylist.
            int index = itemList.indexOf( target );
            int prevX = itemList.get( index ).getCurX();
            int prevY = itemList.get( index ).getCurY();
            target.setPreX( prevX );
            target.setPreY( prevY );
            itemList.set( index, target );

            dragging = false;

            frame.repaint();

            System.out.println("***** mouseReleased! ****************************");

        }

        public void mouseMoved(MouseEvent e) { }
        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }



    }   // end: nested-class Dragger


}
