package persistence;

import model.ListOfNotes;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// Writes the list of notes into file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // Constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }


    //MODIFIES: this
    //EFFECTS: opens writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of List of Notes to file.
    public void write(ListOfNotes lon) {
        JSONObject json = lon.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes the string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }



}
