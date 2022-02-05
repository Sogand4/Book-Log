package model;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Unit tests for the BookLogTest class.
public class BookLogTest {

    private BookLog testBookLog;
    private List<Book> books;
    private Book testBook1;
    private Book testBook2;

    @BeforeEach
    void runBefore() {
        testBookLog = new BookLog();
        testBook1 = new Book("To Kill a Mockingbird", "Harper Lee", 4, true);
        testBook2 = new Book("How to Win Friends and Influence People",
                "Dale Carnegie", 3, false);
    }

    @Test
    void testConstructor() {
        books = testBookLog.getBookLog();
        assertEquals(0,books.size());
    }

    @Test
    void testGetLogInOrderHighestRating() {
        testBookLog.addBook(testBook1);
        books = testBookLog.getLogInOrderHighestRating();
        assertEquals(testBook1, books.get(0));
        testBookLog.addBook(testBook2);
        books = testBookLog.getLogInOrderHighestRating();
        assertEquals(testBook1, books.get(0));
        assertEquals(testBook2, books.get(1));
    }

    @Test
    void testNumBooksRead() {
        assertEquals(0, testBookLog.numBooksRead());
        testBookLog.addBook(testBook1);
        assertEquals(1, testBookLog.numBooksRead());
        testBookLog.addBook(testBook2);
        assertEquals(2, testBookLog.numBooksRead());
    }

    @Test
    void testIsBookInLog() {
        assertFalse(testBookLog.isBookInLog(testBook1));
        testBookLog.addBook(testBook1);
        assertTrue(testBookLog.isBookInLog(testBook1));
        testBookLog.addBook(testBook2);
        assertTrue(testBookLog.isBookInLog(testBook1));
        assertTrue(testBookLog.isBookInLog(testBook2));
    }

    @Test
    void testRemoveBook() {
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.removeBook(testBook1);
        books = testBookLog.getBookLog();
        assertEquals(testBook1, books.get(0));
        assertEquals(1, books.size());
        testBookLog.removeBook(testBook1);
        assertEquals(0, books.size());
    }

    @Test
    void testAddBook() {
        testBookLog.addBook(testBook1);
        books = testBookLog.getBookLog();
        assertEquals(testBook1, books.get(0));
        assertEquals(1, books.size());
        testBookLog.addBook(testBook2);
        books = testBookLog.getBookLog();
        assertEquals(testBook2, books.get(0));
        assertEquals(testBook1, books.get(1));
        assertEquals(2, books.size());
    }

    @Test
    void testGetFictionalBooks() {
        testBookLog.addBook(testBook2);
        books = testBookLog.getNonfictionalBooks();
        assertEquals(1, books.size());
        testBookLog.addBook(testBook1);
        books = testBookLog.getFictionalBooks();
        assertEquals(1, books.size());
        assertEquals(testBook1, books.get(0));
    }

    @Test
    void testGetNonfictionalBooks() {
        testBookLog.addBook(testBook1);
        books = testBookLog.getNonfictionalBooks();
        assertEquals(0, books.size());
        testBookLog.addBook(testBook2);
        books = testBookLog.getNonfictionalBooks();
        assertEquals(1, books.size());
        assertEquals(testBook2, books.get(0));
    }
}