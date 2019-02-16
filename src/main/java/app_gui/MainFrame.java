package app_gui;

import app_data.app_user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class MainFrame extends JPanel {
    private User user;
    ArrayList<User> userList = new ArrayList<User>();

    public void createAndShowGui() {
        JFrame frameMain = new JFrame("WordWar App");
        frameMain.setMinimumSize(new Dimension(450, 300));

        final JPanel contentPane = new JPanel(); //panel do przechowywania paneli
        final MainPanel mainPanel = new MainPanel();//obiekty klas paneli
        final EngToPlPanel engToPlPanel = new EngToPlPanel();
        final EndGamePanel endGamePanel = new EndGamePanel();

        contentPane.setLayout(new CardLayout());
        contentPane.add(mainPanel.getMainPanel(), "1");//dodawanie paneli do panelu przechowującego klasy
        contentPane.add(engToPlPanel.getEngToPlPanel(), "2");
        contentPane.add(endGamePanel.getEndGamePanel(), "3");

        readUserListFile(); //wczytywanie Userów

        //sortowanie userów do rankingu
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
            }
        });
        //wyswietlanie rankingu graczy
        mainPanel.getFirstPlace().setText(userList.get(0).toString());
        mainPanel.getSecondPlace().setText(userList.get(1).toString());
        mainPanel.getThirdPlace().setText(userList.get(2).toString());

        frameMain.add(contentPane);
        frameMain.setVisible(true);
        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.getAddUsername().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                user = new User(mainPanel.getUsername().getText(),engToPlPanel.getScore());
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);
            }
        });

        engToPlPanel.getCheckWord().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(engToPlPanel.getNumberOfTries()<2){
                engToPlPanel.getCheckWord().setText("Koniec Prób. Dalej");
                if(engToPlPanel.getNumberOfTries()<1) {
                    user.setScore(engToPlPanel.getScore());
                    System.out.println(user.toString());
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.next(contentPane);
                }
                }
            }
        });
        endGamePanel.getOneMoreTime().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {   engToPlPanel.getCheckWord().setText("Sprawdź odpowiedź");
                user.setScore(0);
                    engToPlPanel.setNumberOfTries(3);
                    engToPlPanel.setScore(0);
                    engToPlPanel.getTries().setText("Liczba prób: "+String.valueOf(engToPlPanel.getNumberOfTries()));
                    engToPlPanel.getScoreTextField().setText(String.valueOf(engToPlPanel.getScore()));
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.previous(contentPane);
            }
        });
        endGamePanel.getTheEnd().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                userList.add(user);
                userListSaveToFile();
                System.exit(0);
            }
        });

    }

    private void userListSaveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\rafal\\workspace\\ProjektNaProgramowanie\\src\\main\\java\\app_data\\app_files\\listaUserów.txt");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(userList);
            out.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error!");
        }
    }
    private void readUserListFile() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\rafal\\workspace\\ProjektNaProgramowanie\\src\\main\\java\\app_data\\app_files\\listaUserów.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
