package model;


public class Notes {

    private String str;

    public Notes(String s) {
        str = s;

    }


    //REQUIRES: The string must be either "a","s","d","f","g","h","j","k", or "l"
    //EFFECTS: converts keyboard signals into notes that an instrument recognizes
    public int convertNote(String n) {

        if (n.equals("a")) {
            return 60;
        } else if (n.equals("s")) {
            return 61;
        } else if (n.equals("d")) {
            return 62;
        } else if (n.equals("f")) {
            return 63;
        } else if (n.equals("g")) {
            return 64;
        } else if (n.equals("h")) {
            return 65;
        } else if (n.equals("j")) {
            return 66;
        } else if (n.equals("k")) {
            return 67;
        } else if (n.equals("l")) {
            return 68;
        } else {
            return 0;
        }

    }

    //EFFECTS: Returns a note
    public String getNote() {
        return str;

    }


}


