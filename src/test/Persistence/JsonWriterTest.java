package Persistence;

import model.ListOfNotes;
import model.Notes;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    private List<String> ls;
    private Notes s;
    private Notes h;
    private Notes k;


    @Test
    void testWriterInvalidFile() {
        try {
            ListOfNotes lon = new ListOfNotes();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IO Exception");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfNotes lon = new ListOfNotes();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLON.json");
            writer.open();
            writer.write(lon);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLON.json");
            lon = reader.read();
            assertEquals(0, lon.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            s = new Notes("s");
            h = new Notes("h");
            k = new Notes("k");
            ListOfNotes lon = new ListOfNotes();
            lon.addNotes(s);
            lon.addNotes(h);
            lon.addNotes(k);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLON.json");
            writer.open();
            writer.write(lon);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLON.json");
            lon = reader.read();
            ls = new ArrayList<>();
            ls.add("s");
            ls.add("h");
            ls.add("k");

            assertEquals(3, lon.size());
            assertEquals(ls.get(0), lon.getNote(0));
            assertEquals(ls.get(1), lon.getNote(1));
            assertEquals(ls.get(2), lon.getNote(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }


    }
}


