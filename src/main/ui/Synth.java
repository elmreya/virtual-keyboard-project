package ui;

import model.ListOfNotes;

import javax.sound.midi.*;

// Synthesizer for playing an instrument
public class Synth {

    private Synthesizer synthesizer;
    private Instrument[] instrument;

    //MODIFIES: this
    //EFFECTS: Sets up the instrument
    // (i.e., the program can now use a method to make sound)
    public void setUp() {
        getSynthesizer();
        setupInstrument();

    }

    //MODIFIES: this
    //EFFECTS: Loads and returns a synthesizer which can be later used to create sounds
    private Soundbank getSoundBank() {
        return synthesizer.getDefaultSoundbank();
    }

    //EFFECTS: takes a note (this is an int, and it gets converted to play a certain pitch)
    // and produces a grand piano sound
    public void play(int note) {

        MidiChannel[] midiChannels = synthesizer.getChannels();
        midiChannels[0].noteOn(note, 90);

    }

    public void getSynthesizer() {
        try {
            if (this.synthesizer == null) {
                if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
                    System.out.println("Fail");
                    return;
                }
            }

            synthesizer.open();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up a grand piano that can now be played
    public void setupInstrument() {
        if (getSoundBank() != null) {
            instrument = synthesizer.getDefaultSoundbank().getInstruments();
            synthesizer.loadInstrument(instrument[0]);
        }


    }


}
