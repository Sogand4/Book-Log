package persistence;

import model.BookLog;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of booklog to file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // Method taken from JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of book log to file
    // Method modelled after JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void write(BookLog bl) {
        JSONObject json = bl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // Method taken from JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }
}
