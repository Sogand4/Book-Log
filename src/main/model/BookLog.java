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

    // EFFECTS: Returns all the books listed in the book log, in the order of recently added
    public List<Book> getLogInOrderRecentlyAdded() {
        List<Book> sortedList = bookLog;
        int j = 0;

        if (sortedList.size() > 1) {
            for (int i = sortedList.size() - 1; i >= sortedList.size() / 2; i--) {
                Book b1 = sortedList.get(i);
                Book b2 = sortedList.get(j);
                sortedList.set(i, b2);
                sortedList.set(j, b1);
                j++;
            }
            return sortedList;
        } else {
            return bookLog;
        }

    }

    // EFFECTS: Returns all the books listed in the book log, in the order of descending rating
    public List<Book> getLogInOrderHighestRating() {
        List<Book> sortedList = bookLog;

        if (sortedList.size() > 1) {
            for (int i = 1; i < sortedList.size(); i++) {
                int prevIndex = i - 1;
                Book currentBook = sortedList.get(i);
                Book prevBook = sortedList.get(prevIndex);
                if (prevBook.getRating() < currentBook.getRating()) {
                    sortedList.set(i, prevBook);
                    sortedList.set(prevIndex, currentBook);
                }
            }
            return sortedList;
        } else {
            return bookLog;
        }
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
    // EFFECTS: Removes a book from the book log
    public void removeBook(Book book) {
        bookLog.remove(book);
    }

    // EFFECTS: return the number of books in the book log
    public int numBooksRead() {
        return bookLog.size();
    }

    // EFFECTS: returns true if the book is in the book log
    public boolean isBookInLog(Book book) {
        return bookLog.contains(book);
    }
}
