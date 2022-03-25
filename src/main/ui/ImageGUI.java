package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageGUI extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int VGAP = 15;
    private String[] theNotes = { "C", "D", "E", "F", "G","A","B","Higher C", "Higher D" };
   // private InstrumentApp.NoteImage instrumentApp;
    private ImageIcon noteCImage;
    private ImageIcon noteDImage;
    private ImageIcon noteEImage;
    private ImageIcon noteFImage;
    private ImageIcon noteGImage;
    private ImageIcon noteAImage;
    private ImageIcon noteBImage;
    private ImageIcon noteHigherCImage;
    private ImageIcon noteHigherDImage;
    private JPanel panel;
    private JLabel imageAsLabel;

    public ImageGUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VGAP));

        final JComboBox allNotes = new JComboBox(theNotes);
        allNotes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String noteChoice =  (String) allNotes.getSelectedItem();

            }

        });


        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(panel);

        loadImages();
        setNoteImage("a");


    }

    private void loadImages() {
        String sep = System.getProperty("file.separator");
        noteCImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "C - Note.png");
        noteDImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "D - Note.png");
        noteEImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "E - music note.png");
        noteFImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "F - Note.jpg");
        noteGImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "G - Note.jpg");

        noteAImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "A - Note.jpg");

        noteBImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "B - Note.jpg");

        noteHigherCImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "Higher C - Note.jpg");

        noteHigherDImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "Higher D - Note.jpg");

    }

    public void setNoteImage(String n) {
        removeExistingImage();
        if (n.equals("a")) {
            imageAsLabel = new JLabel(noteCImage);
        } else if (n.equals("s")) {
            imageAsLabel = new JLabel(noteDImage);
        } else if (n.equals("d")) {
            imageAsLabel = new JLabel(noteEImage);
        } else if (n.equals("f")) {
            imageAsLabel = new JLabel(noteFImage);
        } else if (n.equals("g")) {
            imageAsLabel = new JLabel(noteGImage);
        } else if (n.equals("h")) {
            imageAsLabel = new JLabel(noteAImage);
        } else if (n.equals("j")) {
            imageAsLabel = new JLabel(noteBImage);
        } else if (n.equals("k")) {
            imageAsLabel = new JLabel(noteHigherCImage);
        } else if (n.equals("l")) {
            imageAsLabel = new JLabel(noteHigherDImage);
        }
        panel.add(imageAsLabel);
    }

    private void removeExistingImage() {
        if (imageAsLabel != null) {
            panel.remove(imageAsLabel);
        }
    }



}
