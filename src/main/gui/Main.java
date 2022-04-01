package gui;

import static gui.BookLogGUI.createAndShowGUI;
import static gui.BookLogGUI.displayWelcomeImage;

public class Main {

    // EFFECTS: Displays a welcome image then runs the BookLogGUI
    public static void main(String[] args) {
        displayWelcomeImage();
        createAndShowGUI();
    }
}
