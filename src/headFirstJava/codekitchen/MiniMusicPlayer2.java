package headFirstJava.codekitchen;

import javax.sound.midi.*;
import javax.swing.*;

/**
 * This code aims at registering and getting ControllerEvents,
 *  based on the programe of MiniMusicPlayer2.
 */
public class MiniMusicPlayer2 implements ControllerEventListener {

    public static void main(String[] args) {
        MiniMusicPlayer2 mini = new MiniMusicPlayer2();
        mini.go();
    }

    public void go() {

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            int[] eventsIWant = {127};  // represents the list of ControllerEvents I want.
            sequencer.addControllerEventListener(this, eventsIWant);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 5; i < 61; i += 5) {
                track.add(makeEvent(144, 1, i, 100, i));
                track.add(makeEvent( 176, 1, 127, 0, i ));
                    // This above line do nothing visible but can get an event each time
                    //  a note is played.
                    //  176 = the event type is ControllerEvent;
                    //  for event number 127, which is registered above.
                track.add(makeEvent(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (Exception ex) {
            ex.printStackTrace();
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

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("la");
    }

}
