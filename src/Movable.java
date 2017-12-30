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

    public boolean isOver(){
        return true;
    }

    public abstract boolean move(FlatSquare flatSquare, Direction direction);
}
