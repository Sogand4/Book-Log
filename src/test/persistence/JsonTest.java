package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkBook(String title, String author, int rating, boolean type, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(rating, book.getRating());
        assertEquals(type, book.getType());
    }
}
