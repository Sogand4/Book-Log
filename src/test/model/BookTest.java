package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for the Book class.
class BookTest {

    private Book testBook;

    @BeforeEach
    void runBefore() {
        testBook = new Book("To Kill a Mockingbird", "Harper Lee", 4, true);
    }

    @Test
    void testConstructor() {
        assertEquals("To Kill a Mockingbird", testBook.getTitle());
        assertEquals("Harper Lee", testBook.getAuthor());
        assertEquals(4, testBook.getRating());
        assertTrue(testBook.getType());
    }

    @Test
    void testSetTitle() {
        testBook.setTitle("Alchemist");
        assertEquals("Alchemist", testBook.getTitle());
        testBook.setTitle("The Alchemist");
        assertEquals("The Alchemist", testBook.getTitle());
    }

    @Test
    void testSetAuthor() {
        testBook.setAuthor("paulo coelho");
        assertEquals("paulo coelho", testBook.getAuthor());
        testBook.setAuthor("Paulo Coelho");
        assertEquals("Paulo Coelho", testBook.getAuthor());
    }

    @Test
    void testSetRating() {
        testBook.setRating(5);
        assertEquals(5, testBook.getRating());
        testBook.setRating(3);
        assertEquals(3, testBook.getRating());
    }

    @Test
    void testSetType() {
        testBook.setType(false);
        assertFalse(testBook.getType());
        testBook.setType(true);
        assertTrue(testBook.getType());
    }
}