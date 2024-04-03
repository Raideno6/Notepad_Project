package org.example;
import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.io.IOException;

public class NotepadGUI {
    public static void main(String[] args) throws IOException {

//        CardLayoutClass cardLayoutFrame = new CardLayoutClass();
//        cardLayoutFrame.setSize(500,650);
//        cardLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        cardLayoutFrame.setLocationRelativeTo(null);
//        cardLayoutFrame.setVisible(true);
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                CardLayoutClass frame = new CardLayoutClass();
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//            }
//        });
//    }
        int width = 500;
        int height = 650;
        int mid = width/2;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DocumentListener myDoc = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };
        JTextField nameField = new JTextField("Name");
        JTextField author = new JTextField("Author");
        JTextField subtitle = new JTextField("Subtitle");
        JTextArea textBox = new JTextArea("");
        nameField.setBounds(mid - (400 / 2), 25, 400, 30);
        author.setBounds(mid - (400 / 2), 65, 400, 30);
        subtitle.setBounds(mid - (400 / 2), 135, 400, 30);
        textBox.setBounds(mid - (400 / 2), 176, 400, 400);
        textBox.setWrapStyleWord(true);
        frame.add(nameField);
        frame.add(author);
        frame.add(subtitle);
        frame.add(textBox);



        frame.setSize(width,height);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}
