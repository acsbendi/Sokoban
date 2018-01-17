import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * The class representing a single wall block by which the actual level should be surrounded.
 */
public class Wall implements Square, Serializable {
    private static final long serialVersionUID = 6768454871593543498L;

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(Wall.class.getResourceAsStream("/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a Wall object.
     */
    public BufferedImage getImage(){
        return Wall.image;
    }

    /**
     * Static getter for the image of any Wall object.
     * @return The common image that all Wall objects have.
     */
    public static BufferedImage getStaticImage(){
        return Wall.image;
    }

    @Override
    public boolean isOver(){
        return true;
    }

    @Override
    public boolean enter(Movable movable,Direction direction){
        return false;
    }
}
