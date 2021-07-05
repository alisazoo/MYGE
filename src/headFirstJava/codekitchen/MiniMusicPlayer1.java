package headFirstJava.codekitchen;

import javax.sound.midi.*;

public class MiniMusicPlayer1 {

    public static void main(String[] args) {

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for( int i = 5; i < 61; i += 5 ){
                track.add(makeEvent(144, 1, i, 100, i ));
                track.add(makeEvent(128, 1, i, 100, i+2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch(Exception ex){
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

}
