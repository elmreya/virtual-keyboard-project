package model;


import java.util.ArrayList;
import java.util.List;

public class ListOfNotes {
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
}
