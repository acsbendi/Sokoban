/**
 *
 */
interface Square extends Displayable{
    /**
     * Checks if there is something on this {@code Square} object that prevents the game from ending.
     * @return Returns true if there is nothing on this {@code Square} that prevents the game from ending.
     */
    boolean isOver();

    /**
     * Method for handling movements into this {@code FlatSquare} by any {@code Movable}.
     * @param movable The moving {@code Movable} object.
     * @param direction The direction of the movement.
     * @return Returns whether the movement was successful.
     */
    boolean enter(Movable movable, Direction direction);
}