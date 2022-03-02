package ui;

import java.io.FileNotFoundException;

public class Main {

    // Method taken from Main class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public static void main(String[] args) {
        try {
            new BookLogApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
