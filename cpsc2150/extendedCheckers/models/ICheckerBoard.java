package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;
import cpsc2150.extendedCheckers.views.CheckersFE;

import java.util.ArrayList;
import java.util.HashMap;

import static cpsc2150.extendedCheckers.models.AbsCheckerBoard.EMPTY_POS;

/**
 * ICheckerBoard is an interface that contains methods for managing and run the game checkers.
 * It manages how the pieces interact with each other and move around the board. This interface contains
 * primary actions that can be performed on a checkerboard.
 *
 * @initialization_ensures board is made, each player has a starting set of directions and starting amount of pieces
 *
 * @defines
 *      board: the internal representation of the board, stored in a private data structure that maintains the
 *                  current state of each position. This abstract board structure should not be directly accessed in
 *                  implementations, and methods in this interface provide means of board interaction.
 *      numberOfPieces: how many pieces each player has
 *      directions: possible directions a piece can move
 *
 * @constraints
 *     [Player cannot exist on OR move to a black tile]
 *     AND 0 <= numberOfPieces <= STARTING_COUNT
 *     AND Pieces cannot be moved out of bounds of the board
 *     AND Piece can only move in its directions
 *     AND When a piece moves, its original position is left as an EMPTY_POS where it started
 */
public interface ICheckerBoard {

    public static final int BOARD_DIMENSIONS = 8;

    /**
     * Retrieves a mapping of pieces to their viable movement direction
     *
     * @return A hashmap where the key is a character representing a piece type [Player One] or [Player Two]
     *          and the value is a list of possible directions that the piece can move
     */
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections();

    /**
     * Retrieves the count of each type of piece currently on the board
     *
     * @return A hashmap where each key is a character representing a piece type and each corresponding value is an
     *         integer count of pieces of that type
     */
    public HashMap<Character, Integer> getPieceCounts();

    /**
     * Retrieves the number of rows
     *
     * @return An int that represents the number of rows on the game board
     */
    public int getRowNum();

    /**
     * Retrieves the number of columns
     *
     * @return An int that represents the number of columns  on the game board
     */
    public int getColNum();

    /**
     * Places a piece on the board at the specified position
     *
     * @param pos The BoardPosition where the piece will be placed
     * @param player A character representing the player piece to be placed
     */
    public void placePiece(BoardPosition pos, char player);

    /**
     * Checks what piece is at the specific board position
     *
     * @param pos The BoardPosition to be checked
     * @return A character representing the piece of the specified position, or a default character
     *         if the position is empty
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if the specified player has won the game. A player only wins if the opponent has no
     * remaining pieces on the board.
     *
     * @param player, The player character [PLAYER_ONE or PLAYER_TWO] whose win status is to be checked.
     *                PLAYER_ONE represents one player and PLAYER_TWO represents the opponent
     * @return true if the specified player has won otherwise it is false
     */
    default public boolean checkPlayerWin(Character player) {
        HashMap<Character, Integer> pieceCounts = getPieceCounts();
        Integer numOfPlayerPieces = pieceCounts.get(player);
        if (numOfPlayerPieces == 0)
        {
            return false;
        }
        else if(player == CheckersFE.getPlayerOne())
        {
            return pieceCounts.get(CheckersFE.getPlayerTwo()) == 0;
        }
        else
        {
            return pieceCounts.get(CheckersFE.getPlayerOne()) == 0;
        }
    }

    /**
     * Crowns the piece at the specific board position and promotes it to a "king" piece or "crowned" by changing it to
     * upper case
     *
     * @param posOfPlayer The position on the board where the player's piece to be crowned is located.
     * @defines boardRepresentation, an abstract representation of the checkerboard state
     */
    default public void crownPiece(BoardPosition posOfPlayer) {
        char player = whatsAtPos(posOfPlayer);
        boolean xAcross = (player == CheckersFE.getPlayerOne());
        boolean oAcross = (player == CheckersFE.getPlayerTwo());
        if (xAcross || oAcross)
        {
            char newCrownedPiece = Character.toUpperCase(whatsAtPos(posOfPlayer));
            placePiece(posOfPlayer, newCrownedPiece);
        }
    }

    /**
     * Moves the piece from the starting position in the specific direction by one space
     *
     * @param startingPos The current position of the piece to be moved
     * @param dir The direction in which to move the piece
     * @return The new position of the piece after moving one space in the specified direction.
     * @defines boardRepresentation, an abstract representation of the checkerBoard state
     */
    default public BoardPosition movePiece(BoardPosition startingPos, DirectionEnum dir) {
        BoardPosition intendedDirection = getDirection(dir);
        int intendedRow = startingPos.getRow() + intendedDirection.getRow();
        int intendedCol = startingPos.getColumn() + intendedDirection.getColumn();
        char playerPiece = whatsAtPos(startingPos);

        BoardPosition endingPos = new BoardPosition(intendedRow, intendedCol);
        boolean withinRowBoundaries = endingPos.getRow() < getRowNum() && endingPos.getRow() >= 0;
        boolean withinColBoundaries = endingPos.getColumn() < getColNum() && endingPos.getColumn() >= 0;

        if (withinRowBoundaries && withinColBoundaries)
        {
                placePiece(startingPos, EMPTY_POS);
                placePiece(endingPos, playerPiece);
                return endingPos;
        }
        return startingPos;
    }

    /**
     * Jumps the piece from the starting position over another piece in the specific direction
     *
     * @param startingPos the current position of the piece to be jumped
     * @param dir the direction in which to jump the piece
     * @return the new position of the piece after jumping over another piece in the specified direction
     */
    default public BoardPosition jumpPiece(BoardPosition startingPos, DirectionEnum dir) {
        char piece = whatsAtPos(startingPos);
        BoardPosition direction = getDirection(dir);

        BoardPosition jumpedPos = new BoardPosition(startingPos.getRow() + direction.getRow(),
                                 startingPos.getColumn() + direction.getColumn());
        BoardPosition moveTo = new BoardPosition(startingPos.getRow() + (2 * direction.getRow()),
                              startingPos.getColumn() + (2 * direction.getColumn()));

        char jumpedPiece = whatsAtPos(jumpedPos);

        placePiece(startingPos, EMPTY_POS);
        placePiece(jumpedPos, EMPTY_POS);
        placePiece(moveTo, piece);

        HashMap<Character, Integer> playerPieceCount = getPieceCounts();
        int totalPieces = playerPieceCount.getOrDefault(piece, 0);
        playerPieceCount.put(piece, totalPieces);

        //Subtracting 1 from the total number of opposing pieces
        if ((jumpedPiece != EMPTY_POS) && (jumpedPiece != '\0')) {
            playerPieceCount.put(jumpedPiece, playerPieceCount.get(jumpedPiece) - 1);
        }
        
        return moveTo;
    }

    /**
     * Scans the surrounding positions around the starting position
     *
     * @param startingPos the position from which to scan surrounding positions
     * @return a map where each key is a direction and each value is the character present at that position in the
     *          specific direction
     * @defines surroundingBoardRepresentation, an abstract representation indicating the contents of adjacent positions
     * on the checkerboard
     */
    default public HashMap<DirectionEnum, Character> scanSurroundingPositions(BoardPosition startingPos) {
        HashMap<DirectionEnum, Character> charAtDirection = new HashMap<>();
        ArrayList<DirectionEnum> viableDirections = new ArrayList<>();
        viableDirections.add(DirectionEnum.SE);
        viableDirections.add(DirectionEnum.SW);
        viableDirections.add(DirectionEnum.NE);
        viableDirections.add(DirectionEnum.NW);

        for(DirectionEnum direction : viableDirections)
        {
           BoardPosition possibleMove = getDirection(direction);
           BoardPosition possiblePos = new BoardPosition(startingPos.getRow() + possibleMove.getRow(),
                                      startingPos.getColumn() + possibleMove.getColumn());
           boolean rowBounds = (possiblePos.getRow() < getRowNum()) && (possiblePos.getRow() >= 0);
           boolean colBounds = (possiblePos.getColumn() < getColNum()) && (possiblePos.getColumn() >= 0);

           if(rowBounds && colBounds)
           {
               charAtDirection.put(direction, whatsAtPos(possiblePos));
           }
        }
        return charAtDirection;
    }

    /**
     * Turns dir into a corresponding board position
     *
     * @param dir a direction that a piece can be moved
     * @return a board representation of the corresponding direction
     */
    public static BoardPosition getDirection(DirectionEnum dir) {
        int tempRow = 0;
        int tempCol = 0;
        if(dir == DirectionEnum.NE){
            tempRow = -1;
            tempCol = 1;
        }
        else if(dir == DirectionEnum.NW){
            tempRow = -1;
            tempCol = -1;
        }
        else if(dir == DirectionEnum.SE){
            tempRow = 1;
            tempCol = 1;
        }
        else if(dir == DirectionEnum.SW){
            tempRow = 1;
            tempCol = -1;
        }
        return new BoardPosition(tempRow, tempCol);
    }

}