package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckerBoard extends AbsCheckerBoard
{
    /**
     * @invariant [board has 8 rows and 8 col] AND [all boards indices have either 'x', 'o', 'X', 'O', '*', OR ' ']
     * AND 0 <= pieceCount.get('x') <= 12 AND 0 <= pieceCount.get('o') <= 12
     * AND [viableDirections has 'x' map to an ArrayList with SE and SW AND 'o' map to an ArrayList with NE and NW]
     * /

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
     * representation) to the directions that player can viably move in. A non-kinged (standard) piece can only move
     * in the diagonal directions away from its starting position. A kinged piece can move in the same directions the
     * standard piece can move in plus the opposite directions the standard piece can move in.
     */
    private HashMap<Character, ArrayList<DirectionEnum>> viableDirections;
    public static final char PLAYER_ONE = 'x';
    public static final char PLAYER_TWO = 'o';
    public static final char EMPTY_POS = ' ';
    public static final char BLACK_TILE = '*';

    /*
    Standard Checkers starts with 8 rows and 8 columns. Both players begin with 12 pieces each.
     */
    public static final int ROW_NUM = 8;
    public static final int COL_NUM = 8;
    public static final int STARTING_COUNT = 12;

    /**
     * A constructor for CheckerBoard, initializes instance variables
     *
     * @pre none
     * @post pieceCount.put('x', 12) AND pieceCount.put('o', 12)
     * AND [viableDirections has 'x' map to an ArrayList with SE and SW AND 'o' map to an ArrayList with NE and NW]
     * AND [initializes all indices in board, 'x' at the top, 'o' at the bottom, '*' for non-playable, ' 'for open space
     */
    public CheckerBoard() {
        board = new char[][] {
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', 'x', '*', 'x', '*', 'x', '*', 'x'},
                {'x', '*', 'x', '*', 'x', '*', 'x', '*'},
                {'*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'},
                {'o', '*', 'o', '*', 'o', '*', 'o', '*'},
                {'*', 'o', '*', 'o', '*', 'o', '*', 'o'}};

        //Initializing pieceCount contain player one and two's number of pieces
        pieceCount = new HashMap<>();
        pieceCount.put(PLAYER_ONE, STARTING_COUNT);
        pieceCount.put(PLAYER_TWO, STARTING_COUNT);

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
     * @post board[pos.getRow()][pos.getCol()] = player AND pieceCount = [#pieceCount IFF pos is empty,
     * else the player losing a piece]
     * AND viableDirections = #viableDirections
     */
    public void placePiece(BoardPosition pos, char player) {
        int intendedRow = pos.getRow();
        int intendedColumn = pos.getColumn();

        char piece = whatsAtPos(pos);

        board[intendedRow][intendedColumn] = player;

        HashMap<Character, Integer> playerPieceCount = getPieceCounts();

        if(piece != ' ') {
            int currentPieceCount = playerPieceCount.getOrDefault(piece, 0);
            playerPieceCount.put(piece, currentPieceCount - 1);
        }

        if(player != ' '){
            int newPieceCount = playerPieceCount.getOrDefault(player, 0);
            playerPieceCount.put(player, newPieceCount + 1);
        }
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