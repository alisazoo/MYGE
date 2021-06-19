import javax.swing.*;
import java.awt.*;

public class DrawPanel3 extends JPanel {

    DrawPanel3(){
        setBackground(Color.DARK_GRAY);

        DataPanel dataPanel = new DataPanel();
        FloorPanel floorPanel = new FloorPanel();

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.SOUTH);
        add(floorPanel,BorderLayout.CENTER);
        repaint();
    }

}
