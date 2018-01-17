import java.io.Serializable;

/**
 * Simple class representing a single record in the database of highscores.
 *
 * @author Bendeguz Acs
 */
public class Record implements Serializable{
    private final long levelID;
    private final int numberOfMoves;
    private final String nameOfPlayer;

    /**
     * Constructs a new record from every necessary identifying data.
     * @param levelID The levelID of the level on which the number of moves was recorded.
     * @param numberOfMoves The number of moves to be recorded.
     * @param nameOfPlayer The player who played the level in a specified number of moves.
     */
    public Record(long levelID, int numberOfMoves, String nameOfPlayer){
        this.levelID = levelID;
        this.numberOfMoves = numberOfMoves;
        this.nameOfPlayer = nameOfPlayer;
    }

    /**
     * Returns the data contained in a record as a string, in readable format.
     * @return The string that contains all the data of this Record object.
     */
    public String toString(){
        return Long.toString(levelID) + " " + Integer.toString(numberOfMoves) + " " + nameOfPlayer;
    }
}
