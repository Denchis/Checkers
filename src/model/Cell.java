package model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class Cell {
    private String name;
    private int checkerscolor;// Цвет шашки, 1 — белая, 2 — черная, 0 — клетка пустая
    private int cellcolor;//1-белая клетка, 2 черная клетка
    private boolean queen;
    private boolean clickedIllumination;
    private boolean moveIllumination;
    private boolean chop;
    private Cell whereToChop;
    private JButton button;
    private ArrayList<Cell> QueenWheretoChop;

    public static final int BLACK = 2;
    public static final int WHITE = 1;
    public static final int EMPTY = 0;

    class ButtonMouseListener implements MouseListener {
        public void  mouseClicked(MouseEvent e)  {
            if (getCellcolor()  == BLACK) {
                getButton().setBackground(Color.green);
                setClickedIllumination(true);
                System.out.println(getName() + " Clicked()");

            }
        }
        public void  mouseEntered(MouseEvent e) {}
        public void  mouseExited(MouseEvent e) {}
        public void  mousePressed(MouseEvent e) {}
        public void  mouseReleased(MouseEvent e) {}
    }

    public Cell(){
        name = "";
        checkerscolor = EMPTY;
        queen = false;
        clickedIllumination = false;
        chop = false;
        whereToChop = null;
        button = new JButton();
        button.addMouseListener( new ButtonMouseListener());
        cellcolor = 0;
        moveIllumination = false;
        QueenWheretoChop = new ArrayList<Cell>();
    }

    public Cell(Cell other){
        name = other.getName();
        checkerscolor = other.getCheckerscolor();
        queen = other.isQueen();
        clickedIllumination = false;
        chop = isClickedIllumination();
        whereToChop = other.getWhereToChop();
        button = new JButton();
        button.addMouseListener( new ButtonMouseListener());
        cellcolor = other.getCellcolor();
        moveIllumination = other.moveIllumination;
        QueenWheretoChop = new ArrayList<Cell>();
        QueenWheretoChop.addAll(other.getQueenWheretoChop());
    }

    public Cell(String name){
        this.name = name;
        this.checkerscolor = 0;
        queen = false;
        clickedIllumination = false;
        chop = false;
        whereToChop = null;
        button = new JButton();
        button.addMouseListener( new ButtonMouseListener());
        cellcolor = 0;
        moveIllumination = false;
        QueenWheretoChop = new ArrayList<Cell>();
    }

    public void setQueen(boolean flag){
        if (flag){
            if (checkerscolor == WHITE) {
                ImageIcon WhiteQueen = new ImageIcon("res/WhiteQueen.png");
                button.setIcon(WhiteQueen);
            }
            if (checkerscolor == BLACK) {
                ImageIcon BlackQueen = new ImageIcon("res/BlackQueen.png");
                button.setIcon(BlackQueen);
            }
        }
        queen = flag;
    }

    public boolean isQueen(){
        return queen;
    }

    public void setClickedIllumination(boolean illumination) {
        this.clickedIllumination = illumination;
    }

    public boolean isClickedIllumination() {
        return clickedIllumination;
    }

    public String getName(){

        return "*" + name;
    }

    public int getCheckerscolor() {
        return checkerscolor;
    }

    public void setCheckersColor(int color) {
        ImageIcon whiteImage = new ImageIcon("res/White.png");
        ImageIcon blackImage = new ImageIcon("res/Black.png");
        this.checkerscolor = color;
        if (color == BLACK){
            button.setIcon(blackImage);
            return;
        }
        if (color == WHITE){
            button.setIcon(whiteImage);
            return;
        }
        button.setIcon(null);
    }

    public int getCellcolor() {
        return cellcolor;
    }

    public void setCellcolor(int cellcolor) {
        this.cellcolor = cellcolor;
    }

    public boolean isChop() {
        return chop;
    }

    public void setChop(boolean flag){
        chop = flag;
    }

    public void setWhereToChop(Cell whereToChop) {
        this.whereToChop = whereToChop;
    }

    public Cell getWhereToChop() {
        return whereToChop;
    }

    public JButton getButton(){
        return button;
    }

    public String toString(){
        String s = getName();
        s = s + checkerscolor + queen;
        return s;
    }

    public boolean isMoveIllumination() {
        return moveIllumination;
    }

    public void setMoveIllumination(boolean moveIllumination) {
        this.moveIllumination = moveIllumination;
    }

    public ArrayList<Cell> getQueenWheretoChop() {
        return QueenWheretoChop;
    }

    public void addQueenWheretoChop(Cell tmp) {
        QueenWheretoChop.add(tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return checkerscolor == cell.checkerscolor &&
                cellcolor == cell.cellcolor &&
                queen == cell.queen &&
                clickedIllumination == cell.clickedIllumination &&
                moveIllumination == cell.moveIllumination &&
                chop == cell.chop &&
                Objects.equals(name, cell.name) &&
                Objects.equals(whereToChop, cell.whereToChop) &&
                Objects.equals(button, cell.button) &&
                Objects.equals(QueenWheretoChop, cell.QueenWheretoChop);
    }
}
