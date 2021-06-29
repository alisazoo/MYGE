import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private ArrayList<Furniture> furnitureList;

    private FloorPanel floorPanel;
    private FurniturePanel furniturePanel;
    private JLabel floorWidth, floorLength;
    private int dataFloorWidth, dataFloorLength;

    DrawPanel(){
        setBackground(Color.DARK_GRAY);

        floorPanel = new FloorPanel();
        furniturePanel = new FurniturePanel();

        setLayout(new BorderLayout());
        add(floorPanel,BorderLayout.CENTER);
        add(furniturePanel, BorderLayout.SOUTH);
    } // end: Constructor DrawPanel3

    /**
     * This nested class is used to display floor image, its sizes
     * center of the main window.
     */
    public class FloorPanel extends JPanel implements ActionListener{

        public FloorPanel(){

            setBackground(Color.WHITE);
            setPreferredSize( new Dimension(400,300) );

            dataFloorWidth = Floor.getWidth() ;
            dataFloorLength = Floor.getLength();

            floorWidth = new JLabel(dataFloorWidth + " mm (width)", JLabel.CENTER);
            floorLength = new JLabel(dataFloorLength + "mm (length)", JLabel.CENTER);
            floorLength.setUI(new VerticalLabelUI(true));

            JButton setBtn = new JButton("Edit Floor Size");
            setBtn.addActionListener(this);

            setLayout( new BorderLayout(5,5) );
                //TODO check how the gaps above are reflected.
            add(floorWidth, BorderLayout.NORTH);
            add(floorLength, BorderLayout.EAST);
            add(setBtn, BorderLayout.SOUTH);
        } // end: constructor

        /**
         * This method draws the floor image in FloorPanel.
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setForeground(Color.LIGHT_GRAY);
            g.fillRect(50,50,675,400);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            if( cmd.equals("Edit Floor Size")) {
                Moyogae.inputFloorData();
                floorWidth.setText(Floor.getWidth() + " mm (width)");
                floorLength.setText(Floor.getLength() + " mm (length)");
                repaint();
            }
        }
    } // end: nested class FloorPanel

    private class FurniturePanel extends JPanel implements ActionListener {

        JButton addBtn, deleteBtn;
        JLabel furnitureNotice;
        JList<String> furnitureList;
        DefaultListModel<String> furnitureListText = new DefaultListModel<>();
        Furniture items = new Furniture();
        ArrayList<Furniture> furnitureArrayList = items.getFurnitureArrayList();

        FurniturePanel() {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            addBtn = new JButton("Add Furniture");
            addBtn.addActionListener(this);

            // this delete button only appears after the data is set
            deleteBtn = new JButton("Delete this furniture");
            deleteBtn.addActionListener(this);

            furnitureNotice = new JLabel("No furniture is registered.", JLabel.CENTER);

            // Layout
            setLayout(new GridLayout(0,2));
            // show only user input and xLabel, mmLabel, and setBtn.
            //TODO change the layout with more components.

            add(addBtn);
            add(furnitureNotice);
            add(new JLabel("test"));
//            furnitureList = new JList<>(furnitureListText);
//            add(furnitureList);
        }

        private void addFurnitureInputArea(){

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            if (cmd.equals("Add Furniture")) {
                Moyogae.inputFurnitureData(furnitureArrayList, true);
                furnitureNotice.setEnabled(false);

                // Display the registered item
                for (Furniture item : furnitureArrayList) {
                    System.out.print(item.getName() + ": " + item.getWidth() + " (w) x "
                            + item.getLength() + " (l)\n"); // debugging
                }
                    repaint();
            }
        }
    } // end: nested-inner class FurniturePanel
}
