import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class representing an "empty" square, used at the initialization of a level object.
 * Also serves as the base class for other square types on which a {@code Movable} object can move.
 */
public class FlatSquare implements Square, Serializable{
    private static final long serialVersionUID = 3210605766743706355L;

    /**
     * The {@code Movable} object that occupies this square
     */
    protected Movable movable;

    /**
     * Constructs a new {@code FlatSquare} with the specified {@code Movable} on it.
     * @param movable The {@code Movable} to occupy this square.
     */
    FlatSquare(Movable movable){
        setMovable(movable);
    }

    /**
     * Getter for the {@code Movable} object that occupies this square.
     * @return The {@code Movable} object that occupies this square.
     */
    public Movable getMovable() {
        return movable;
    }

    /**
     * Setter method for the {@code Movable} object that will occupy this square.
     * @param movable The {@code Movable} object to occupy this square.
     */
    public void setMovable(Movable movable) {
        this.movable = movable;
        if(movable != null)
            movable.setSquare(this);
    }

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(FlatSquare.class.getResourceAsStream("/empty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a {@code FlatSquare} object.
     */
    @Override
    public BufferedImage getImage(){
        if (movable == null) return FlatSquare.image;
        else return Displayable.combineImages(FlatSquare.image,movable.getImage());
    }

    /**
     * Static getter for the image of any {@code FlatSquare} object.
     * @return The common image that all {@code FlatSquare} objects have.
     */
    public static BufferedImage getStaticImage(){
        return FlatSquare.image;
    }

    @Override
    public boolean isOver(){
        return movable == null || movable.isOver();
    }

    /**
     * Method for handling movements into this {@code FlatSquare} by any {@code Movable}.
     * Calls their {@code move} methods to tell them they may enter here.
     * @param movable The moving {@code Movable} object.
     * @param direction The direction of the movement.
     * @return Returns whether the movement was successful.
     */
    @Override
    public boolean enter(Movable movable, Direction direction){
        return movable.move(this,direction);
    }

    /**
     * Method for handling movements into this {@code FlatSquare} by a {@code Player}.
     * It can enter if this {@code FlatSquare} is empty or the next {@ocde Square} in the direction
     * of the movement is also a {@code FlatSquare} and empty.
     * @param player The moving {@code Player} object.
     * @param direction The direction of the movement.
     * @return Returns whether the movement was successful.
     */
    public boolean enter(Player player, Direction direction){
        if(this.movable != null){
            if(player.getLevel().getNeighbor(this,direction).enter(movable,direction)){
                this.movable = player;
                player.getGame().refreshSquare(player.getLevel().getNeighbor(this,direction));
                return true;
            }
            else return false;
        }
        this.movable = player;
        return true;
    }

    /**
     * Method for handling movements into this {@code FlatSquare} by a {@code Crate}.
     * It can only enter if this {@code FlatSquare} is empty.
     * @param crate The moving {@code Player} object.
     * @param direction The direction of the movement.
     * @return Returns whether the movement was successful.
     */
    public boolean enter(Crate crate, Direction direction){
        if(this.movable != null){
            return false;
        }
        this.movable = crate;
        return true;
    }
}

