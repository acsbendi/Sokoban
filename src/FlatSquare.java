import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class representing an "empty" square, used at the initialization of a level object.
 * Also serves as the base class for other square types on which a Movable object can move.
 */
public class FlatSquare implements Square, Serializable{
    private static final long serialVersionUID = 3210605766743706355L;

    /**
     * The Movable (either the Player or a Crate) object that occupies this square
     */
    protected Movable movable;

    FlatSquare(Movable movable){
        setMovable(movable);
    }

    /**
     * Getter for the Movable object that occupies this square.
     * @return The Movable object that occupies this square.
     */
    public Movable getMovable() {
        return movable;
    }

    /**
     * Setter method for the Movable object that will occupy this square.
     * @param movable The Movable object to occupy this square.
     */
    public void setMovable(Movable movable) {
        this.movable = movable;
        if(movable != null)
            movable.setSquare(this);
    }

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("src/empty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a FlatSquare object.
     */
    @Override
    public BufferedImage getImage(){
        if (movable == null) return FlatSquare.image;
        else return Displayable.combineImages(FlatSquare.image,movable.getImage());
    }

    /**
     * Static getter for the image of any FlatSquare object.
     * @return The common image that all FlatSquare objects have.
     */
    public static BufferedImage getStaticImage(){
        return FlatSquare.image;
    }

    @Override
    public boolean isOver(){
        return movable == null || movable.isOver();
    }

    @Override
    public boolean enter(Movable movable, Direction direction){
        return movable.move(this,direction);
    }

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

    public boolean enter(Crate crate, Direction direction){
        if(this.movable != null){
            return false;
        }
        this.movable = crate;
        return true;
    }
}

