package ui;

import model.Book;
import model.BookLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Book Log application
public class BookLogApp {
    private static final String JSON_BOOKLOG = "./data/bookLog.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input = new Scanner(System.in);
    private BookLog myBookLog = new BookLog();

    // EFFECTS: runs the teller application
    public BookLogApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_BOOKLOG);
        jsonReader = new JsonReader(JSON_BOOKLOG);
        runBookLog();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Parts of code taken from TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runBookLog() {
        boolean keepGoing = true;
        int command;
        input.useDelimiter("\n");

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 0) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: processes user command
    @SuppressWarnings("methodlength")
    private void processCommand(int command) {
        if (command == 1 || command == 8 || command == 9) {
            switch (command) {
                case 1:
                    doAddBook();
                    break;
                case 8:
                    saveBookLog();
                    break;
                case 9:
                    loadBookLog();
                    break;
                default:
                    break;
            }
        } else if (myBookLog.getBookLog().size() == 0 && command > 0 && command <= 7) {
            System.out.println("Your book log is empty. Try adding a book!");
        } else {
            switch (command) {
                case 2:
                    doRemoveBook();
                    break;
                case 3:
                    selectBook();
                    break;
                case 4:
                    viewBookLog(myBookLog.getBookLog());
                    break;
                case 5:
                    viewBookLog(myBookLog.getLogInOrderHighestRating());
                    break;
                case 6:
                    if (myBookLog.getFictionBooks().size() == 0) {
                        System.out.println("You haven't read any fiction books.");
                    } else {
                        viewBookLog(myBookLog.getFictionBooks());
                    }
                    break;
                case 7:
                    if (myBookLog.getNonfictionBooks().size() == 0) {
                        System.out.println("You haven't read any nonfiction books.");
                    } else {
                        viewBookLog(myBookLog.getNonfictionBooks());
                    }
                    break;
                default:
                    System.out.println("Invalid input - try again:");
                    break;
            }
        }
    }

    // EFFECTS: displays the list of books to the user
    private void viewBookLog(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i + 1 + ") " + books.get(i).getTitle());
        }
    }

    // EFFECTS: Prompts user to selects a book from their book log
    private void selectBook() {
        int index;
        List<Book> books = myBookLog.getBookLog();

        viewBookLog(books);
        System.out.println("\nSelect the number of the book you would like to view the details of: ");
        index = input.nextInt();
        index--;

        if (index >= 0 && index < books.size()) {
            displayBookDetails(index);
        } else {
            System.out.println("Error - not a valid input. Back to menu...");
        }
    }

    // REQUIRES: book log has an element in the given index
    // EFFECTS: displays the details of the book with the given index in book log
    private void displayBookDetails(int index) {
        Book book = myBookLog.getBookLog().get(index);
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Rating: " + book.getRating());
        if (book.getType()) {
            System.out.println("Type: Fiction");
        } else {
            System.out.println("Type: Nonfiction");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a book to myBookLog
    private void doAddBook() {
        boolean type;

        System.out.println("\nInput the details for the book:");
        System.out.print("\t Title: ");
        String title = input.next();
        System.out.print("\t Author: ");
        String author = input.next();
        System.out.print("\t Rating (1 to 5 stars): ");
        int rating = input.nextInt();
        System.out.print("\t Type (enter \"f\" if it's fiction and \"n\" for nonfiction): ");
        String bookType = input.next();

        if (!bookType.equalsIgnoreCase("f") && !bookType.equalsIgnoreCase("n")) {
            System.out.println("Error: Invalid book type. Returning to menu...");
        } else {
            type = bookType.equalsIgnoreCase("f");
            Book newBook = new Book(title, author, rating, type);
            myBookLog.addBook(newBook);
            System.out.println(title + " was added to your book log.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a book from myBookLog
    private void doRemoveBook() {
        int index;
        List<Book> books = myBookLog.getBookLog();
        viewBookLog(books);
        System.out.println("\nSelect the number of the book you would like to remove: ");
        index = input.nextInt();
        index--;

        if (index >= 0 && index < books.size()) {
            System.out.println(books.get(index).getTitle() + " was removed from your book log.");
            myBookLog.removeBook(index);
        } else {
            System.out.println("Error - not a valid input.\nBack to menu:\n");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1) Add a book");
        System.out.println("\t2) Remove a book");
        System.out.println("\t3) Select and view a book");
        System.out.println("\t4) View book log");
        System.out.println("\t5) View book log in order of descending rating");
        System.out.println("\t6) View all fiction books");
        System.out.println("\t7) View all nonfiction books");
        System.out.println("\t8) Save book log to file");
        System.out.println("\t9) Load book log from file");
        System.out.println("\t0) QUIT");
    }

    // EFFECTS: saves the booklog to file
    // Code taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveBookLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(myBookLog);
            jsonWriter.close();
            System.out.println("Saved your bookLog to " + JSON_BOOKLOG);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_BOOKLOG);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads booklog from file
    // Code taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void loadBookLog() {
        try {
            myBookLog = jsonReader.read();
            System.out.println("Loaded your bookLog from " + JSON_BOOKLOG);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_BOOKLOG);
        }
    }
}
