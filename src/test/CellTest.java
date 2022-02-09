package test;

import model.Cell;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void isQueen() {
        Cell cell = new Cell();
        cell.setQueen(true);
        assertTrue(cell.isQueen());
    }

    @Test
    public void isClickedIllumination() {
        Cell cell = new Cell();
        cell.setClickedIllumination(true);
        assertTrue(cell.isClickedIllumination());
    }

    @Test
    public void getName() {
        String expectedName = "A8";
        Cell cell = new Cell(expectedName);
        assertEquals(expectedName, cell.getName());
    }

    @Test
    public void getCheckerscolor() {
        Cell tmp = new Cell();
        int expectedcolor = Cell.BLACK;
        tmp.setCheckersColor(expectedcolor);
        assertEquals(expectedcolor, tmp.getCheckerscolor());
    }

    @Test
    public void getCellcolor() {
        Cell tmp = new Cell();
        int expectedcolor = Cell.WHITE;
        tmp.setCellcolor(expectedcolor);
        assertEquals(expectedcolor, tmp.getCellcolor());
    }

    @Test
    public void isChop() {
        Cell tmp = new Cell();
        assertFalse(tmp.isChop());
    }

    @Test
    public void getWhereToChop() {
        Cell expectedCell = new Cell("A8");
        Cell tmp = new Cell();
        tmp.setChop(true);
        tmp.setWhereToChop(expectedCell);
        assertEquals(expectedCell,tmp.getWhereToChop());
    }

    @Test
    public void getQueenWheretoChop() {
        ArrayList expectedArray = new ArrayList(2);
        Cell cell1 = new Cell("A8");
        Cell cell2 = new Cell("A1");
        Cell tmp = new Cell();
        tmp.addQueenWheretoChop(cell1);
        tmp.addQueenWheretoChop(cell2);
        expectedArray.add(cell1);
        expectedArray.add(cell2);
        assertEquals(expectedArray,tmp.getQueenWheretoChop());
    }
}