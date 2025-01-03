package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;
import cpsc2150.extendedCheckers.views.CheckersFE;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckerBoard extends AbsCheckerBoard
{
    /**
     * @invariant [board dimensions are even and minimum 8 x 8] AND [all boards indices have either
     * PLAYER_ONE, PLAYER_TWO, crowned PLAYER_ONE, crowned PLAYER_TWO, '*', OR ' ' ]
     * AND 0 <= pieceCount.get(PLAYER_ONE) <= [(Total rows / 2 - 1) * (Total columns / 2)]
     * AND 0 <= pieceCount.get(PLAYER_TWO) <= [(Total rows / 2 - 1) * (Total columns / 2)]
     * AND [viableDirections has PLAYER_ONE map to an ArrayList with SE and SW AND PLAYER_ONE map
     * to an ArrayList with NE and NW]
     *
     * @corresponds
     *      self: board
     *      numberOfPieces: pieceCount
     *      directions: getViableDirections
     */

    /**
     * A 2D array of characters used to represent our checkerboard.
     */
    private char[][] board;

    /**
     * A HashMap, with a Character key and an Integer value, that is used to map a player's char to the number of
     * tokens that player still has left on the board.
     */
    private HashMap<Character, Integer> pieceCount;

    /**
     * A HashMap, with a Character key and an ArrayList of DirectionEnums value, used to map a player (and its king
     * representation) to the directions that player can viably move in. A non-crowned (standard) piece can only move
     * in the diagonal directions away from its starting position. A crowned piece can move in the same directions the
     * standard piece can move in plus the opposite directions the standard piece can move in.
     */
    private HashMap<Character, ArrayList<DirectionEnum>> viableDirections;
    public static final char PLAYER_ONE = CheckersFE.getPlayerOne();
    public static final char PLAYER_TWO = CheckersFE.getPlayerTwo();

    /**
     * Standard checkers rows and columns. The rows and columns must match in size
     */
    public final int ROW_NUM;
    public final int COL_NUM;

    /**
     * A constructor for CheckerBoard, initializes instance variables
     *
     * @pre none
     * @post pieceCount.put(PLAYER_ONE, startingCount) AND pieceCount.put(PLAYER_TWO, startingCount)
     * AND [viableDirections has PLAYER_ONE map to an ArrayList with SE and SW AND PLAYER_TWO map
     * to an ArrayList with NE and NW] AND [initializes all indices in board, PLAYER_ONE at the top,
     * PLAYER_TWO at the bottom, '*' for non-playable, ' ' for open space
     */
    public CheckerBoard(int aDimensions) {
        ROW_NUM = aDimensions;
        COL_NUM = aDimensions;

        //Adding all pieces, blank spaces, and black tiles into the board
        board = new char[ROW_NUM][COL_NUM];
        for (int row = 0; row < ROW_NUM; row++) {
            for (int col = 0; col < COL_NUM; col++) {
                if ((row + col) % 2 == 1) {
                    board[row][col] = BLACK_TILE;
                }
                if ((row + col) % 2 == 0) {
                    if (row < (ROW_NUM / 2 - 1)) {
                        board[row][col] = PLAYER_ONE;
                    }
                    else if (row > (ROW_NUM / 2)) {
                        board[row][col] = PLAYER_TWO;
                    }
                    else {
                        board[row][col] = EMPTY_POS;
                    }
                }
            }
        }

        //Initializing pieceCount contain player one and two's number of pieces
        pieceCount = new HashMap<>();
        int totalPieces = (((ROW_NUM / 2) - 1) * (COL_NUM / 2));
        pieceCount.put(PLAYER_ONE, totalPieces);
        pieceCount.put(PLAYER_TWO, totalPieces);

        //Initializing viable directions for player one and two
        viableDirections = new HashMap<>();
        ArrayList<DirectionEnum> player1Directions = new ArrayList<>();
        ArrayList<DirectionEnum> player2Directions = new ArrayList<>();

        ArrayList<DirectionEnum> allDirections = new ArrayList<>();

        player1Directions.add(DirectionEnum.SE);
        player1Directions.add(DirectionEnum.SW);
        player2Directions.add(DirectionEnum.NE);
        player2Directions.add(DirectionEnum.NW);

        allDirections.add(DirectionEnum.SE);
        allDirections.add(DirectionEnum.SW);
        allDirections.add(DirectionEnum.NE);
        allDirections.add(DirectionEnum.NW);

        viableDirections.put(Character.toUpperCase(PLAYER_ONE), allDirections);
        viableDirections.put(Character.toUpperCase(PLAYER_TWO), allDirections);
        viableDirections.put(PLAYER_ONE, player1Directions);
        viableDirections.put(PLAYER_TWO, player2Directions);
    }

    /**
     * Standard getter for ROW_NUM
     * @pre none
     * @post getViableDirections = viableDirections AND viableDirections = #viableDirections
     * AND board = #board AND pieceCount = #pieceCount
     */
    public int getRowNum() {
        return ROW_NUM;
    }

    /**
     * Standard getter for COL_NUM
     * @pre none
     * @post getViableDirections = viableDirections AND viableDirections = #viableDirections
     * AND board = #board AND pieceCount = #pieceCount
     */
    public int getColNum() {
        return COL_NUM;
    }

    /**
     * Standard getter for viableDirections
     *
     * @return viableDirections, HashMap<Character, ArrayList<DirectionEnum>>
     * @pre none
     * @post getViableDirections = viableDirections AND viableDirections = #viableDirections
     * AND board = #board AND pieceCount = #pieceCount
     */
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections() {
        return viableDirections;
    }

    /**
     * Standard getter for pieceCounts
     *
     * @return pieceCounts, HashMap<Character, Integer>
     * @pre none
     * @post getPieceCounts = pieceCounts AND pieceCounts = #pieceCounts AND board = #board
     * AND viableDirections = #viableDirections
     */
    public HashMap<Character, Integer> getPieceCounts() {
        return pieceCount;
    }

    /**
     * Setter for one given index in board 2D array
     *
     * @param pos BoardPosition object with variables int row and int col that are used as the given index
     * @param player char that the given index will be set to
     *
     * @pre [pos is valid within board]
     * @post board[pos.getRow()][pos.getCol()] = player AND pieceCount = #pieceCount
     * AND viableDirections = #viableDirections
     */
    public void placePiece(BoardPosition pos, char player) {
        int intendedRow = pos.getRow();
        int intendedColumn = pos.getColumn();
        board[intendedRow][intendedColumn] = player;
    }

    /**
     * Getter for a single index in 2D array board
     *
     * @param pos BoardPosition object with variables int row and int col used as the board index
     * @return char found at pos in board
     *
     * @pre [pos is valid within board]
     * @post whatAtPos = board[pos.getRow()][board[pos.getCol()] AND board = #board AND pieceCount = #pieceCount
     * AND viableDirections = #viableDirections
     */
    public char whatsAtPos(BoardPosition pos) {
        int intendedRow = pos.getRow();
        int intendedCol = pos.getColumn();
        return board[intendedRow][intendedCol];
    }
}