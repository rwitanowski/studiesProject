package app_gui;

import app_data.app_files.EasyLevel;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    private JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JTextField username;
    private JButton addUsername;
    private JLabel tekst;
    private JLabel rank;
    private JTextField firstPlace;
    private JTextField secondPlace;
    private JTextField thirdPlace;

    public JTextField getFirstPlace() {
        return firstPlace;
    }
    public JTextField getSecondPlace() {
        return secondPlace;
    }
    public JTextField getThirdPlace() {
        return thirdPlace;
    }
    public JTextField getUsername() {
        return username;
    }
    public JButton getAddUsername() {
        return addUsername;
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainPanel() {
        username = new JTextField(12);
        addUsername = new JButton("Rozpocznij");
        tekst = new JLabel("Podaj nazwę użytkownika");
        rank = new JLabel("<TOP USERS");
        firstPlace = new JTextField(20);
        secondPlace= new JTextField(20);
        thirdPlace = new JTextField(20);

        firstPlace.setEnabled(false);
        firstPlace.setBackground(Color.DARK_GRAY);
        secondPlace.setEnabled(false);
        secondPlace.setBackground(Color.DARK_GRAY);
        thirdPlace.setEnabled(false);
        thirdPlace.setBackground(Color.DARK_GRAY);

        mainPanel.add(tekst);
        mainPanel.add(username);
        mainPanel.add(addUsername);
        mainPanel.add(firstPlace);
        mainPanel.add(rank);
        mainPanel.add(secondPlace);
        mainPanel.add(thirdPlace);
        readDictionary();
    }

    private void readDictionary(){
        EasyLevel easyLevel = new EasyLevel();
        easyLevel.readDictionary();
    }

}
