package org.example;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NotepadGUI {
       // JTextField
    static JTextField t;

    // JFrame
    static JFrame f;

    // JButton
    static JButton b;

    // label to display text
    static JLabel l;

    // default constructor
    NotepadGUI()
    {
    }
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
        JPanel panel = new JPanel();


        JTextField nameField = new JTextField();
        JTextField author = new JTextField();
        JTextField subtitle = new JTextField();
        JTextField textBox = new JTextField();
        panel.add(nameField);


        frame.add(panel);
        frame.setSize(width,height);
        frame.show();

    }
}
