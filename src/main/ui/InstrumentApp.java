package ui;

import model.ListOfNotes;
import model.Notes;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


//Instrument application
public class InstrumentApp extends JPanel
        implements ListSelectionListener {
    private ImageGUI imageGUI;
    private String oneNote;
    private JList list;
    private JTextField noteName;
    private JButton jbutton = new JButton("A");
    private DefaultListModel listModel;
    private static final String addNote = "Add Note";
    ListOfNotes lon;
    Notes note;
    Synth synth = new Synth();
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/listOfNotes.json";
    private static final String playThisNote = "Play Note";


    //EFFECTS: Runs the instrument app
    public InstrumentApp() {
        super(new BorderLayout());
        JPanel p = new JPanel(new BorderLayout());
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addNoteButton = new JButton(addNote);
        AddANote addANote = new AddANote(addNoteButton);
        addNoteButton.setActionCommand(addNote);
        addNoteButton.addActionListener(addANote);
        addNoteButton.setEnabled(false);


        //jbutton = new JButton(playThisNote);
        // jbutton.setActionCommand(playThisNote);
        // jbutton.addActionListener(new NoteImage());

        noteName = new JTextField(10);
        noteName.addActionListener(addANote);
        noteName.getDocument().addDocumentListener(addANote);
        //   String name = listModel.getElementAt(
        //           list.getSelectedIndex()).toString();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));


        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(noteName);
        buttonPane.add(addNoteButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        ImageIcon img = new ImageIcon("images/C - Note .png");


        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        
        runInstrument();

    }


    private void runInstrument() {

        boolean keepGoing = true;
        String command = null;
        lon = new ListOfNotes();

        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("v")) {
                System.out.println(lon.getListOfNotes());
            } else if (command.equals("listen")) {
                playAll(lon);
            } else if (command.equals("save")) {
                saveLON();
            } else if (command.equals("load")) {
                loadLON();
            } else {
                playInstrument(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    //EFFECTS: Displays menu
    private void displayMenu() {
        System.out.println("\nUse the 2nd row of keys to play the instrument");
        System.out.println("\tv -> View all notes played");
        System.out.println("\tq -> quit");
        System.out.println("\tlisten -> Listen to all the notes you've played at once");
        System.out.println("\tsave -> Save List of Notes to file");
        System.out.println("\tload -> Load List of Notes from file");
    }

    //MODIFIES: this
    //EFFECTS: Initializes a ui interface
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: Plays all notes in the list of notes at once
    public void playAll(ListOfNotes ln) {
        int i;
        int j = ln.size();
        for (i = 0; i < j; i++) {
            String n = ln.getNote(i);
            synth.play(note.convertNote(n));


        }

    }

    //EFFECTS: Saves the lon to file
    public void saveLON() {
        try {
            jsonWriter.open();
            jsonWriter.write(lon);
            jsonWriter.close();
            System.out.println("Saved " + lon.getListOfNotes() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads lon from file
    private void loadLON() {
        try {
            lon = jsonReader.read();
            System.out.println("Loaded " + lon.getListOfNotes() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void playInstrument(String command) {
        note = new Notes(command);
        synth.setUp();
        synth.play(note.convertNote(command));
        lon.addNotes(note);
    }


    class AddANote implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;


        public AddANote(JButton button) {
            this.button = button;
        }


        public void actionPerformed(ActionEvent e) {
            String note = noteName.getText();

            if (note.equals("") || !note.matches("a|s|d|f|g|h|j|k|l")) {
                Toolkit.getDefaultToolkit().beep();
                noteName.requestFocusInWindow();
                noteName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(note, listModel.size());
            noteName.requestFocusInWindow();
            noteName.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }


        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                jbutton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                jbutton.setEnabled(true);
            }
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Instrument App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new InstrumentApp();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}


