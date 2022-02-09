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
    private Book testBook3;

    @BeforeEach
    void runBefore() {
        testBookLog = new BookLog();
        testBook1 = new Book("To Kill a Mockingbird", "Harper Lee", 4, true);
        testBook2 = new Book("How to Win Friends and Influence People",
                "Dale Carnegie", 3, false);
        testBook3 = new Book("The Hate U Give", "Angie Thomas", 4, true);
    }

    @Test
    void testConstructor() {
        books = testBookLog.getBookLog();
        assertEquals(0, books.size());
    }

    @Test
    void testGetLogInOrderHighestRating() {
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.addBook(testBook3);
        books = testBookLog.getLogInOrderHighestRating();
        assertEquals(testBook1, books.get(0));
        assertEquals(testBook3, books.get(1));
        assertEquals(testBook2, books.get(2));
    }

    @Test
    void testRemoveBook() {
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.addBook(testBook3);
        testBookLog.removeBook(1);
        books = testBookLog.getBookLog();
        assertEquals(testBook1, books.get(0));
        assertEquals(testBook3, books.get(1));
        assertEquals(2, books.size());
        testBookLog.removeBook(0);
        testBookLog.removeBook(0);
        assertEquals(0, books.size());
    }

    @Test
    void testAddBook() {
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.addBook(testBook3);
        books = testBookLog.getBookLog();
        assertEquals(testBook1, books.get(0));
        assertEquals(testBook2, books.get(1));
        assertEquals(testBook3, books.get(2));
        assertEquals(3, books.size());
    }

    @Test
    void testGetFictionBooks() {
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.addBook(testBook3);
        books = testBookLog.getFictionBooks();
        assertEquals(2, books.size());
        assertEquals(testBook1, books.get(0));
        assertEquals(testBook3, books.get(1));
    }

    @Test
    void testGetNonfictionBooks() {
        Book testBook4 = new Book("Atomic Habits: An Easy and Proven Way to Build Good Habits and Break Bad Ones",
                "James Clear", 5, false);
        testBookLog.addBook(testBook1);
        testBookLog.addBook(testBook2);
        testBookLog.addBook(testBook3);
        testBookLog.addBook(testBook4);
        books = testBookLog.getNonfictionBooks();
        assertEquals(2, books.size());
        assertEquals(testBook2, books.get(0));
        assertEquals(testBook4, books.get(1));
    }
}