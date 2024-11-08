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
                HashMap<DirectionEnum, Character> availableMoves = gameBoard.scanSurroundingPositions(new BoardPosition(startPos.getRow(), startPos.getColumn()));
                System.out.println("In which direction do you wish to move the piece?\n" +
                        "Enter one of these options:");

                //Loop through hashmap and only show direction
                for (DirectionEnum allDir : availableMoves.keySet())
                {
                    System.out.println(allDir);
                }
                String directionToMove = readInInput.next().toLowerCase();
                intendedDir = DirectionEnum.valueOf(directionToMove);

                if (availableMoves.containsKey(directionToMove))
                {
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

                //Input validation for accepting or declinging playing the game again
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
     *
     * @param whichPlayer
     * @param gameBoard
     * @return
     */
    private static BoardPosition getPieceCoordinates(char whichPlayer, ICheckerBoard gameBoard) {
        int row, col;
        while (true) {
            System.out.println("Player " + whichPlayer + ", which piece do you wish to move?" +
                    "\nEnter the row followed by a space followed by the column.");
            row = readInInput.nextInt();
            col = readInInput.nextInt();

            boolean withinBounds = (row >= 0 && row < CheckerBoard.ROW_NUM) && (col >= 0 && col < CheckerBoard.COL_NUM);
            if (!withinBounds) {
                System.out.println("Out of bounds! Choose a different coordinate.");
                continue;
            }

            char pieceAtPos = gameBoard.whatsAtPos(new BoardPosition(row, col));
            if (pieceAtPos == whichPlayer) {
                return new BoardPosition(row, col);
            } else {
                System.out.println("Player " + whichPlayer + ", that isn't your piece. Pick one of your pieces.");
            }
        }
    }
}
