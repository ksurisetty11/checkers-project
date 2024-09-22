package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckerBoard
{
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
     * A constructor for CheckerBoard, initializes the three instance variables
     *
     * @pre none
     * @post pieceCount.put('x', 12) AND pieceCount.put('o', 12)
     * AND [viableDirections has 'x' map to an ArrayList with SE and SW AND 'o' map to an ArrayList with NE and NW]
     * AND [initializes all indices in board, 'x' at the top, 'o' at the bottom, '*' for non-playable, ' ' for open space
     *
     */
    public CheckerBoard() {
        /*
        Constructor for the CheckerBoard object. The constructor should initialize the three instance variables to
        a new data structure of their respective type. Furthermore, the constructor should use the pieceCount HashMap
        to map the starting count of tokens to each player, and use the viableDirections HashMap to map the players to
        their respective starting directions (SE and SW for player one, NE and NW for player two). Finally, the
        constructor should also initialize all the indices within the checkerboard to either a player char, an asterisk
        (a 'black, non-playable' position), or a space (the 'empty' position)
         */
    }

    /**
     * Standard getter for viableDirections
     *
     * @return viableDirections, HashMap<Character, ArrayList<DirectionEnum>>
     * @pre none
     * @post getViableDirections = viableDirections
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
     * @post getPieceCounts = pieceCounts
     */
    public HashMap<Character, Integer> getPieceCounts() {
        /*
        Simple accessor for the getPieceCounts HashMap
         */
    }

    /**
     * Setter for one given index in board 2D array
     *
     * @param pos BoardPosition object with int row and int col used as the given index
     * @param player char to be set for given index
     *
     * @pre [pos is valid within board] AND [player = 'x', 'X', 'o', OR 'O']
     * @post board[pos.getRow()][pos.getCol()] = player AND pieceCount = #pieceCount
     * AND viableDirection = #viableDirection
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
     * @param pos BoardPosition object with int row and int col used as the board index
     * @return char at pos in board
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
     * @return boolean
     *
     * @pre [player equals 'x' OR 'o']
     * @post checkPlayerWin = [true IFF all remaining pieces belong to player, false OW]
     * AND board = #board AND pieceCount = #pieceCount
     * AND viableDirection = #viableDirection
     */
    public boolean checkPlayerWin(Character player) {
        /*
        returns true or false if a player, designated by the player parameter, has won the game of checkers. A player
        has won if all remaining pieces on the board belong to the player.
         */
    }

    /**
     *
     * @param posOfPlayer BoardPosition object with int row and int col used as the board index
     *
     * @pre [pos has reached the end of the board] AND [posOfPlayer piece is not crowned yet]
     * @post [posOfPlayer = The uppercase equivalent of the car] AND board = #board AND
     * pieceCount = #pieceCount AND viableDirection = #viableDirection
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
     * @return BoardPosition object
     *
     * @pre [startingPos is within the bounds of the board] AND [startingPos is an available piece that
     * can be moved] AND [The desired new position is empty]
     * @post [startingPos = null] AND board = [board containing the coordinates of the moved piece] AND
     * pieceCount = #pieceCount AND viableDirection = #viableDirection
     */
    public BoardPosition movePiece(BoardPosition startingPos, DirectionEnum dir) {
        /*
        A modified version of placePiece moves a piece on the board, located at the startingPos index, in the direction
        designated by the DirectionEnum dir. Remember, not only should a piece now appear at the moved location, but the
        position the piece moved from should now be empty.
         */
    }

    public BoardPosition jumpPiece(BoardPosition startingPos, DirectionEnum dir) {
        /*
        A modified version of movePiece that moves a piece by "jumping" an opponent player piece. When a player "jumps"
        an opponent, that player should move two positions in the direction passed in by dir parameter. Remember, not
        only should the piece now appear at the moved location, and the starting position should not be empty, but the
        position that was "jumped" should now also be empty.

        Furthermore, this method should remove 1 from the opponent's pieceCount.
         */
    }

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
