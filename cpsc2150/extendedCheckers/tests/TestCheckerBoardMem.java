package cpsc2150.extendedCheckers.tests;

import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoardMem;
import cpsc2150.extendedCheckers.models.ICheckerBoard;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestCheckerBoardMem {
    private ICheckerBoard makeBoard() {
        return new CheckerBoardMem(8);
    }


    @Test
    public void test_CheckerBoard_Constructor() {
        ICheckerBoard obsCb = makeBoard();
        String obs = obsCb.toString();
        Map<Character, List<BoardPosition>> expCb = new HashMap<>();
        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7)
        ));
        expCb.put(Character.toUpperCase(CheckerBoardMem.PLAYER_ONE), List.of());
        expCb.put(Character.toUpperCase(CheckerBoardMem.PLAYER_TWO), List.of());

        String exp = toStringForTest(expCb, 8);

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

        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6), new BoardPosition(3,3)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7)
        ));
        String exp = toStringForTest(expCb, 8);
        assertEquals(exp, obs);

    }

    @Test
    public void test_placePiece_CornerTile_replaceX_withO() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(7, 7);
        char inputPlayer = CheckerBoardMem.PLAYER_ONE;

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();
        Map<Character, List<BoardPosition>> expCb = new HashMap<>();
        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6), new BoardPosition(7,7)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5)));

        String exp = toStringForTest(expCb, 8);
        assertEquals(exp, obs);
    }

    @Test
    public void test_placePiece_WhiteTile() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(3, 0);
        char inputPlayer = CheckerBoardMem.PLAYER_ONE;

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();

        Map<Character, List<BoardPosition>> expCb = new HashMap<>();
        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6), new BoardPosition(3,0)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7)
        ));

        String exp = toStringForTest(expCb, 8);
        assertEquals(exp, obs);
    }

    @Test
    public void test_placePiece_MiddleTile_replaceX_withO() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(2, 4);
        char inputPlayer = CheckerBoardMem.PLAYER_TWO;

        obsCb.placePiece(input, inputPlayer);

        String obs= obsCb.toString();

        Map<Character, List<BoardPosition>> expCb = new HashMap<>();
        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 6)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7), new BoardPosition (2,4)
        ));

        String exp = toStringForTest(expCb, 8);
        assertEquals(exp, obs);
    }

    @Test
    public void test_placePiece_CornerTile_replacex_withX() {
        ICheckerBoard obsCb = makeBoard();
        BoardPosition input = new BoardPosition(0, 0);
        char inputPlayer = Character.toUpperCase(CheckerBoardMem.PLAYER_ONE);

        obsCb.placePiece(input, inputPlayer);

        String obs = obsCb.toString();

        Map<Character, List<BoardPosition>> expCb = new HashMap<>();
        expCb.put(inputPlayer, List.of(new BoardPosition(0,0)));
        expCb.put(CheckerBoardMem.PLAYER_ONE, List.of(new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6)));
        expCb.put(CheckerBoardMem.PLAYER_TWO, List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7)));

        String exp = toStringForTest(expCb, 8);
        assertEquals(exp, obs);
    }

    //ToString function for expected boards
    private String toStringForTest(Map<Character, List<BoardPosition>> memBoard, int size) {
        StringBuilder mapString = new StringBuilder("|  ");
        for (int i = 0; i < size; i++) {
            mapString.append("| ").append(i);
        }
        mapString.append("|");
        for (int i = 0; i < size; i++) {
            mapString.append("\n|").append(i).append(" |");
            for (int j = 0; j < size; j++) {
                BoardPosition pos = new BoardPosition(i,j);
                char positionPiece = whatsAtTestPos(memBoard, pos);
                mapString.append(positionPiece).append(" |");
            }
        }
        return mapString.toString();
    }

    //Helper function to get the position character in Map<Character, List<BoardPosition>>
    public char whatsAtTestPos(Map<Character, List<BoardPosition>> memBoard, BoardPosition pos) {
        for (Character player : memBoard.keySet()) {
            List<BoardPosition> positions = memBoard.get(player);
            for (BoardPosition currentPos : positions) {
                if (currentPos.equals(pos)) {
                    return player;
                }
            }
        }
        return '\0';
    }
}

