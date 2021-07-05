package headFirstJava.ch11;

import javax.sound.midi.*;

public class MusicTest1 {

    public static void main(String[] args) {
        MusicTest1 mt1 = new MusicTest1();
        mt1.play();
    }

    public void play(){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("We got a sequencer");
        } catch (MidiUnavailableException emidi){
            System.out.println("Bummer");
        }
    }

}
