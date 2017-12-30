import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class representing the square onto which the crates and the player can be moved, but is not a destination for the crates.
 */
public class Floor extends FlatSquare {
    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("src/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a Floor object.
     */
    @Override
    public BufferedImage getImage(){
        if (movable == null) return Floor.image;
        else return Displayable.combineImages(Floor.image,movable.getImage());
    }

    /**
     * Static getter for the image of any Floor object.
     * @return The common image that all Floor objects have.
     */
    public static BufferedImage getStaticImage(){
        return Floor.image;
    }


    Floor(Movable movable){
        super(movable);
    }
}
