package app_gui;

import javax.swing.*;
import java.awt.*;

public class EndGamePanel {
    private JPanel endGamePanel;
    private JLabel endGameText;
    private JButton oneMoreTime;
    private JButton theEnd;
    public EndGamePanel() {
        initializeComponents();
    }

    public JButton getOneMoreTime() {
        return oneMoreTime;
    }
    public JButton getTheEnd() {
        return theEnd;
    }
    public JPanel getEndGamePanel() {
        return endGamePanel;
    }

    private void initializeComponents() {
        endGamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        oneMoreTime = new JButton("Zagraj jeszcze raz");
        theEnd = new JButton("Wyjdz z Gry");

        endGamePanel.add(oneMoreTime);
        endGamePanel.add(theEnd);
    }
}

