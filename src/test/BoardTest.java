package test;

import model.Board;
import model.Cell;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void searchIlluminationCell() {
        Board b = new Board();
        Cell expected = b.getCells().get(0).get(0);
        expected.setClickedIllumination(true);
        assertEquals(expected,b.SearchIlluminationCell(null));
    }

    @Test
    public void makeMove() {
        Board b = new Board();
        assertTrue(b.MakeMove(b.getCells().get(2).get(0),b.getCells().get(3).get(1)));
    }

    @Test
    public void makeQueenMove() {
        Board b = new Board();
        b.getCells().get(2).get(0).setQueen(true);
        assertTrue(b.MakeQueenMove(b.getCells().get(2).get(0), b.getCells().get(4).get(2)));
    }

    @Test
    public void checkToChopBlack() {
        Board b = new Board();
        assertNull(b.CheckToChopBlack());
        b.getCells().get(4).get(2).setCheckersColor(Cell.WHITE);
        assertEquals(b.getCells().get(5).get(1), b.CheckToChopBlack());
    }

    @Test
    public void checkToChopWhite() {
        Board b = new Board();
        assertNull(b.CheckToChopWhite());
        b.getCells().get(3).get(1).setCheckersColor(Cell.BLACK);
        assertEquals(b.getCells().get(2).get(0), b.CheckToChopWhite());
    }

    @Test
    public void getCellrow() {
        Board b = new Board();
        assertEquals(4,b.getCellrow("B5"));
    }

    @Test
    public void getCellcoulumn() {
        Board b = new Board();
        assertEquals(1,b.getCellcoulumn("B5"));
    }

    @Test
    public void isGameOver() {
        Board b = new Board();
        for (int i = 0; i < 12; i++){
            b.Blacknumdecrement();
        }
        assertEquals(b.isGameOver(),Board.WHITES_WIN);
        Board b2 = new Board();
        for (int i = 0; i < 12; i++){
            b2.Whitenumdecrement();
        }
        assertEquals(b2.isGameOver(),Board.BLACKS_WIN);
    }

    @Test
    public void isBlacksQueenChop() {
        Board b = new Board();
        assertNull(b.isBlacksQueenChop());
        b.getCells().get(5).get(1).setQueen(true);
        b.getCells().get(4).get(2).setCheckersColor(Cell.WHITE);
        assertEquals(b.isBlacksQueenChop(), b.getCells().get(4).get(2));
    }

    @Test
    public void isWhiteQueenChop() {
        Board b = new Board();
        assertNull(b.isWhiteQueenChop());
        b.getCells().get(2).get(0).setQueen(true);
        b.getCells().get(3).get(1).setCheckersColor(Cell.BLACK);
        assertEquals(b.isWhiteQueenChop(), b.getCells().get(3).get(1));
    }

    @Test
    public void searchCellisChoptrue() {
        Board b = new Board();
        assertNull(b.SearchCellisChoptrue());
        b.getCells().get(2).get(0).setChop(true);
        Cell expected = b.getCells().get(2).get(0);
        assertEquals(expected, b.SearchCellisChoptrue());
    }

    @Test
    public void onTheSameDiagonal() {
        Board b = new Board();
        assertFalse(b.OnTheSameDiagonal(b.getCells().get(5).get(1),b.getCells().get(6).get(1)));
        assertTrue(b.OnTheSameDiagonal(b.getCells().get(5).get(1),b.getCells().get(6).get(2)));
    }
}