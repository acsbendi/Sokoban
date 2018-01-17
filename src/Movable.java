import java.io.Serializable;

/**
 * The abstract class representing something that can be moved on other squares (on FlatSquares)
 */
public abstract class Movable implements Displayable, Serializable{
    private static final long serialVersionUID = 6671366719177481073L;

    /**
     * The Square object occupied by this Movable object
     */
    protected FlatSquare square;

    /**
     * Getter method for the square that this Movable object occupies.
     * @return The square occupied by this Movable object.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Setter method for the square that this Movable object occupies.
     * @param square The square to be occupied by this Movable object.
     */
    public void setSquare(FlatSquare square) {
        this.square = square;
    }

    /**
     * By default, {@code Movable}s don't stop the game from ending.
     * @return True.
     */
    public boolean isOver(){
        return true;
    }

    /**
     * Method for moving a {@code Movable} into a {@code FlatSquare}. It may not be successful.
     * @param flatSquare The destination of the moving {@code Movable} object.
     * @param direction The direction of the attempted move.
     * @return Returns whether the move was successful.
     */
    public abstract boolean move(FlatSquare flatSquare, Direction direction);
}
