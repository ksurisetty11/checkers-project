package cpsc2150.extendedCheckers.models;

/**
 * AbsCheckerBoard implements ICheckerBoard and overrides the toString
 * method so that it will print the current checkerboard state
 */
public abstract class AbsCheckerBoard implements ICheckerBoard {
    @Override
    public String toString() {
        {
            ///absBoard = getBoard
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
                    stringBoard.append(positionPiece).append(" |");
                }
            }
            return stringBoard.toString();
        }
    }
}
