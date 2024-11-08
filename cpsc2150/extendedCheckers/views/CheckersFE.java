package cpsc2150.extendedCheckers.views;
import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import cpsc2150.extendedCheckers.models.ICheckerBoard;
import cpsc2150.extendedCheckers.util.DirectionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static cpsc2150.extendedCheckers.models.ICheckerBoard.getDirection;

/**
 * CheckersFE currently contains the main method for the checkers game.
 * In future projects, the main will handle user input and be responsible for running
 * the entire checkers game
 * The main will control the gameplay and is where all of the functions for checkers
 * will be called.
 */
public class CheckersFE {
    public static final Scanner readInInput = new Scanner(System.in);

    public static void main(String[] args) {
        ICheckerBoard gameBoard = new CheckerBoard();
        HashMap<Character, ArrayList<DirectionEnum>> gameDir = gameBoard.getViableDirections();
        Scanner readInInput = new Scanner(System.in);
        boolean gameOn = true;
        //Game starts with player x
        char whichPlayer = CheckerBoard.PLAYER_ONE;

        //Loop that switches control of each player each iteration
        while (gameOn)
        {
            String currentBoard = gameBoard.toString();
            System.out.println(currentBoard);

            BoardPosition startPos = getPieceCoordinates(whichPlayer, gameBoard);

            //Input validation for getting the direction
            boolean isValidDirection = true;
            DirectionEnum intendedDir = null;
            do
            {
                HashMap<DirectionEnum, Character> availableMoves = gameBoard.scanSurroundingPositions(startPos);
                System.out.println("In which direction do you wish to move the piece?\n" +
                        "Enter one of these options:");

                //Do a null check
                char checkPosition = gameBoard.whatsAtPos(startPos);
                ArrayList<DirectionEnum> possibleMoves = gameBoard.getViableDirections().get(checkPosition);
                if (possibleMoves != null) {
                    for (DirectionEnum possibleDirections : possibleMoves) {
                        if (availableMoves.containsKey(possibleDirections)) {
                            System.out.println(possibleDirections);
                        }
                    }
                } else {
                    System.out.println("There are no possible moves");
                }

                String directionToMove = readInInput.next().toUpperCase();

                if (directionToMove.contains("NE" )) {
                    intendedDir = DirectionEnum.NE;
                } else if (directionToMove.contains("NW")) {
                    intendedDir = DirectionEnum.NW;
                } else if (directionToMove.contains("SE")) {
                    intendedDir = DirectionEnum.SE;
                } else if (directionToMove.contains("SW")) {
                    intendedDir = DirectionEnum.SW;
                } else{
                    System.out.println("Invalid direction.");
                }
                intendedDir = DirectionEnum.valueOf(directionToMove);
                if (!(availableMoves.containsKey(intendedDir))) {
                    System.out.println("Unable to move in that direction.");
                }
                if (availableMoves.containsKey(directionToMove)) {
                    isValidDirection = true;
                }

            } while (!isValidDirection);

            //Moving the piece in the specified direction
            BoardPosition firstPos = ICheckerBoard.getDirection(intendedDir);
            BoardPosition moveToPos = new BoardPosition(startPos.getRow() + firstPos.getRow(), startPos.getColumn() + firstPos.getColumn());

            if (gameBoard.whatsAtPos(moveToPos) == CheckerBoard.EMPTY_POS) {
                BoardPosition endPos = gameBoard.movePiece(new BoardPosition(startPos.getRow(), startPos.getColumn()), intendedDir);
                gameBoard.crownPiece(endPos);
            } else {
                BoardPosition endPos = gameBoard.jumpPiece(new BoardPosition(startPos.getRow(), startPos.getColumn()), intendedDir);
                gameBoard.crownPiece(endPos);
            }

            //Ending or repeating the games if a player has won
            if (gameBoard.checkPlayerWin(whichPlayer))
            {
                System.out.println("Player " + whichPlayer + " has won!");
                boolean keepPlaying = true;
                boolean endGame = true;

                //Input validation for accepting or declining playing the game again
                do
                {
                    System.out.println("Would you like to play again? Enter 'Y' or 'N'");
                    String continuePlaying = readInInput.next();
                    keepPlaying = (continuePlaying.equals("Y") || continuePlaying.equals("y"));
                    endGame = (continuePlaying.equals("N") || continuePlaying.equals("n"));
                } while (!keepPlaying && !endGame);

                if (keepPlaying)
                {
                    gameBoard = new CheckerBoard();
                    whichPlayer = CheckerBoard.PLAYER_ONE;
                }
                else
                {
                    gameOn = false;
                }

            }

            //If the game hasn't ended yet, it is the other players turn to move a piece
            if (whichPlayer == CheckerBoard.PLAYER_ONE) { whichPlayer = CheckerBoard.PLAYER_TWO; }
            else { whichPlayer = CheckerBoard.PLAYER_ONE; }
        }
    }

    /**
     * Prompts the player to select a piece to move
     * The method repeatedly asks the player to choose valid coordinates to move their pieces.
     * @param whichPlayer The character that represents the player 'x' or 'o'
     * @param gameBoard The checker board that implements the interface
     *
     * @return a BoardPosition object representing the coordinates of the selected piece
     * @pre whichPlayer needs to be 'x', 'X', 'o', 'O'
     * @post getPieceCoordinates = BoardPosition [BoardPosition that was chosen by users]
     */
    private static BoardPosition getPieceCoordinates(char whichPlayer, ICheckerBoard gameBoard) {
        int inputRow, inputCol;
        boolean isValidPiece = false;
        do {
            System.out.println("Player " + whichPlayer + ", which piece do you wish to move?" +
                    "\nEnter the row followed by a space followed by the column.");
            inputRow = readInInput.nextInt();
            inputCol = readInInput.nextInt();

            boolean withinBounds = (inputRow >= 0 && inputRow < CheckerBoard.ROW_NUM) && (inputCol >= 0 && inputCol < CheckerBoard.COL_NUM);
            if (!withinBounds) {
                System.out.println("Out of bounds! Choose a different coordinate.");
                continue;
            }

            char pieceAtPos = gameBoard.whatsAtPos(new BoardPosition(inputRow, inputCol));
            if ((pieceAtPos == whichPlayer) || (pieceAtPos == Character.toUpperCase(whichPlayer))) {
                isValidPiece = true;
            } else {
                System.out.println("Player " + whichPlayer + ", that isn't your piece. Pick one of your pieces.");
            }
        } while (!isValidPiece);
        return new BoardPosition(inputRow, inputCol);
    }
}
