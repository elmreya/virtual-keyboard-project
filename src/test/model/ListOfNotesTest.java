package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfNotesTest {

    ListOfNotes lon;
    private List<String> ln;
    private Notes note;


    @BeforeEach

    public void setup(){
        lon = new ListOfNotes();
    }

    @Test

    public void testConstructor(){
        ln = new ArrayList<>();
        lon = new ListOfNotes();
        assertEquals(ln, lon.getListOfNotes());
    }

    @Test
    public void addNotesTest(){
        note = new Notes("g");
        ln = new ArrayList<>();
        ln.add(note.getNote());
        lon.addNotes(note);
        assertEquals(ln, lon.getListOfNotes());

    }

    @Test
    public void getNoteTest(){

        note = new Notes(("g"));
        lon.addNotes(note);
        assertEquals("g",lon.getNote(0));

    }

    @Test
    public void sizeTest(){
        note = new Notes("s");
        assertEquals(0, lon.size());
        lon.addNotes(note);
        assertEquals(1, lon.size());

    }


}
