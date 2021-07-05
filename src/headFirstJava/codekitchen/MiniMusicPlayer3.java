package headFirstJava.codekitchen;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

/**
 * This code aims at registering and getting ControllerEvents,
 *  based on the programe of MiniMusicPlayer2.
 */
public class MiniMusicPlayer3 {

    static JFrame f = new JFrame("My First Music Video(?)");
    static MyDrawPanel ml;

    public static void main(String[] args) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }

    public void setUpGui(){
        ml = new MyDrawPanel();
        f.setContentPane(ml);
        f.setBounds(30,30,300,300);
        f.setVisible(true);
    }

    public void go() {
        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            int[] eventsIWant = {127};  // represents the list of ControllerEvents I want.
            sequencer.addControllerEventListener(ml, eventsIWant);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r;
            for (int i = 0; i < 61; i += 4) {
                r = (int)( (Math.random() * 50) + 1 );
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent( 176, 1, 127, 0, i ));
                track.add(makeEvent(128, 1, r, 100, i + 2));
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

    class MyDrawPanel extends JPanel implements  ControllerEventListener{
        boolean msg = false;

        @Override
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent(Graphics g){
            if(msg){

                int r = (int)( Math.random() * 250 );
                int gr = (int)( Math.random() * 250 );
                int b = (int)( Math.random() * 250 );
                g.setColor( new Color(r,gr,b) );

                int hight = (int)( (Math.random() * 120) + 10 );
                int width = (int)( (Math.random() * 120) + 10 );

                int x = (int)( (Math.random() * 40) + 10 );
                int y = (int)( (Math.random() * 40) + 10 );

                g.fillRect(x,y,width,hight);
                msg = false;

            }
        }   // end of paintComponent();
    }   // end of inner class

}
