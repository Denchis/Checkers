package view;

import model.GameController;
import model.SingleGameController;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainMenu extends JFrame {
    class GameMouseListener implements MouseListener {
        public void  mouseClicked(MouseEvent e)  {
            GameController g = new GameController();
            g.start();
            System.out.println("mouseClicked()");
        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}
    }

    class RulesMouseListener implements MouseListener {
        public void  mouseClicked(MouseEvent e)  {
            try {

                JDialog dialog = new JDialog();
                BufferedReader reader = new BufferedReader(new FileReader("res/rules.txt"));
                JPanel p = new JPanel();
                p.setSize(14000, 600);
                dialog.add(p);
                dialog.setSize(1400, 600);
                dialog.setVisible(true);
                String s = reader.readLine();
                while (s != null){
                    p.add(new JLabel("<html><h2>" + s + "</h2></html>"));
                    s = reader.readLine();
                }


            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}
    }

    public MainMenu(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,1));
        JButton[] menuButtons = new JButton[5];
        menuButtons[0] = new JButton("New Game (2 player)");
        menuButtons[0].addMouseListener(new GameMouseListener());

        menuButtons[1] = new JButton("New Game (1 player)");
        menuButtons[1].addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SingleGameController g = new SingleGameController();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        menuButtons[3] = new JButton("Rules");
        menuButtons[3].addMouseListener(new RulesMouseListener());

        menuButtons[2] = new JButton("Continue the game(2 players)");
        menuButtons[2].addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameController g = new GameController("res/savegame.txt");
                g.StartSaveGame();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        menuButtons[4] = new JButton("Exit");
        menuButtons[4].addMouseListener( new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                dispose();
            }
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            });


        for (int i = 0; i < menuButtons.length; i++){
            menuButtons[i].setBackground(Color.LIGHT_GRAY);
            p.add(menuButtons[i]);
        }
        add(p);
    }
    public void ViewMainMenu(){
        MainMenu frame = new MainMenu();
        frame.setTitle("Checkers");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
