package model;

// Represents a book having a title, author, rating, and type
public class Book {

    private String title;        // title of the book
    private String author;       // author of the book
    private int rating;          // rating of the book
    private boolean type;        // the type of the book. true = fiction, false = non-fiction

    // REQUIRES: rating is an int in [1,5]
    // EFFECTS: constructs a Book with a title, author, and rating
    public Book(String title, String author, int rating, boolean type) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public boolean getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // REQUIRES: rating is an int in [1,5]
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
