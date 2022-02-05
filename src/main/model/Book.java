package model;

// Represents a book having a title, author, rating, and type
public class Book {

    private String title;        // title of the book
    private String author;       // author of the book
    private int rating;          // rating of the book
    private boolean type;        // the type of the book. true = fiction, false = non-fiction

    // REQUIRES: rating is an int in [1,5]
    // EFFECTS: constructs a Book with a title, author, and rating
    public Book(String title, String author, int rating, boolean fiction) {
        //stub
    }

    public String getTitle() {
        return null; //stub
    }

    public String getAuthor() {
        return null; //stub
    }

    public int getRating() {
        return 0; //stub
    }

    public boolean getType() {
        return false; //stub
    }

    public void setTitle(String title) {
        //stub
    }

    public void setAuthor(String author) {
        //stub
    }

    // REQUIRES: rating is an int in [1,5]
    public void setRating(int rating) {
        //stub
    }

    public void setType(Boolean type) {
        //stub
    }
}
