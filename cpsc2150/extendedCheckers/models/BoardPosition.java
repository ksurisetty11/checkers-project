package cpsc2150.extendedCheckers.models;

public class BoardPosition
{
    /**
     * Row component of the BoardPosition
     */
    private int row;

    /**
     * Column component of the BoardPosition
     */
    private int column;

    /**
     * @invariant 0 <= row < [max row size] AND 0 <= column < [max column size] AND row and column must always be in bounds
     *
     * @param aRow The row index for the board position, an Int
     * @param aCol The row index for the board position, an Int
     * @pre 0 <= aRow < [max row size] AND 0 <= aCol < [max column size]
     * @post row = aRow AND column = aCol
     */
    public BoardPosition(int aRow, int aCol) {
       row = aRow;
       column = aCol;
    }

    /**
     * standard getter for the row
     *
     * @return the row component of the board position
     * @pre none
     * @post getRow = row AND row = #row AND column = #column
     */
    public int getRow() {
        return row;
    }

    /**
     * standard getter for the column
     *
     * @return the column component of the board position
     * @pre none
     * @post getColumn = column AND column = #column AND row = #row
     */
    public int getColumn() {
        return column;
    }

    /**
     * Method that will return true if BoardPosition is equal to the parameter object
     * @param obj The object to compare with this BoardPosition
     * @return true if obj is an instance of BoardPosition and both the row and column of this BoardPosition are the row and column of obj or else return false
     * @pre obj not NULL
     * @post equals = [obj an instance of BoardPosition AND this.row = obj.row AND this.column = obj.column]
     */
    public boolean equals(Object obj) {
        if (obj instanceof BoardPosition comparedPosition) {
            return ((this.row == comparedPosition.row) && (this.column == comparedPosition.column));
        }
        return false;
    }

    /**
     * Returns a string representation of the BoardPosition in the format of "row,column"
     *
     * @return a string in "row,column" format
     * @pre none
     * @post toString = "row,column" AND row = #row AND column = #column
     */
    public String toString() {
        return row + "," + column;
    }

}