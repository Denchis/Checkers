package model;

import view.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SingleGameController {
    private boolean MoveofBlack;
    private boolean MoveofWhite;
    private Cell WhiteChopCheckers;
    private Cell BlackChopCheckers;
    BoardPanel vboard;
    Board b;

    class MoveMouseListener implements MouseListener {
        public void  mouseClicked(MouseEvent e)  {
            if (MoveofWhite){
                if (BlackChopCheckers != null){
                    if (BotProcessQueen() && BlackChopCheckers == null)
                        return;
                    else{
                        Cell compare = BlackChopCheckers.getWhereToChop();
                        b.ChopBlack(BlackChopCheckers);
                        BlackChopCheckers.getButton().setBackground(Color.black);
                        BlackChopCheckers = b.CheckToChopWhite();
                        if (BlackChopCheckers == null || BlackChopCheckers != compare){
                            swapMoves();
                            if (BlackChopCheckers != null)
                                BlackChopCheckers.getButton().setBackground(Color.black);
                            WhiteChopCheckers = b.isBlacksQueenChop();
                            if (WhiteChopCheckers == null)
                                WhiteChopCheckers = b.CheckToChopBlack();
                        }
                        return;
                    }
                }
                else {
                    BotMakeWhiteMove();
                }
            }
            else {
                if (WhiteChopCheckers != null){
                    if (ProcessQueenMove(WhiteChopCheckers))
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
                if (start != null && finish != null) {
                    if (start.getCheckerscolor() == Cell.BLACK) {
                        if (start.isQueen())
                            BlackQueenMove(start, finish);
                        else
                            BlackMove(start, finish);
                    }
                    returnBack(start, finish);
                }
            }
        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}

        private boolean BotProcessQueen(){
            Cell start = b.SearchCellisChoptrue();
            if (start == null || !start.isQueen()){
                return false;
            }
            ArrayList<ArrayList<Cell>> cells = b.getCells();
            ArrayList<Cell> moves = start.getQueenWheretoChop();
            for (int k = 0; k < moves.size();k++){
                boolean flag = false;
                int i = b.getCellrow(moves.get(k).getName());
                int j = b.getCellcoulumn(moves.get(k).getName());
                if (i == 0 && j >= 0 || i >= 0 && j == 0 || i == 7 && j >= 0 || i >= 0 && j == 7){
                    flag = true;
                }
                check:
                {
                    if (flag)
                        break check;
                    if (cells.get(i + 1).get(j + 1).getCheckerscolor() == Cell.BLACK
                            && cells.get(i - 1).get(j - 1).getCheckerscolor() == Cell.EMPTY
                            || cells.get(i + 1).get(j + 1).getCheckerscolor() == Cell.EMPTY
                            && cells.get(i - 1).get(j - 1).getCheckerscolor() == Cell.BLACK) {
                        continue;
                    }
                    if (cells.get(i + 1).get(j - 1).getCheckerscolor() == Cell.BLACK
                            && cells.get(i - 1).get(j + 1).getCheckerscolor() == Cell.EMPTY
                            || cells.get(i + 1).get(j - 1).getCheckerscolor() == Cell.EMPTY
                            && cells.get(i - 1).get(j + 1).getCheckerscolor() == Cell.BLACK) {
                        continue;
                    }
                }
                moves.get(k).setCheckersColor(start.getCheckerscolor());
                start.setChop(false);
                start.setCheckersColor(Cell.EMPTY);
                moves.get(k).setQueen(start.isQueen());
                start.setQueen(false);
                start.getQueenWheretoChop().clear();
                start.getButton().setBackground(Color.black);
                BlackChopCheckers.setCheckersColor(Cell.EMPTY);
                BlackChopCheckers = null;
                swapMoves();
                WhiteChopCheckers = b.isBlacksQueenChop();
                if (WhiteChopCheckers == null)
                    WhiteChopCheckers = b.CheckToChopBlack();
                return true;
            }

            return false;
        }
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

                    ChopCheckers.setCheckersColor(Cell.EMPTY);
                    tmp = b.isBlacksQueenChop();
                    if (tmp == null) {
                        //begin
                        if (WhiteChopCheckers == null)
                            WhiteChopCheckers = b.CheckToChopBlack();
                    }
                    if (tmp != null && b.OnTheSameDiagonal(tmp, finish)){
                        System.out.println("2 CHOOPS!!");
                        if (tmp.getCheckerscolor() == Cell.WHITE)
                            WhiteChopCheckers = tmp;///
                        returnBack(start,start);
                        //System.out.println(tmp +"  " + WhiteChopCheckers);
                        return true;
                    }
                    BlackChopCheckers = b.isWhiteQueenChop();
                    if (BlackChopCheckers == null)
                        BlackChopCheckers = b.CheckToChopWhite();
                    returnBack(start, finish);
                    swapMoves();
                    return true;

                }
            }
            return false;
        }
    }


    public SingleGameController(){

        MoveofBlack = false;
        MoveofWhite = true;
        WhiteChopCheckers = null;
        BlackChopCheckers = null;
        b = new Board();
        vboard = new BoardPanel(b.getCells());
        ArrayList<ArrayList<Cell>> tmp = b.getCells();
        for (int i = 0; i < tmp.size(); i++){
            for (int j = 0; j < tmp.get(i).size(); j++){
                tmp.get(i).get(j).getButton().addMouseListener(new MoveMouseListener());
            }
        }
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
        else
        {
            System.out.println("Illegal Move");
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

    public void GameOver(String message){
        vboard.ViewGameOverMessage(message);
    }

    private Cell isWhiteMakeMove(int i, int j, ArrayList<ArrayList<Cell>> cells){
        if (i < 7){
            if (j == 0){
                if (cells.get(i+1).get(j+1).getCheckerscolor() == Cell.EMPTY){
                    //cells.get(i).get(j+1).setMoveIllumination(true);
                    return cells.get(i+1).get(j+1);
                }
                return null;
            }
            if (j == 7){
                if (cells.get(i+1).get(j-1).getCheckerscolor() == Cell.EMPTY){
                   // cells.get(i+1).get(j-1).setMoveIllumination(true);
                    return cells.get(i+1).get(j-1);
                }
                return null;
            }
            if (cells.get(i+1).get(j+1).getCheckerscolor() == Cell.EMPTY){
                //cells.get(i+1).get(j+1).setMoveIllumination(true);
                return cells.get(i+1).get(j+1);
            }
            if (cells.get(i+1).get(j-1).getCheckerscolor() == Cell.EMPTY){
                //cells.get(i+1).get(j-1).setMoveIllumination(true);
                return cells.get(i+1).get(j-1);
            }

        }
        return null;
    }

    public void BotMakeWhiteMove() {
        System.out.println(b.getCells());
        Board b1 = new Board(b.getCells());
        ArrayList<ArrayList<Cell>> cells = b1.getCells();
        System.out.println(b1.getCells());
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                Cell tmp;
                if (cells.get(i).get(j).getCheckerscolor() == Cell.WHITE && (tmp = isWhiteMakeMove(i, j, cells)) != null) {
                    if (cells.get(i).get(j).isQueen()) {
                        SearchFortheQueenMove(cells.get(i).get(j), b1);
                    }
                    else {
                        if (b1.MakeMove(cells.get(i).get(j), tmp)) {
                            System.out.println(b.getCells().get(i).get(j) + " --" + isWhiteMakeMove(i, j, b.getCells()) + MoveofWhite);
                            Cell chopping;
                            if ((chopping = b1.CheckToChopBlack()) == null) {
                                System.out.println("Success");
                                this.WhiteMove(b.getCells().get(i).get(j), isWhiteMakeMove(i, j, b.getCells()));
                                return;
                            }
                            chopping.setWhereToChop(null);
                            chopping.setChop(false);
                            b1.UndoMove(cells.get(i).get(j), tmp);
                            System.out.println(b1.getCells());
                        }

                    }



                }
            }

        }
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                Cell tmp;
                if (b.getCells().get(i).get(j).getCheckerscolor() == Cell.WHITE && (tmp = isWhiteMakeMove(i, j, b.getCells())) != null) {
                    this.WhiteMove(b.getCells().get(i).get(j), tmp);
                    return;
                }
            }
        }
    }

    public void SearchFortheQueenMove(Cell queen, Board b1){
        ArrayList<ArrayList<Cell>> cells = b1.getCells();
        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (cells.get(i).get(j).getCellcolor() != Cell.BLACK && cells.get(i).get(j).getCellcolor() != Cell.EMPTY)
                    continue;
                if (b1.MakeQueenMove(queen,cells.get(i).get(j)) && b1.CheckToChopBlack() != null){
                    WhiteQueenMove(b.getCellbyName(queen.getName()),b.getCells().get(i).get(j));
                    return;
                }
                b1.UndoMove(queen, cells.get(i).get(j));

            }
        }
        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (cells.get(i).get(j).getCellcolor() != Cell.BLACK && cells.get(i).get(j).getCellcolor() != Cell.EMPTY)
                    continue;
                if (b1.MakeQueenMove(queen,cells.get(i).get(j))){
                    WhiteQueenMove(b.getCellbyName(queen.getName()),b.getCells().get(i).get(j));
                    return;
                }
                b1.UndoMove(queen, cells.get(i).get(j));

            }
        }
    }

}
