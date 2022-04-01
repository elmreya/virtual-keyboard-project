package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ListOfNotes implements Writable {
    private List<String> lon;
    private String str;

    //MODIFIES: this
    //EFFECTS: Constructs a new list of notes, this list is initially empty
    public ListOfNotes() {
        lon = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a note to the list of notes
    public void addNotes(Notes n) {
        str = n.getNote();
        lon.add(str);
        EventLog.getInstance().logEvent(new Event("Added note " + str + " to List of Notes"));

    }

    //EFFECTS: Returns the list of notes
    public List<String> getListOfNotes() {
        return lon;

    }

    //EFFECTS: Returns the size of the list of notes
    public int size() {
        return lon.size();
    }

    //EFFECTS: Get the ith note from the list of notes
    public String getNote(int i) {

        return lon.get(i);

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List of Notes", lonToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray lonToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : lon) {
            Notes t = new Notes(s);
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }


}
