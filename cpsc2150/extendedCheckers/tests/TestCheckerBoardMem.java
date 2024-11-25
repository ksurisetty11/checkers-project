package cpsc2150.extendedCheckers.tests;

import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import cpsc2150.extendedCheckers.models.CheckerBoardMem;
import cpsc2150.extendedCheckers.models.ICheckerBoard;
import cpsc2150.extendedCheckers.util.DirectionEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestCheckerBoardMem {
    private ICheckerBoard makeBoard() {
        return new CheckerBoardMem(8);
    }


    @Test
    public void test_CheckerBoard_Constructor() {
        ICheckerBoard obsCb = makeBoard();
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
    public void test_whatsAtPos_MinRowMinCol() {
        BoardPosition input = new BoardPosition(0, 0);
        char exp = CheckerBoardMem.PLAYER_ONE;
        ICheckerBoard board = makeBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_MaxRowMaxCol() {
        BoardPosition input = new BoardPosition(7, 7);
        char exp = CheckerBoardMem.PLAYER_TWO;
        ICheckerBoard board = makeBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_MidRowMidCol() {
        BoardPosition input = new BoardPosition(2, 4);
        char exp = CheckerBoardMem.PLAYER_ONE;
        ICheckerBoard board = makeBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_BlackTile() {
        BoardPosition input = new BoardPosition(0, 1);
        char exp = '\0';
        ICheckerBoard board = makeBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_whatsAtPos_EmptyTile() {
        BoardPosition input = new BoardPosition(4, 0);
        char exp = '\0';
        ICheckerBoard board = makeBoard();
        int obs = board.whatsAtPos(input);
        assertEquals(exp, obs);
    }

    @Test
    public void test_placePiece_EmptyTile_playerX() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(3, 3);
        char inputPlayer = CheckerBoardMem.PLAYER_ONE;

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();
        Map<Character, List<BoardPosition>> expCb = new HashMap<>();

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
        assertEquals(expected, obs);

    }

    @Test
    public void test_placePiece_CornerTile_replaceX_withO() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(7, 7);
        char inputPlayer = CheckerBoardMem.PLAYER_ONE;

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();
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
        assertEquals(expected, obs);
    }

    @Test
    public void test_placePiece_WhiteTile() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(3, 0);
        char inputPlayer = CheckerBoardMem.PLAYER_ONE;

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();

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
        assertEquals(expected, obs);
    }

    @Test
    public void test_placePiece_MiddleTile_replaceX_withO() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(2, 4);
        char inputPlayer = CheckerBoardMem.PLAYER_TWO;

        obsCb.placePiece(input, inputPlayer);

        String obs= obsCb.toString();

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
        assertEquals(expected, obs);
    }

    @Test
    public void test_placePiece_CornerTile_replacex_withX() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(0, 0);
        char inputPlayer = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();

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
        assertEquals(expected, obs);
    }

    @Test
    public void test_getPieceCounts_x12_o12()
    {
        Map<Character, Integer> exp = new HashMap<>();

        char playerOne = CheckerBoardMem.PLAYER_ONE;
        char playerTwo = CheckerBoardMem.PLAYER_TWO;

        exp.put(playerOne, 12);
        exp.put(playerTwo, 12);
        ICheckerBoard board = makeBoard();
        Map<Character, Integer> obs = board.getPieceCounts();
        assertEquals(exp, obs);
    }

    @Test
    public void test_getViableDirections_8x8board()
    {
        ICheckerBoard board = makeBoard();
        Map<Character, ArrayList<DirectionEnum>> actual = board.getViableDirections();
        Map<Character, ArrayList<DirectionEnum>> expected = new HashMap<>();

        ArrayList<DirectionEnum> xDirections = new ArrayList<>();
        ArrayList<DirectionEnum> oDirections = new ArrayList<>();
        ArrayList<DirectionEnum> crownedDirections = new ArrayList<>();

        char playerOne = CheckerBoardMem.PLAYER_ONE;
        char playerTwo = CheckerBoardMem.PLAYER_TWO;
        char playerOneCrowned = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);
        char playerTwoCrowned = Character.toUpperCase(CheckerBoardMem.PLAYER_TWO);

        xDirections.add(DirectionEnum.SE);
        xDirections.add(DirectionEnum.SW);
        oDirections.add(DirectionEnum.NE);
        oDirections.add(DirectionEnum.NW);

        crownedDirections.add(DirectionEnum.SE);
        crownedDirections.add(DirectionEnum.SW);
        crownedDirections.add(DirectionEnum.NE);
        crownedDirections.add(DirectionEnum.NW);

        expected.put(playerOne, xDirections);
        expected.put(playerTwo, oDirections);
        expected.put(playerOneCrowned, crownedDirections);
        expected.put(playerTwoCrowned, crownedDirections);

        assertEquals(expected, actual);
    }

    @Test
    public void test_checkPlayerWin_no_opponent_pieces_left()
    {
        ICheckerBoard testBoard = makeBoard();
        //Eliminating o pieces
        for (int row = 5; row < testBoard.getRowNum(); row++) {
            for (int col = 0; col < testBoard.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    testBoard.placePiece(new BoardPosition(row, col), CheckerBoardMem.EMPTY_POS);
                    HashMap<Character, Integer> playerPieceCount = testBoard.getPieceCounts();
                    playerPieceCount.put(CheckerBoardMem.PLAYER_TWO, playerPieceCount.get(CheckerBoardMem.PLAYER_TWO) - 1);
                }
            }
        }
        boolean hasWon = testBoard.checkPlayerWin(CheckerBoard.PLAYER_ONE);
        assertTrue(hasWon);
    }

    @Test
    public void test_checkPlayerWin_opponent_pieces_exist()
    {
        ICheckerBoard testBoard = makeBoard();
        boolean hasWon = testBoard.checkPlayerWin(CheckerBoardMem.PLAYER_ONE);
        assertFalse(hasWon);
    }

    @Test
    public void test_crownPiece_playerx_on_opposing_side()
    {
        char playerOne = CheckerBoardMem.PLAYER_ONE;
        char playerTwo = CheckerBoardMem.PLAYER_TWO;
        char playerOneCrowned = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);

        ICheckerBoard testBoard = makeBoard();
        BoardPosition targetPos = new BoardPosition(7,1);
        testBoard.placePiece(targetPos, playerOne);

        //Removing pieces from board
        testBoard.placePiece(new BoardPosition(6, 0), CheckerBoardMem.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(6, 2), CheckerBoardMem.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(5, 1), CheckerBoardMem.EMPTY_POS);

        //Moving pieces on board
        testBoard.placePiece(new BoardPosition(4, 4), playerTwo);
        testBoard.placePiece(new BoardPosition(4, 6), playerTwo);

        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);
        assertEquals(obsChar, playerOneCrowned);
    }

    @Test
    public void test_crownPiece_playerx_on_player_side()
    {
        ICheckerBoard testBoard = makeBoard();
        char playerOneCrowned = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);
        BoardPosition targetPos = new BoardPosition(0,0);
        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);
        assertEquals(obsChar, playerOneCrowned);
    }

    @Test
    public void test_crownPiece_playerx_already_crowned()
    {
        ICheckerBoard testBoard = makeBoard();
        testBoard.placePiece(new BoardPosition(2, 0), CheckerBoardMem.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(5, 7), CheckerBoardMem.EMPTY_POS);
        testBoard.placePiece(new BoardPosition(4, 6), CheckerBoardMem.PLAYER_TWO);

        char playerOneCrowned = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);

        BoardPosition targetPos = new BoardPosition(4,0);
        testBoard.placePiece(targetPos, playerOneCrowned);
        testBoard.crownPiece(targetPos);
        char obsChar = testBoard.whatsAtPos(targetPos);

        assertEquals(obsChar, playerOneCrowned);
    }

    @Test
    public void test_movePiece_in_empty_spot() {
        ICheckerBoard obsBoard = makeBoard();
        BoardPosition startPos = new BoardPosition(2, 0);
        BoardPosition endPos = obsBoard.movePiece(startPos, DirectionEnum.SE);

        char[][] exp = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {' ', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}};

        String obs = obsBoard.toString();
        String expBoard = toStringForTest(exp);

        assertEquals('x', obsBoard.whatsAtPos(endPos));
        assertEquals(exp, obs);
    }

    @Test
    public void test_movePiece_in_occupied_spot() {
        //Moving pieces to match the initial state
        ICheckerBoard obsBoard = makeBoard();

        obsBoard.placePiece(new BoardPosition(2, 0), CheckerBoard.EMPTY_POS);
        obsBoard.placePiece(new BoardPosition(5, 1), CheckerBoard.EMPTY_POS);

        BoardPosition startPosx = new BoardPosition(3, 1);
        BoardPosition startPoso = new BoardPosition(4, 0);
        obsBoard.placePiece(startPosx, CheckerBoard.PLAYER_ONE);
        obsBoard.placePiece(startPoso, CheckerBoard.PLAYER_TWO);

        BoardPosition obsPos = obsBoard.movePiece(startPosx, DirectionEnum.SW);
        BoardPosition expPos = new BoardPosition(4, 0);

        String obs = obsBoard.toString();

        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {' ', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {'x', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', ' ', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}};

        String exp = toStringForTest(expBoard);
        assertEquals(obs, exp);
        assertEquals(obsPos, expPos);
    }

    @Test
    public void test_movePiece_SW_out_of_bounds() {
        ICheckerBoard obsBoard = makeBoard();

        BoardPosition startPos = new BoardPosition(2, 0);
        BoardPosition obsPos = obsBoard.movePiece(startPos, DirectionEnum.SW);
        BoardPosition expPos = new BoardPosition(2, 0);

        String obs = obsBoard.toString();
        char[][] expBoard = {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}
        };
        String exp = toStringForTest(expBoard);
        assertEquals(exp, obs);
        assertEquals(obsPos, expPos);
    }

    @Test
    public void test_jumpPiece_SEjump()
    {
        ICheckerBoard testBoard = makeBoard();

        // Empty the board
        for (int row = 0; row < testBoard.getRowNum(); row++) {
            for (int col = 0; col < testBoard.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    testBoard.placePiece(new BoardPosition(row, col), CheckerBoardMem.EMPTY_POS);
                }
            }
        }
        //Manually place the pieces
        testBoard.placePiece(new BoardPosition(4,4), 'x');
        testBoard.placePiece(new BoardPosition(5,5), 'o');

        BoardPosition startingPos = new BoardPosition(4,4);
        DirectionEnum dir = DirectionEnum.SE;
        BoardPosition obsPos = testBoard.jumpPiece(startingPos, dir);

        assertEquals(new BoardPosition(6,6), obsPos);
    }

    @Test
    public void test_jumpPiece_SWjump()
    {
        ICheckerBoard testBoard = makeBoard();

        //Empty the board
        for (int row = 0; row < testBoard.getRowNum(); row++) {
            for (int col = 0; col < testBoard.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    testBoard.placePiece(new BoardPosition(row, col), CheckerBoardMem.EMPTY_POS);
                }
            }
        }

        //Manually place the pieces
        testBoard.placePiece(new BoardPosition(2,2), 'x');
        testBoard.placePiece(new BoardPosition(3,1), 'o');

        BoardPosition startingPos = new BoardPosition(2,2);
        DirectionEnum dir = DirectionEnum.SW;
        BoardPosition obsPos = testBoard.jumpPiece(startingPos, dir);

        assertEquals(new BoardPosition(4,0), obsPos);
    }

    @Test
    public void test_jumpPiece_jumpBlankTile()
    {
        ICheckerBoard board = makeBoard();
        HashMap<Character, Integer> playerPieceCount = board.getPieceCounts();

        // Empty the board
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    BoardPosition coordinate = new BoardPosition(row,col);
                    board.placePiece(coordinate, CheckerBoardMem.EMPTY_POS);
                }
            }
        }
        playerPieceCount.put(CheckerBoardMem.PLAYER_ONE, playerPieceCount.get(CheckerBoard.PLAYER_ONE) - 11);
        playerPieceCount.put(CheckerBoardMem.PLAYER_TWO, playerPieceCount.get(CheckerBoard.PLAYER_TWO) - 12);

        // Manually place the 'x' piece
        board.placePiece(new BoardPosition(3, 3), 'x');

        // Define starting position and jump direction
        BoardPosition startingPos = new BoardPosition(3, 3);
        DirectionEnum dir = DirectionEnum.SE;

        // Attempt to jump in SE direction
        board.jumpPiece(startingPos, dir);

        // Getting the pieceCount of x and o
        HashMap<Character, Integer> boardPieceCount = board.getPieceCounts();
        int xNum = boardPieceCount.get('x');
        int oNum = boardPieceCount.get('o');

        // Check board to remain same
        assertEquals(1, xNum);
        assertEquals(0, oNum);
    }

    @Test
    public void test_scanSurroundingPositions_validMoves() {
        ICheckerBoard board = makeBoard();

        // Empty the board
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        //Define starting position
        BoardPosition startingPos = new BoardPosition(3, 3);

        //Manually add
        board.placePiece(new BoardPosition(2, 2), 'x');
        board.placePiece(new BoardPosition(1, 1), 'o');

        HashMap<DirectionEnum, Character> surroundingPos = board.scanSurroundingPositions(startingPos);

        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.NW, 'x');
        expectedSurroundingPos.put(DirectionEnum.SE, ' ');
        expectedSurroundingPos.put(DirectionEnum.SW, ' ');
        expectedSurroundingPos.put(DirectionEnum.NE, ' ');

        assertEquals(expectedSurroundingPos, surroundingPos);
    }

    @Test
    public void test_scanSurroundingPositions_oSurroundings() {
        ICheckerBoard board = makeBoard();

        // Empty the board
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        // Manually add pieces to the board
        board.placePiece(new BoardPosition(4, 4), 'o');  // 'o' at (4,4)
        board.placePiece(new BoardPosition(3, 5), 'x');  // 'x' at (3,5)
        board.placePiece(new BoardPosition(5, 3), 'x');  // 'x' at (5,3)
        board.placePiece(new BoardPosition(3, 3), 'x');  // 'x' at (3,3)

        // Define the starting position
        BoardPosition startingPos = new BoardPosition(4, 4);

        // Scan the surrounding positions
        HashMap<DirectionEnum, Character> surroundingPos = board.scanSurroundingPositions(startingPos);

        // Expected positions and values
        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.NW, 'x');
        expectedSurroundingPos.put(DirectionEnum.SW, 'x');
        expectedSurroundingPos.put(DirectionEnum.SE, ' ');
        expectedSurroundingPos.put(DirectionEnum.NE, 'x');

        // Assert that the surrounding positions match the expected ones
        assertEquals(expectedSurroundingPos, surroundingPos);
    }

    @Test
    public void test_scanSurroundingPositions_noValidMove() {
        ICheckerBoard board = makeBoard();

        // Empty the board
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                if ((row + col) % 2 == 0) {
                    board.placePiece(new BoardPosition(row, col), CheckerBoard.EMPTY_POS);
                }
            }
        }

        // Manually add one piece to the board at (0, 0)
        board.placePiece(new BoardPosition(0, 0), 'x');

        BoardPosition startingPosition = new BoardPosition(0, 0);

        //Assuming board has one 'x' at (0,0)
        HashMap<DirectionEnum, Character> surroundingPositions = board.scanSurroundingPositions(startingPosition);

        // Expected positions and values
        HashMap<DirectionEnum, Character> expectedSurroundingPos = new HashMap<>();
        expectedSurroundingPos.put(DirectionEnum.SE, ' ');

        assertEquals(expectedSurroundingPos, surroundingPositions);
    }

    @Test
    public void test_getDirection_invalidDirection() {
        ICheckerBoard board = makeBoard();
        int expRow = 1;
        int expCol = 1;
        DirectionEnum dir = DirectionEnum.SE;
        BoardPosition result = ICheckerBoard.getDirection(dir);

        assertEquals(expRow, result.getRow());
        assertEquals(expCol, result.getColumn());
    }

    private String toStringForTest(char[][] array) {
        StringBuilder arrayString = new StringBuilder("|  ");
        for (int i = 0; i < array.length; i++) {
            arrayString.append("| ").append(i);
        }
        arrayString.append("|");
        for (int i = 0; i < array.length; i++) {
            arrayString.append("\n|").append(i).append(" |");
            for (int j = 0; j < array[i].length; j++) {
                arrayString.append(array[i][j]).append(" |");
            }
        }
        return arrayString.toString();
    }

}

