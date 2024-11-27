package cpsc2150.extendedCheckers.views;
import cpsc2150.extendedCheckers.models.BoardPosition;
import cpsc2150.extendedCheckers.models.CheckerBoard;
import cpsc2150.extendedCheckers.models.CheckerBoardMem;
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
 * The main will control the gameplay and is where all the functions for checkers
 * will be called.
 */
public class CheckersFE {
    public static final Scanner readInInput = new Scanner(System.in);

    /**
     * The board sizes that hte player is allowed to choose from
     */
    public static final int[] allowedBoardSizes = {8, 10, 12, 14, 16};
    /**
     * Both the players pieces. Both players start with the default chars 'x' and 'o'
     */
    private static char playerOne = 'x';
    private static char playerTwo = 'o';

    public static void main(String[] args) {
        Scanner readInInput = new Scanner(System.in);

        //Game asks player one what piece they want to use
        System.out.println("Player 1, enter your piece:");
        String inputPlayerPiece = readInInput.next();
        while(inputPlayerPiece.length() != 1 || Character.isUpperCase(inputPlayerPiece.charAt(0))){
            System.out.println("Please enter only a single lowercase character");
            inputPlayerPiece = readInInput.next();
        }
        playerOne = inputPlayerPiece.charAt(0);

        //Game asks player two what piece they want to use
        System.out.println("Player 2, enter your piece:");
        inputPlayerPiece = readInInput.next();
        while(inputPlayerPiece.length() != 1 || Character.isUpperCase(inputPlayerPiece.charAt(0))
                || inputPlayerPiece.charAt(0) == playerOne){
            System.out.println("Please enter only a single lowercase character");
            inputPlayerPiece = readInInput.next();
        }
        playerTwo = inputPlayerPiece.charAt(0);

        //Game asks players if they want fast game or memory efficient game
        System.out.println("Do you want a fast game (F/f) or a memory efficient game (M/m)?");
        String gameType = readInInput.next();
        //ICheckerBoard gameBoard = new CheckerBoard(8);
        while(!gameType.equals("M") && !gameType.equals("m") && !gameType.equals("F") && !gameType.equals("f")){
            System.out.println("Please enter a character for fast game (F/f) or a memory efficient game (M/m)");
            gameType = readInInput.next();
        }

        //Scanner sizeOfBoard = new Scanner(System.in);
        boolean validBoardSize = false;
        int sizeOfBoard = 0;
        int defaultSize = allowedBoardSizes[0];
        while (!validBoardSize) {
            System.out.println("How big should the Board be? It can be 8x8, 10x10, 12x12, 14x14, or 16x16." +
                    "Enter one number:");
            if (readInInput.hasNextInt()) {
                sizeOfBoard = readInInput.nextInt();

                //Checking if the size chosen by the user is within the allowed range
                for (int i: allowedBoardSizes) {
                    if (sizeOfBoard == i) {
                        validBoardSize = true;
                        break;
                    }
                }
            }
        }

        ICheckerBoard gameBoard = new CheckerBoard(defaultSize);
        if (gameType.equals("F") || gameType.equals("f")) {
            gameBoard = new CheckerBoard(sizeOfBoard);
        }
        //If players want a memory efficient game, gameBoard changes it's dynamic type to CheckerBoardMem
        if(gameType.equals("M") || gameType.equals("m")){
            gameBoard = new CheckerBoardMem(sizeOfBoard);
        }

        //Game starts with player one
        char whichPlayer = CheckerBoard.PLAYER_ONE;
        boolean gameOn = true;

        //Loop that switches control of each player each iteration
        while (gameOn) {
            //Current State of the board
            String currentBoard = gameBoard.toString();
            System.out.println(currentBoard);

            //Validation for the correct piece chosen
            BoardPosition startPos = getPieceCoordinates(whichPlayer, gameBoard);
            DirectionEnum intendedDir = getValidPieceDirections(gameBoard, startPos, whichPlayer);

            //Validation for getting the allowed directions
            movePieceOnBoard(startPos, gameBoard, intendedDir);

            if (gameBoard.checkPlayerWin(whichPlayer)) {
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
                    gameBoard = new CheckerBoard(sizeOfBoard);
                    whichPlayer = playerOne;
                }
                else
                {
                    gameOn = false;
                }
            }
            //If the game hasn't ended yet, it is the other players turn to move a piece
            if (whichPlayer == playerOne) {
                whichPlayer = playerTwo;
            }
            else {
                whichPlayer = playerOne;
            }
        }
    }

    /**
     * Prompts the player to select their piece to move
     * The method repeatedly asks the player to choose valid coordinates to move their pieces.
     */
    private static BoardPosition getPieceCoordinates(char whichPlayer, ICheckerBoard gameBoard) {
        int inputRow, inputCol;
        boolean isValidPiece = false;
        do {
            System.out.println("Player " + whichPlayer + ", which piece do you wish to move?" +
                    " Enter the row followed by a space followed by the column.");
            inputRow = readInInput.nextInt();
            inputCol = readInInput.nextInt();

            boolean withinBounds = (inputRow >= 0 && inputRow < gameBoard.getRowNum())
                    && (inputCol >= 0 && inputCol < gameBoard.getColNum());
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

    /**
     * Prompts the player to choose a direction for their chosen piece to move.
     */
    private static DirectionEnum getValidPieceDirections(ICheckerBoard gameBoard, BoardPosition startPos, char currPlayer) {
        //Input validation for getting the direction
        boolean isValidDirection = true;
        DirectionEnum intendedDir = null;
        char checkPosition = gameBoard.whatsAtPos(startPos);
        ArrayList<DirectionEnum> officialAvailableMoves = new ArrayList<>();

        HashMap<DirectionEnum, Character> availableMoves = gameBoard.scanSurroundingPositions(startPos);
        ArrayList<DirectionEnum> possibleMoves = gameBoard.getViableDirections().get(checkPosition);

        if (possibleMoves != null) {
            //Going through all possible moves
            for (DirectionEnum direction : possibleMoves) {
                //If the possible move contains an empty position, add it to optional player moves
                if ((availableMoves.containsKey(direction)) && (availableMoves.get(direction) == ' ')) {
                    officialAvailableMoves.add(direction);
                }
                //Checking if it's possible to jump a piece.
                else if (availableMoves.containsKey(direction) && availableMoves.get(direction) != ' ') {
                    BoardPosition testDirection = getDirection(direction);

                    int timesPiecesMove = 2;
                    //Locate the piece being jumped and the ending position after the jump
                    BoardPosition jumpedPiecePosition = new BoardPosition(startPos.getRow()
                            + testDirection.getRow(), startPos.getColumn() + testDirection.getColumn());
                    BoardPosition endedJumpPosition = new BoardPosition(startPos.getRow() +
                            (timesPiecesMove * testDirection.getRow()), startPos.getColumn() +
                            (timesPiecesMove * testDirection.getColumn()));

                    boolean withinRowBoundaries = ((endedJumpPosition.getRow() >= 0)
                            && (endedJumpPosition.getRow() < gameBoard.getRowNum()));
                    boolean withinColBoundaries = ((endedJumpPosition.getColumn()
                            < gameBoard.getColNum()) && (endedJumpPosition.getColumn() >= 0));

                    //Checking the potential jumped piece is the opposite player's piece
                    char jumpedPieceChar = gameBoard.whatsAtPos(jumpedPiecePosition);
                    boolean isOpponentPiece = ((currPlayer == getPlayerOne() && jumpedPieceChar == getPlayerTwo()) ||
                            (currPlayer == getPlayerTwo() && jumpedPieceChar == getPlayerOne()));

                    //Adding the direction to the player optional moves if it is a valid move
                    if (withinRowBoundaries && withinColBoundaries && isOpponentPiece &&
                            (gameBoard.whatsAtPos(endedJumpPosition) == ' ')) {
                        officialAvailableMoves.add(direction);
                    }
                }
            }
        }
        //Printing the players available moves
        if (!officialAvailableMoves.isEmpty()) {
            do {
                System.out.println("In which direction do you wish to move the piece? " +
                        "Enter one of these options:");
                for (DirectionEnum dir : officialAvailableMoves) {
                    System.out.println(dir);
                }
                String directionToMove = readInInput.next().toUpperCase();
                boolean checkingValidPlayerDir = false;

                for (DirectionEnum dir : DirectionEnum.values()) {
                    if (dir.name().equalsIgnoreCase(directionToMove)) {
                        checkingValidPlayerDir = true;
                        intendedDir = dir;
                        break;
                    }
                }

                //Input validation for the players chosen direction
                if (checkingValidPlayerDir && officialAvailableMoves.contains(intendedDir)) {
                    isValidDirection = true;
                } else {
                    System.out.println("Invalid direction.");
                    isValidDirection = false;
                }
            } while (!isValidDirection);
        }
        return intendedDir;
    }

    /**
     * Moving the chosen piece in startPos to a different position on the board according to the direction.
     */
    private static void movePieceOnBoard(BoardPosition startPos, ICheckerBoard gameBoard, DirectionEnum dir)
    {
        //Direction the piece will be going
        BoardPosition intendedDirCoord = ICheckerBoard.getDirection(dir);

        //Validation for moving to that direction and not going out of bounds.
        BoardPosition moveToPos = new BoardPosition(startPos.getRow() +
                intendedDirCoord.getRow(),startPos.getColumn() + intendedDirCoord.getColumn());

        //Moving the piece if the position adjacent to the chosen direction is empty
        if (gameBoard.whatsAtPos(moveToPos) == CheckerBoard.EMPTY_POS) {
            BoardPosition endPos = gameBoard.movePiece(new BoardPosition(startPos.getRow(),
                    startPos.getColumn()), dir);
            canBeCrowned(endPos, gameBoard);
        }
        //Jumping the piece if the piece can't be moved
        else
        {
            BoardPosition endPos = gameBoard.jumpPiece(new BoardPosition(startPos.getRow(),
                    startPos.getColumn()), dir);
            canBeCrowned(endPos, gameBoard);
        }
    }

    /**
     * Determining if the piece at pos can be crowned. If it can be crowned, call the
     * crownPiece method.
     */
    private static void canBeCrowned(BoardPosition pos, ICheckerBoard gameBoard)
    {
        //Checking if the piece can be crowned if it is PLAYER_ONE
        if (gameBoard.whatsAtPos(pos) == playerOne) {
            if (pos.getRow() == gameBoard.getRowNum() - 1) {
                gameBoard.crownPiece(pos);
            }
        }
        //Checking if the piece can be crowned if it is PLAYER_ONE
        if (gameBoard.whatsAtPos(pos) == playerTwo) {
            if (pos.getRow() == 0) {
                gameBoard.crownPiece(pos);
            }
        }
    }

    /**
     *Standard getter for playerOne
     *
     * @return playerOne, char
     * @pre none
     * @post getPlayerOne = playerOne AND playerOne = #playerOne AND playerTwo = #playerTwo
     */
    public static char getPlayerOne()
    {
        return playerOne;
    }

    /**
     *Standard getter for playerTwo
     *
     * @return playerTwo, char
     * @pre none
     * @post getPlayerTwo = playerTwo AND playerOne = #playerOne AND playerTwo = #playerTwo
     */
    public static char getPlayerTwo()
    {
        return playerTwo;
    }
}