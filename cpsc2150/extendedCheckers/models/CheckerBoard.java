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
     * AND [initializes all indices in board, 'x' at the top, 'o' at the bottom, '*' for non-playable, ' ' for open space
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

        xPlayerDirections.add(DirectionEnum.SE);
        xPlayerDirections.add(DirectionEnum.SW);
        oPlayerDirections.add(DirectionEnum.NE);
        oPlayerDirections.add(DirectionEnum.NW);

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
        /*
        Simple accessor for the viableDirections HashMap.
         */
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
        /*
        Simple accessor for the getPieceCounts HashMap
         */
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
        /*
        A "mutator" for the board 2D array. This should be used for setting a given 2D index within the board 2D array,
         given by the row and col of the parameter BoardPosition, equal to the char given by player.
         */
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
        /*
        an "accessor" for the board 2D array. Returns what is at the position given by the row and col of the BoardPosition
        parameter.
         */
    }

    /**
     * Determines if a player has won
     *
     * @param player Character representing one of the two players
     * @return true if player won, boolean
     *
     * @pre [player equals 'x' OR 'o']
     * @post checkPlayerWin = [true IFF all remaining pieces belong to player, false OW]
     * AND board = #board AND pieceCount = #pieceCount AND viableDirections = #viableDirections
     */
    public boolean checkPlayerWin(Character player) {
        /*
        returns true or false if a player, designated by the player parameter, has won the game of checkers. A player
        has won if all remaining pieces on the board belong to the player.
         */
    }

    /**
     * Crowns a chosen piece by changing the char to its uppercase equivalent
     *
     * @param posOfPlayer BoardPosition object with int row and int col used as the board index
     *
     * @pre [posOfPlayer is valid] AND [posOfPlayer piece is not crowned yet]
     * @post [posOfPlayer = The uppercase equivalent of the car] AND board = #board AND
     * pieceCount = #pieceCount AND viableDirections = #viableDirections
     */
    public void crownPiece(BoardPosition posOfPlayer) {
        /*
        "crowns" a piece by converting the char at the position on the board, given by the posOfPlayer parameter, to an
        uppercase equivalent of the char.
         */
    }

    /**
     * Moves a piece on the board in the direction determined by DirectionEnum dir
     *
     * @param startingPos BoardPosition object with int row and int col used as the board index
     * @param dir DirectionEnum containing the directions that the piece can move (NE, NW, SE, SW)
     * @return BoardPosition object that resides in the new position designated by direction dir
     *
     * @pre [startingPos is within the bounds of the board] AND [startingPos is an available piece that
     * can be moved] AND [The desired new position is empty]
     * @post startingPos = null AND board = [board containing the coordinates of the moved piece] AND
     * pieceCount = #pieceCount AND viableDirections = #viableDirections
     */
    public BoardPosition movePiece(BoardPosition startingPos, DirectionEnum dir) {
        /*
        A modified version of placePiece moves a piece on the board, located at the startingPos index, in the direction
        designated by the DirectionEnum dir. Remember, not only should a piece now appear at the moved location, but the
        position the piece moved from should now be empty.
         */
    }

    /**
     * Player's piece is moved on the board by jumping over the opposing players piece
     *
     * @param startingPos BoardPosition object with int row and int col used as the board index
     * @param dir DirectionEnum containing the directions that the piece can move (NE, NW, SE, SW)
     * @return BoardPosition object that resides in the position two positions away from startingPos in direction dir
     *
     * @pre [statingPos isn't empty] AND [startingPos is within the bounds of the board] AND
     * [startingPos is an available piece that can be moved] AND [The new position is empty] AND [The opponent
     * piece occupies the position that will be "jumped"]
     * @post jumpPiece = [startingPos moving two positions in direction dir and removing the opposing player's
     * piece] AND [startingPos = null] AND [opponent piece position is null] AND board = [board containing
     * the coordinates of the moved piece] AND pieceCount = [The opposing player losing a piece] AND
     * viableDirections = #viableDirections
     */
    public BoardPosition jumpPiece(BoardPosition startingPos, DirectionEnum dir) {
        /*
        A modified version of movePiece that moves a piece by "jumping" an opponent player piece. When a player "jumps"
        an opponent, that player should move two positions in the direction passed in by dir parameter. Remember, not
        only should the piece now appear at the moved location, and the starting position should not be empty, but the
        position that was "jumped" should now also be empty.

        Furthermore, this method should remove 1 from the opponent's pieceCount.
         */
    }

    /**
     * Scans and returns what is around the startingPos piece as a Hashmap
     *
     * @param startingPos BoardPosition object with int row and int col used as the board index
     * @return Hashmap<DirectionEnum, Character> containing the neighboring positions and whether
     * the positions are occupied
     *
     * @pre [startingPos isn't empty] AND [startingPos is within the bounds of the board]
     * @post scanSurroundingPositions = [Hashmap that contains whether the positions around startingPos
     * are empty or contain a player] AND board = #board AND pieceCount = #pieceCount
     * AND viableDirections = #viableDirections
     */
    public HashMap<DirectionEnum, Character> scanSurroundingPositions(BoardPosition startingPos) {
        /*
        "Scans" the indices surrounding the index given by the startingPos parameter. There are a few different ways
        we can use this method, so I won't specify any given one in this description. Still, know that this function
        should return a HashMap mapping the four DirectionEnums to the char located at that position in the respective
        direction.

        For example, the position DirectionEnum.SE of startingPos (2,2) is position (3,3). If position (3,3) is empty,
        this function would return a HashMap where DirectionEnum.SE is mapped to ' '. If position (3,3) contained a
        player, it would map DirectionEnum.SE to the char that represents that player.
         */
    }

    /**
     * Returns a new boardPosition that represents movement in the direction of dir
     *
     * @param dir DirectionEnum containing the directions that the piece can move (NE, NW, SE, SW)
     * @return BoardPosition object that represents a piece moving in direction dir by 1
     *
     * @pre [dir must be a valid direction] AND [A piece can move in the direction dir]
     * @post getDirection = [BoardPosition object that represents a piece moving in dir direction by 1
     * row/column]
     */
    public static BoardPosition getDirection(DirectionEnum dir) {
        /*
        a STATIC function that we can use to return a new BoardPosition that represents movement in the direction given
        by the dir parameter.

        For example, to move a piece DirectionEnum.SE we would have to add 1 to that piece's row position and 1 to that
        pieces column position. As such, if DirectionEnum.SE is passed in as a parameter, this function should return a
        new BoardPosition that could be added to any given piece's position so that the piece now sits one position SE
        of where it began.
         */
    }

    /**
     * String representation of a 8x8 checkerboard
     *
     * @return string that represents the checkerboard, coordinate system, and pieces in a 8x8 grid
     *
     * @pre [all BoardPosition objects are initialized]
     * @post toString = string [that represents a checkerboard that contains the players
     * pieces and positions in addition to the column and row numbers] AND board = #board AND
     * pieceCount = #pieceCount AND viableDirections = #viableDirections
     */
    public String toString()
    {
        /*
        returns a String representation of the checkerboard with all the pieces on it and their current positions. there
        should be a "header" line to display all the column numbers and a "header column" that displays all the row
        numbers. In essence, it should look like this:

        |  | 0| 1| 2| 3| 4| 5| 6| 7|
        |0 |x |* |x |* |x |* |x |* |
        |1 |* |x |* |x |* |x |* |x |
        |2 |x |* |x |* |x |* |x |* |
        |3 |* |  |* |  |* |  |* |  |
        |4 |  |* |  |* |  |* |  |* |
        |5 |* |o |* |o |* |o |* |o |
        |6 |o |* |o |* |o |* |o |* |
        |7 |* |o |* |o |* |o |* |o |

        THIS FUNCTION DOES NOT PRINT TO THE CONSOLE OR MAKE ANY KIND OF SYSTEM.OUT.PRINTLN CALLS
         */
    }
}