package persistence;

import model.Book;
import model.BookLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    BookLog bl;

    @BeforeEach
    void runBefore() {
        bl = new BookLog();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookLog() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookLog.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookLog.json");
            bl = reader.read();
            assertEquals(0, bl.getBookLog().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookLog() {
        try {
            bl.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 4, true));
            bl.addBook(new Book("How to Win Friends and Influence People",
                    "Dale Carnegie", 3, false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookLog.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookLog.json");
            bl = reader.read();
            List<Book> books = bl.getBookLog();
            assertEquals(2, books.size());
            checkBook("To Kill a Mockingbird", "Harper Lee", 4, true, books.get(0));
            checkBook("How to Win Friends and Influence People",
                    "Dale Carnegie", 3, false, books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
