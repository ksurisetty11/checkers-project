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
        expCb.put('x', List.of(new BoardPosition(0, 0),
                new BoardPosition(0, 2), new BoardPosition(0, 4),
                new BoardPosition(0, 6), new BoardPosition(1, 1),
                new BoardPosition(1, 3), new BoardPosition(1, 5),
                new BoardPosition(1, 7), new BoardPosition(2, 0),
                new BoardPosition(2, 2), new BoardPosition(2, 4),
                new BoardPosition(2, 6)));
        expCb.put('o', List.of(new BoardPosition(5, 1),
                new BoardPosition(5, 3), new BoardPosition(5, 5),
                new BoardPosition(5, 7), new BoardPosition(6, 0),
                new BoardPosition(6, 2), new BoardPosition(6, 4),
                new BoardPosition(6, 6), new BoardPosition(7, 1),
                new BoardPosition(7, 3), new BoardPosition(7, 5),
                new BoardPosition(7, 7)
        ));
        expCb.put('X', List.of());
        expCb.put('O', List.of());

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

