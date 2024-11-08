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
            gameBoard.toString();
            System.out.println("player " + whichPlayer + ", which piece do you wish to move?/n Enter " +
                    "the row followed " + "by a space followed by the column.");
            int startRow = readInInput.nextInt();
            int startCol = readInInput.nextInt();

            //If the player choose a piece out of bounds
            boolean withinBounds = ((startRow >= 0 && startRow < CheckerBoard.ROW_NUM) &&
                    (startCol >= 0 && startCol < CheckerBoard.COL_NUM));

            //Input validation for coordinates
            if (!(withinBounds))
            {
                System.out.println("Out of bounds! Choose a different coordinate.");
                continue;
            }

            //If the player chooses a piece other than their own
            char correctPiece = gameBoard.whatsAtPos(new BoardPosition(startRow, startCol));
            if (correctPiece != whichPlayer)
            {
                System.out.println("Player " + whichPlayer +", that isn't your piece. Pick one of your pieces.");
                continue;
            }

            //Input validation for getting the direction
            boolean isValidDirection = true;
            DirectionEnum intendedDir = null;
            do
            {
                HashMap<DirectionEnum, Character> availableMoves = gameBoard.scanSurroundingPositions(new BoardPosition(startRow, startCol));
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
                else
                {
                    System.out.println("Invalid Direction.");
                }
            } while (!isValidDirection);

            //Moving the piece int he specified direction
            BoardPosition firstPos = ICheckerBoard.getDirection(intendedDir);
            BoardPosition endPos = new BoardPosition(startRow + firstPos.getRow(), startCol + firstPos.getColumn());

            if (gameBoard.whatsAtPos(endPos) == CheckerBoard.EMPTY_POS)
            {
                gameBoard.movePiece(new BoardPosition(startRow, startCol), intendedDir);
            }
            else {
                gameBoard.jumpPiece(new BoardPosition(startRow, startCol), intendedDir);
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
}
