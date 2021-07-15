package practice.zetcode;

// https://zetcode.com/javaswing/swingmodels/

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class ListModelsEx extends JFrame {

    private DefaultListModel<String> model;
    private JList<String> myList;
    private JButton remAllBtn;
    private JButton addBtn;
    private JButton renBtn;
    private JButton delBtn;

    public ListModelsEx(){
        initUI();
    }


    private void createList(){

        model = new DefaultListModel<>();
        model.addElement("Amelie");
        model.addElement("Aguirre, der Zorn Gottes");
        model.addElement("Fargo");
        model.addElement("Exorcist");
        model.addElement("Scindler's myList");

        myList = new JList<>(model);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        myList.addMouseListener( new MouseAdapter(){

            @Override
            public void mouseClicked( MouseEvent e ){

                if( e.getClickCount() == 2 ){  //TODO: recap

                    int index = myList.locationToIndex( e.getPoint() );  //TODO: recap
                    var item = model.getElementAt( index );
                    var text = JOptionPane.showInputDialog( "Rename item", item );  //TODO: recap

                    String newItem;

                    if( text != null ){ // when the user inputs something.
                        newItem = text.trim();
                    } else { // when the user inputs nothing.
                        return;
                    }

                    if( !newItem.isEmpty() ){

                        model.remove( index );
                        model.add( index, newItem );

                        var selModel = myList.getSelectionModel();  //TODO: recap
                        selModel.setLeadSelectionIndex( index );  //TODO: recap
                    }

                }
            }
        } );
    } // end: createList()

    private void createButtons(){

        remAllBtn = new JButton("Remove All");
        addBtn = new JButton("Add");
        renBtn = new JButton("Remane");
        delBtn = new JButton("Delete");

        remAllBtn.addActionListener( e -> model.clear() );

        addBtn.addActionListener( e -> {

            var text = JOptionPane.showInputDialog("Add a new item");
            String item;

            if( text != null ){
                item = text.trim();
            } else {
                return;
            }

            if ( !item.isEmpty() ){
                model.addElement( item );
            }
        });

        delBtn.addActionListener( event -> {

            var selModel = myList.getSelectionModel();
            int index = selModel.getMinSelectionIndex();

            if( index >= 0 ){
                model.remove(index);
            }
        });

        renBtn.addActionListener( e -> {

            var selModel = myList.getSelectionModel();
            int index = selModel.getMinSelectionIndex();

            if( index == -1 ){
                return;
            }

            var item = model.getElementAt( index );
            var text = JOptionPane.showInputDialog("Rename item", item);
            String newItem;

            if( text != null ){
                newItem = text.trim();
            } else {
                return;
            }

            if( !newItem.isEmpty() ){
                model.remove(index);
                model.add(index, newItem);
            }
        });

    } // end: createButtons()

    private void initUI(){

        createList();
        createButtons();

        var scrollPane = new JScrollPane(myList);
        createLayout(scrollPane, addBtn, renBtn, delBtn, remAllBtn);

        setTitle("JList Models");
        setLocationRelativeTo(null);
            // window.setLocationRelativeTo(Component c)
            // - Set the location of the window relative to the specified component
            //   according to the following scenarios.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }   // end: initUI()

    private void createLayout(JComponent... arg){
        var pane = getContentPane(); //TODO a-ha!
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGroup( gl.createParallelGroup()
                        .addComponent( arg[1] )
                        .addComponent( arg[2] )
                        .addComponent( arg[3] )
                        .addComponent( arg[4] ) )
        );

        gl.setVerticalGroup( gl.createParallelGroup( CENTER )
                .addComponent( arg[0] )
                .addGroup( gl.createSequentialGroup()
                        .addComponent( arg[1] )
                        .addComponent( arg[2] )
                        .addComponent( arg[3] )
                        .addComponent( arg[4] ) )
        );

        gl.linkSize( addBtn, renBtn, delBtn, remAllBtn );

        pack();
    } // end: createLayout(arg)

    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {

            var ex = new ListModelsEx();
            ex.setVisible(true);
        });
    }// end: main()
}
