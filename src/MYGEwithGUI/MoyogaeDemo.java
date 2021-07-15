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

    ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

    /**
     * This main routine allow to use this program as an application.
     */
    public static void main(String[] args) {
        MoyogaeDemo main = new MoyogaeDemo();
        inputFloorData();
        main.buildGUI();
    }

    //-------------- GUI ---------------------------------------------

    /**
     * This method build components and display them as the initial GUI.
     */
    public void buildGUI(){

        frame = new JFrame("Moyogae Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane( buildMainPanel() );
        frame.setBounds(10,10,500,650);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Build main panel of this app.
     * @return mainPanel
     */
    private JPanel buildMainPanel(){

        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.Y_AXIS) );
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setPreferredSize( new Dimension( 480, 500 ) );

        mainPanel.add( buildFloorPanel() );
        mainPanel.add( buildFurniturePanel() );

        return mainPanel;

    }

    /**
     * Build furniture Panel, including list of the furniture items,
     * and 3 buttons to manipulate items displayed in the list and floorPanel.
     * @return furniturePanel
     */
    private JPanel buildFurniturePanel(){

        furniturePanel = new JPanel();
        furniturePanel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        furniturePanel.setPreferredSize( new Dimension(400, 150));

        JLabel floorSizeInfo = new JLabel( "Floor Size: " + Floor.getWidth() + " cm (width)" + " x " +
                Floor.getLength() + " cm (length)", JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        furniturePanel.add( floorSizeInfo, c );

        listModel = new DefaultListModel<>();
        for (Furniture item: itemList ){
            String s = item.getName() + ": " + item.getWidth() + " cm x " + item.getLength() + " cm";
            listModel.addElement(s);
        }
        furnitureList = new JList<>(listModel);
        furnitureList.addListSelectionListener( new ListListener() );
        JScrollPane scrollPane = new JScrollPane(furnitureList);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 60;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 1;
        furniturePanel.add( scrollPane, c );

        JButton addItem = new JButton("Add Furniture");
        addItem.addActionListener(new addItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        furniturePanel.add( addItem, c );

        JButton rotateItem = new JButton("Rotate Furniture");
        rotateItem.addActionListener(new rotateItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        furniturePanel.add( rotateItem, c );

        JButton remItem = new JButton("Remove Furniture");
        remItem.addActionListener(new remItemListener() );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        furniturePanel.add( remItem, c );

        return furniturePanel;
    }

    /**
     * Build floor panel includes the space to display furniture images
     * @return floorPanel
     */
    private FloorPanel buildFloorPanel(){

        floorPanel = new FloorPanel();
        adjustRatioWidth = floorAreaWidth / Floor.getWidth();
        adjustRatioLength = floorAreaLength / Floor.getLength();
        floorPanel.setFloorRatio(adjustRatioWidth, adjustRatioLength);

        floorPanel.setPreferredSize(new Dimension(440,350));

        Dragger dragListener = new Dragger();
        floorPanel.addMouseListener( dragListener );
        floorPanel.addMouseMotionListener( dragListener );
        floorPanel.add( BorderLayout.NORTH, new JLabel("Here is your floor..."));

        return floorPanel;
    }

    //--------- input Dialog: floor ---------------------------------------

    /**
     * This inputFloorData method create the input dialog window, then
     * assign each values to the Floor class.
     */
    public static void inputFloorData(){

// TODO: uncomment out the following code to use dialog input

        //Input Dialog
        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Enter the size:") );
        label.add( new JLabel("Floor Width (cm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Floor Length (cm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        control.add( new JLabel(""));   // to fill the empty space for layout
        JTextField floorWidthField = new JTextField();
        control.add(floorWidthField);
        JTextField floorLengthField = new JTextField();
        control.add(floorLengthField);
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Set Floor Size", JOptionPane.QUESTION_MESSAGE);
        boolean isSetFloorData = false;
        while(!isSetFloorData ){
            try {
                // Set the input data into the floor object
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

//        // TODO: delete the following test data
//        Floor.setWidth(350);
//        Floor.setLength(210);

    }   // end inputFloorSize() method


    //--------- input Dialog: furniture -------------------------------------
    /**
     * This static inputFurnitureData method aims to set the new item in the ArrayList<Furniture>.
     * The data of the new item is sent from input dialog.
     */
    public static void inputFurnitureData(
            ArrayList<Furniture> itemList, DefaultListModel<String> listModel){

        do{
            result = inputFurnitureDialog();
        } while (isDuplicateFurniture);

        String inputName;
        int inputWidth, inputLength;

        try {

            inputName = result[0];
            inputWidth = Integer.parseInt(result[1]);
            inputLength = Integer.parseInt(result[2]);

            Furniture inputItem = new Furniture(inputName, inputWidth, inputLength);
            itemList.add(inputItem);

            String newItemTxt = inputName + ": " + inputWidth + " cm x " + inputLength + " cm";
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
     * for user to input the data of new furniture item.
     * If the input name of the new item is same as the existing one,
     * this program requires to input again.
     * If the input item is valid, this set the isDuplicatedFurniture of the item true and
     * return the result as a String array.
     * @return array with String value of name, width, and length of the new item.
     */
    public static String[] inputFurnitureDialog(){

        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

        if( isDuplicateFurniture ){
            //TODO: find more sophisticated way!.
            result = new String[3];
        }

        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Name", SwingConstants.RIGHT) );
        label.add( new JLabel("Width (cm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Length (cm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        JTextField furnitureNameField = new JTextField();
        control.add( furnitureNameField );
        JTextField furnitureWidthField = new JTextField();
        control.add(furnitureWidthField);
        JTextField furnitureLengthField = new JTextField();
        control.add(furnitureLengthField);

        if(isDuplicateFurniture) {
            panel.add( new JLabel("You cannot add duplicated item. " +
                    "Please make the new one with different name."), BorderLayout.NORTH );
        } else {
            panel.add( new JLabel("Enter the data of the new item."), BorderLayout.NORTH);
        }
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Add new furniture", JOptionPane.QUESTION_MESSAGE);

        String inputFurnitureName = furnitureNameField.getText();
        String[] infoArray = new String[3];
        infoArray[0] = inputFurnitureName;
        infoArray[1] = furnitureWidthField.getText();
        infoArray[2] = furnitureLengthField.getText();

        for (Furniture newItem: itemList){
            if(newItem.getName().equals(inputFurnitureName)){
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

        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

        public void actionPerformed(ActionEvent evt){

            // process to handle when no item is selected
            boolean isSelectedNow = false;
            for(Furniture item: itemList) {
                if (item.isSelected()) {
                    isSelectedNow = true;
                    break;
                }
            }

            if(!listModel.isEmpty() && isSelectedNow ) {

                int indexToDelete = furnitureList.getSelectedIndex();
                String itemToDelete = extractSubstring( furnitureList.getSelectedValue(), ':');

                for(Furniture item: itemList ){
                    if( item.getName().equals(itemToDelete) ){
                        itemList.remove(item);
                        break;
                    }
                }
                listModel.removeElementAt(indexToDelete);

                frame.repaint();

            } else if ( listModel.isEmpty() ){
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before deleting!");
            } else if ( !isSelectedNow ){
                JOptionPane.showMessageDialog(null, "Please select an item to delete.");
            }
        }
    }

    private class rotateItemListener implements ActionListener{

        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
        Furniture target;

        public void actionPerformed(ActionEvent e) {

            // process to handle when no item is selected
            boolean isSelectedNow = false;
            for(Furniture item: itemList) {
                if (item.isSelected()) {
                    isSelectedNow = true;
                    break;
                }
            }

            if(!listModel.isEmpty() && isSelectedNow ) {

                String targetItemName = extractSubstring( furnitureList.getSelectedValue(), ':' );

                for(Furniture item: itemList ){
                    if( item.getName().equals(targetItemName) ){

                        int i = itemList.indexOf(item);
                        int preW = itemList.get(i).getWidth();
                        int preL = itemList.get(i).getLength();
                        itemList.get(i).setWidth(preL);
                        itemList.get(i).setLength(preW);
                        target = item;

                        break;
                    }
                }

                // set the location information in furnitureArrayList, and
                // the location information of starting point is stored as
                // previous coords in the furnitureArraylist.
                int index = itemList.indexOf( target );
                int prevX = itemList.get( index ).getCurX();
                int prevY = itemList.get( index ).getCurY();
                target.setPreX( prevX );
                target.setPreY( prevY );
                itemList.set( index, target );

                // Change the location information if user can put the item out of the floor
                // after rotating.
                // If so, the item is automatically put on the nearest edge/corner.
                int topLeftX = target.getCurX();
                int topLeftY = target.getCurY();
                int[] sizeList = new Dragger().calcItemSize(target);
                int itemWidth = sizeList[0];
                int itemLength = sizeList[1];
                new Dragger().resetPosition(topLeftX, topLeftY, itemWidth, itemLength, target);

                frame.repaint();

            } else if (listModel.isEmpty() ){
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before trying to rotate nothing!");
            } else if ( !isSelectedNow ){
                JOptionPane.showMessageDialog(null, "Please select an item to rotate.");
            }
        }
    }

    private class ListListener implements ListSelectionListener{

        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()) {

                String str = furnitureList.getSelectedValue();

                if( str!=null ) {
                    String targetItemName = extractSubstring( str, ':');
                    for (Furniture item : itemList) {
                        item.setSelected(false);
                        if (item.getName().equals(targetItemName)) {
                            item.setSelected(true);
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

                int[] sizeArray = calcItemSize(item);
                itemWidth = sizeArray[0];
                itemLength = sizeArray[1];

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

                String targetItemName = extractSubstring( furnitureList.getSelectedValue(), ':');
                for(Furniture item: itemList ) {
                    if (item.getName().equals(targetItemName)) {
                        item.setSelected(true);
                        target = item;
                    }
                }
            }

            // Select the item on the list if the target is selected
            String targetName = target.getName();

            for( int i = 0; i< listModel.getSize(); i++) {

                String targetItemNameInList = extractSubstring( listModel.getElementAt(i), ':');
                if( targetName.equals(targetItemNameInList)){
                    // set selected the item in the list
                    furnitureList.setSelectedIndex(i);
                    // assign true for the status of isSelected of the item;
                    // otherwise assign false.
                    for (Furniture fItem: itemList){
                        fItem.setSelected(false);
                        if(fItem.getName().equals(targetName) ){
                            fItem.setSelected(true);
                        }
                    }
                }
            }
            target.setSelected(true);

            dragging = true;
            frame.repaint();// to reflect isSeleceted status

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
            int[] sizeList = calcItemSize(target);
            itemWidth = sizeList[0];
            itemLength = sizeList[1];

            System.out.println("info of " + target.getName());
            System.out.println("top: " + topLeftX + ", " + topLeftY);
            System.out.println("size: " + itemWidth + " x " + itemLength);

            resetPosition(topLeftX, topLeftY, itemWidth, itemLength, target);

            // reset isSelected status of the unselected items
            for( Furniture item: itemList ){
                if( !item.equals(target))
                    item.setSelected(false);
            }

            dragging = false;
            frame.repaint();
        }

        /**
         * Calculate the size of the item on the floorPanel.
         * @param item Furniture object that is clicked by user
         * @return int[0] = the width of item, int[1] = the length of the item
         */
        int[] calcItemSize(Furniture item){
            int itemWidth = (int) (item.getWidth() * adjustRatioWidth);
            int itemLength = (int) (item.getLength() * adjustRatioLength);

            return new int[]{itemWidth, itemLength};
        }

        /**
         * Check whether this program need to repaint the image
         * at the nearest edge/corner of the floor, instead of user-set position,
         * when user try to put the item outside of the floor
         * If need repaint, this program set the new location information to the Furniture object.
         * @param topLeftX the x-coords of the user-set placement
         * @param topLeftY the y-coords of the user-set placement
         * @param itemWidth the width of the item on the floorPanel
         * @param itemLength the length of the item on the floorPanel
         * @param target Furniture object that is clicked by user
         */
        void resetPosition(int topLeftX, int topLeftY, int itemWidth, int itemLength, Furniture target){

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
            // the location information of starting point is stored
            // as previous coords in the furnitureArraylist.
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

    /**
     * Extract substring start from the beginning and end before something
     * at the indexOf in the String, str.
     * @param fullString a string to extract a substring from.
     * @param mark substring ends at right before of this char
     * @return substring start from the begening and end before mark
     */
    public String extractSubstring( String fullString, char mark ){

        int strIndex = fullString.indexOf(mark);
        return fullString.substring(0, strIndex);

    }

}
