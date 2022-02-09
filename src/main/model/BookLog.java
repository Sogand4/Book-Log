package model;

import java.util.List;
import java.util.ArrayList;

// Represents a book log consisting of a list of books
public class BookLog {

    private List<Book> bookLog;

    // EFFECTS: Constructs an empty list of books
    public BookLog() {
        bookLog = new ArrayList<>();
    }

    public List<Book> getBookLog() {
        return bookLog;
    }

    // EFFECTS: Returns all the books listed in the book log, in the order of descending rating
    public List<Book> getLogInOrderHighestRating() {
        List<Book> sortedList = new ArrayList<>();

        for (int i = 5; i >= 1; i--) {
            for (Book book : bookLog) {
                if (book.getRating() == i) {
                    sortedList.add(book);
                }
            }
        }
        return sortedList;
    }

    // EFFECTS: Returns all the fictional books listed in the book log
    public List<Book> getFictionBooks() {
        List<Book> fictionBooks = new ArrayList<>();

        for (Book book : bookLog) {
            if (book.getType()) {
                fictionBooks.add(book);
            }
        }
        return fictionBooks;
    }

    // EFFECTS: Returns all the nonfictional books listed in the book log
    public List<Book> getNonfictionBooks() {
        List<Book> nonfictionBooks = new ArrayList<>();

        for (Book book : bookLog) {
            if (!book.getType()) {
                nonfictionBooks.add(book);
            }
        }
        return nonfictionBooks;
    }

    // MODIFIES: this
    // EFFECTS: Adds a book to the book log
    public void addBook(Book book) {
        bookLog.add(book);
    }

    // MODIFIES: this
    // EFFECTS: Removes the book in the given index from the book log
    public void removeBook(int index) {
        bookLog.remove(index);
    }
}
