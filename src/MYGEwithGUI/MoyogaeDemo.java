package MYGEwithGUI;

import javax.swing.*;

public class MoyogaeDemo extends JPanel {

    static private boolean defaultData;

    /**
     * This main routine allow to use this program as an application.
     */
    public static void main(String[] args) {
        BuildGui gui = new BuildGui();
        FloorDialog.inputFloorDialog();
        gui.buildGUI();
    }

    public static boolean isDefaultData() {
        return defaultData;
    }

}
