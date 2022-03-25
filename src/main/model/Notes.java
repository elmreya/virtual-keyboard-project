package model;


import org.json.JSONObject;
import persistence.Writable;

public class Notes implements Writable {

    private String str;

    public Notes(String s) {
        this.str = s;
    }


    //REQUIRES: The string must be either "a","s","d","f","g","h","j","k", or "l"
    //EFFECTS: converts keyboard signals into notes that an instrument recognizes
    public int convertNote(String n) {

        if (n.equals("a")) {
            return 60;
        } else if (n.equals("s")) {
            return 62;
        } else if (n.equals("d")) {
            return 64;
        } else if (n.equals("f")) {
            return 65;
        } else if (n.equals("g")) {
            return 67;
        } else if (n.equals("h")) {
            return 69;
        } else if (n.equals("j")) {
            return 71;
        } else if (n.equals("k")) {
            return 72;
        } else if (n.equals("l")) {
            return 74;
        } else {
            return 0;
        }

    }

    //EFFECTS: Returns a note
    public String getNote() {
        return str;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("note", str);
        return json;
    }




}


