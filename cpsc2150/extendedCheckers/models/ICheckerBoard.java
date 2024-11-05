package cpsc2150.extendedCheckers.models;

import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICheckerBoard {
    public HashMap<Character, ArrayList<DirectionEnum>> getViableDirections();
    public HashMap<Character, Integer> getPieceCounts();
    public void placePiece(BoardPosition pos, char player);
    public char whatsAtPos(BoardPosition pos);

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

    default public void crownPiece(BoardPosition posOfPlayer) {

    }

    default public BoardPosition movePiece(BoardPosition startingPos, DirectionEnum dir) {

    }

    default public BoardPosition jumpPiece(BoardPosition startingPos, DirectionEnum dir) {

    }

    default public HashMap<DirectionEnum, Character> scanSurroundingPositions(BoardPosition startingPos) {

    }

    //public static BoardPosition getDirection(DirectionEnum dir);


}