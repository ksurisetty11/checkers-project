package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ICheckerBoard is an interface that contains methods for managing and run the game checkers.
 * It manages how the pieces interact with each other and move around the board. This interface contains
 * primary actions that can be performed on a checkerboard.
 *
 * @defines board: the internal representation of the board, stored in a private data structure that maintains the current
 *                  state of each position. This abstract board structure should not be directly accessed in implementations,
 *                  and methods in this interface provide means of board interaction.
 */
public interface ICheckerBoard {

    public static final int BOARD_DIMENSIONS = 8;

    /**
     * Retrieves a mapping of pieces to their viable movement direction
     * @return A hashmap where the key is a character representing a piece type 'X' or 'O' and the value is a list of possible directions that the piece can move
     */
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections();

    /**
     * Retrieves the count of each type of piece currently on the board
     * @return A hashmap where each key is a character representing a piece type and each corresponding value is an integer count of pieces of that type
     */
    public HashMap<Character, Integer> getPieceCounts();

    /**
     * Places a piece on the board at the specified position
     * @param pos The BoardPosition where the piece will be placed
     * @param player A character representing the player piece to be placed
     */
    public void placePiece(BoardPosition pos, char player);

    /**
     * Checks what piece is at the specific board position
     * @param pos The BoardPosition to be checked
     * @return A character representing the piece the specified position, or a default character if the position is empty
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if the specified player has won the game. A player only wins if the opponent has no remaining pieces on the board.
     * @param player, The player character ('x' or 'o') whose win status is to be checked.
     *                'x' represents one player and ;i; represents the opponent
     * @return true if the specified player has won otherwise it is false
     */
    default public boolean checkPlayerWin(Character player) {
        HashMap<Character, Integer> pieceCounts = getPieceCounts();
        Integer numOfPlayerPieces = pieceCounts.get(player);
        if(numOfPlayerPieces == 0){
            return false;
        }
        else if(player == 'x') {
            return pieceCounts.get('o') == 0;
        }
        else {
            return pieceCounts.get('x') == 0;
        }
    }

    /**
     * Crowns the piece at the specific board position and promotes it to a "king" piece or "crowned" by changing it to upper case
     * @param posOfPlayer The position on the board where the player's piece to be crowned is located.
     * @defines boardRepresentation, an abstract representation of the checkerboard state
     */
    default public void crownPiece(BoardPosition posOfPlayer) {
        char player = whatsAtPos(posOfPlayer);
        int originRow = 0;
        boolean xAcross = (player == CheckerBoard.PLAYER_ONE && posOfPlayer.getRow() == CheckerBoard.ROW_NUM - 1);
        boolean oAcross = (player == CheckerBoard.PLAYER_TWO && posOfPlayer.getRow() == originRow);
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
        placePiece(startingPos, CheckerBoard.EMPTY_POS);
        placePiece(endingPos, playerPiece);

        return endingPos;
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

        BoardPosition jumpedPos = new BoardPosition(startingPos.getRow() + direction.getRow(),startingPos.getColumn() + direction.getColumn());
        BoardPosition moveTo = new BoardPosition(startingPos.getRow() + (2 * direction.getRow()),startingPos.getColumn() + (2 * direction.getColumn()));

        char jumpedPiece = whatsAtPos(jumpedPos);

        if (jumpedPiece == ' ' || jumpedPiece == piece) {
            //not sure what to return if trying to jump over invalid piece
            return startingPos;
        }

        placePiece(startingPos, ' ');
        placePiece(jumpedPos, ' ');
        placePiece(moveTo, piece);

        HashMap<Character, Integer> playerPieceCount = getPieceCounts();
        int totalPieces = playerPieceCount.getOrDefault(piece, 0);
        playerPieceCount.put(piece, totalPieces - 1);


        int jumpedPieceCount = playerPieceCount.getOrDefault(jumpedPiece, 0);
        playerPieceCount.put(jumpedPiece, jumpedPieceCount - 1);

        return moveTo;
    }

    /**
     * Scans the surrounding positions around the starting position
     *
     * @param startingPos the position from which to scan surrounding positions
     * @return a map where each key is a direction and each value is the character present at that position in the specific direction
     * @defines surroundingBoardRepresentation, an abstract representation indicating the contents of adjacent positions on the checkerboardgit
     */
    default public HashMap<DirectionEnum, Character> scanSurroundingPositions(BoardPosition startingPos) {
        HashMap<DirectionEnum, Character> charAtDirection = new HashMap<>();

        BoardPosition posAtNW = new BoardPosition(startingPos.getRow()-1, startingPos.getColumn()-1);
        charAtDirection.put(DirectionEnum.NW, whatsAtPos(posAtNW));

        BoardPosition posAtNE = new BoardPosition(startingPos.getRow()-1, startingPos.getColumn()+1);
        charAtDirection.put(DirectionEnum.NE, whatsAtPos(posAtNE));

        BoardPosition posAtSW = new BoardPosition(startingPos.getRow()+1, startingPos.getColumn()-1);
        charAtDirection.put(DirectionEnum.SW, whatsAtPos(posAtSW));

        BoardPosition posAtSE = new BoardPosition(startingPos.getRow()+1, startingPos.getColumn()+1);
        charAtDirection.put(DirectionEnum.SE, whatsAtPos(posAtSE));

        return charAtDirection;
    }

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