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

        assertEquals(60, note.convertNote("a"));
        assertEquals(62, note.convertNote("s"));
        assertEquals(64, note.convertNote("d"));
        assertEquals(65, note.convertNote("f"));
        assertEquals(67, note.convertNote("g"));
        assertEquals(69, note.convertNote("h"));
        assertEquals(71, note.convertNote("j"));
        assertEquals(72, note.convertNote("k"));
        assertEquals(74, note.convertNote("l"));
        assertEquals(0, note.convertNote("n"));

    }


}