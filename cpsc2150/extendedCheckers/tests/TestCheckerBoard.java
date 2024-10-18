package cpsc2150.extendedCheckers.tests;
import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;


public class TestCheckerBoard {
    @Test
    public void test_CheckerBoard_Constructor()
    {
        char[][] expBoard;
        CheckerBoard obsCb = new CheckerBoard();
        String exp = toStringForTest(expBoard);
        assertEquals(exp, obsCb.toString());
    }

    @Test
    public void test_whatsAtPos_MinRowMinCol()
    {
        BoardPosition input = new BoardPosition(0,0);
        char exp = 'x';
        CheckerBoard board = new CheckerBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_MaxRowMaxCol()
    {
        BoardPosition input = new BoardPosition(7,7);
        char exp = 'o';
        CheckerBoard board = new CheckerBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_MidRowMidCol()
    {
        BoardPosition input = new BoardPosition(2,4);
        char exp = 'x';
        CheckerBoard board = new CheckerBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_BlackTile()
    {
        BoardPosition input = new BoardPosition(0,1);
        char exp = '*';
        CheckerBoard board = new CheckerBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_EmptyTile()
    {
        BoardPosition input = new BoardPosition(4,0);
        char exp = ' ';
        CheckerBoard board = new CheckerBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_getPieceCounts_x12_o12()
    {
        HashMap<Character, Integer> exp = new HashMap<>();
        exp.put('x', 12);
        exp.put('o', 12);
        CheckerBoard board = new CheckerBoard();
        HashMap<Character, Integer> obs = board.getPieceCounts();
        assertEquals(exp, obs);
    }

    private String toStringForTest(char[][] array)
    {

        String arrayString = "|  ";
        for(int i = 0; i < array.length; i++){
            arrayString += "| " + i;
        }
        for(int i = 0; i < array.length; i++){
            arrayString += "\n|i |";
            for(int j = 0; j < array[i].length; j++) {
                arrayString += array[i][j] + " |";
            }
        }
        return arrayString;
    }
}