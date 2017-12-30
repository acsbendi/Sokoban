import java.io.Serializable;

/**
 * Simple class representing a single record in the database of highscores.
 */
public class Record implements Serializable{
    public int levelIndex;
    public int numberOfMoves;
    public String nameOfPlayer;

    /**
     * Returns the data contained in a record as a string, in readable format.
     * @return The string that contains all the data of this Record object.
     */
    public String toString(){
        return Integer.toString(levelIndex) + " " + Integer.toString(numberOfMoves) + " " + nameOfPlayer;
    }
}
