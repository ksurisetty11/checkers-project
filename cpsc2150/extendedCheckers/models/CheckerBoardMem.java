package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerBoardMem extends AbsCheckerBoard {

    /**
     *
     */
    private HashMap<Character, Integer> pieceCount;

    /**
     *
     */
    private HashMap<Character, ArrayList<DirectionEnum>> viableDirections;

    /**
     * row and col
     */
    private final int ROW_NUM;
    private final int COL_NUM;

    /**
     *
     */
    private Map<Character, List<BoardPosition>> memBoard;

    public CheckerBoardMem(int aDimensions) {
        ROW_NUM = aDimensions;
        COL_NUM = aDimensions;

        memBoard = new HashMap<>();

        char playerOneChar = getPlayerOne();
        char playerTwoChar = getPlayerTwo();

        char crownedPlayerOne = Character.toUpperCase(playerOneChar);
        char crownedPlayerTwo = Character.toUpperCase(playerTwoChar);

        memBoard.put(playerOneChar, new ArrayList<>());
        memBoard.put(playerTwoChar, new ArrayList<>());
        memBoard.put(crownedPlayerOne, new ArrayList<>());
        memBoard.put(crownedPlayerTwo, new ArrayList<>());

        for (int row = 0; row < ROW_NUM; row++) {
            for (int col = 0; col < COL_NUM; col++) {
                BoardPosition pos = new BoardPosition(row,col);
                boolean blackTileLocation = (pos.getColumn() % 2 == 0
                        && pos.getRow() % 2 == 1) || (pos.getColumn() % 2 == 1
                        && pos.getRow() % 2 == 0);

                //If it is a black tile, skip that board coordinate
                if (blackTileLocation) {
                    continue;
                }
                if (row < ROW_NUM / 2 - 1) {
                    memBoard.get(playerOneChar).add(new BoardPosition(row,col));
                }
                if (row > ROW_NUM / 2) {
                    memBoard.get(playerOneChar).add(new BoardPosition(row,col));
                }
            }
        }
        pieceCount = new HashMap<>();
        int totalPieces = (((ROW_NUM / 2) - 1) * (COL_NUM / 2));
        pieceCount.put(playerOneChar, totalPieces);
        pieceCount.put(playerTwoChar, totalPieces);

        //Initializing viable directions for player one and two
        viableDirections = new HashMap<>();
        ArrayList<DirectionEnum> xPlayerDirections = new ArrayList<>();
        ArrayList<DirectionEnum> oPlayerDirections = new ArrayList<>();

        ArrayList<DirectionEnum> allDirections = new ArrayList<>();

        xPlayerDirections.add(DirectionEnum.SE);
        xPlayerDirections.add(DirectionEnum.SW);
        oPlayerDirections.add(DirectionEnum.NE);
        oPlayerDirections.add(DirectionEnum.NW);

        allDirections.add(DirectionEnum.SE);
        allDirections.add(DirectionEnum.SW);
        allDirections.add(DirectionEnum.NE);
        allDirections.add(DirectionEnum.NW);

        viableDirections.put('X', allDirections);
        viableDirections.put('O', allDirections);
        viableDirections.put('x', xPlayerDirections);
        viableDirections.put('o', oPlayerDirections);
    }

    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections() {
        return viableDirections;
    }

    public HashMap<Character, Integer> getPieceCounts() {
        return pieceCount;
    }

    public int getRowNum() {
        return 0;
    }

    public int getColNum() {
        return 0;
    }

    public void placePiece(BoardPosition pos, char player) {

    }

    public char whatsAtPos(BoardPosition pos) {
        return 0;
    }
}


