package cpsc2150.extendedCheckers.tests;
import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import cpsc2150.extendedCheckers.util.DirectionEnum;
import org.junit.*;

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
    public void test_placePiece_EmptyTile_playerX()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition input = new BoardPosition(3,3);
        char inputPlayer = 'x';

        board.placePiece(input, inputPlayer);

        String observedBoard = board.toString();

        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', 'x', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };

        String expected = toStringForTest(expBoard);
        assertEquals(expected, observedBoard);
    }

    @Test
    public void test_placePiece_CornerTile_replaceX_withO()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition input = new BoardPosition(7,7);
        char inputPlayer = 'x';

        board.placePiece(input, inputPlayer);

        String observedBoard = board.toString();

        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'x'}
        };

        String expected = toStringForTest(expBoard);
        assertEquals(expected, observedBoard);
    }

    @Test
    public void test_placePiece_WhiteTile()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition input = new BoardPosition(3,0);
        char inputPlayer = 'x';

        board.placePiece(input, inputPlayer);

        String observedBoard = board.toString();

        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'x', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };

        String expected = toStringForTest(expBoard);
        assertEquals(expected, observedBoard);
    }

    @Test
    public void test_placePiece_MiddleTile_replaceX_withO()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition input = new BoardPosition(2,4);
        char inputPlayer = 'o';

        board.placePiece(input, inputPlayer);

        String observedBoard = board.toString();

        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'o', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };

        String expected = toStringForTest(expBoard);
        assertEquals(expected, observedBoard);
    }

    @Test
    public void test_placePiece_CornerTile_replacex_withX()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition input = new BoardPosition(0,0);
        char inputPlayer = 'x';

        board.placePiece(input, inputPlayer);

        String observedBoard = board.toString();

        char[][] expBoard = {
                {'X', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };

        String expected = toStringForTest(expBoard);
        assertEquals(expected, observedBoard);
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
    public void test_getViableDirections_8x8board()
    {
        CheckerBoard board = new CheckerBoard();
        HashMap<Character, ArrayList<DirectionEnum>> actual = board.getViableDirections();
        HashMap<Character, ArrayList<DirectionEnum>> expected = new HashMap<>();

        ArrayList<DirectionEnum> xDirections = new ArrayList<>();
        ArrayList<DirectionEnum> oDirections = new ArrayList<>();

        xDirections.add(DirectionEnum.SE);
        xDirections.add(DirectionEnum.SW);
        oDirections.add(DirectionEnum.NE);
        oDirections.add(DirectionEnum.NW);

        expected.put('x', xDirections);
        expected.put('o', oDirections);

        assertEquals(expected, actual);
    }

    @Test
    public void test_checkPlayerWin_no_opponent_pieces_left()
    {
        CheckerBoard testBoard = new CheckerBoard();
        HashMap<Character, Integer> exp = new HashMap<>();
        exp.put(CheckerBoard.PLAYER_ONE, 12);
        exp.put(testBoard.PLAYER_TWO, 0);
        boolean hasWon = testBoard.checkPlayerWin(testBoard.PLAYER_ONE);
        assertTrue(hasWon);
    }

    @Test
    public void test_checkPlayerWin_opponent_pieces_exist()
    {
        CheckerBoard testBoard = new CheckerBoard();
        HashMap<Character, Integer> exp = new HashMap<>();
        exp.put(CheckerBoard.PLAYER_ONE, 12);
        exp.put(CheckerBoard.PLAYER_TWO, 12);
        boolean hasWon = testBoard.checkPlayerWin(CheckerBoard.PLAYER_ONE);
        assertFalse(hasWon);
    }

    @Test
    public void test_crownPiece_playerx_on_opposing_side()
    {
        CheckerBoard testBoard = new CheckerBoard();
        BoardPosition targetPos = new BoardPosition(7,1);
        testBoard.placePiece(targetPos, 'x');

        //Removing pieces from board
        testBoard.placePiece(new BoardPosition(6, 0), CheckerBoard.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(6, 2), CheckerBoard.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(5, 1), CheckerBoard.EMPTY_POS);

        //Moving pieces on board
        testBoard.placePiece(new BoardPosition(4, 4), CheckerBoard.PLAYER_TWO);
        testBoard.placePiece(new BoardPosition(4, 6), CheckerBoard.PLAYER_TWO);

        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);
        assertEquals(obsChar, 'X');
    }

    @Test
    public void test_crownPiece_playerx_on_player_side()
    {
        CheckerBoard testBoard = new CheckerBoard();
        BoardPosition targetPos = new BoardPosition(0,0);
        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);

        assertEquals(obsChar, 'x');
    }

    @Test
    public void test_crownPiece_playerx_already_crowned()
    {
        CheckerBoard testBoard = new CheckerBoard();
        testBoard.placePiece(new BoardPosition(2, 0), CheckerBoard.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(5, 7), CheckerBoard.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(4, 6), CheckerBoard.PLAYER_TWO);

        BoardPosition targetPos = new BoardPosition(4,0);
        testBoard.placePiece(targetPos, 'X');
        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);

        assertEquals(obsChar, 'X');
    }

    @Test
    public void test_movePiece_in_empty_spot()
    {
        CheckerBoard board = new CheckerBoard();
        BoardPosition startPos = new BoardPosition(2,0);
        BoardPosition endPos = board.movePiece(startPos, DirectionEnum.SE);
        assertEquals('x', board.whatsAtPos(endPos));
    }

    @Test
    public void test_movePiece_in_occupied_spot()
    {
        CheckerBoard testBoard = new CheckerBoard();
        BoardPosition startPosX = new BoardPosition(2,0);
        testBoard.movePiece(startPosX, DirectionEnum.SE);

        BoardPosition startPosO = new BoardPosition(5,1);
        testBoard.movePiece(startPosO, DirectionEnum.NW);

        BoardPosition obsPosX = new BoardPosition(3,1);
        testBoard.movePiece(obsPosX, DirectionEnum.SW);

        String obsBoard = testBoard.toString();
        char[][] exp = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {' ', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', ' ', '*', ' ', '*', ' '},
                {'o', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', ' ', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}};

        String expBoard = toStringForTest(exp);
        assertEquals(expBoard, obsBoard);
    }

    @Test
    public void test_movePiece_SW_out_of_bounds()
    {
        CheckerBoard testBoard = new CheckerBoard();
        BoardPosition startPos = new BoardPosition(2,0);
        BoardPosition endPos = testBoard.movePiece(startPos, DirectionEnum.SW);

        String obsBoard = testBoard.toString();
        char[][] exp = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'o', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };

        String expBoard = toStringForTest(exp);
        assertEquals(expBoard, obsBoard);
    }

    @Test
    public void test_jumpPiece_SEjump()
    {
        BoardPosition startPos = new BoardPosition(4, 4);
        DirectionEnum dir = DirectionEnum.SE;

        Board board = new Board();
        board.setPieceAt(4,4)

    }

    @Test
    public void test_jumpPiece_SWjump()
    {
        CheckerBoard testBoard = new CheckerBoard();

        // Empty the board
        for (int row = 0; row < CheckerBoard.ROW_NUM; row++) {
            for (int col = 0; col < CheckerBoard.COL_NUM; col++) {
                if ((row + col) % 2 == 0) {
                    testBoard.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }
        //Manually place the pieces
        testBoard.placePiece(new BoardPosition(2,2), 'x');
        testBoard.placePiece(new BoardPosition(3,1), 'o');

    }

    @Test
    public void test_jumpPiece_invalidMove_noPieceToJump()
    {
        CheckerBoard board = new CheckerBoard();

        // Empty the board
        for (int row = 0; row < CheckerBoard.ROW_NUM; row++) {
            for (int col = 0; col < CheckerBoard.COL_NUM; col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        //Manually place the 'x' piece
        board.placePiece(new BoardPosition(3,3), 'x');

        //Define starting position and jump direction
        BoardPosition startingPos = new BoardPosition(3,3);
        DirectionEnum dir = DirectionEnum.SE;

        //Attempt to jump in SE direction
        board.jumpPiece(startingPos, dir);

        //Check board to remain same
        assertEquals(1, board.getPieceCounts('x');
        assertEquals(0, board.getPieceCounts('o');

    }

    @Test
    public void test_scanSurroundingPositions_validMoves()
    {
        CheckerBoard board = new CheckerBoard();

        // Empty the board
        for (int row = 0; row < CheckerBoard.ROW_NUM; row++) {
            for (int col = 0; col < CheckerBoard.COL_NUM; col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        //Define starting position
        BoardPosition startingPos = new BoardPosition(3,3);

        //Manually add
        board.placePiece(new BoardPosition(2,2), 'x');
        board.placePiece(new BoardPosition(1,1), 'o');

        HashMap<DirectionEnum, Character> surroundingPos = board.scanSurroundingPositions(startingPos);

        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.NW, 'o');
        expectedSurroundingPos.put(DirectionEnum.SE, 'o');
        expectedSurroundingPos.put(DirectionEnum.SW, '');
        expectedSurroundingPos.put(DirectionEnum.NE, '');

        assertEquals(expectedSurroundingPos, surroundingPos);
    }

    @Test
    public void test_scanSurroundingPositions_oSurroundings() {
        CheckerBoard board = new CheckerBoard();

        // Empty the board
        for (int row = 0; row < CheckerBoard.ROW_NUM; row++) {
            for (int col = 0; col < CheckerBoard.COL_NUM; col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        // Manually add pieces to the board
        board.placePiece(new BoardPosition(5, 5), 'o');  // 'o' at (5,5)
        board.placePiece(new BoardPosition(3, 5), 'x');  // 'x' at (3,5)
        board.placePiece(new BoardPosition(5, 3), 'x');  // 'x' at (5,3)
        board.placePiece(new BoardPosition(3, 3), 'x');  // 'x' at (3,3)

        // Define the starting position
        BoardPosition startingPos = new BoardPosition(5, 5);

        // Scan the surrounding positions
        HashMap<DirectionEnum, Character> surroundingPos = board.scanSurroundingPositions(startingPos);

        // Expected positions and values
        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.NW, 'x');
        expectedSurroundingPos.put(DirectionEnum.SW, 'x');
        expectedSurroundingPos.put(DirectionEnum.SE, 'x');
        expectedSurroundingPos.put(DirectionEnum.NE, 'o');  // Assuming 'o' remains at (5,5)

        // Assert that the surrounding positions match the expected ones
        assertEquals(expectedSurroundingPos, surroundingPos);
    }
}


@Test
    public void test_scanSurroundingPositions_noValidMove()
    {
        CheckerBoard board = new CheckerBoard();

        // Empty the board
        for (int row = 0; row < CheckerBoard.ROW_NUM; row++) {
            for (int col = 0; col < CheckerBoard.COL_NUM; col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        // Manually add one piece to the board at (0, 0)
        board.placePiece(new BoardPosition(0, 0), 'x');

        BoardPosition startingPosition = new BoardPosition(0,0);
        //Assuming board has one 'x' at (0,0)

        HashMap<DirectionEnum, Character> surroundingPositions = board.scanSurroundingPositions(startingPosition);

        // Expected positions and values
        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.SE, ' ');
        expectedSurroundingPos.put(DirectionEnum.SW, ' ');
        expectedSurroundingPos.put(DirectionEnum.NE, ' ');
        expectedSurroundingPos.put(DirectionEnum.NW, ' ');

        assertEquals(expectedSurroundingPos, surroundingPositions);
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

        StringBuilder arrayString = new StringBuilder("|  ");
        for(int i = 0; i < array.length; i++){
            arrayString.append("| ").append(i);
        }
        arrayString.append("|");
        for(int i = 0; i < array.length; i++){
            arrayString.append("\n|").append(i).append(" |");
            for(int j = 0; j < array[i].length; j++) {
                arrayString.append(array[i][j]).append(" |");
            }
        }
        return arrayString.toString();
    }
}