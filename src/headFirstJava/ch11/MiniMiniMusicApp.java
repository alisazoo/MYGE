package headFirstJava.ch11;

import javax.sound.midi.*;

public class MiniMiniMusicApp {

    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    public void play() {

        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,20,100);
            MidiEvent noteOn = new MidiEvent(a,1);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,20,100);
            MidiEvent noteOff = new MidiEvent(b,16);

//            ShortMessage c = new ShortMessage();
//            c.setMessage(192,1,102,0);
//            MidiEvent change = new MidiEvent(c, 17);
//
//            ShortMessage d = new ShortMessage();
//            d.setMessage(128,1,102,100);
//            MidiEvent noteOff2 = new MidiEvent(d,32);


            track.add(noteOn);
            track.add(noteOff);
//            track.add(change);
//            track.add(noteOff2);

            player.setSequence(seq);

            player.start();

//            } catch (MidiUnavailableException e) {
//                e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
