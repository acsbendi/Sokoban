import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;

/**
 * The class representing the player, who can can move crates.
 */
public class Player extends Movable implements KeyListener {
    private static final long serialVersionUID = 1693030520995343601L;

    static private BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File("src/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    private Level level;
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    Player(Level level) {
        this.level = level;
    }

    /**
     * Getter for the image of this instance.
     *
     * @return The image of a Player object.
     */
    @Override
    public BufferedImage getImage() {
        return Player.image;
    }

    /**
     * Static getter for the image of any Player object.
     *
     * @return The common image that all Player objects have.
     */
    static BufferedImage getStaticImage() {
        return Player.image;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @param e The {@code KeyEvent} object containing the pressed key's code.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_RIGHT:
                handleMove(Direction.Right);
                break;
            case KeyEvent.VK_LEFT:
                handleMove(Direction.Left);
                break;
            case KeyEvent.VK_DOWN:
                handleMove(Direction.Down);
                break;
            case KeyEvent.VK_UP:
                handleMove(Direction.Up);
                break;
        }
    }

    /**
     * The method for handling a single move made by the user. The move may not be valid.
     *
     * @param direction The direction in which the user tries to make a move.
     */
    private void handleMove(Direction direction) {
        Square destinationSquare = level.getNeighbor(square, direction);
        if (destinationSquare == null) return;
        destinationSquare.enter(this, direction);
    }

    @Override
    public boolean move(FlatSquare flatSquare, Direction direction) {
        if (flatSquare.enter(this, direction)) {
            square.setMovable(null);
            game.refreshSquare(square);
            game.refreshSquare(flatSquare);
            square = flatSquare;
            game.onMove();
            return true;
        }
        else return false;
    }
}
