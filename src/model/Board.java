package model;


import java.awt.*;
import java.util.ArrayList;


public class Board {

    public static final int CHECKERS_NUMBER = 12;
    public static final int LENGTH_OF_THE_BOARD = 8;
    public static final int BLACKS_WIN = -1;
    public static final int WHITES_WIN = -2;
    public static final int GAME_CONTINUES = 0;
    public static final int CHOP_FORWARD = 10;
    public static final int CHOP_BACK = 11;
    public static final int NOT_CHOP = 9;

    private ArrayList<ArrayList<Cell>> cells;
    private ArrayList<ArrayList<Cell>> ways;

    private ArrayList<Cell> GoldWay;
    private ArrayList<Cell> DoubleWayG1A7;
    private ArrayList<Cell> DoubleWayH2B8;
    private ArrayList<Cell> TripleWayC1H6;
    private ArrayList<Cell> TripleWayC1A3;
    private ArrayList<Cell> TripleWayA3F8;
    private ArrayList<Cell> TripleWayH6F8;
    private ArrayList<Cell> UltraWayE1A5;
    private ArrayList<Cell> UltraWayH4D8;
    private ArrayList<Cell> UltraWayE1H4;
    private ArrayList<Cell> UltraWayA5D8;

    private int whitnum;
    private int blacknum;

    public Cell SearchIlluminationCell(Cell compare){
        for (int i = 0; i < LENGTH_OF_THE_BOARD;i++) {
            for (int j = 0; j < LENGTH_OF_THE_BOARD; j++) {
                if (cells.get(i).get(j).isClickedIllumination() && cells.get(i).get(j) != compare){
                    return cells.get(i).get(j);
                }
            }
        }
        return null;
    }

    public Board(){
        GoldWay = new ArrayList<>(LENGTH_OF_THE_BOARD);
        cells = new ArrayList<>(LENGTH_OF_THE_BOARD);
        DoubleWayG1A7 = new ArrayList<>(LENGTH_OF_THE_BOARD - 1);
        DoubleWayH2B8 = new ArrayList<>(LENGTH_OF_THE_BOARD - 1);
        TripleWayC1H6 = new ArrayList<>(LENGTH_OF_THE_BOARD - 2);
        TripleWayA3F8 = new ArrayList<>(LENGTH_OF_THE_BOARD - 2);
        TripleWayC1A3 = new ArrayList<>(3);
        TripleWayH6F8 = new ArrayList<>(3);
        UltraWayE1A5 = new ArrayList<>(5);
        UltraWayH4D8 = new ArrayList<>(5);
        UltraWayE1H4 = new ArrayList<>(4);
        UltraWayA5D8 = new ArrayList<>(4);
        ways = new ArrayList<>(10);
        ways.add(GoldWay);
        ways.add(DoubleWayG1A7);
        ways.add(DoubleWayH2B8);
        ways.add(TripleWayA3F8);
        ways.add(TripleWayC1A3);
        ways.add(TripleWayC1H6);
        ways.add(TripleWayH6F8);
        ways.add(UltraWayE1A5);
        ways.add(UltraWayE1H4);
        ways.add(UltraWayA5D8);
        ways.add(UltraWayH4D8);

        for (int i = 0; i < LENGTH_OF_THE_BOARD; i++){
            ArrayList<Cell> tmp = new ArrayList<>(LENGTH_OF_THE_BOARD);
            char c = 'A';
            for (int j = 0; j < LENGTH_OF_THE_BOARD; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                sb.append(i+1);
                c++;
                tmp.add(new Cell(sb.toString()));
            }
            cells.add(tmp);
        }

        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (i % 2 == j % 2)
                    cells.get(i).get(j).setCellcolor(Cell.BLACK);
                else
                    cells.get(i).get(j).setCellcolor(Cell.WHITE);
            }
        }

        for (int i = 0; i < LENGTH_OF_THE_BOARD;i++){
            for (int j = 0; j < LENGTH_OF_THE_BOARD; j++){
                if (whitnum != CHECKERS_NUMBER){
                    if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1){
                        cells.get(i).get(j).setCheckersColor(Cell.WHITE);
                        whitnum++;
                    }
                }
                if (i > LENGTH_OF_THE_BOARD / 2){
                    if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1){
                        cells.get(i).get(j).setCheckersColor(Cell.BLACK);
                        blacknum++;
                    }
                }

                if (i == j){
                    GoldWay.add(cells.get(i).get(j));

                }

                if (i + j == 6){
                    DoubleWayG1A7.add(cells.get(i).get(j));
                }

                if (i + j == LENGTH_OF_THE_BOARD){
                    DoubleWayH2B8.add(cells.get(i).get(j));
                }

                if (i + j == 2){
                    TripleWayC1A3.add(cells.get(i).get(j));
                }

                if (i + j == 12){
                    TripleWayH6F8.add(cells.get(i).get(j));
                }

                if (j - i == 2){
                    TripleWayC1H6.add(cells.get(i).get(j));
                }

                if (i - j == 2){
                    TripleWayA3F8.add(cells.get(i).get(j));
                }

                if (i + j == 4){
                    UltraWayE1A5.add(cells.get(i).get(j));
                }

                if (i + j == 10){
                    UltraWayH4D8.add(cells.get(i).get(j));
                }

                if (j - i == 4){
                    UltraWayE1H4.add(cells.get(i).get(j));
                }
                if (i - j == 4){
                    UltraWayA5D8.add(cells.get(i).get(j));
                }
            }
        }
        System.out.println(cells);

    }

    public Board(ArrayList<ArrayList<Cell>> otcells){
        GoldWay = new ArrayList<>(LENGTH_OF_THE_BOARD);
        cells = new ArrayList<>(LENGTH_OF_THE_BOARD);
        DoubleWayG1A7 = new ArrayList<>(LENGTH_OF_THE_BOARD - 1);
        DoubleWayH2B8 = new ArrayList<>(LENGTH_OF_THE_BOARD - 1);
        TripleWayC1H6 = new ArrayList<>(LENGTH_OF_THE_BOARD - 2);
        TripleWayA3F8 = new ArrayList<>(LENGTH_OF_THE_BOARD - 2);
        TripleWayC1A3 = new ArrayList<>(3);
        TripleWayH6F8 = new ArrayList<>(3);
        UltraWayE1A5 = new ArrayList<>(5);
        UltraWayH4D8 = new ArrayList<>(5);
        UltraWayE1H4 = new ArrayList<>(4);
        UltraWayA5D8 = new ArrayList<>(4);
        ways = new ArrayList<>(10);
        ways.add(GoldWay);
        ways.add(DoubleWayG1A7);
        ways.add(DoubleWayH2B8);
        ways.add(TripleWayA3F8);
        ways.add(TripleWayC1A3);
        ways.add(TripleWayC1H6);
        ways.add(TripleWayH6F8);
        ways.add(UltraWayE1A5);
        ways.add(UltraWayE1H4);
        ways.add(UltraWayA5D8);
        ways.add(UltraWayH4D8);

        for (int i = 0; i < LENGTH_OF_THE_BOARD; i++){
            ArrayList<Cell> tmp = new ArrayList<>(LENGTH_OF_THE_BOARD);
            for (int j = 0; j <LENGTH_OF_THE_BOARD; j++) {
                tmp.add(new Cell(otcells.get(i).get(j)));
            }
            cells.add(tmp);
        }

        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (i % 2 == j % 2)
                    cells.get(i).get(j).setCellcolor(Cell.BLACK);
                else
                    cells.get(i).get(j).setCellcolor(Cell.WHITE);
            }
        }

        for (int i = 0; i < LENGTH_OF_THE_BOARD;i++){
            for (int j = 0; j < LENGTH_OF_THE_BOARD; j++){
                if (i == j){
                    GoldWay.add(cells.get(i).get(j));

                }

                if (i + j == 6){
                    DoubleWayG1A7.add(cells.get(i).get(j));
                }

                if (i + j == LENGTH_OF_THE_BOARD){
                    DoubleWayH2B8.add(cells.get(i).get(j));
                }

                if (i + j == 2){
                    TripleWayC1A3.add(cells.get(i).get(j));
                }

                if (i + j == 12){
                    TripleWayH6F8.add(cells.get(i).get(j));
                }

                if (j - i == 2){
                    TripleWayC1H6.add(cells.get(i).get(j));
                }

                if (i - j == 2){
                    TripleWayA3F8.add(cells.get(i).get(j));
                }

                if (i + j == 4){
                    UltraWayE1A5.add(cells.get(i).get(j));
                }

                if (i + j == 10){
                    UltraWayH4D8.add(cells.get(i).get(j));
                }

                if (j - i == 4){
                    UltraWayE1H4.add(cells.get(i).get(j));
                }
                if (i - j == 4){
                    UltraWayA5D8.add(cells.get(i).get(j));
                }
            }
        }
    }

    private boolean isCorrectMove(Cell start,Cell finish, int i1,int j1,int i2, int j2){
        if (start.getCheckerscolor() == Cell.BLACK && i2-i1 == -1 && Math.abs(j2-j1) == 1){
            return true;
        }
        if (start.getCheckerscolor() == Cell.WHITE && i2-i1 == 1 && Math.abs(j2-j1) == 1){
            return true;
        }
        return false;
    }

    public boolean MakeMove(Cell start, Cell finish ){
        System.out.println(start + "--" + finish);
        int i1 = start.getName().charAt(1) - '1';
        int j1 = start.getName().charAt(0) - 'A';
        int i2 = finish.getName().charAt(1) - '1';
        int j2 = finish.getName().charAt(0) - 'A';
        if (isCorrectMove(start, finish, i1, j1, i2, j2) && !start.isQueen()) {
            if (cells.get(i2).get(j2).getCheckerscolor() == Cell.EMPTY) {
                cells.get(i2).get(j2).setCheckersColor(cells.get(i1).get(j1).getCheckerscolor());
                cells.get(i1).get(j1).setCheckersColor(Cell.EMPTY);
                if (cells.get(i2).get(j2).getCheckerscolor() == Cell.BLACK && i2 == 0){
                    cells.get(i2).get(j2).setQueen(true);
                }
                if (cells.get(i2).get(j2).getCheckerscolor() == Cell.WHITE && i2 == LENGTH_OF_THE_BOARD -1){
                    cells.get(i2).get(j2).setQueen(true);
                }
                return true;
            }
            else {
                System.out.println("Illegal Move!!");
            }
        }
        else {
            System.out.println("Illegal Move!11");
        }
        return false;

    }

    public void UndoMove(Cell start, Cell finish){
        System.out.println("UNDO_MOVE");
        int i1 = start.getName().charAt(1) - '1';
        int j1 = start.getName().charAt(0) - 'A';
        int i2 = finish.getName().charAt(1) - '1';
        int j2 = finish.getName().charAt(0) - 'A';
            if (cells.get(i1).get(j1).getCheckerscolor() == Cell.EMPTY) {
                System.out.println("UNDO_MOVE_SUCCES");
                cells.get(i1).get(j1).setCheckersColor(cells.get(i2).get(j2).getCheckerscolor());
                cells.get(i1).get(j1).setQueen(cells.get(i2).get(j2).isQueen());
                cells.get(i2).get(j2).setQueen(false);
                cells.get(i2).get(j2).setCheckersColor(Cell.EMPTY);

            }
    }

    public boolean MakeQueenMove(Cell start, Cell finish){
        int k = -1;
        if (finish.getCheckerscolor() != Cell.EMPTY )
            return false;

        for (int i = 0; i < ways.size(); i++){
             if (ways.get(i).contains(start) && ways.get(i).contains(finish)){
                 k = i;
             }
        }
        if (k < 0) {
            System.out.println("ILLegal Move!");
            return false;
        }
        for (int i = ways.get(k).indexOf(start); i != ways.get(k).indexOf(finish);){
            if (i < ways.get(k).indexOf(finish))
                i++;
            else
                i--;
            if (ways.get(k).get(i) != finish && ways.get(k).get(i).getCheckerscolor() != Cell.EMPTY)
                return false;
        }
        finish.setCheckersColor(start.getCheckerscolor());
        finish.setQueen(start.isQueen());
        start.setQueen(false);
        start.setCheckersColor(Cell.EMPTY);
        return true;
    }

    public Cell CheckToChopBlack(){//проверка можно ли рубить черными белых
        for (int i = 0; i < ways.size(); i++){
            for (int j = 1; j < ways.get(i).size() - 1; j++){
                int tmpj = j;
                int wheretochop = j;
                if (ChoppingCondition(i, j, Cell.WHITE, Cell.BLACK) == NOT_CHOP)
                    continue;
                if (ChoppingCondition(i, j, Cell.WHITE, Cell.BLACK) == CHOP_FORWARD){
                    tmpj = j - 1;
                    wheretochop = j + 1;
                }
                if (ChoppingCondition(i, j, Cell.WHITE, Cell.BLACK) == CHOP_BACK){
                    tmpj = j + 1;
                    wheretochop = j - 1;
                }
                if (ways.get(i).get(j).isChop())
                continue;
                ways.get(i).get(tmpj).setChop(true);
                ways.get(i).get(tmpj).getButton().setBackground(Color.RED);
                ways.get(i).get(tmpj).setWhereToChop(ways.get(i).get(wheretochop));
                return ways.get(i).get(tmpj);
            }
        }
        return null;
    }

    private int ChoppingCondition(int i, int j, int eatenColor, int choppingColor){
        if (ways.get(i).get(j).getCheckerscolor() == eatenColor && ways.get(i).get(j - 1).getCheckerscolor() == choppingColor
                && ways.get(i).get(j + 1).getCheckerscolor() == Cell.EMPTY)
            return CHOP_FORWARD;
        if (ways.get(i).get(j).getCheckerscolor() == eatenColor && ways.get(i).get(j + 1).getCheckerscolor() == choppingColor
                && ways.get(i).get(j - 1).getCheckerscolor() == Cell.EMPTY)
            return CHOP_BACK;
        return NOT_CHOP;
    }

    public Cell CheckToChopWhite(){//проверка можно ли рубить белыми черных
        for (int i = 0; i < ways.size(); i++){
            for (int j = 1; j < ways.get(i).size() - 1; j++){
                /*if (ways.get(i).get(j).isQueen()){
                    boolean AddMoveFlag = false;
                    Cell res = null;
                    for (int t = j; t < ways.get(i).size() - 1; t++){

                        if (ways.get(i).get(t).getCheckerscolor() == Cell.BLACK && ways.get(i).get(t+1).getCheckerscolor() == Cell.EMPTY){
                            AddMoveFlag = true;
                            res = ways.get(i).get(t);
                        }
                        if (AddMoveFlag) {
                            if (ways.get(i).get(t).getCheckerscolor() == Cell.EMPTY) {
                                res.addQueenWheretoChop(ways.get(i).get(t + 1));
                                continue;
                            } else
                                break;
                        }

                    }
                    return res;
                }*/
                int tmpj = j;
                int wheretochop = j;
                if (ChoppingCondition(i, j, Cell.BLACK, Cell.WHITE) == NOT_CHOP)
                    continue;
                if (ChoppingCondition(i, j, Cell.BLACK, Cell.WHITE) == CHOP_FORWARD){
                    tmpj = j - 1;
                    wheretochop = j + 1;
                }
                if (ChoppingCondition(i, j, Cell.BLACK, Cell.WHITE) == CHOP_BACK){
                    tmpj = j + 1;
                    wheretochop = j - 1;
                }
                if (ways.get(i).get(j).isChop())
                    continue;
                ways.get(i).get(tmpj).setChop(true);
                ways.get(i).get(tmpj).getButton().setBackground(Color.RED);
                ways.get(i).get(tmpj).setWhereToChop(ways.get(i).get(wheretochop));
                return ways.get(i).get(tmpj);
            }
        }
        return null;
    }

    public void ChopWhite(Cell chopping){//рубить белого
        chopping.getWhereToChop().setCheckersColor(Cell.BLACK);
        chopping.getWhereToChop().setQueen(chopping.isQueen());
        chopping.setQueen(false);
        chopping.setCheckersColor(Cell.EMPTY);
        chopping.setChop(false);
        int i = (getCellrow(chopping.getName()) + getCellrow(chopping.getWhereToChop().getName())) / 2;
        int j = (getCellcoulumn(chopping.getName()) + getCellcoulumn(chopping.getWhereToChop().getName())) / 2;
        cells.get(i).get(j).setCheckersColor(Cell.EMPTY);
        cells.get(i).get(j).setQueen(false);
        whitnum--;
        if (getCellrow(chopping.getWhereToChop().getName()) == 0)
            chopping.getWhereToChop().setQueen(true);
        chopping.setWhereToChop(null);
    }

    public void ChopBlack(Cell chopping){//рубить черного
        chopping.getWhereToChop().setCheckersColor(Cell.WHITE);
        chopping.getWhereToChop().setQueen(chopping.isQueen());
        chopping.setCheckersColor(Cell.EMPTY);
        chopping.setChop(false);
        chopping.setQueen(false);
        int i = (getCellrow(chopping.getName()) + getCellrow(chopping.getWhereToChop().getName())) / 2;
        int j = (getCellcoulumn(chopping.getName()) + getCellcoulumn(chopping.getWhereToChop().getName())) / 2;
        cells.get(i).get(j).setCheckersColor(Cell.EMPTY);

        blacknum--;
        if (getCellrow(chopping.getWhereToChop().getName()) == LENGTH_OF_THE_BOARD-1)
            chopping.getWhereToChop().setQueen(true);
        chopping.setWhereToChop(null);
    }

    public int getCellrow(String name){
        int i = name.charAt(1)- '1';
        //int j = name.charAt(0) - 'A';
        return i;
    }

    public int getCellcoulumn(String name){
        int j = name.charAt(0) - 'A';
        return j;
    }

    public ArrayList<ArrayList<Cell>> getCells(){
        return cells;
    }

    public int isGameOver() {
        if (blacknum == 0)
            return WHITES_WIN;
        if (whitnum == 0)
            return BLACKS_WIN;
        return 0;
    }

    public Cell getCellbyName(String name){
        int i = name.charAt(1)- '1';
        int j = name.charAt(0) - 'A';
        return cells.get(i).get(j);
    }

    private Cell isQueenChop(Cell cell, int w){//рубит ли королева
        int chopcolor;
        if (cell.getCheckerscolor() == Cell.BLACK)
            chopcolor = Cell.WHITE;
        else
            chopcolor = Cell.BLACK;

        for (int j = ways.get(w).indexOf(cell); j < ways.get(w).size() - 1; j++){
            if (ways.get(w).get(j).getCheckerscolor() == chopcolor){
                if (ways.get(w).get(j + 1).getCheckerscolor() == Cell.EMPTY){
                    cell.setChop(true);
                    cell.getButton().setBackground(Color.red);
                    int tmp = j + 1;
                    while (tmp < ways.get(w).size() && ways.get(w).get(tmp).getCheckerscolor() == Cell.EMPTY ){
                        cell.addQueenWheretoChop(ways.get(w).get(tmp));
                        tmp++;
                    }
                    return ways.get(w).get(j);
                }
                else
                    break;
            }
        }
        for (int j = ways.get(w).indexOf(cell); j > 0; j--){
            if (ways.get(w).get(j).getCheckerscolor() == chopcolor){
                if (ways.get(w).get(j - 1).getCheckerscolor() == Cell.EMPTY){
                    cell.setChop(true);
                    cell.getButton().setBackground(Color.red);
                    int tmp = j - 1;
                    while (tmp >= 0 &&ways.get(w).get(tmp).getCheckerscolor() == Cell.EMPTY){
                        cell.addQueenWheretoChop(ways.get(w).get(tmp));
                        tmp--;
                    }
                    return ways.get(w).get(j);
                }
                else
                    break;
            }
        }
        return null;
    }

    public Cell isBlacksQueenChop(){
        Cell res = null;
        for (int i = 0; i < ways.size(); i++){
            for (int j = 0; j < ways.get(i).size(); j++){
                if (ways.get(i).get(j).getCheckerscolor() == Cell.BLACK && ways.get(i).get(j).isQueen())
                    res = isQueenChop(ways.get(i).get(j), i);
                if (res != null)
                    return res;

            }
        }
        return res;
    }

    public Cell isWhiteQueenChop(){
        Cell res = null;
        for (int i = 0; i < ways.size(); i++){
            for (int j = 0; j < ways.get(i).size(); j++){
                if (ways.get(i).get(j).getCheckerscolor() == Cell.WHITE && ways.get(i).get(j).isQueen())
                    res = isQueenChop(ways.get(i).get(j), i);
                if (res != null)
                    return res;

            }
        }
        return res;
    }

    public Cell SearchCellisChoptrue(){
        for (int i = 0; i < cells.size(); i++){
            for (int j = 0; j < cells.get(i).size(); j++){
                if (cells.get(i).get(j).getCellcolor() == Cell.BLACK && cells.get(i).get(j).isChop())
                    return cells.get(i).get(j);
            }
        }
        return null;
    }

    public boolean OnTheSameDiagonal(Cell c1, Cell c2){
        for (int i = 0; i< ways.size();i++){
            if (ways.get(i).contains(c1) && ways.get(i).contains(c2))
                return true;

        }
        return false;
    }

    public void Whitenumdecrement(){
        whitnum--;
    }

    public void Blacknumdecrement(){
        blacknum--;
    }

    public ArrayList<ArrayList<Cell>> getWays() {
        return ways;
    }

}
