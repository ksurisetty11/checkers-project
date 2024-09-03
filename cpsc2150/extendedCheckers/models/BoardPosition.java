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

    public BoardPosition(int aRow, int aCol) {
        /*
        Constructor for the BoardPosition object. This should set both the row and column instance variables to their
        respective parameters.
         */
    }

    public int getRow() {
        /*
        Typical accessor for the row instance variable.
         */
    }

    public int getColumn() {
        /*
        Typical accessor for the column instance variable.
         */
    }


    public boolean equals(Object obj) {
        /*
        returns true if this BoardPosition is equal to the parameter object. Two BoardPositions are equal if their row
        and column vlaues are the same.

        hint: it is intentional that this accepts a parameter of type Object. There is a way to check if that parameter
        Object just happens to be an instance of a BoardPosition.
         */
    }

    public String toString() {
        /*
        returns a String representation of the BoardPosition in the format of "row,column"
         */
    }

}
