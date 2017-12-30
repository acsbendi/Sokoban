/**
 *
 */
interface Square extends Displayable{
    boolean isOver();
    boolean enter(Movable movable, Direction direction);
}