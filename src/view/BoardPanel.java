package view;

import model.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class BoardPanel extends JFrame{

    private JButton savebut;

    private JPanel p1;
    /*public BoardPanel(){
        p1 = new JPanel();
        p1.setLayout(new GridLayout(8, 8));
        p1.setSize(600, 600);

        blackButtons = new JButton[4 * 8];
        whiteButtons = new JButton[4 * 8];

        ImageIcon whiteImage = new ImageIcon("res/White.png");
        ImageIcon blackImage = new ImageIcon("res/Black.png");


        for(int i = 0; i < blackButtons.length; i++)
        {
            if (i < 12){
                blackButtons[i] = new JButton();
                blackButtons[i].setBackground(Color.BLACK);
                blackButtons[i].setIcon(whiteImage);
                continue;
            }
            if (i > 19){
                blackButtons[i] = new JButton(blackImage);
                blackButtons[i].setBackground(Color.BLACK);
                continue;
            }

            blackButtons[i] = new JButton();
            blackButtons[i].setBackground(Color.BLACK);
        }
        for(int i = 0; i < whiteButtons.length; i++)
        {
            whiteButtons[i] = new JButton();
            whiteButtons[i].setBackground(Color.WHITE);
        }

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    p1.add(blackButtons[4 * i + j]);
                    p1.add(whiteButtons[4 * i + j]);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    p1.add(whiteButtons[4 * i + j]);
                    p1.add(blackButtons[4 * i + j]);
                }
            }
        }

        add(p1);
        this.setTitle("Checkers");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }*/



    public BoardPanel(ArrayList<ArrayList<Cell>> cells){
        p1 = new JPanel();
        p1.setLayout(new GridLayout(8, 8));
        p1.setSize(600, 600);
        //ImageIcon whiteImage = new ImageIcon("res/White.png");
        //ImageIcon blackImage = new ImageIcon("res/Black.png");
        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (i % 2 == j % 2){
                    cells.get(i).get(j).getButton().setBackground(Color.black);
                    //cells.get(i).get(j).setCellcolor(Cell.BLACK);
                    p1.add(cells.get(i).get(j).getButton());
                }
                else{
                    cells.get(i).get(j).getButton().setBackground(Color.white);
                    //cells.get(i).get(j).setCellcolor(Cell.WHITE);
                    p1.add(cells.get(i).get(j).getButton());
                }


            }
        }
        savebut = new JButton("Save");
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        getContentPane().add(savebut);
        getContentPane().add(p1);
        this.setTitle("Checkers");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void refreshBoardPanel(ArrayList<ArrayList<Cell>> cells){
        ImageIcon whiteImage = new ImageIcon("res/White.png");
        ImageIcon blackImage = new ImageIcon("res/Black.png");
        p1.removeAll();
        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                p1.add(cells.get(i).get(j).getButton());
            }
        }
        p1.revalidate();
    }

    public void ViewGameOverMessage(String message){
        JDialog dialog = new JDialog(this,"GameOver");
        JPanel p = new JPanel();
        p.add(new JLabel("<html><h1><i>" + message + "</i></h1><hr></html>"),
                BorderLayout.CENTER);
        p.setSize(150, 150);
        JButton but = new JButton("Okey");
        but.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p1.setVisible(false);
                dialog.setVisible(false);
                setVisible(false);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        p.add(but);
        dialog.add(p);
        dialog.setLocationRelativeTo(null);
        dialog.setSize(200, 160);
        dialog.setVisible(true);
    }

    public JPanel getP1(){
        return p1;
    }
    public JButton getSavebut(){
        return savebut;
    }

}
