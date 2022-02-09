package model;

import view.BoardPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class GameController {
    private boolean MoveofBlack;
    private boolean MoveofWhite;
    private Cell WhiteChopCheckers;
    private Cell BlackChopCheckers;

    class SaveMouseListener implements MouseListener {
        public void  mouseClicked(MouseEvent e)  {
            try {
                FileWriter writer = new FileWriter("res/savegame.txt");
                ArrayList<ArrayList<Cell>> cells = b.getCells();
                for (int i = 0; i < cells.size(); i++){
                    for (int j = 0; j < cells.get(i).size(); j++){
                        writer.write(cells.get(i).get(j).getCheckerscolor());
                        if (cells.get(i).get(j).isQueen())
                            writer.write(1);
                        else
                            writer.write(0);
                    }
                }
                if (MoveofWhite)
                    writer.write(1);
                writer.write(0);
                writer.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}
    }

    class MoveMouseListener implements MouseListener {
        private void returnBack(Cell start, Cell finish){
            start.setClickedIllumination(false);
            finish.setClickedIllumination(false);
            start.getButton().setBackground(Color.black);
            finish.getButton().setBackground(Color.black);
            vboard.refreshBoardPanel(b.getCells());
            if(b.isGameOver() != Board.GAME_CONTINUES){
                if (b.isGameOver() == Board.BLACKS_WIN)
                    GameOver("BLAKS_WIN");
                if (b.isGameOver() == Board.WHITES_WIN)
                    GameOver("WHITES_WIN");
            }
        }

        private boolean ProcessQueenMove(Cell ChopCheckers){
            System.out.println(ChopCheckers);
            Cell truestart = b.SearchCellisChoptrue();
            if (truestart == null || !truestart.isQueen())
                return false;
            Cell start = b.SearchIlluminationCell(null);
            Cell finish = b.SearchIlluminationCell(start);
            if (finish != null && finish.getCheckerscolor() != Cell.EMPTY){
                Cell tmp = start;
                start = finish;
                finish = tmp;
            }
            if (start != null && start == truestart){
                ArrayList<Cell> wheretochop = start.getQueenWheretoChop();
                for (int i = 0; i < wheretochop.size(); i++){
                    wheretochop.get(i).getButton().setBackground(Color.ORANGE);
                }

                System.out.println("FINISH---" + finish + "TRUESTART---" + truestart);
                System.out.println(wheretochop.contains(finish));
                if (wheretochop.contains(finish)){
                    System.out.println(("2th SCENA"));
                    finish.setCheckersColor(start.getCheckerscolor());
                    finish.setQueen(start.isQueen());
                    for (int i = 0; i < wheretochop.size(); i++){
                        wheretochop.get(i).getButton().setBackground(Color.BLACK);
                    }
                    wheretochop.clear();
                    start.setCheckersColor(Cell.EMPTY);
                    start.setChop(false);
                    start.setQueen(false);
                    Cell tmp;

                    if (ChopCheckers.getCheckerscolor() == Cell.WHITE) {
                        WhiteChopCheckers = null;
                        b.Whitenumdecrement();
                    }
                    else {
                        BlackChopCheckers = null;
                        b.Blacknumdecrement();
                    }

                    if (ChopCheckers.getCheckerscolor() == Cell.BLACK) {

                        ChopCheckers.setCheckersColor(Cell.EMPTY);
                        tmp = b.isWhiteQueenChop();
                        if (tmp == null) {
                            WhiteChopCheckers = b.isBlacksQueenChop();
                            if (WhiteChopCheckers == null)
                                    WhiteChopCheckers = b.CheckToChopBlack();
                        }
                    }
                    else {
                        ChopCheckers.setCheckersColor(Cell.EMPTY);
                        tmp = b.isBlacksQueenChop();
                        if (tmp == null) {
                            //begin
                            BlackChopCheckers = b.isWhiteQueenChop();
                            if (BlackChopCheckers == null)
                                BlackChopCheckers = b.CheckToChopWhite();
                        }

                    }

                    if (tmp != null && b.OnTheSameDiagonal(tmp, finish)){
                        System.out.println("2 CHOOPS!!");
                        if (tmp.getCheckerscolor() == Cell.WHITE)
                            WhiteChopCheckers = tmp;
                        else
                            BlackChopCheckers = tmp;
                        returnBack(start,start);
                        System.out.println(tmp +"  " + WhiteChopCheckers);
                        return true;
                    }

                    returnBack(start, finish);
                    swapMoves();
                    return true;

                }
            }
            return false;
        }

        public void  mouseClicked(MouseEvent e)  {
            if (WhiteChopCheckers != null){
                if (ProcessQueenMove(WhiteChopCheckers))
                    return;
            }
            if (BlackChopCheckers != null){
                if (ProcessQueenMove(BlackChopCheckers))
                    return;
            }
            Cell start = b.SearchIlluminationCell(null);
            Cell finish = b.SearchIlluminationCell(start);

            if (finish != null && finish.getCheckerscolor() != Cell.EMPTY){
                Cell tmp = start;
                start = finish;
                finish = tmp;
            }
            if (start == WhiteChopCheckers && start != null){
                start.getWhereToChop().getButton().setBackground(Color.orange);
            }
            if (start == BlackChopCheckers && start != null){
                start.getWhereToChop().getButton().setBackground(Color.orange);
            }
            if (WhiteChopCheckers != null && finish != null){
                if (start == WhiteChopCheckers && finish == WhiteChopCheckers.getWhereToChop()){
                    b.ChopWhite(start);
                    Cell tmp = b.CheckToChopBlack();
                    if (tmp != null && tmp == finish){
                            WhiteChopCheckers = tmp;
                            returnBack(start,finish);
                            tmp.getButton().setBackground(Color.red);
                            return;
                    }
                    WhiteChopCheckers = null;
                    swapMoves();
                    BlackChopCheckers = b.isWhiteQueenChop();
                    if (BlackChopCheckers == null)
                        BlackChopCheckers = b.CheckToChopWhite();
                }
                returnBack(start,finish);
                return;
            }
            if (BlackChopCheckers != null && finish != null){
                if (start == BlackChopCheckers && finish == BlackChopCheckers.getWhereToChop()){
                    b.ChopBlack(start);
                    Cell tmp = b.CheckToChopWhite();
                    if (tmp != null && tmp == finish){
                        BlackChopCheckers = tmp;
                        System.out.println(tmp + "CHOOOP");
                        returnBack(start,finish);
                        tmp.getButton().setBackground(Color.red);
                        return;
                    }
                    BlackChopCheckers = null;
                    swapMoves();
                    WhiteChopCheckers = b.isBlacksQueenChop();
                    if (WhiteChopCheckers == null)
                        WhiteChopCheckers = b.CheckToChopBlack();

                }
                returnBack(start,finish);
                return;
            }

            if (start != null && finish != null) {
                if (start.getCheckerscolor() == Cell.WHITE) {
                    if (start.isQueen())
                        WhiteQueenMove(start,finish);
                    else
                        WhiteMove(start, finish);
                }
                if (start.getCheckerscolor() == Cell.BLACK) {
                    if (start.isQueen())
                        BlackQueenMove(start, finish);
                    else
                        BlackMove(start, finish);
                }
                returnBack(start, finish);
            }

        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}
    }
    BoardPanel vboard;
    Board b;
    public GameController(){

        MoveofBlack = false;
        MoveofWhite = true;
        WhiteChopCheckers = null;
        BlackChopCheckers = null;
        b = new Board();
        vboard = new BoardPanel(b.getCells());
        vboard.getSavebut().addMouseListener(new SaveMouseListener());
        ArrayList<ArrayList<Cell>> tmp = b.getCells();
        for (int i = 0; i < tmp.size(); i++){
            for (int j = 0; j < tmp.get(i).size(); j++){
                tmp.get(i).get(j).getButton().addMouseListener(new MoveMouseListener());
            }
        }
    }

    public GameController(String filename){
        try {

            WhiteChopCheckers = null;
            BlackChopCheckers = null;
            b = new Board();
            FileInputStream os = new FileInputStream("res/savegame.txt");
            ArrayList<ArrayList<Cell>> tmp = b.getCells();
            for (int i = 0; i < tmp.size(); i++) {
                for (int j = 0; j < tmp.get(i).size(); j++) {
                    tmp.get(i).get(j).setCheckersColor((int)os.read());
                    if ((int)os.read() == 1){
                        tmp.get(i).get(j).setQueen(true);
                    }
                    tmp.get(i).get(j).getButton().addMouseListener(new MoveMouseListener());
                }
            }
            if ((int)os.read() == 1){
                MoveofWhite = true;
                MoveofBlack = false;
            }
            MoveofWhite = false;
            MoveofBlack = true;
            vboard = new BoardPanel(b.getCells());
            vboard.getSavebut().addMouseListener(new SaveMouseListener());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void start(){


    }

    private void swapMoves(){
        boolean tmp = MoveofBlack;
        MoveofBlack = MoveofWhite;
        MoveofWhite = tmp;
    }

    public void WhiteMove(Cell start, Cell finish){
        if (MoveofWhite && b.MakeMove(start, finish)){
            swapMoves();
            WhiteChopCheckers = b.isBlacksQueenChop();
            if (WhiteChopCheckers == null)
                WhiteChopCheckers = b.CheckToChopBlack();
        }
    }

    public void BlackMove(Cell start, Cell finish){
        if (MoveofBlack && b.MakeMove(start, finish)) {
            swapMoves();
            BlackChopCheckers = b.isWhiteQueenChop();
            if (BlackChopCheckers == null)
                BlackChopCheckers = b.CheckToChopWhite();
        }
    }

    public void WhiteQueenMove(Cell start, Cell finish){
        if (MoveofWhite && b.MakeQueenMove(start, finish)){
            swapMoves();
            WhiteChopCheckers = b.isBlacksQueenChop();
            if (WhiteChopCheckers == null)
                WhiteChopCheckers = b.CheckToChopBlack();
        }
    }

    public void BlackQueenMove(Cell start, Cell finish){
        if (MoveofBlack && b.MakeQueenMove(start, finish)) {
            swapMoves();
            BlackChopCheckers = b.isWhiteQueenChop();
            if (BlackChopCheckers == null)
                BlackChopCheckers = b.CheckToChopWhite();
        }
    }

    public void StartSaveGame(){
        if (MoveofBlack) {
            BlackChopCheckers = b.isWhiteQueenChop();
            if (BlackChopCheckers == null)
                BlackChopCheckers = b.CheckToChopWhite();
        }
        if (MoveofWhite){
            WhiteChopCheckers = b.isBlacksQueenChop();
            if (WhiteChopCheckers == null)
                WhiteChopCheckers = b.CheckToChopBlack();
        }
    }

    public void GameOver(String message){
        vboard.ViewGameOverMessage(message);
    }



}
