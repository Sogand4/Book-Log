package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for the Book class.
class BookTest {

    private Book testBook;

    @Test
    void testConstructor() {
        testBook = new Book("To Kill a Mockingbird", "Harper Lee", 4, true);
        assertEquals("To Kill a Mockingbird", testBook.getTitle());
        assertEquals("Harper Lee", testBook.getAuthor());
        assertEquals(4, testBook.getRating());
        assertTrue(testBook.getType());
    }
}