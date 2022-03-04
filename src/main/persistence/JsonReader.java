package persistence;


import model.ListOfNotes;
import model.Notes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Reads the list of notes from the file
public class JsonReader {
    private String source;


    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    //EFFECTS: reads the list of notes from file and returns it
    // in case of an error when reading from file, throws IO Exception
    public ListOfNotes read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLON(jsonObject);
    }

    // EFFECTS: reads the file
    private String readFile(String source) throws IOException {
        StringBuilder notesBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> notesBuilder.append(s));

        }

        return notesBuilder.toString();

    }

    //EFFECTS: parses the list of notes from JSON object and returns it
    private ListOfNotes parseLON(JSONObject jsonObject) {
        ListOfNotes lon = new ListOfNotes();
        addNotes(lon, jsonObject);
        return lon;
    }

    // MODIFIES: lon
    // EFFECTS: parses notes from JSON object and adds them to List Of Notes
    private void addNotes(ListOfNotes lon, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List of Notes");
        for (Object json : jsonArray) {
            JSONObject nextNotes = (JSONObject) json;
            addNote(lon, nextNotes);
        }
    }

    // MODIFIES: lon
    // EFFECTS: parses note from JSON object and adds it to List Of Notes
    private void addNote(ListOfNotes lon, JSONObject jsonObject) {
        String name = jsonObject.getString("note");
        Notes note = new Notes(name);
        lon.addNotes(note);
    }


}
