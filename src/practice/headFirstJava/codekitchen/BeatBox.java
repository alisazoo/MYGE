package practice.headFirstJava.codekitchen;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeatBox {

    JFrame frame;
    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxList = new ArrayList<>();

    Sequencer sequencer;
    Sequence sequence;

    Track track;

    String[] instrumentName = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle",
            "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }

    public void buildGUI(){
        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        buttonBox.setBackground(Color.BLUE);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener( new startListener() );
        buttonBox.add(startBtn);

        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener( new stopListener() );
        buttonBox.add(stopBtn);

        JButton tempoUpBtn = new JButton("Tempo Up");
        tempoUpBtn.addActionListener( new tempoUpListener() );
        buttonBox.add(tempoUpBtn);

        JButton tempoDownBtn = new JButton("Tempo Down");
        tempoDownBtn.addActionListener( new tempoDownListener() );
        buttonBox.add(tempoDownBtn);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0; i < 16; i++){
            nameBox.add( new Label(instrumentName[i]) );
        }

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);

        background.add(BorderLayout.WEST, nameBox);
        background.add(BorderLayout.CENTER, mainPanel);
        background.add(BorderLayout.EAST, buttonBox);

        for(int i = 0; i < 256; i ++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }
        setupMidi();

        frame.setBounds(50,50,300,300);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Basic setup to use midi:
     *  Create and open sequencer, create sequence and track, and
     *  set the default tempo of sequencer.
     */
    public void setupMidi(){

        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * This method is used to turn checkbox state into MIDI events,
     * and add them to the Track.
     */
    public void buildTrackAndStart(){
        int[] trackList;

        // Remove the old track and make the fresh one
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for( int i = 0; i < 16; i++ ){
            trackList = new int[16];
            int key = instruments[i];

            // If checkbox is selected, set the key value, representing the choice of instrument,
            //  in this slot in the array. Otherwise, set 0 to play nothing in the slot.
            for ( int j = 0; j < 16; j++ ){
                JCheckBox jc = checkboxList.get( j + 16*i );
                if( jc.isSelected() ){
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }   // end: inner loop

            makeTrack(trackList);
            track.add( makeEvent(176, 1, 127, 0, 16) );
        }   // end: outer loop

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
    public class startListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }   // end startListener

    public class stopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }   // end stopListener

    public class tempoUpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float)(tempoFactor * 1.03) );
        }
    }   // end tempoUpListener

    public class tempoDownListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float)(tempoFactor * .97) );
        }
    }

    //------Create track and MIDIEvenet-----------------------------------------

    /**
     * This method creates events for one instrument at a time, for all 16 beats.
     * each index in the array will hold either the key of that instrument, or zero.
     * If it's a zero, the instrument is not supported to play at the beat; otherwise,
     * it make an event and add it to the track
     * @param list hold the key of the instrument
     */
    private void makeTrack(int[] list) {

        for( int i = 0; i < 16; i++ ){
            int key = list[i];

            if( key != 0 ){
                track.add( makeEvent(144, 9, key, 100, i) );
                track.add( makeEvent(128, 9, key, 100, i+1) );
            }
        }

    }

    /**
     * This method create MidiEvent based on the information based on
     * the following argument.
     * @param cmd message type
     * @param chan the number of channel
     * @param one note to play
     * @param two velocity
     * @param tick duration of the note
     * @return instance of type MidiEvent
     */
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
