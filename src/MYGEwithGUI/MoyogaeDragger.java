//package MYGEwithGUI;
//
//import javax.swing.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class MoyogaeDragger extends MoyogaeBuildGUI implements Serializable {
//    private final MoyogaeBuildGUI moyogaeBuildGUI;
//
//    public MoyogaeDragger(MoyogaeBuildGUI moyogaeBuildGUI) {
//        this.moyogaeBuildGUI = moyogaeBuildGUI;
//    }
//
//    class Dragger implements MouseListener, MouseMotionListener {
//
//        ArrayList<Furniture> itemList = Furniture.getFurnitureArrayList();
//        // used to manage the final result of
//        // the mouse motions.
//        boolean dragging;           // Set to true when a drag is in progress.
//        Furniture target = null;    // Set the instance of the current dragging item (in furnitureArrayList)
//
//        int topLeftX = 0;           // the top-left x-coords of the target furniture
//        int topLeftY = 0;           // the top-left y-coords of the target furniture
//        int bottomRightX;           // the buttom-right x-coords of the target furniture
//        int bottomRightY;           // the buttom-right y-coods of the target furniture
//
//        int itemWidth;
//        int itemLength;
//
//        @Override
//        public void mousePressed(MouseEvent evt) {
//
//            if (dragging)
//                return;
//
//            target = moyogaeBuildGUI.findTarget(evt.getX(), evt.getY());
//
//            if (target == null) {
//                String targetItemName = moyogaeBuildGUI.extractSubstring(moyogaeBuildGUI.getFurnitureList().getSelectedValue(), ':');
//                for (Furniture item : itemList) {
//                    if (item.getName().equals(targetItemName)) {
//                        item.setSelected(true);
//                        target = item;
//                    }
//                }
//            }
//
//            // Select the item on the list if the target is selected
//            String targetName = target.getName();
//
//            for (int i = 0; i < moyogaeBuildGUI.getListModel().getSize(); i++) {
//
//                String targetItemNameInList = moyogaeBuildGUI.extractSubstring(moyogaeBuildGUI.getListModel().getElementAt(i), ':');
//                if (targetName.equals(targetItemNameInList)) {
//                    // set selected the item in the list
//                    moyogaeBuildGUI.getFurnitureList().setSelectedIndex(i);
//                    // assign true for the status of isSelected of the item;
//                    // otherwise assign false.
//                    for (Furniture fItem : itemList) {
//                        fItem.setSelected(false);
//                        if (fItem.getName().equals(targetName)) {
//                            fItem.setSelected(true);
//                        }
//                    }
//                }
//            }
//            target.setSelected(true);
//
//            dragging = true;
//            moyogaeBuildGUI.getFrame().repaint();
//        }
//
//
//        /**
//         * Respond when the user drags the mouse.
//         * If a square, representing each furniture, is not being dragged, then exit.
//         * Otherwise, change the position of the mouse.
//         * NOTE: the corner of the square is placed in the same relative position with
//         * respect to the mouse that I had when the user started dragging it.
//         */
//        @Override
//        public void mouseDragged(MouseEvent evt) {
//            if (!dragging)
//                return;
//
//            int x = evt.getX();
//            int y = evt.getY();
//
//            target.setCurX(x - target.getOffsetX());
//            target.setCurY(y - target.getOffsetY());
//
//            moyogaeBuildGUI.getFrame().repaint();
//
//        }
//
//        /**
//         * Dragging stops when user releases the mouse button.
//         */
//        @Override
//        public void mouseReleased(MouseEvent evt) {
//
//            topLeftX = evt.getX() - target.getOffsetX();
//            topLeftY = evt.getY() - target.getOffsetY();
//            moyogaeBuildGUI.resetPosition(topLeftX, topLeftY, target);
//
//            for (Furniture item : itemList) {
//                if (!item.equals(target))
//                    item.setSelected(false);
//            }
//            dragging = false;
//            moyogaeBuildGUI.getFrame().repaint();
//        }
//
//        /**
//         * Find the clicked item by user according to the current clicked position.
//         *
//         * @param locX x-coord of current clicked position
//         * @param locY y-coord of current clicked position
//         * @return the instance represents the current clicked furniture item
//         */
//        public Furniture findTarget(int locX, int locY) {
//            ArrayList<Furniture> tempTargetList = new ArrayList<Furniture>();
//            for (Furniture item : itemList) {
//
//                int[] sizeArray = moyogaeBuildGUI.calcItemSize(item);
//                itemWidth = sizeArray[0];
//                itemLength = sizeArray[1];
//
//                topLeftX = item.getCurX();
//                topLeftY = item.getCurY();
//
//                bottomRightX = topLeftX + itemWidth;
//                bottomRightY = topLeftY + itemLength;
//
//                // the length of the gap between the mouse-clicked position and the
//                // top-left corner of the item at the very starting point.
//                item.setOffsetX(locX - topLeftX);
//                item.setOffsetY(locY - topLeftY);
//
//                // Check whether the area of this item contains the clicked position.
//                // And add the item to targetList as a potential item to detect the clicked item.
//                if (topLeftX <= locX && locX <= bottomRightX
//                        && topLeftY <= locY && locY <= bottomRightY) {
//                    tempTargetList.add(item);
//                }
//            } // end for-loop
//
//            // assingn the item with the one containing the latest id. (= shown on the top
//            // layer)
//            int latestItemId = 0;
//
//            //TODO the following process can be improved with Stream! try later.
//            Furniture target = null;
//            for (Furniture item : tempTargetList) {
//                if (item.getId() >= latestItemId) {
//                    latestItemId = item.getId();
//                    item.setSelected(true);
//                    target = item;
//                }
//            }
//            return target;
//        }
//
//
//        /**
//         * Calculate the size of the item on the floorPanel.
//         *
//         * @param item Furniture object that is clicked by user
//         * @return int[0] = the width of item, int[1] = the length of the item
//         */
//        int[] calcItemSize(Furniture item) {
//            int itemWidth = (int) (item.getWidth() * moyogaeBuildGUI.getAdjustRatioWidth());
//            int itemLength = (int) (item.getLength() * moyogaeBuildGUI.getAdjustRatioLength());
//
//            return new int[]{itemWidth, itemLength};
//        }
//
//        /**
//         * Check whether this program need to repaint the image
//         * at the nearest edge/corner of the floor, instead of user-set position
//         * when user try to put the item outside of the floor.
//         * If need repaint, this program set the new location information
//         * to the Furniture object.
//         *
//         * @param topLeftX the x-coords of the user-set placement
//         * @param topLeftY the y-coords of the user-set placement
//         * @param target   Furniture object that is clicked by user
//         */
//        void resetPosition(int topLeftX, int topLeftY, Furniture target) {
//
//            int[] sizeList = new MoyogaeBuildGUI.Dragger().calcItemSize(target);
//            int itemWidth = sizeList[0];
//            int itemLength = sizeList[1];
//
//            boolean showNotification = false;
//            if ((topLeftX + itemWidth) > 450) {
//                int bottomXReset = 450 - itemWidth;
//                target.setCurX(bottomXReset);
//                System.out.println("bottom X reset.");
//                showNotification = true;
//            }
//            if ((topLeftY + itemLength) > 310) {
//                int bottomYReset = 310 - itemLength;
//                target.setCurY(bottomYReset);
//                System.out.println("bottom Y reset.");
//                showNotification = true;
//            }
//            if (topLeftX < 10) {
//                target.setCurX(10);
//                System.out.println("top x reset.");
//                showNotification = true;
//            }
//            if (topLeftY < 10) {
//                target.setCurY(10);
//                System.out.println("top y reset.");
//                showNotification = true;
//            }
//
//            if (showNotification) {
//                JOptionPane.showMessageDialog(null,
//                        "You cannot move the item out side your room. " +
//                                "Please put it in the room, please!");
//            }
//
//            // set the location information in furnitureArrayList, and
//            // the location information of starting point is stored
//            // as previous coords in the furnitureArraylist.
//            int index = itemList.indexOf(target);
//            int prevX = itemList.get(index).getCurX();
//            int prevY = itemList.get(index).getCurY();
//            target.setPreX(prevX);
//            target.setPreY(prevY);
//            itemList.set(index, target);
//        }
//
//        public void mouseMoved(MouseEvent e) {
//        }
//
//        public void mouseClicked(MouseEvent evt) {
//        }
//
//        public void mouseEntered(MouseEvent e) {
//        }
//
//        public void mouseExited(MouseEvent e) {
//        }
//
//    }   // end: nested-class Dragger}