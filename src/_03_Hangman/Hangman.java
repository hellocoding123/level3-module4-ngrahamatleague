package _03_Hangman;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Hangman extends JFrame implements KeyListener {

    private Stack<String> words = new Stack<>();
    private String currentWord;
    private char[] guessedWord;
    private int lives = 6;

    private JLabel wordLabel;
    private JLabel livesLabel;

    public Hangman() {
        setTitle("Hangman");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        wordLabel = new JLabel("");
        wordLabel.setBounds(50, 40, 400, 30);
        add(wordLabel);

        livesLabel = new JLabel("Lives: " + lives);
        livesLabel.setBounds(50, 80, 200, 30);
        add(livesLabel);

        addKeyListener(this);
        setFocusable(true);

        loadWords();
        startRound();

        setVisible(true);
    }

    // Step 1: Load random words into stack
    private void loadWords() {
        words.clear();

        int numWords = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "How many words would you like to play?"));

        while (words.size() < numWords) {
            String word = readRandomLineFromFile("dictionary.txt");

            if (word != null) {
                word = word.trim().toUpperCase();

                if (!words.contains(word)) {
                    words.push(word);
                }
            }
        }
    }

    // Step 2: Start a new word
    private void startRound() {

        if (words.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You won!");
            playAgain();
            return;
        }

        lives = 6;
        livesLabel.setText("Lives: " + lives);

        currentWord = words.pop();

        guessedWord = new char[currentWord.length()];
        Arrays.fill(guessedWord, '_');

        updateWordLabel();
    }

    private void updateWordLabel() {
        String display = "";

        for (char c : guessedWord) {
            display += c + " ";
        }

        wordLabel.setText(display);
    }

    // Read a random line from dictionary.txt
    private String readRandomLineFromFile(String filename) {

        ArrayList<String> list = new ArrayList<>();

        try {
            Scanner file = new Scanner(new File(filename));

            while (file.hasNextLine()) {
                list.add(file.nextLine());
            }

            file.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not read dictionary.txt");
            System.exit(0);
        }

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

        char guess = Character.toUpperCase(e.getKeyChar());

        if (!Character.isLetter(guess))
            return;

        boolean found = false;

        for (int i = 0; i < currentWord.length(); i++) {

            if (currentWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                found = true;
            }
        }

        if (!found) {
            lives--;
            livesLabel.setText("Lives: " + lives);
        }

        updateWordLabel();
        checkGame();
    }

    private void checkGame() {

        if (String.valueOf(guessedWord).equals(currentWord)) {
            JOptionPane.showMessageDialog(this,
                    "Correct! Next word.");
            startRound();
            return;
        }

        if (lives <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Game Over!\nThe word was: " + currentWord);
            playAgain();
        }
    }

    // Step 5: Play again
    private void playAgain() {

        int option = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play again?",
                "Hangman",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            loadWords();
            startRound();
            requestFocusInWindow();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Hangman();
    }
}