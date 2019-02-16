package app_gui;

import app_data.app_api.yandex_api.EngToPol;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import static app_data.app_files.EasyLevel.words;

public class EngToPlPanel {
    private JButton randomWord;
    private JTextField plWord;
    private JPanel engToPlPanel;
    private JTextField engWord;
    private JButton checkWord;
    private JTextField wrong;
    private JLabel tries;
    private JLabel scoreLabel;
    private JTextField scoreTextField;
    private JLabel correctWordLabel;
    private int score = 0;
    private int numberOfTries = 3;


    public EngToPlPanel(){
        initializeComponents();
    }

    public JTextField getScoreTextField() {
        return scoreTextField;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public JLabel getTries() {
        return tries;
    }
    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }
    public int getScore() {
        return score;
    }
    public int getNumberOfTries() {
        return numberOfTries;
    }
    public JPanel getEngToPlPanel() {
        return engToPlPanel;
    }
    public JButton getCheckWord() {
        return checkWord;
    }


    private void initializeComponents(){
        engToPlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plWord = new JTextField(20);
        engWord = new JTextField(20);
        randomWord = new JButton("Start");
        checkWord = new JButton("Sprawdź odpowiedź");
        scoreTextField = new JTextField(4);
        scoreLabel = new JLabel("Zdobyte punkty");
        wrong = new JTextField(20);
        tries = new JLabel("Liczba prób: "+String.valueOf(numberOfTries));
        correctWordLabel = new JLabel("Poprawne słowo");

        engWord.setBackground(Color.BLACK);
        engWord.setEnabled(false);
        checkWord.setEnabled(false);
        scoreTextField.setBackground(Color.GREEN);
        scoreTextField.setText(String.valueOf(score));
        wrong.setEnabled(false);
        wrong.setBackground(Color.BLACK);

        engToPlPanel.add(engWord);
        engToPlPanel.add(randomWord);
        engToPlPanel.add(plWord);
        engToPlPanel.add(checkWord);
        engToPlPanel.add(tries);
        engToPlPanel.add(scoreTextField);
        engToPlPanel.add(scoreLabel);
        engToPlPanel.add(wrong);
        engToPlPanel.add(correctWordLabel);
        randomWord();
        randomWordShow();
        checkWord();
    }

    public void checkWord() {
            checkWord.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                EngToPol engToPol = new EngToPol();
                String englishWord = engWord.getText();
                String polishWord = plWord.getText();
                String correctPolishWord;
                try {
                    engToPol.translate(englishWord);
                } catch (UnirestException e1) {
                    e1.printStackTrace();
                }
                correctPolishWord = engToPol.getTranslatedWord().toString();
                if (correctPolishWord.equals(polishWord)) {
                    nextWord();
                    score = score + 10;
                    scoreTextField.setText(String.valueOf(score));
                    plWord.setText("");
                } else {
                    wrong.setText(correctPolishWord);
                    nextWord();
                    plWord.setText("");
                    numberOfTries--;
                    tries.setText("Liczba prób: " + String.valueOf(numberOfTries));
                }
            }
        });
    }
    private void randomWordShow() {
        randomWord.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                randomWord.setEnabled(false);
                checkWord.setEnabled(true);
                int randomNumber = randomWord();
                engWord.setText(words.get(randomNumber));
            }
        });
    }
    private void nextWord(){
        int randomNumber = randomWord();
        engWord.setText(words.get(randomNumber));
    }
    public int randomWord (){
        Random random = new Random();
        int randomNumber = random.nextInt(997);
        return randomNumber;
    }
}


