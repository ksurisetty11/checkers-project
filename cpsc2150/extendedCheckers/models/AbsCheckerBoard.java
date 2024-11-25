package cpsc2150.extendedCheckers.models;

/**
 * AbsCheckerBoard implements ICheckerBoard and overrides the toString
 * method so that it will print the current checkerboard state
 */
public abstract class AbsCheckerBoard implements ICheckerBoard {
    private int dimensionCheck;

    public static char getPlayerOne() {
        return CheckerBoard.PLAYER_ONE;
    }

    public static char getPlayerTwo() {
        return CheckerBoard.PLAYER_TWO;
    }

    @Override
    public String toString() {
        {
            StringBuilder stringBoard = new StringBuilder("|  ");
            for(int columnGuide = 0; columnGuide < BOARD_DIMENSIONS; columnGuide++){
                stringBoard.append("| ").append(columnGuide);
            }
            stringBoard.append("|");
            for(int row = 0; row < BOARD_DIMENSIONS; row++){
                stringBoard.append("\n|").append(row).append(" |");
                for(int col = 0; col < BOARD_DIMENSIONS; col++) {
                    BoardPosition posOnBoard = new BoardPosition(row,col);
                    char positionPiece = whatsAtPos(posOnBoard);
                    if (positionPiece != '\0') {
                        stringBoard.append(positionPiece).append(" |");
                    }
                    else if ((row + col) % 2 == 1) {
                        stringBoard.append('*').append(" |");
                    }
                    else {
                        stringBoard.append(' ').append(" |");
                    }
                }
            }
            return stringBoard.toString();
        }
    }
}
