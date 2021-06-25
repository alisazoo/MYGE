package reference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

public class SillyStamper extends JPanel {

    /**
     * The main routine simply opens a window that shows a SillyStamper panel.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Silly Stamper");
        SillyStamper content = new SillyStamper();
        window.setContentPane(content);
        window.pack();
        window.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screenSize.width - window.getWidth())/2,
                (screenSize.height - window.getHeight())/2 );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    /**
     * An object of type IconInfo stores the information needed to draw one icon image
     * on the display area.
     */
    private static class IconInfo {
        int iconNumber;         // an index into the iconImages array.
        int x, y;               // coordinates of the upper left corner of the image
    }

    /**
     * Contains info for all the icons that have been placed on the display area.
     * Might contain more than have actually been shown, because of the Undo command.
     * An icon that is removed from the display area by an undo is not removed from this list.
     */
    private ArrayList<IconInfo> icons = new ArrayList<IconInfo>();
    private int iconsShown;      // Number of icons shown in the display area.
    private int iconsPlaced;    // Number of icons that have been placed.
                                // Can be greater than iconShown, because of undo/redo.
    private JList<Icon> iconList;  // The JList from which the user selects the icon for stamping.
    private JButton undoButton; // A button for removing the most recently added image.
    private JButton redoButton; // A button for restoring the most recently removed image.

    private IconDisplayPanel displayPanel;  // The display panel. The IconDisplayPanel class is a nested class.

    private Image[] iconImages; // The little images that can be "stamped".

    /**
     * This class replresents the drawing area where the user can stamp images.
     */
    private class IconDisplayPanel extends JPanel implements MouseListener{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(iconImages == null){
                g.drawString("Can't load icons.", 10, 30);
                return;
            }
            for(int i = 0; i < iconsShown; i++){
                IconInfo info = icons.get(i);
                g.drawImage(iconImages[info.iconNumber], info.x, info.y, this);
            }
        }


        /**
         * When the user clicks the display panel, place a copy of
         * the currently selected icon image at the point where the user clicked.
         * @param e MouseEvent object
         */
        @Override
        public void mousePressed(MouseEvent e) {
            IconInfo info = new IconInfo();
            info.iconNumber = iconList.getSelectedIndex();
            info.x = e.getX() - 16;     // Offset x-coord, so center of icon is at the point that was clicked.
            info.y = e.getY() - 16;     // Offset y-coord too.
            if ( iconsShown == icons.size() )
                icons.add(info);
            else
                icons.set( iconsShown, info );
            iconsShown++;
            iconsPlaced = iconsShown;
            redoButton.setEnabled( false );
            undoButton.setEnabled( true );
            repaint();                  // Tell system to redraw the image, with the new data.
        }

        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }

    }   // end nested class IconDisplayPanel

    /**
     * The constructor sets up a BorderLayout on the panel with a display panel
     * in the CENTER position, a list of icon images in the EAST position, and
     * a JPanel in the SOUTH position that contains two control buttons.
     */
    public SillyStamper() {

        setLayout(new BorderLayout(2, 2)); // Set basic properties of this panel.
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        displayPanel = new IconDisplayPanel();      // Create and configure the display panel.
        displayPanel.setPreferredSize(new Dimension(400, 300));
        displayPanel.setBackground(new Color(220, 220, 225));  // Very light blue.
        displayPanel.addMouseListener(displayPanel);
        add(displayPanel, BorderLayout.CENTER);

        iconList = createIconList();                // Create the scrolling list of icons.
        if (iconList == null)
            return;
        add(new JScrollPane(iconList), BorderLayout.EAST);

        Action undoAction = new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iconsShown > 0) {
                    iconsShown--;
                    redoButton.setEnabled(true);
                    displayPanel.repaint();
                }
            }
        };

        Action redoAction = new AbstractAction("Redo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iconsShown < iconsPlaced) {
                    iconsShown++;
                    if (iconsShown == iconsPlaced)
                        redoButton.setEnabled(false);
                    undoButton.setEnabled(true);
                    displayPanel.repaint();
                }
            }
        };

        undoButton = new JButton(undoAction);
        redoButton = new JButton(redoAction);
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }   // end no-arg constructor

    /**
     * Create a JList that contains all of the available icon images.
     */
    private JList<Icon> createIconList(){
        String[] iconNames = new String[]{
                "icons1.png", "icons2.png", "icons3.png",
                "icons4.png", "icons5.png", "icons6.png"
        };
        iconImages = new Image[ iconNames.length ];

        ClassLoader classLoader = getClass().getClassLoader();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try{
            for (int i = 0; i < iconNames.length; i++){
                URL imageURL = classLoader.getResource( "stamper_icons/" + iconNames[i] );
                if (imageURL == null)
                    throw new Exception();
                iconImages[i] = toolkit.createImage( imageURL );
            }
        }
        catch(Exception e){
            iconImages = null;
            return null;
        }

        ImageIcon[] icons = new ImageIcon[iconImages.length];
        for (int i = 0; i < iconImages.length; i++)
            icons[i] = new ImageIcon(iconImages[i]);

        JList<Icon> list = new JList<Icon>(icons);      // Make a list containing the image icons from the array.

        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        list.setSelectedIndex(0);
        return list;

    }

}   // end class SillyStamper
