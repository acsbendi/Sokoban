import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * The class representing a level on which a game of Sokoban can be played.
 */
public class Level implements Serializable, Iterable<Square>{
    private static final long serialVersionUID = 412163760458870809L;

    static int MIN_WIDTH = 5;
    static int MAX_WIDTH = 30;
    static int MIN_HEIGHT = 5;
    static int MAX_HEIGHT = 30;

    private List<Square> squares;
    private final Player player;
    private final int width;
    private final int height;

    Level(int width, int height, Player player){
        this.height = height;
        this.width = width;
        this.player = player;
        squares = new ArrayList<>();
        for(int i = 0; i < width*height; ++i)
            squares.add(new Floor(null));
        ((FlatSquare)squares.get(0)).setMovable(player);
        player.setSquare((FlatSquare)squares.get(0));
    }

    /**
     * Getter method for the width of this level.
     * @return The width of this level.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter method for the height of this level.
     * @return The height of this level.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets a square in the given position, to a given square.
     * @param x The x coordinate of the square to be set.
     * @param y The y coordinate of the square to be set.
     * @param square The square to which set.
     */
    public void setSquare(int x, int y, Square square){
        if(y*width+x == getIndexOf(getPlayer().getSquare())){
            try{
                ((FlatSquare)square).setMovable(player);
            }
            catch (ClassCastException e) {
                throw new InvalidParameterException("Player's square must be FlatSquare");
            }
        }
        squares.set(y*width+x,square);
    }

    /**
     * Getter method for the square that this level contains in a given position.
     * @param x The x coordinate of the square to be returned.
     * @param y The y coordinate of the square to be returned.
     * @return The square in this level in the given position.
     */
    public Square getSquare(int x, int y){
        return squares.get(y*width + x);
    }

    /**
     * Getter method for the square that this level contains in a given direction relative to a given square.
     * @param square The square that serves as the reference point.
     * @param direction The direction of the square to be returned relative to the given square.
     * @return The square in the given direction relative to the given square.
     */
    public Square getNeighbor(Square square, Direction direction ){
        int index = squares.indexOf(square);
        switch (direction){
            case Up:
                if(index/width == 0)
                    return null;
                else
                    return squares.get(index-width);
            case Down:
                if(index/width == height-1)
                    return null;
                else
                    return squares.get(index+width);
            case Left:
                if(index%width == 0)
                    return null;
                else
                    return squares.get(index-1);
            case Right:
                if(index%width == width-1)
                    return null;
                else
                    return squares.get(index+1);
            default:
                    return null;
        }
    }

    /**
     * Getter method for the Player object of this level.
     * @return The Player object of this level.
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Getter method for the square that contains the Player object.
     * If the level does not contain the given square, an {@code InvalidParameterException} is thrown.
     * @param square The square in this level that will contain the Player object.
     */
    public void setPlayerSquare(FlatSquare square){
        if(!squares.contains(square))
            throw new InvalidParameterException("The given square is not contained by this level");
        ((FlatSquare)player.getSquare()).setMovable(null);
        square.setMovable(player);
        player.setSquare(square);
    }

    /**
     * Getter method for index of a given square in the list of squares.
     * @param square The square whose index is to be returned.
     * @return The index of the given square.
     */
    public int getIndexOf(Square square){
        for(int i = 0; i < squares.size(); ++i )
            if(squares.get(i) == square)
                return i;
        return -1;
    }

    /**
     * Checks whether the current game is already over, i.e if there is any crate not on a destination square.
     * @return Indicates whether the game has ended.
     */
    public boolean isOver(){
        for(Square s : squares)
            if(!s.isOver()) return false;
        return true;
    }

    /**
     * The iterator of the contained list of squares. In this way, Level objects also implement the Iterable interface.
     * @return The iterator of the contained list of squares.
     */
    @Override
    public Iterator<Square> iterator() {
        return squares.iterator();
    }
}
