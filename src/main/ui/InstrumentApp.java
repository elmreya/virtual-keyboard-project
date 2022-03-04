package ui;

import model.ListOfNotes;
import model.Notes;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Instrument application
public class InstrumentApp {
    ListOfNotes lon;
    Notes note;
    Synth synth = new Synth();
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/listOfNotes.json";

    //EFFECTS: Runs the instrument app
    public InstrumentApp() {

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

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
            } else if (command.equals("save")) {
                saveLON();
            } else if (command.equals("load")) {
                loadLON();
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
        System.out.println("\tsave -> Save List of Notes to file");
        System.out.println("\tload -> Load List of Notes from file");
    }

    //MODIFIES: this
    //EFFECTS: Initializes a ui interface
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: Plays all notes in the list of notes at once
    public void playAll(ListOfNotes ln) {
        int i;
        int j = ln.size();
        for (i = 0; i < j; i++) {
            String n = ln.getNote(i);
            synth.play(note.convertNote(n));


        }

    }

    //EFFECTS: Saves the lon to file
    public void saveLON() {
        try {
            jsonWriter.open();
            jsonWriter.write(lon);
            jsonWriter.close();
            System.out.println("Saved " + lon.getListOfNotes() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads lon from file
    private void loadLON() {
        try {
            lon = jsonReader.read();
            System.out.println("Loaded " + lon.getListOfNotes() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }




}


