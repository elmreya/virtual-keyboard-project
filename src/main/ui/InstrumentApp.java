package ui;

import model.ListOfNotes;
import model.Notes;
import sound.Synth;

import java.util.Scanner;

public class InstrumentApp {
    ListOfNotes lon;
    Notes note;
    Synth synth = new Synth();
    private Scanner input;

    //EFFECTS: Runs the instrument app
    public InstrumentApp() {

        runInstrument();

    }

    private void runInstrument() {
        boolean keepGoing = true;
        String command = null;
        lon = new ListOfNotes();

        init();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("v")) {
                System.out.println(lon.getListOfNotes());
            } else if (command.equals("listen")) {
                playAll(lon);
            } else {
                note = new Notes(command);
                synth.setUp();
                synth.play(note.convertNote(command));
                lon.addNotes(note);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS: Displays menu
    private void displayMenu() {
        System.out.println("\nUse the 2nd row of keys to play the instrument");
        System.out.println("\tv -> View all notes played");
        System.out.println("\tq -> quit");
        System.out.println("\tlisten -> Listen to all the notes you've played at once");
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    public void playAll(ListOfNotes ln) {
        int i;
        int j = ln.size();
        for (i = 0; i < j; i++) {
            String n = ln.getNote(i);
            synth.play(note.convertNote(n));


        }

    }


}


