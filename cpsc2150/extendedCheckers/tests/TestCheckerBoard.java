package cpsc2150.extendedCheckers.tests;
import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import cpsc2150.extendedCheckers.util.DirectionEnum;
import org.junit.*;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public class TestCheckerBoard {
    @Test
    public void test_CheckerBoard_Constructor()
    {
        CheckerBoard obsCb = new CheckerBoard();
        String obs = obsCb.toString();
        char[][] expBoard = {{'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                            {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                            {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                            {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                            {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                            {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                            {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                            {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}};
        String exp = toStringForTest(expBoard);
        assertEquals(exp, obs);
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

    @Test
    public void test_checkPlayerWin_no_opponent_pieces_left()
    {
        CheckerBoard board = new CheckerBoard();
        HashMap<Character, Integer> exp = new HashMap<>();
        exp.put(board.PLAYER_ONE, 12);
        exp.put(board.PLAYER_TWO, 0);
        boolean hasWon = board.checkPlayerWin(board.PLAYER_ONE);
        assertTrue(hasWon);
    }

    @Test
    public void test_checkPlayerWin_opponent_pieces_exist()
    {
        CheckerBoard board = new CheckerBoard();
        HashMap<Character, Integer> exp = new HashMap<>();
        exp.put(board.PLAYER_ONE, 12);
        exp.put(board.PLAYER_TWO, 3);
        boolean hasWon = board.checkPlayerWin(board.PLAYER_ONE);
        assertFalse(hasWon);
    }

    @Test
    public void test_scanSurroundingPositions_validMoves()
    {
        CheckerBoard board = new CheckerBoard(8);
        BoardPosition startingPos = new BoardPosition(3,3);
        board.placePiece(new BoardPosition(2,2), 'x');
        board.placePiece(new BoardPosition(1,1), 'o');
        HashMap<DirectionEnum, Character> surroundingPosition = board.scanSurroundingPositions(startingPos);

        BoardPosition[] expectedPositions = {
            new BoardPosition(0,0), //NW
            new BoardPosition(2,0), //SE
            new BoardPosition(0,2), //NE
        };

        assertArrayEquals(expectedPositions, surroundingPosition);
    }

    @Test
    public void test_scanSurroundingPositions_oSurroundings()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition startingPos = new BoardPosition(5,5);

        board.placePiece(new BoardPosition(5,5), 'o');
        board.placePiece(new BoardPosition(3,5), 'x');
        board.placePiece(new BoardPosition(5,3), 'x');
        board.placePiece(new BoardPosition(3,3), 'x');

        HashMap<DirectionEnum, Character> surroundingPos = board.scanSurroundingPositions(startingPos);

        BoardPosition[] expectedPositions = {
                new BoardPosition(3,3),
                new BoardPosition(5,3),
                new BoardPosition(3,5),
                new BoardPosition(5,5),
        };

        assertArrayEquals(expectedPositions, surroundingPos);
    }

    @Test
    public void test_scanSurroundingPositions_noValidMove()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition startingPosition = new BoardPosition(0,0);
        //Assuming board has one 'x' at (0,0)
        board.placePiece(startingPosition, 'x');
        HashMap<DirectionEnum, Character> surroundingPositions = board.scanSurroundingPositions(startingPosition);
        BoardPosition[] expPos = {
            new BoardPosition(1,1); //SE Position
            new BoardPosition(0,3); //Directly to the right
            new BoardPosition(2,0); //Directly below
        };

        assertArrayEquals(expPos, surroundingPositions);
    }

    @Test
    public void test_getDirection_invalidDirection()
    {
        CheckerBoard board = new CheckerBoard();
        int expX = -1;
        int expY = 1;
        HashMap<Character, ArrayList<DirectionEnum>> result = board.getViableDirections();

        assertEquals(expX, result[0]);
        assertEquals(expY, result[1]);

    }



    private String toStringForTest(char[][] array)
    {

        String arrayString = "|  ";
        for(int i = 0; i < array.length; i++){
            arrayString += "| " + i;
        }
        arrayString += "|";
        for(int i = 0; i < array.length; i++){
            arrayString += "\n|" + i + " |";
            for(int j = 0; j < array[i].length; j++) {
                arrayString += array[i][j] + " |";
            }
        }
        return arrayString;
    }
}

