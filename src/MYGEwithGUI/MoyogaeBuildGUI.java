//package MYGEwithGUI;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
//public class MoyogaeBuildGUI extends JPanel {
//
//    JFrame frame;
//    JPanel mainPanel, furniturePanel;
//    FloorPanel floorPanel;
//
//    DefaultListModel<String> listModel;
//    JList<String> furnitureList;
//
//    double floorAreaWidth = 440.0;
//    double floorAreaLength = 300.0;
//    double adjustRatioWidth, adjustRatioLength;
//
//    ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
//
//    /**
//     * This method build components and display them as the initial GUI.
//     */
//    public void buildGUI(){
//        frame = new JFrame("Moyogae Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setContentPane( buildMainPanel() );
//        frame.setBounds(10,10,500,650);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    /**
//     * Build main panel of this app.
//     * @return mainPanel
//     */
//    private JPanel buildMainPanel(){
//
//        mainPanel = new JPanel();
//        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.Y_AXIS) );
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//        mainPanel.setPreferredSize( new Dimension( 480, 500 ) );
//
//        mainPanel.add( buildFloorPanel() );
//        mainPanel.add( buildFurniturePanel() );
//
//        return mainPanel;
//
//    }
//
//    /**
//     * Build furniture Panel, including list of the furniture items,
//     * and 3 buttons to manipulate items displayed in the list and floorPanel.
//     * @return furniturePanel
//     */
//    private JPanel buildFurniturePanel(){
//
//        furniturePanel = new JPanel();
//        furniturePanel.setLayout( new GridBagLayout() );
//        GridBagConstraints c = new GridBagConstraints();
//        furniturePanel.setPreferredSize( new Dimension(400, 150));
//
//        JLabel floorSizeInfo = new JLabel( "Floor Size: " + Floor.getWidth() + " cm (width)" + " x " +
//                Floor.getLength() + " cm (length)", JLabel.CENTER);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.FIRST_LINE_START;
//        c.gridwidth = 3;
//        c.gridx = 0;
//        c.gridy = 0;
//        furniturePanel.add( floorSizeInfo, c );
//
//        listModel = new DefaultListModel<>();
//        for (Furniture item: itemList ){
//            String s = item.getName() + ": " + item.getWidth() + " cm x " + item.getLength() + " cm";
//            listModel.addElement(s);
//        }
//        furnitureList = new JList<>(listModel);
//        furnitureList.addListSelectionListener( new ListListener() );
//        JScrollPane scrollPane = new JScrollPane(furnitureList);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 60;
//        c.gridx = 0;
//        c.gridwidth = 3;
//        c.gridy = 1;
//        furniturePanel.add( scrollPane, c );
//
//        JButton addItem = new JButton("Add Furniture");
//        addItem.addActionListener(new addItemListener() );
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 2;
//        furniturePanel.add( addItem, c );
//
//        JButton rotateItem = new JButton("Rotate Furniture");
//        rotateItem.addActionListener(new rotateItemListener() );
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = 2;
//        furniturePanel.add( rotateItem, c );
//
//        JButton remItem = new JButton("Remove Furniture");
//        remItem.addActionListener(new remItemListener() );
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;
//        c.gridwidth = 1;
//        c.gridx = 2;
//        c.gridy = 2;
//        furniturePanel.add( remItem, c );
//
//        return furniturePanel;
//    }
//
//    /**
//     * Build floor panel includes the space to display furniture images
//     * @return floorPanel
//     */
//    private FloorPanel buildFloorPanel(){
//
//        floorPanel = new FloorPanel();
//        adjustRatioWidth = floorAreaWidth / Floor.getWidth();
//        adjustRatioLength = floorAreaLength / Floor.getLength();
//        floorPanel.setFloorRatio(adjustRatioWidth, adjustRatioLength);
//
//        floorPanel.setPreferredSize(new Dimension(440,350));
//
//        MoyogaeDragger.Dragger dragListener = new MoyogaeDragger.Dragger();
//        floorPanel.addMouseListener( dragListener );
//        floorPanel.addMouseMotionListener( dragListener );
//        floorPanel.add( BorderLayout.NORTH, new JLabel("Here is your floor..."));
//
//        return floorPanel;
//    }
//
//    class addItemListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            MoyogaeDemo.inputFurnitureData(Furniture.getFurnitureArrayList(), listModel);
//            floorPanel.repaint();
//        }
//    }
//
//
//    class remItemListener implements ActionListener{
//
//        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
//
//        @Override
//        public void actionPerformed(ActionEvent evt){
//
//            boolean isSelectedItemNow = checkIsSelectedNow();
//
//            if(!listModel.isEmpty() && isSelectedItemNow ) {
//                int itemIndexToDelete = furnitureList.getSelectedIndex();
//                String itemNameToDelete = extractSubstring( furnitureList.getSelectedValue(), ':');
//
//                for( Furniture item: itemList ){
//                    if( item.getName().equals(itemNameToDelete) ){
//                        itemList.remove(item);
//                        break;
//                    }
//                }
//                listModel.removeElementAt(itemIndexToDelete);
//
//                frame.repaint();
//
//            } else if ( listModel.isEmpty() ){
//                JOptionPane.showMessageDialog(null,
//                        "No more item here. Please add the new one before deleting!");
//
//            } else if ( !isSelectedItemNow ){
//                JOptionPane.showMessageDialog(null, "Please select an item to delete.");
//
//            }
//        }
//    }
//
//    class rotateItemListener implements ActionListener{
//
//        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
//        Furniture target;
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            boolean isSelectedItemNow = checkIsSelectedNow();
//
//            if(!listModel.isEmpty() && isSelectedItemNow ) {
//
//                String targetItemName = extractSubstring( furnitureList.getSelectedValue(), ':' );
//
//                for(Furniture item: itemList ){
//                    if( item.getName().equals(targetItemName) ){
//
//                        int i = itemList.indexOf(item);
//                        int preW = itemList.get(i).getWidth();
//                        int preL = itemList.get(i).getLength();
//                        itemList.get(i).setWidth(preL);
//                        itemList.get(i).setLength(preW);
//                        target = item;
//
//                        break;
//                    }
//                }
//
//                // set the location information in furnitureArrayList, and
//                // the location information of starting point is stored as
//                // previous coords in the furnitureArraylist.
//                int index = itemList.indexOf( target );
//                int prevX = itemList.get( index ).getCurX();
//                int prevY = itemList.get( index ).getCurY();
//                target.setPreX( prevX );
//                target.setPreY( prevY );
//                itemList.set( index, target );
//
//                // Change the location information if user can put the item out of the floor
//                // after rotating.
//                // If so, the item is automatically put on the nearest edge/corner.
//                int topLeftX = target.getCurX();
//                int topLeftY = target.getCurY();
//                new MoyogaeDragger.Dragger().resetPosition(topLeftX, topLeftY, target);//TODO
//
//                frame.repaint();
//
//            } else if (listModel.isEmpty() ){
//                JOptionPane.showMessageDialog(null,
//                        "No more item here. Please add the new one before trying to rotate nothing!");
//            } else if ( !isSelectedItemNow ){
//                JOptionPane.showMessageDialog(null, "Please select an item to rotate.");
//            }
//        }
//    }
//
//    class ListListener implements ListSelectionListener{
//
//        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
//
//        @Override
//        public void valueChanged(ListSelectionEvent e) {
//            if(!e.getValueIsAdjusting()) {
//
//                String str = furnitureList.getSelectedValue();
//                if( str!=null ) {
//                    String targetItemName = extractSubstring( str, ':');
//                    for (Furniture item : itemList) {
//                        item.setSelected(false);
//                        if (item.getName().equals(targetItemName)) {
//                            item.setSelected(true);
//                        }
//                    }
//                    frame.repaint();
//                }
//            }
//        }
//    }
//
//
//    /**
//     * Extract substring start from the beginning and end before something
//     * at the indexOf in the String, str.
//     * @param fullString a string to extract a substring from.
//     * @param mark substring ends at right before of this char
//     * @return substring start from the begening and end before mark
//     */
//    public String extractSubstring( String fullString, char mark ){
//
//        int strIndex = fullString.indexOf(mark);
//        return fullString.substring(0, strIndex);
//
//    }
//
//    /**
//     * Check whether there is a selected furniture item now.
//     * @return true if a furniture item is selected.
//     */
//    public boolean checkIsSelectedNow(){
//        boolean isSelectedNow = false;
//        for(Furniture item: itemList) {
//            if (item.isSelected()) {
//                isSelectedNow = true;
//                break;
//            }
//        }
//        return isSelectedNow;
//    }
//
//
//
//}
