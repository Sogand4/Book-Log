package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    // EFFECTS: Checks that the details of the book is correct
    // Method modelled after JsonTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    protected void checkBook(String title, String author, int rating, boolean type, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(rating, book.getRating());
        assertEquals(type, book.getType());
    }
}
