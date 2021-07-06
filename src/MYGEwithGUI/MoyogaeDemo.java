package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoyogaeDemo extends JPanel {

    JFrame frame;
    JPanel mainPanel, furniturePanel;
    FloorPanel floorPanel;

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

        // TODO: delete the following test data after implement imput dialog.
        Furniture.addFurniture("desk", 120,60);
        Furniture.addFurniture("Shelf", 40,30);
        Furniture.addFurniture("Chair", 60,60);

        String[] furnitureEntries = Furniture.createFurnitureList();
        JList<String> furnitureList;

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
        floorPanel.add( BorderLayout.SOUTH, floorWidth );

        //------furniturePanel----------------------------------------
        furnitureList = new JList<>(furnitureEntries);
        furniturePanel.add( BorderLayout.CENTER, furnitureList);

        JButton addItem = new JButton("Add MYGEwithGUI.Furniture");
        addItem.addActionListener(new addItemListener() );
        furniturePanel.add( BorderLayout.EAST, addItem);

        mainPanel.add( floorPanel );
        mainPanel.add( furniturePanel );

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
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked!");
        }
    }

    /**
     * This inputFloorData method create the input dialog window, then
     * assign each values to the Floor class.
     */
    public static void inputFloorData(){

        // Input Dialog
        JPanel panel = new JPanel(new BorderLayout(5,5));

        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add( new JLabel("Enter the size:") );
        label.add( new JLabel("Floor Width (mm)", SwingConstants.RIGHT) );
        label.add( new JLabel("Floor Length (mm)", SwingConstants.RIGHT) );
        panel.add( label, BorderLayout.WEST );

        JPanel control = new JPanel(new GridLayout(0,1,2,2));
        control.add( new JLabel(""));   // to fill the empty space for layout
        JTextField floorWidthInput = new JTextField();
        control.add(floorWidthInput);
        JTextField floorLengthInput = new JTextField();
        control.add(floorLengthInput);
        panel.add(control, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel,
                "Set Floor Size", JOptionPane.QUESTION_MESSAGE);

        // Set the input data into the floor object
        Floor.setWidth( Integer.parseInt(floorWidthInput.getText()) );
        Floor.setLength( Integer.parseInt(floorLengthInput.getText()) );

    }   // end inputFloorSize() method



//    /**
//     * This inputFurnitureData method set the new item in the ArrayList<MYGEwithGUI.Furniture>.
//     */
//    public static void inputFurnitureData(ArrayList<Furniture> arrayList, boolean isFurnitureAdd){
//
//        // Input Dialog
//        JPanel panel = new JPanel(new BorderLayout(5,5));
//
//        JPanel label = new JPanel(new GridLayout(0,1,2,2));
//        label.add( new JLabel("Name", SwingConstants.RIGHT) );
//        label.add( new JLabel("Width (mm)", SwingConstants.RIGHT) );
//        label.add( new JLabel("Length (mm)", SwingConstants.RIGHT) );
//        panel.add( label, BorderLayout.WEST );
//
//        JPanel control = new JPanel(new GridLayout(0,1,2,2));
//        JTextField furnitureName = new JTextField();
//        control.add( furnitureName );
//        JTextField furnitureWidthInput = new JTextField();
//        control.add(furnitureWidthInput);
//        JTextField furnitureLengthInput = new JTextField();
//        control.add(furnitureLengthInput);
//        panel.add(control, BorderLayout.CENTER);
//
//        if(isFurnitureAdd) {
//            JOptionPane.showMessageDialog(null, panel,
//                    "Add new furniture", JOptionPane.QUESTION_MESSAGE);
//        }
//        else {
//            // process for editing
//        }
//
//        // Set the input data into the ArrayList
//        Furniture item = new Furniture();
//        item.setName(furnitureName.getText());
//        item.setWidth( Integer.parseInt(furnitureWidthInput.getText()) );
//        item.setLength( Integer.parseInt(furnitureLengthInput.getText()) );
//        arrayList.add(item);
//
//    }   // end inputFurnitureData() method


}
