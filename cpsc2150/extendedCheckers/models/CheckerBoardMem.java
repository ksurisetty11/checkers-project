package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;
import cpsc2150.extendedCheckers.views.CheckersFE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerBoardMem extends AbsCheckerBoard {

    /**
     *Tracks the number of pieces for each player 
     *Key: Player character PLAYER_ONE,[crowned PLAYER_ONE], PLAYER_TWO, [crowned PLAYER_TWO]
     *Value: Number of pieces on the board 
     */
    private HashMap<Character, Integer> pieceCount;

    /**
     *Tracks the viable move directions for each player
     *Key: Player character PLAYER_ONE,[crowned PLAYER_ONE], PLAYER_TWO, [crowned PLAYER_TWO]
     *Value: List of allowed move directions
     */
    private HashMap<Character, ArrayList<DirectionEnum>> viableDirections;

    public static final char PLAYER_ONE = CheckersFE.getPlayerOne();
    public static final char PLAYER_TWO = CheckersFE.getPlayerTwo();

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
     *Key: Player character PLAYER_ONE,[crowned PLAYER_ONE], PLAYER_TWO, [crowned PLAYER_TWO]
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

        char playerOneChar = PLAYER_ONE;
        char playerTwoChar = PLAYER_TWO;

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
        ArrayList<DirectionEnum> player1Positions = new ArrayList<>();
        ArrayList<DirectionEnum> player2Positions = new ArrayList<>();

        ArrayList<DirectionEnum> allDirections = new ArrayList<>();

        player1Positions.add(DirectionEnum.SE);
        player1Positions.add(DirectionEnum.SW);
        player2Positions.add(DirectionEnum.NE);
        player2Positions.add(DirectionEnum.NW);

        allDirections.add(DirectionEnum.SE);
        allDirections.add(DirectionEnum.SW);
        allDirections.add(DirectionEnum.NE);
        allDirections.add(DirectionEnum.NW);

        viableDirections.put(Character.toUpperCase(PLAYER_ONE), allDirections);
        viableDirections.put(Character.toUpperCase(PLAYER_TWO), allDirections);
        viableDirections.put(PLAYER_ONE, player1Positions);
        viableDirections.put(PLAYER_TWO, player2Positions);
    }

    /**
     * Returns the number of rows on the board
     *
     * @return the number of rows
     * @post getRowNum() == ROW_NUM AND viableDirections = #viableDirections
     * AND memBoard = #memBoard AND pieceCount = #pieceCount
     */
    public int getRowNum() {
        return ROW_NUM;
    }

    /**
     * Returns the number of rows on the board
     *
     * @return the number of rows
     * @post getColNum() == COL_NUM AND viableDirections = #viableDirections
     * AND memBoard = #memBoard AND pieceCount = #pieceCount
     */
    public int getColNum() {
        return COL_NUM;
    }

    /**
     * Returns the viable directions for each player
     * @return a map of player characters to their allowed move directions
     * @post getViableDirections() == viableDirections AND viableDirections = #viableDirections
     * AND memBoard = #memBoard AND pieceCount = #pieceCount
     */
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections() {
        return viableDirections;
    }

    /**
     * Returns the current piece counts for each player
     * @return a map of player characters to their piece counts 
     * @post getPieceCounts() == pieceCount AND viableDirections = #viableDirections
     * AND memBoard = #memBoard AND pieceCount = #pieceCount
     */
    public HashMap<Character, Integer> getPieceCounts() {
        return pieceCount;
    }

    /**
     * Places a piece on the board for a given player
     * @param pos the position to place the piece 
     * @param player the player char to be placed in memBoard
     * @pre memBoard.containsKey(player) && pos != null
     * @post memBoard = memBoard.put(player,pos) AND viableDirections =#viableDirections
     * AND pieceCount = #pieceCount
     */
    public void placePiece(BoardPosition pos, char player) {
        //Look at all the positions on memBoard to check if the position is occupied
        for (Map.Entry<Character, List<BoardPosition>> allPositions: memBoard.entrySet()) {
            char positionPiece = allPositions.getKey();
            List<BoardPosition> currPlayerPositions  = allPositions.getValue();

            //Going through the current players location to determine if pos is already occupied
            for (int i = 0; i < currPlayerPositions.size(); i++) {
                if (currPlayerPositions.get(i).equals(pos)) {
                    //If there is a piece at pos, remove it to eventually replace it with player
                    currPlayerPositions.remove(i);
                    break;
                }
            }
            memBoard.put(positionPiece, currPlayerPositions);
        }

        //if player isn't ' ' add the piece to the board
        if (player != ' ') {
            List<BoardPosition> locationToPlace = memBoard.get(player);
            locationToPlace.add(pos);
            memBoard.put(player, locationToPlace);
        }
    }

    /**
     * Checks what is at a given position on the board
     * @param pos the position to check 
     * @return the character of the player at the position, '*' if row + col % 2 == 1 or ' '
     * @pre pos != null
     * @post whatsAtPos(pos) == the character at pos or [black tile] or [empty position] AND
     * viableDirections = #viableDirections AND memBoard = #memBoard AND pieceCount = #pieceCount
     */
    public char whatsAtPos(BoardPosition pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        for (Character player : memBoard.keySet()) {
            List<BoardPosition> positions = memBoard.get(player);
            for (BoardPosition currentPos : positions) {
                if (currentPos.equals(pos)) {
                    return player;
                }
            }
        }
        if ((col % 2 == 0 && row % 2 == 1) || (col % 2 == 1 && row % 2 == 0)) {
            return '*';
        }
        return ' ';
    }
}