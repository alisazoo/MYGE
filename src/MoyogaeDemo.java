import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoyogaeDemo extends JPanel {

    JFrame frame;
    JPanel background, floorPanel, furniturePanel;
    FloorImagePanel floorImagePanel;
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

        frame = new JFrame("Moyogae Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        background = new JPanel();
        background.setLayout( new BoxLayout(background, BoxLayout.Y_AXIS) );
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        background.setPreferredSize( new Dimension( 480, 480 ) );

        BorderLayout layout = new BorderLayout();
        floorImagePanel = new FloorImagePanel();   // FlowLayout
        floorImagePanel.setPreferredSize(new Dimension(350,200));
        floorPanel = new JPanel(layout);
        floorPanel.setPreferredSize(new Dimension(400,300));
        furniturePanel = new JPanel(layout);

        //-----------------for Debugging-------------
        // TODO: erase the following block later
        floorImagePanel.setBackground(Color.PINK);
//        floorPanel.setBackground(Color.BLUE);
//        furniturePanel.setBackground(Color.LIGHT_GRAY);
        //-------------------------------------------

        //-----floorImagePanel--------------------------------------

        floorImagePanel.add(new JButton("hell0"));

        //------floorPanel-------------------------------------------
        JLabel floorWidth = new JLabel("000" + " mm (width)" + " x " +
                "111" + " mm (length)", JLabel.CENTER);
        floorWidth.setForeground(Color.DARK_GRAY); // for debugging.
            //TODO: delete the above code later

        floorPanel.add( BorderLayout.NORTH, floorWidth );
        floorPanel.add( BorderLayout.CENTER, floorImagePanel );


        //------furniturePanel----------------------------------------
        String[] furnitureEntries;
        furnitureEntries = new String[]{"desk: 1200 x 600", "Shelf: 300 x 400", "Chair: 60 x 60"};
            //TODO delete the test data above later
        JList<String> furnitureList;

        furnitureList = new JList<>(furnitureEntries);
        furniturePanel.add( BorderLayout.CENTER, furnitureList);

        JButton addItem = new JButton("Add Furniture");
        addItem.addActionListener(new addItemListener() );
        furniturePanel.add( BorderLayout.EAST, addItem);

        background.add( floorPanel );
        background.add( furniturePanel );

        frame.setContentPane(background);
        frame.setBounds(50,50,500,500);
        frame.pack();
        frame.setVisible(true);
    } // end: buildGUI()


    //------Panel Class-----------------------------------------------
    private class FloorImagePanel extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.CYAN);
            g.fillRect(0,0,100,100);
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
