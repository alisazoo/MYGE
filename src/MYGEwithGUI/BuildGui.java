package MYGEwithGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildGui extends MoyogaeDemo {

    static JFrame frame;
    FloorDrawing floorPanel;
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> furnitureList;

    final double floorAreaWidth = 440.0;
    final double floorAreaLength = 300.0;
    double adjustRatioWidth, adjustRatioLength;

    ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
    Furniture target;

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
        JPanel mainPanel = new JPanel();
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
        JPanel furniturePanel = new JPanel();
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
    private FloorDrawing buildFloorPanel(){



//        if(MoyogaeDemo.isDefaultData()){
//            setDefaultFurnitureData(listModel);
//        }



        floorPanel = new FloorDrawing();
        adjustRatioWidth = floorAreaWidth / Floor.getWidth();
        adjustRatioLength = floorAreaLength / Floor.getLength();
        floorPanel.setFloorRatio(adjustRatioWidth, adjustRatioLength);

        floorPanel.setPreferredSize(new Dimension(440,350));

        Dragger draggingListener = new Dragger();
        floorPanel.addMouseListener( draggingListener );
        floorPanel.addMouseMotionListener( draggingListener );
        floorPanel.add( BorderLayout.NORTH, new JLabel("Here is your floor..."));

        return floorPanel;
    }



    //-------- Nested classes to handle events ---------------------------------------

    private class addItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            FurnitureDialog.inputFurnitureData(listModel);
            floorPanel.repaint();
        }
    }

    private class remItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt){

            boolean isSelectedItemNow = checkIsSelectedNow();

            if(!listModel.isEmpty() && isSelectedItemNow ) {
                int itemIndexToDelete = furnitureList.getSelectedIndex();
                String itemNameToDelete =
                        extractSubstring( furnitureList.getSelectedValue());

                for( Furniture item: itemList ){
                    if( item.getName().equals(itemNameToDelete) ){
                        itemList.remove(item);
                        break;
                    }
                }
                listModel.removeElementAt(itemIndexToDelete);

                frame.repaint();

            } else if ( listModel.isEmpty() ){
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before deleting!");

            } else if ( !isSelectedItemNow ){
                JOptionPane.showMessageDialog(null, "Please select an item to delete.");

            }
        }
    }

    private class rotateItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean isSelectedItemNow = checkIsSelectedNow();

            if(!listModel.isEmpty() && isSelectedItemNow ) {

                String targetItemName =
                        extractSubstring( furnitureList.getSelectedValue());
                for(Furniture item: itemList ){
                    if( item.getName().equals(targetItemName) ){

                        int indexOfTarget = itemList.indexOf(item);
                        int itemWidthBeforeRotating = itemList.get(indexOfTarget).getWidth();
                        int itemLengthBeforeRotating = itemList.get(indexOfTarget).getLength();
                        itemList.get(indexOfTarget).setWidth(itemLengthBeforeRotating);
                        itemList.get(indexOfTarget).setLength(itemWidthBeforeRotating);
                        target = item;

                        break;
                    }
                }
                setTargetInfo(target);
                resetPosition(target.getCurX(), target.getCurY(), target);
                frame.repaint();

            } else if (listModel.isEmpty() ){
                JOptionPane.showMessageDialog(null,
                        "No more item here. Please add the new one before trying to rotate nothing!");
            } else if ( !isSelectedItemNow ){
                JOptionPane.showMessageDialog(null, "Please select an item to rotate.");
            }
        }
    }

    private class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {

            if(!e.getValueIsAdjusting()) {

                String str = furnitureList.getSelectedValue();
                if( str!=null ) {
                    String targetItemName = extractSubstring( str);
                    setIsSelected(targetItemName);

                    frame.repaint();
                }
            }
        }
    }

    private class Dragger implements MouseListener, MouseMotionListener {

        private boolean dragging;
        private int topLeftX;
        private int topLeftY;

        @Override
        public void mousePressed(MouseEvent evt) {

            if (dragging)
                return;

            target = findTarget( evt.getX(), evt.getY() );

            if( target == null ){
                String targetItemName =
                        extractSubstring( furnitureList.getSelectedValue());
                for(Furniture item: itemList ) {
                    if (item.getName().equals(targetItemName)) {
                        item.setSelected(true);
                        target = item;
                    }
                }
            }

            String targetName = target.getName();
            for( int i = 0; i< listModel.getSize(); i++) {
                String targetNameInList =
                        extractSubstring( listModel.getElementAt(i));
                if( targetName.equals(targetNameInList)){
                    furnitureList.setSelectedIndex(i);
                    setIsSelected(targetNameInList);
                }
            }
            dragging = true;
            frame.repaint();
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
            if (!dragging)
                return;

            int x = evt.getX();
            int y = evt.getY();

            target.setCurX( x - target.getOffsetX() );
            target.setCurY( y - target.getOffsetY() );

            frame.repaint();
        }

        /**
         * Dragging stops when user releases the mouse button.
         */
        @Override
        public void mouseReleased(MouseEvent evt) {

            topLeftX = evt.getX() - target.getOffsetX();
            topLeftY = evt.getY() - target.getOffsetY();
            resetPosition(topLeftX, topLeftY, target);

            for( Furniture item: itemList ){
                if( !item.equals(target))
                    item.setSelected(false);
            }

            dragging = false;

            frame.repaint();
        }

        /**
         * Find the clicked item by user according to the current clicked position.
         * The gap between the mouse-clicked position and the top-left corner is stored
         * as setOffsetX and setOffsetY.
         * Item(s) stored in tempTargetList (= a potential item to detect the clicked item)
         * is determined by the check whether the area of this item contains the clicked position.
         * The clicked item is the element with higher id (=top-layered) in tempTargetList.
         * @param locX x-coord of current clicked position
         * @param locY y-coord of current clicked position
         * @return the instance represents the current clicked furniture item
         */
        private Furniture findTarget( int locX, int locY ){
            ArrayList<Furniture> tempTargetList = new ArrayList<>();

            for (Furniture item : itemList) {

                int[] sizeArray = calcItemSize(item);
                int itemWidth = sizeArray[0];
                int itemLength = sizeArray[1];

                topLeftX = item.getCurX();
                topLeftY = item.getCurY();

                int bottomRightX = topLeftX + itemWidth;
                int bottomRightY = topLeftY + itemLength;

                item.setOffsetX( locX - topLeftX );
                item.setOffsetY( locY - topLeftY );

                if (topLeftX <= locX && locX <= bottomRightX
                        && topLeftY <= locY && locY <= bottomRightY) {
                    tempTargetList.add(item);
                }
            }

            //TODO the following process can be improved with Stream? try later.
            Furniture target = null;
            int topLayeredItemId = 0;
            for (Furniture item : tempTargetList) {
                if (item.getId() >= topLayeredItemId) {
                    topLayeredItemId = item.getId();
                    item.setSelected(true);
                    target = item;
                }
            }
            return target;
        }

        public void mouseMoved(MouseEvent e) { }
        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }

    }

    /**
     * Extract substring start from the beginning and end before ":"
     * in the String str.
     * @param fullString a string to extract a substring from.
     * @return substring start from the beginning and end before ":"
     */
    private String extractSubstring(String fullString){
        int strIndex = fullString.indexOf(':');
        return fullString.substring(0, strIndex);
    }

    /**
     * Check whether there is a selected furniture item now.
     * @return true if a furniture item is selected.
     */
    private boolean checkIsSelectedNow(){
        boolean isSelectedNow = false;
        for(Furniture item: itemList) {
            if (item.isSelected()) {
                isSelectedNow = true;
                break;
            }
        }
        return isSelectedNow;
    }

    /**
     * Calculate the size of the item on the floorPanel.
     * @param item Furniture object that is clicked by user
     * @return int[0] = the width of item, int[1] = the length of the item
     */
    private int[] calcItemSize(Furniture item){
        int itemWidth = (int) (item.getWidth() * adjustRatioWidth);
        int itemLength = (int) (item.getLength() * adjustRatioLength);

        return new int[]{itemWidth, itemLength};
    }

    /**
     * Check whether this program need to repaint the image
     * at the nearest edge/corner of the floor, instead of user-set position
     * when user try to put the item outside of the floor.
     * If need repaint, this program set the new location information
     * to the Furniture object.
     * @param topLeftX the x-coords of the user-set placement
     * @param topLeftY the y-coords of the user-set placement
     * @param target Furniture object that is clicked by user
     */
    private void resetPosition(int topLeftX, int topLeftY, Furniture target){

        int[] sizeList = calcItemSize(target);
        int itemWidth = sizeList[0];
        int itemLength = sizeList[1];

        boolean showNotification = false;
        //TODO consider organise the following if statements in a better way.
        if ( (topLeftX + itemWidth) > 450) {
            int bottomXReset = 450 - itemWidth;
            target.setCurX(bottomXReset);
            showNotification = true;
        }
        if ( ( topLeftY + itemLength) > 310) {
            int bottomYReset = 310 - itemLength;
            target.setCurY(bottomYReset);
            showNotification = true;
        }
        if( topLeftX < 10 ){
            target.setCurX(10);
            showNotification = true;
        }
        if ( topLeftY < 10){
            target.setCurY(10);
            showNotification = true;
        }

        if( showNotification ){
            JOptionPane.showMessageDialog(null,
                    "You cannot move the item out side your room. " +
                            "Please put it in the room, please!");
        }
        setTargetInfo(target);
    }

    /**
     * set the location information in furnitureArrayList, and
     * the location information of starting point is stored
     * as previous coords in the furnitureArraylist.
     * @param target clicked furniture object
     */
    private void setTargetInfo(Furniture target){
        int targetIndex = itemList.indexOf( target );
        int targetPrevX = itemList.get( targetIndex ).getCurX();
        int targetPrevY = itemList.get( targetIndex ).getCurY();
        target.setPreX( targetPrevX );
        target.setPreY( targetPrevY );
        itemList.set( targetIndex, target );
    }

    /**
     * Set true if an item in itemList is selected;
     * otherwise set false.
     * @param targetName the name of the clicked item
     */
    private void setIsSelected(String targetName){
        for (Furniture item: itemList){
            item.setSelected(false);
            if(item.getName().equals(targetName) ){
                item.setSelected(true);
            }
        }
    }






    public void setDefaultFurnitureData(DefaultListModel<String> listModel){

        Furniture ex1 = new Furniture("desk", 120, 60);
        Furniture ex2 = new Furniture("bed", 210, 100);
        Furniture ex3 = new Furniture("shelf", 40,30);
        Furniture ex4 = new Furniture("book shelf", 100, 30);
        ArrayList<Furniture> itemList = new ArrayList<>();
        itemList.add(ex1);
        itemList.add(ex2);
        itemList.add(ex3);
        itemList.add(ex4);

        for(Furniture item: itemList){
            String[] tempArray = new String[3];
            tempArray[0] = item.getName();
            tempArray[1] = String.valueOf(item.getWidth());
            tempArray[2] = String.valueOf(item.getLength());
            System.out.println(Arrays.toString(tempArray));
            FurnitureDialog.setFurnitureInput(tempArray, itemList, listModel);
        }

    }



}
