package practice.headFirstJava.codekitchen;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class BeatBoxFinal {

    JFrame theFrame;
    JPanel mainPanel;
    JList incomingList;
    JTextField userMessage;
    ArrayList<JCheckBox> checkboxList = new ArrayList<>();
    int nextNum;
    Vector<String> listVector = new Vector<>();
    String userName;
    ObjectOutputStream out;
    ObjectInputStream in;
    HashMap<String, boolean[] > otherSeqsMap = new HashMap<>();

    Sequencer sequencer;
    Sequence sequence;
    Sequence mySequence = null;
    Track track;

    String[] instrumentName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle",
            "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) {
        new BeatBoxFinal().startUp("arisa");
            // args[0] is your user ID/screen name
    }

    public void startUp(String name){
        userName = name;
        try{
            Socket sock = new Socket("localhost", 4242);
            out = new ObjectOutputStream( sock.getOutputStream() );
            in = new ObjectInputStream( sock.getInputStream() );
            Thread remote = new Thread( new RemoteReader() );
            remote.start();
        } catch (Exception e){
            System.out.println("Couldn't connect - you'll have to play alone.");
        }
        setUpMidi();
        buildGUI();
    } // close startUp()

    public void buildGUI(){
        theFrame = new JFrame("Cyber BeatBox");
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkboxList = new ArrayList<JCheckBox>();

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener( new MyStartListener() );
        buttonBox.add(startBtn);

        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener( new MyStopListener() );
        buttonBox.add(stopBtn);

        JButton tempoUpBtn = new JButton("Tempo Up");
        tempoUpBtn.addActionListener( new MyUpTempoListener() );
        buttonBox.add(tempoUpBtn);

        JButton tempoDownBtn = new JButton("Tempo Down");
        tempoDownBtn.addActionListener( new MyDownTempoListener() );
        buttonBox.add(tempoDownBtn);

        JButton sendIt = new JButton("sendIt");
        sendIt.addActionListener( new MySendListener() );
        buttonBox.add(sendIt);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        incomingList = new JList(); // display the incoming message
        incomingList.addListSelectionListener( new MyListSelecitonListener() );
        incomingList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector);
            // no data to start with

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0; i < 16; i++){
            nameBox.add( new Label(instrumentName[i]) );
        }

        background.add(BorderLayout.WEST, nameBox);
        background.add(BorderLayout.EAST, buttonBox);

        theFrame.getContentPane().add(background);
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for(int i = 0; i < 256; i ++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }

        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);

    } // close buildGUI()

    public void setUpMidi(){

        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    } // close setupMidi()


    public void buildTrackAndStart(){
        // this will hold the instruments for each
        ArrayList<Integer> trackList = null;
        // Remove the old track and make the fresh one
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for( int i = 0; i < 16; i++ ){

            trackList = new ArrayList<>();

            // If checkbox is selected, set the key value, representing the choice of instrument,
            //  in this slot in the array. Otherwise, set 0 to play nothing in the slot.
            for ( int j = 0; j < 16; j++ ){
                JCheckBox jc = checkboxList.get( j + 16*i );
                if( jc.isSelected() ){
                    int key = instruments[i];
                    trackList.add(key);
                } else {
                    // Because this slot should be empty in the track.
                    trackList.add(null);
                }
            }   // end: inner loop
            makeTrack(trackList);
        }   // end: outer loop

            // so we always go to full 16 beats.
        track.add( makeEvent(192, 9, 1, 0, 15) );

        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount( sequencer.LOOP_CONTINUOUSLY );
            sequencer.start();
            sequencer.setTempoInBPM(120);

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }   // end: buildTrackAndStart method

    //------ActionListener-------------------------------------------------
    public class MyStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            buildTrackAndStart();
        }
    }   // end startListener

    public class MyStopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            sequencer.stop();
        }
    }   // end stopListener

    public class MyUpTempoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float)(tempoFactor * 1.03) );
        }
    }   // end tempoUpListener

    public class MyDownTempoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float)(tempoFactor * .97) );
        }
    }

    public class MySendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Make an arrayList of just the STATE of the checkboxes.
            boolean[] checkboxState = new boolean[256];
            for( int i = 0; i < 256; i++ ){
                JCheckBox check = checkboxList.get(i);
                if( check.isSelected() ){
                    checkboxState[i] = true;
                }
            }
            String messageToSend = null;

            try{
                out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
                out.writeObject( checkboxState );
            } catch(Exception ex){
                System.out.println("Sorry dude. Could not send it to the server.");
            }
            userMessage.setText("");
        } // Close method
    } // close inner class

    public class MyListSelecitonListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent le) {
            if( !le.getValueIsAdjusting() ){
                String selected = (String) incomingList.getSelectedValue();
                if( selected != null ){
                    // now go to the map, and change the sequence
                    boolean[] selectedState = (boolean[])otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }

        }
    }

    public class RemoteReader implements Runnable{
        boolean[] checkBoxState = null;
        String nameToShow = null;
        Object obj = null;
        public void run(){
            try{
                while( (obj=in.readObject() ) != null){
                    System.out.println("got an object from server.");
                    System.out.println(obj.getClass());
                    String nameToShow = (String)obj;
                    checkBoxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkBoxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    } // close inner class

    public class MyPlayMineListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(mySequence != null){
                sequence = mySequence;
                // restore to my original
            }
        }
    }

    public void changeSequence(boolean[] checkboxState){
        for(int i = 0; i < 256; i++){
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if(checkboxState[i]){
                check.setSelected(true);
            }else{
                check.setSelected(false);
            }
        }
    }


    private void makeTrack(ArrayList list) {
        Iterator it = list.iterator();

        for( int i = 0; i < 16; i++ ){
            Integer num = (Integer) it.next();

            if( num != null ){
                int numKey = num.intValue();
                track.add( makeEvent(144, 9, numKey, 100, i) );
                track.add( makeEvent(128, 9, numKey, 100, i+1) );
            }
        }

    }

    public static MidiEvent makeEvent(int cmd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(cmd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch(Exception e){
            e.printStackTrace();
        }
        return event;
    }







}
