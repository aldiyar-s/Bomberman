/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Aldiyar
 */
public class SoundHandler {

    Sequencer s, l, w;
    Synthesizer synth;

    public void load() {
        try {

            synth = MidiSystem.getSynthesizer();
            synth.open();
            s = MidiSystem.getSequencer();
            s.open();
            s.setSequence(MidiSystem.getSequence(new File("underground2.mid")));
            l = MidiSystem.getSequencer();
            l.open();
            l.setSequence(MidiSystem.getSequence(new File("death.mid")));
            w = MidiSystem.getSequencer();
            w.open();
            w.setSequence(MidiSystem.getSequence(new File("level-complete.mid")));



        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SoundHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SoundHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void plant() {
        try {
            MidiChannel[] channels = synth.getChannels();
            channels[9].allNotesOff();
            channels[9].noteOn(34, 100); //34
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void explode() {
        try {
            MidiChannel[] channels = synth.getChannels();
            channels[9].allNotesOff();
            channels[9].noteOn(45, 100); //45

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void win() {
        try {
            MidiChannel[] channels = synth.getChannels();
            channels[9].allNotesOff();
            channels[9].noteOn(53, 100);
            w.setLoopCount(0);
            w.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void die() {
        try {
            MidiChannel[] channels = synth.getChannels();
            channels[9].allNotesOff();
            channels[9].noteOn(30, 100);
            l.setLoopCount(0);
            l.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void songStart() {
        s.setLoopCount(0);
        s.start();
    }

    public void songStop() {
        s.stop();
        l.stop();
    }
}
