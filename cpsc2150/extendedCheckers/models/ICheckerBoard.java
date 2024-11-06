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

    default public BoardPosition movePiece(BoardPosition startingPos, DirectionEnum dir) {

    }

    default public BoardPosition jumpPiece(BoardPosition startingPos, DirectionEnum dir) {

    }

    default public HashMap<DirectionEnum, Character> scanSurroundingPositions(BoardPosition startingPos) {

    }

    //public static BoardPosition getDirection(DirectionEnum dir);


}