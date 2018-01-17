import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The class representing the crates that should be moved to a specified location (square).
 */
public class Crate extends Movable{
    private static final long serialVersionUID = -5335837897392534181L;

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(Crate.class.getResourceAsStream("/crate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the image of this instance.
     * @return The image of a Crate object.
     */
    @Override
    public BufferedImage getImage() {
        return Crate.image;
    }

    /**
     * Static getter for the image of any Crate object.
     * @return The common image that all Crate objects have.
     */
    public static BufferedImage getStaticImage(){ return  Crate.image; }

    /**
     * Crate can stop the game from ending (if they are not on the right {@code Square}.
     * @return False.
     */
    @Override
    public boolean isOver(){
        return false;
    }

    /**
     * Method for moving a {@code Crate} into a {@code FlatSquare}. It may not be successful.
     * @param flatSquare The destination of this moving {@code Crate} object.
     * @param direction The direction of the attempted move.
     * @return Returns whether the move was successful.
     */
    @Override
    public boolean move(FlatSquare flatSquare, Direction direction){
        boolean res = flatSquare.enter(this,direction);
        if(res)
            square = flatSquare;
        return res;
    }
}
