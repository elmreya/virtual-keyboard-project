package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesTest {

    Notes note;
    @BeforeEach
    public void setup(){

        note = new Notes("a");

    }

    @Test

    public void testConstructor(){

        assertEquals("a", note.getNote());

    }

    @Test
    public void convertNotesTest(){
        assertEquals(24, note.convertNote("a"));
        assertEquals(28, note.convertNote("g"));

    }


}