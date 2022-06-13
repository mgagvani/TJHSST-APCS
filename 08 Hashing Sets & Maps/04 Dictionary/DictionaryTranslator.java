import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.util.*;
import java.io.*;
import java.awt.event.*;

public class DictionaryTranslator {
    public static void main(String[] args) {
        JFrame window = new JFrame("English/Spanish Translator");
        window.setLocation(800, 300);
        window.setSize(300,400);
        window.setContentPane(new Panel());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
    }
}

class Panel extends JPanel {
    private JTextField textbox;
    private JTextComponent bottom;
    private Map<String, Set<String>> eng2spn;
    private Map<String, Set<String>> spn2eng;
    private Map<String, Set<String>> currentDict;
    private JComboBox<String> combo;



    public Dictionary dict = new Dictionary();


    public Panel() {
        Scanner infile = null;
        try
        {
           infile = new Scanner(new File("spanglish.txt"));
        }
        catch(Exception e)
        {
            System.exit(1);
        }

        eng2spn = Dictionary.makeDictionary( infile );
        spn2eng = Dictionary.reverse(eng2spn);



        JLabel title = new JLabel("English/Spanish Translator");
        add(title);


        // JTextArea top = new JTextArea("Enter a word to be translated here.");
        // add(top);

        textbox = new JTextField(20);
        add(textbox);

        bottom = new JTextArea("Translations will apppear here.");
        add(bottom);


        JButton button = new JButton("Translate");
        button.addActionListener(new Listener());
        add(button);

        String[] optionsToChoose = {"English to Spanish", "Spanish to English"};
        combo = new JComboBox<>(optionsToChoose);
        combo.addActionListener(new comboListener());
        add(combo);

        JButton quit = new JButton("Quit");
        QuitListener qListener = new QuitListener();
        quit.addActionListener(qListener);
        add(quit);

    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputText = textbox.getText();

            
            if(currentDict.containsKey(inputText)) {
                bottom.setText("Translating " + inputText);
                bottom.setText(currentDict.get(inputText).toString().substring(1, currentDict.get(inputText).toString().length() - 1));
            }

            else {
                bottom.setText("Sorry, cannot find " + inputText + " in the database. 😭");
            }
        }
    }

    class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    }

    class comboListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if(combo.getSelectedIndex() == 0) {
                currentDict = eng2spn;
            }
            else {
                currentDict = spn2eng;
            }
        }
    }
}