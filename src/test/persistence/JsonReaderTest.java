package persistence;

import model.Book;
import model.BookLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookLog bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookLog.json");
        try {
            BookLog bl = reader.read();
            assertEquals(0, bl.getBookLog().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookLog.json");
        try {
            BookLog bl = reader.read();
            List<Book> books = bl.getBookLog();
            assertEquals(2, books.size());
            checkBook("To Kill a Mockingbird", "Harper Lee", 4, true, books.get(0));
            checkBook("How to Win Friends and Influence People",
                    "Dale Carnegie", 3, false, books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
