import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class representing the crates that should be moved to a specified location (square).
 */
public class Crate extends Movable{
    private static final long serialVersionUID = -5335837897392534181L;

    static private BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("src/crate.png"));
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

    @Override
    public boolean isOver(){
        return false;
    }

    @Override
    public boolean move(FlatSquare flatSquare, Direction direction){
        boolean res = flatSquare.enter(this,direction);
        if(res)
            square = flatSquare;
        return res;
    }
}
