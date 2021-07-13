package MYGEwithGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    double floorAreaWidth = 440.0;
    double floorAreaLength = 300.0;
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
        adjustRatioWidth = floorAreaWidth / Floor.getWidth();
        adjustRatioLength = floorAreaLength / Floor.getLength();
        floorPanel.setFloorRatio(adjustRatioWidth, adjustRatioLength);

        floorPanel.setPreferredSize(new Dimension(440,350));

        furniturePanel = new JPanel();
        furniturePanel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
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
        furnitureList.addListSelectionListener( new ListListener() );
        JScrollPane scrollPane = new JScrollPane(furnitureList);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 60;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 0;
        furniturePanel.add( scrollPane, c );

        JButton addItem = new JButton("Add Furniture");
        addItem.addActionListener(new addItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        furniturePanel.add( addItem, c );

        JButton rotateItem = new JButton("Rotate Furniture");
        rotateItem.addActionListener(new rotateItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        furniturePanel.add( rotateItem, c );

        JButton remItem = new JButton("Remove Furniture");
        remItem.addActionListener(new remItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        furniturePanel.add( remItem, c );

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
//        boolean isSetFloorData = false;
//        while(!isSetFloorData ){
//            try {
//                // Set the input data into the floor object
//                Floor.setWidth(Integer.parseInt(floorWidthInput.getText()));
//                Floor.setLength(Integer.parseInt(floorLengthInput.getText()));
//                isSetFloorData = true;
//            } catch (NumberFormatException exception) {
//                JOptionPane.showMessageDialog(null,
//                        "Please enter number for both width and length.");
//                JOptionPane.showMessageDialog(null, panel,
//                        "Set Floor Size", JOptionPane.QUESTION_MESSAGE);
//            }
//        }

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
        String name;
        int width, length;

        try {
            name = result[0];
            width = Integer.parseInt(result[1]);
            length = Integer.parseInt(result[2]);

            // new data is assigned to the ArrayList
            Furniture item = new Furniture(name, width, length);
            arrayList.add(item);

            // Set the input data into the DefaultListModel
            String newItemTxt = name + ": " + width + " cm x " + length + " cm";
            listModel.addElement(newItemTxt);
        }
        catch (NumberFormatException numEx){
            numEx.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Please enter the valid data. Width and length of the furniture should be number.");
        }

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

        String name;
        String width;
        String length;

        JOptionPane.showMessageDialog(null, panel,
                "Add new furniture", JOptionPane.QUESTION_MESSAGE);

        // keep the input data into the String array, infoArray.
        name = furnitureName.getText();
        width = furnitureWidthInput.getText();
        length = furnitureLengthInput.getText();

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

                // Delete the existing item from furnitureArrayList
                int strIndex = str.indexOf(":");
                String subtractText = str.substring(0, strIndex);
                ArrayList<Furniture> list = Furniture.getFurnitureArrayList();

                for(Furniture item: list ){
                    if( item.getName().equals(subtractText) ){

                        list.remove(item);
                        break;

                    }
                }

                // Delete the existing item from JList
                listModel.removeElementAt(index);

                frame.repaint();

            } else {
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before deleting!");
            }
        }
    }

    //TODO add checking the position: notice if user put the item out of the area
    private class rotateItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!listModel.isEmpty()) {

                String str = furnitureList.getSelectedValue();

                // rotate the furniture
                int strIndex = str.indexOf(":");
                String subtractText = str.substring(0, strIndex);
                ArrayList<Furniture> list = Furniture.getFurnitureArrayList();

                for(Furniture item: list ){
                    if( item.getName().equals(subtractText) ){

                        int i = list.indexOf(item);
                        int preW = list.get(i).getWidth();
                        int preL = list.get(i).getLength();
                        list.get(i).setWidth(preL);
                        list.get(i).setLength(preW);

                        break;
                    }
                }
                frame.repaint();

            } else {
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before trying to rotate nothing!");
            }
        }
    }

    private class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()) {

                String str = furnitureList.getSelectedValue();

                if( str!=null ) {
                    // Reset the isSelected status: set true only if it is currently selected.
                    int strIndex = str.indexOf(":");
                    String subtractText = str.substring(0, strIndex);
                    ArrayList<Furniture> list = Furniture.getFurnitureArrayList();
                    for (Furniture item : list) {
                        if (item.getName().equals(subtractText)) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                    }
                    frame.repaint();
                }
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

                // the length of the gap between the mouse-clicked position and the
                // top-left corner of the item at the very starting point.
                item.setOffsetX( locX - topLeftX );
                item.setOffsetY( locY - topLeftY );

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
                    item.setSelected(true);
                    target = item;
                }
            }

            //
            if( target == null ){

                // get the information that is currently selected.
                String str = furnitureList.getSelectedValue();

                // rotate the furniture
                int strIndex = str.indexOf(":");
                String subtractText = str.substring(0, strIndex);
                ArrayList<Furniture> list = Furniture.getFurnitureArrayList();

                for(Furniture item: list ) {
                    if (item.getName().equals(subtractText)) {
                        item.setSelected(true);
                        target = item;
                    }
                }
            }

            // Select the item on the list if the target is selected
            String targetName = target.getName();

            for( int i = 0; i< listModel.getSize(); i++) {
                // extract only the item name
                String listStr = listModel.getElementAt(i);
                int listStrIndex = listStr.indexOf(":");
                String subListStr = listStr.substring(0, listStrIndex);
                if( targetName.equals(subListStr)){
                    // set selected the item in the list
                    furnitureList.setSelectedIndex(i);
                    // assign true for the status of isSelected of the item;
                    // otherwise assign false.
                    for (Furniture fItem: itemList){
                        if(fItem.getName().equals(targetName) ){
                            fItem.setSelected(true);
                        } else {
                            fItem.setSelected(false);
                        }
                    }
                }
            }

            dragging = true;

            target.setSelected(true);
            frame.repaint();// to reflect isSeleceted status

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

            frame.repaint();

//            System.out.println("====== mouseDragged() ===========================");

        }

        /**
         * Dragging stops when user releases the mouse button.
         */
        @Override
        public void mouseReleased(MouseEvent evt) {
            System.out.println("\n***** mouseReleased! ****************************");

            // Reset the locations of topLeftX and Y.
            topLeftX = evt.getX();
            topLeftY = evt.getY();

            // Reset the size of item
            itemWidth = (int) (target.getWidth() * adjustRatioWidth);
            itemLength = (int) (target.getLength() * adjustRatioLength);

            System.out.println("info of " + target.getName());
            System.out.println("top: " + topLeftX + ", " + topLeftY);
            System.out.println("size: " + itemWidth + " x " + itemLength);

            resetPosition();
//            // If the bottom-right corner of the item will out of the floor,
//            // repaint the image in one of the edge of the floor.
//            boolean showNotification = false;
//            if ( (topLeftX + itemWidth) > 450) {
//                int bottomXReset = 450 - itemWidth;
//                target.setCurX(bottomXReset);
//                System.out.println("bottom X reset.");
//                showNotification = true;
//            }
//            if ( ( topLeftY + itemLength) > 310) {
//                int bottomYReset = 310 - itemLength;
//                target.setCurY(bottomYReset);
//                System.out.println("bottom Y reset.");
//                showNotification = true;
//            }
//            if( topLeftX < 10 ){
//                target.setCurX(10);
//                System.out.println("top x reset.");
//                showNotification = true;
//            }
//            if ( topLeftY < 10){
//                target.setCurY(10);
//                System.out.println("top y reset.");
//                showNotification = true;
//            }
//
//            if( showNotification ){
//                JOptionPane.showMessageDialog(null,
//                        "You cannot move the item out side your room. " +
//                                "Please put it in the room, please!");
//            }
//
//            // set the location information in furnitureArrayList, and
//            // the location information of starting point is stored as previous coords in the furnitureArraylist.
//            int index = itemList.indexOf( target );
//            int prevX = itemList.get( index ).getCurX();
//            int prevY = itemList.get( index ).getCurY();
//            target.setPreX( prevX );
//            target.setPreY( prevY );
//            itemList.set( index, target );

            // reset isSelected status of the unselected items
            for( Furniture item: itemList ){
                if( !item.equals(target))
                    item.setSelected(false);
            }

            System.out.println("target: " + target.getName() + " (" + target.getOffsetX() + ", " + target.getOffsetY() + ")");

            dragging = false;

            frame.repaint();

            System.out.println("***** mouseReleased! ****************************");

        }

        void resetPosition(){
            // If the bottom-right corner of the item will out of the floor,
            // repaint the image in one of the edge of the floor.
            boolean showNotification = false;
            if ( (topLeftX + itemWidth) > 450) {
                int bottomXReset = 450 - itemWidth;
                target.setCurX(bottomXReset);
                System.out.println("bottom X reset.");
                showNotification = true;
            }
            if ( ( topLeftY + itemLength) > 310) {
                int bottomYReset = 310 - itemLength;
                target.setCurY(bottomYReset);
                System.out.println("bottom Y reset.");
                showNotification = true;
            }
            if( topLeftX < 10 ){
                target.setCurX(10);
                System.out.println("top x reset.");
                showNotification = true;
            }
            if ( topLeftY < 10){
                target.setCurY(10);
                System.out.println("top y reset.");
                showNotification = true;
            }

            if( showNotification ){
                JOptionPane.showMessageDialog(null,
                        "You cannot move the item out side your room. " +
                                "Please put it in the room, please!");
            }

            // set the location information in furnitureArrayList, and
            // the location information of starting point is stored as previous coords in the furnitureArraylist.
            int index = itemList.indexOf( target );
            int prevX = itemList.get( index ).getCurX();
            int prevY = itemList.get( index ).getCurY();
            target.setPreX( prevX );
            target.setPreY( prevY );
            itemList.set( index, target );
        }

        public void mouseMoved(MouseEvent e) { }
        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }

    }   // end: nested-class Dragger

}
