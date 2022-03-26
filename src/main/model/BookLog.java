package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a book log consisting of a list of books
public class BookLog implements Writable {

    private List<Book> bookLog;

    // EFFECTS: Constructs an empty list of books
    public BookLog() {
        bookLog = new ArrayList<>();
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
        EventLog.getInstance().logEvent(new Event(book.getTitle() + " was added to your book log!"));
    }

    // MODIFIES: this
    // EFFECTS: Removes the book in the given index from the book log
    public void removeBook(int index) {
        EventLog.getInstance().logEvent(new Event(bookLog.get(index).getTitle()
                + " was removed from your book log!"));
        bookLog.remove(index);
    }

    public List<Book> getBookLog() {
        return bookLog;
    }

    // EFFECTS: returns book log as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Book b : bookLog) {
            jsonArray.put(b.toJson());
        }

        json.put("books", jsonArray);
        return json;
    }
}
