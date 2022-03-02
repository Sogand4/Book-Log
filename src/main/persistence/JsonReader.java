package persistence;

import model.Book;
import model.BookLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads booklog from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads booklog from file and returns it;
    // throws IOException if an error occurs reading data from file
    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public BookLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses book log from JSON object and returns it
    // Method modelled after JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private BookLog parseBookLog(JSONObject jsonObject) {
        BookLog bl = new BookLog();
        addBooks(bl, jsonObject);
        return bl;
    }

    // MODIFIES: bl
    // EFFECTS: parses books from JSON object and adds them to book log
    // Method modelled after JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addBooks(BookLog bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bl, nextBook);
        }
    }

    // MODIFIES: bl
    // EFFECTS: parses book from JSON object and adds it to book log
    private void addBook(BookLog bl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int rating = jsonObject.getInt("rating");
        boolean type = jsonObject.getBoolean("type");
        Book book = new Book(title, author, rating, type);
        bl.addBook(book);
    }
}
