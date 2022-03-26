package gui;

import model.Book;
import model.BookLog;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represents a GUI for the Book Log
public class BookLogGUI extends JPanel implements ListSelectionListener {

    private JTextField title = new JTextField(30);
    private JTextField author = new JTextField(30);
    private JTextField rating = new JTextField(5);
    private JTextField type = new JTextField(5);
    private BookLog bl = new BookLog();

    private static final Color COLOR = new Color(255, 236, 236);

    private static final String addBookString = "Add Book";
    private static final String removeBookString = "Remove Book";
    private JButton removeBookButton;
    private JButton addBookButton;
    private JList list;
    private DefaultListModel bookTitlesList;

    // Creates the main panel for the GUI
    public BookLogGUI() {
        super(new BorderLayout());
        initializeJList();

        add(createMenuBar(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createAddBookInfoPanel(), BorderLayout.SOUTH);
    }

    // EFFECTS: Creates and returns a JLabel with details on how to use the Book Log GUI
    public JLabel createInstructionsLabel() {
        JLabel instructions = new JLabel("<html><center><h1>This is your book log to keep tack of the books you "
                + "have read!</h1><br> <font size=\"+2\">You can... </font></center> <br>"
                + "<ul style=“list-style-type:circle><li> <font size=\"+1\"> Save/load data under \"FILE\" in the "
                + "menu above</li></font> <li><font size=\"+1\"> Add information below and press \"Add Book\" to add "
                + "the book to your book log</li></font> <li><font size=\"+1\"> Select a book in your book "
                + "log and press \"Remove Book\" to remove it</li></font></html>",
                JLabel.CENTER);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        return instructions;
    }

    // EFFECTS: Creates and returns a JPanel containing all the elements to be displayed in the center of the GUI
    public JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        JScrollPane listScrollPane = new JScrollPane(list);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(createInstructionsLabel());
        centerPanel.add(listScrollPane);
        centerPanel.add(createButtonPanel());
        centerPanel.setBackground(COLOR);
        return centerPanel;
    }

    // EFFECTS: Creates and returns a JPanel containing all the buttons of the Book Log GUI
    public JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBackground(COLOR);
        createButtons();
        buttonPanel.add(removeBookButton);
        buttonPanel.add(addBookButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: Initializes an editable JList containing the titles of the books read
    public void initializeJList() {
        bookTitlesList = new DefaultListModel();
        list = new JList(bookTitlesList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(8);
    }

    // MODIFIES: this
    // EFFECTS: Creates two buttons, one to add books and one to remove books
    public void createButtons() {
        AddBookListener addBookListener;
        addBookButton = new JButton(addBookString);
        addBookListener = new AddBookListener(addBookButton);
        addBookButton.setActionCommand(addBookString);
        addBookButton.addActionListener(addBookListener);
        addBookButton.setEnabled(false);

        title.addActionListener(addBookListener);
        title.getDocument().addDocumentListener(addBookListener);
        author.addActionListener(addBookListener);
        author.getDocument().addDocumentListener(addBookListener);
        rating.addActionListener(addBookListener);
        rating.getDocument().addDocumentListener(addBookListener);
        type.addActionListener(addBookListener);
        type.getDocument().addDocumentListener(addBookListener);

        removeBookButton = new JButton(removeBookString);
        removeBookButton.setActionCommand(removeBookString);
        removeBookButton.addActionListener(new RemoveBookListener());
    }

    // EFFECTS: Creates and returns a JPanel containing all the text fields required to add a book
    public JPanel createAddBookInfoPanel() {
        JPanel addBookInfoPanel = new JPanel();
        addBookInfoPanel.setLayout(new BoxLayout(addBookInfoPanel, BoxLayout.PAGE_AXIS));

        addBookInfoPanel.add(createTitlePanel());
        addBookInfoPanel.add(createAuthorPanel());
        addBookInfoPanel.add(createRatingPanel());
        addBookInfoPanel.add(createTypePanel());
        return addBookInfoPanel;
    }

    // EFFECTS: Creates and returns a JPanel containing a text field for the title of a book
    public JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Enter the book's title:");
        titlePanel.setBackground(COLOR);
        titlePanel.add(titleLabel);
        titlePanel.add(title);
        return titlePanel;
    }

    // EFFECTS: Creates and returns a JPanel containing a text field for the author of a book
    public JPanel createAuthorPanel() {
        JPanel authorPanel = new JPanel();
        JLabel authorLabel = new JLabel("Enter the book's author:");
        authorPanel.setBackground(COLOR);
        authorPanel.add(authorLabel);
        authorPanel.add(author);
        return authorPanel;
    }

    // EFFECTS: Creates and returns a JPanel containing a text field for the rating of a book
    public JPanel createRatingPanel() {
        JPanel ratingPanel = new JPanel();
        JLabel ratingLabel = new JLabel("Enter the book's rating (an integer from 1 to 5 inclusive):");
        ratingPanel.setBackground(COLOR);
        ratingPanel.add(ratingLabel);
        ratingPanel.add(rating);
        return ratingPanel;
    }

    // EFFECTS: Creates and returns a JPanel containing a text field for the type of a book
    public JPanel createTypePanel() {
        JPanel typePanel = new JPanel();
        JLabel typeLabel = new JLabel("Enter the book's type (default is non-fiction, "
                + "enter \"true\" for fiction)");
        typePanel.setBackground(COLOR);
        typePanel.add(typeLabel);
        typePanel.add(type);
        return typePanel;
    }

    // EFFECTS: Creates and returns a JMenuBar containing the options to load or save data
    public JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu menuBarItem1 = new JMenu("FILE");
        mb.add(menuBarItem1);

        JMenuItem loadBookLog = new JMenuItem("Load");
        JMenuItem saveBookLog = new JMenuItem("Save");
        loadBookLog.setActionCommand("load");
        saveBookLog.setActionCommand("save");
        loadBookLog.addActionListener(new SaveAndLoadDataListener());
        saveBookLog.addActionListener(new SaveAndLoadDataListener());
        menuBarItem1.add(loadBookLog);
        menuBarItem1.add(saveBookLog);

        return mb;
    }

    // Represents a listener that listens for when "Save" or "Load" is pressed
    class SaveAndLoadDataListener implements ActionListener {

        private static final String JSON_BOOKLOG = "./data/bookLog.json";
        private JsonWriter jsonWriter = new JsonWriter(JSON_BOOKLOG);
        private JsonReader jsonReader = new JsonReader(JSON_BOOKLOG);

        // MODIFIES: this, bl
        // EFFECTS: Saves/loads the book log to/from file
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("save")) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(bl);
                    jsonWriter.close();
                } catch (FileNotFoundException fe) {
                    System.out.println("Unable to write to file: " + JSON_BOOKLOG);
                }
            } else if (e.getActionCommand().equals("load")) {
                try {
                    bl = jsonReader.read();
                    setScrollList();
                } catch (IOException ioe) {
                    System.out.println("Unable to read from file: " + JSON_BOOKLOG);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Clears the current list of book titles and sets it to the titles of the books in this.bl
    public void setScrollList() {
        bookTitlesList.clear();

        for (Book book : bl.getBookLog()) {
            bookTitlesList.addElement(book.getTitle());
        }
    }

    // Represents a listener that listens for when "Remove Book" pressed
    class RemoveBookListener implements ActionListener {

        // MODIFIES: bookTitlesList, bl, removeBookButton
        // EFFECTS: Removes the selected item from the list of book titles and disables
        //          the Remove Book button if bookTitlesList is empty
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            bookTitlesList.remove(index);
            bl.removeBook(index);

            if (bookTitlesList.getSize() == 0) {
                removeBookButton.setEnabled(false);
            } else {
                if (index == bookTitlesList.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: If user selects an item in the list, enable the remove book button
    // Method modelled after ListDemo.java found at
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            removeBookButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }

    // Represents a listener that listens for when "Add Book" is pressed
    class AddBookListener implements ActionListener, DocumentListener {
        private String bookTitle;
        private String bookAuthor;
        private int bookRating;
        private boolean bookType;
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddBookListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this, bl, title, author, rating, type
        // EFFECTS: Adds a book to the book log and adds the title of the book to the list of book titles
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        @Override
        public void actionPerformed(ActionEvent e) {
            bookTitle = title.getText();
            bookAuthor = author.getText();
            bookRating = Integer.parseInt(rating.getText());
            bookType = Boolean.parseBoolean(type.getText());

            Book newBook = new Book(bookTitle, bookAuthor, bookRating, bookType);
            bl.addBook(newBook);
            bookTitlesList.addElement(title.getText());

            title.setText("");
            author.setText("");
            rating.setText("");
            type.setText("");
        }

        // MODIFIES: this
        // EFFECTS: Enables the add book button when text is inserted in the document
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // MODIFIES: this
        // EFFECTS: Disables the add book button when the text field is empty and text is removed from the document
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // MODIFIES: this
        // EFFECTS: Enables the button if text is changed and the text field is not empty
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: Enables the button if not already enabled
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: Returns true if there is no text in the document and sets the button to false, else returns false
        // Method modelled after ListDemo.java found at
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: Creates the GUI for the Book Log and displays it
    // Method taken from ListDemo.java found at
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Book Log");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JComponent newContentPane = new BookLogGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                for (Event e : EventLog.getInstance()) {
                    System.out.println(e.getDescription());
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: Sets and displays the welcome image
    public static void displayWelcomeImage() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL welcomeImage = BookLogGUI.class.getClassLoader().getResource("Book Log Loading Image.jpg");
        JLabel label = new JLabel(new ImageIcon(welcomeImage));
        frame.add(label);
        frame.setVisible(true);
        frame.pack();

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException thrown");
        }

        frame.setVisible(false);
        frame.dispose();
    }

    // EFFECTS: Displays a welcome image then runs the BookLogGUI
    public static void main(String[] args) {
        displayWelcomeImage();
        createAndShowGUI();
    }
}