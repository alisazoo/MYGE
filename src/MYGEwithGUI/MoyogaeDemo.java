package MYGEwithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoyogaeDemo extends JPanel {

    JFrame frame;
    JPanel mainPanel, furniturePanel;
    FloorPanel floorPanel;

    /**
     * This main routine allow to use this program as an application.
     */
    public static void main(String[] args) {
        new MoyogaeDemo().buildGUI();
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

        frame = new JFrame("MYGEwithGUI.Moyogae Demo");
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
        JLabel floorWidth = new JLabel("000" + " mm (width)" + " x " +
                "111" + " mm (length)", JLabel.CENTER);
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
}
