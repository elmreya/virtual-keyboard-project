package Persistence;

import model.ListOfNotes;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    private List<String> ls;

    @Test

    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfNotes lon = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLON.json");
        try {
            ListOfNotes lon = reader.read();
            assertEquals(0, lon.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLON.json");
        ls = new ArrayList<>();
        ls.add("a");
        ls.add("g");
        ls.add("j");
        try {
            ListOfNotes lon = reader.read();
            assertEquals(3, lon.size());
            assertEquals(ls.get(0), lon.getNote(0));
            assertEquals(ls.get(1), lon.getNote(1));
            assertEquals(ls.get(2), lon.getNote(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
