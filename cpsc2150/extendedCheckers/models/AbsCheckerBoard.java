package cpsc2150.extendedCheckers.models;

/**
 * AbsCheckerBoard implements ICheckerBoard and overrides the toString
 * method so that it will print the current checkerboard state
 */
public abstract class AbsCheckerBoard implements ICheckerBoard {
    private int dimensionCheck;

    public static final char EMPTY_POS = ' ';
    public static final char BLACK_TILE = '*';

    /**
     * Converts a board into a string representation
     *
     * @return A string representing a board in its current state
     * @post toString = [string that represents a board]
     */
    @Override
    public String toString() {
        {
            StringBuilder stringBoard = new StringBuilder("|  ");
            for(int columnGuide = 0; columnGuide < getColNum(); columnGuide++){
                if (columnGuide < 10) {
                    stringBoard.append("| ").append(columnGuide);
                }
                else {
                    stringBoard.append("|").append(columnGuide);
                }
            }
            stringBoard.append("|");
            for(int row = 0; row < getRowNum(); row++){
                if (row < 10) {
                    stringBoard.append("\n|").append(row).append(" |");
                }
                else {
                    stringBoard.append("\n|").append(row).append("|");
                }
                for(int col = 0; col < getColNum(); col++) {
                    BoardPosition posOnBoard = new BoardPosition(row,col);
                    char positionPiece = whatsAtPos(posOnBoard);
                    if (positionPiece != '\0') {
                        stringBoard.append(positionPiece).append(" |");
                    }
                }
            }
            stringBoard.append("\n");
            return stringBoard.toString();
        }
    }
}
