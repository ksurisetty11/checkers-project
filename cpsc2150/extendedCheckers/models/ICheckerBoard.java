package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICheckerBoard {
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections();
    public HashMap<Character, Integer> getPieceCounts();
    public void placePiece(BoardPosition pos, char player);
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
        char newCrownedPiece = Character.toUpperCase(whatsAtPos(posOfPlayer));
        placePiece(posOfPlayer, newCrownedPiece);
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

    }

    /**
     * Scans the surrounding positions around the starting position
     *
     * @param startingPos the position from which to scan surrounding positions
     * @return a map where each key is a direction and each value is the character present at that position in the specific direction
     * @defines surroundingBoardRepresentation, an abstract representation indicating the contents of adjacent positions on the checkerboard
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


    }


}