package model;

import java.util.List;

// Represents a book log consisting of a list of books
public class BookLog {

    private List<Book> bookLog;

    // EFFECTS: Constructs an empty list of books
    public BookLog() {
        // stub
    }

    // EFFECTS: Returns all the books listed in the book log, in the order of recently added
    public List<Book> getBookLog() {
        return bookLog;
    }

    // EFFECTS: Returns all the books listed in the book log, in the order of descending rating
    public List<Book> getLogInOrderHighestRating() {
        return null; // stub
    }

    // EFFECTS: Returns all the fictional books listed in the book log
    public List<Book> getFictionalBooks() {
        return null; // stub
    }

    // EFFECTS: Returns all the nonfictional books listed in the book log
    public List<Book> getNonfictionalBooks() {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECTS: Adds a book to the book log
    public void addBook(Book book) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Removes a book from the book log
    public void removeBook(Book book) {
        //stub
    }

    // EFFECTS: return the number of books in the book log
    public int numBooksRead() {
        return 0;
    }

    // EFFECTS: returns true if the book is in the book log
    public boolean isBookInLog(Book book) {
        // public or private??
        return false;
    }
}
