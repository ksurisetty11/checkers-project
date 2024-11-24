package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerBoardMem extends AbsCheckerBoard {

    /**
     *Tracks the number of pieces for each player 
     *Key: Player character 'X', 'x', 'O', 'o'
     *Value: Number of pieces on the board 
     */
    private HashMap<Character, Integer> pieceCount;

    /**
     *Tracks the viable move directions for each player
     *Key: Player character 'X', 'x', 'O', 'o'
     *Value: List of allowed move directions
     */
    private HashMap<Character, ArrayList<DirectionEnum>> viableDirections;

    /**
     * Number of rows on the board
     */
    private final int ROW_NUM;

    /**
     * Number of columns on the board
     */
    private final int COL_NUM;

    /**
     *Tracks the state of the board
     *Key: Player character 'X', 'x', 'O', 'o'
     *Value: List of positions occupied by pieces
     */
    private Map<Character, List<BoardPosition>> memBoard;

    /**
     * Constructor to initialize a checkerboard with the given dimensions
     * @param aDimensions the size of the board (even and positive)
     * @pre aDimensions > 0 && aDimensions % 2 == 0
     * @post ROW_NUM == aDimensions && COL_NUM == aDimensions &&
     *                  memBoard initialized with pieces in starting positions && pieceCount
     *                  updated with total pieces for each player && viableDirections initialized for all players
     */
    public CheckerBoardMem(int aDimensions) {
        ROW_NUM = aDimensions;
        COL_NUM = aDimensions;

        memBoard = new HashMap<>();

        char playerOneChar = 'x';
        char playerTwoChar = 'o';

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
                    memBoard.get(playerTwoChar).add(new BoardPosition(row,col));
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

    /**
     * Returns the viable directions for each player
     * @return a map of player characters to their allowed move directions
     * @post getViableDirections() == viableDirections
     */
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections() {
        return viableDirections;
    }

    /**
     * Returns the current piece counts for each player
     * @return a map of player characters to their piece counts 
     * @post getPieceCounts() == pieceCount
     */
    public HashMap<Character, Integer> getPieceCounts() {
        return pieceCount;
    }

    /**
     * Returns the number of rows on the board
     * 
     * @return the number of rows
     * @post getRowNum() == ROW_NUM
     */
    public int getRowNum() {
        return ROW_NUM;
    }

    /**
     * Returns the number of rows on the board
     * 
     * @return the number of rows
     * @post getColNum() == COL_NUM
     */
    public int getColNum() {
        return COL_NUM;
    }

    /**
     * Places a piece on the board for a given player
     * @param pos the position to place the piece 
     * @param player the player characters 'X', 'x', 'O', 'o'
     * @pre memBoard.containsKey(player) && pos != null
     * @post memBoard.get(player).contains(pos)
     */
    //assuming the player char is one that is already in the map
    public void placePiece(BoardPosition pos, char player) {
        memBoard.get(player).add(pos);
    }

    /**
     * Checks what is at a given position on the board
     * @param pos the position to check 
     * @return the character of the player at teh position, or '\0' if empty
     * @pre pos != null
     * @post whatsAtPos(pos) == the character at pos or 0 
     */
    //assuming pos is in memBoard
    public char whatsAtPos(BoardPosition pos) {
        for(Character player : memBoard.keySet()) {
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