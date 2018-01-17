import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The class representing the square onto which the crates should be moved.
 */
public class DestinationFloor extends FlatSquare {
    private static final long serialVersionUID = -1870089800750473926L;

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(DestinationFloor.class.getResourceAsStream("/destination_floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a DestinationFloor object.
     */
    @Override
    public BufferedImage getImage(){
        if (movable == null) return DestinationFloor.image;
        else return Displayable.combineImages(DestinationFloor.image,movable.getImage());
    }

    /**
     * Static getter for the image of any DestinationFloor object.
     * @return The common image that all DestinationFloor objects have.
     */
    public static BufferedImage getStaticImage(){
        return DestinationFloor.image;
    }

    DestinationFloor(Movable movable){
        super(movable);
    }

    @Override
    public boolean isOver(){
        return true;
    }
}
