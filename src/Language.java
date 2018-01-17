import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The Language class responsible for handling all language-specific string in the application.
 * Provides a simple way to add any new languages.
 */
public enum Language { English("/english.txt"),  Magyar("/magyar.txt");

    private List<String> data;

    Language(String fileName) {
        BufferedReader br;
        data = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName), StandardCharsets.UTF_8));
            String line;
            do {
                line = br.readLine();
                data.add(line);
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStartNewGameText(){
        return data.get(0);
    }

    public String getAddNewLevelText(){
        return data.get(1);
    }

    public String getShowHighScoreText(){
        return data.get(2);
    }

    public String getExitText(){
        return data.get(3);
    }

    public String getLanguageSelectionText() { return data.get(4); }

    public String getOKText() { return data.get(5); }

    public String getWidthSelectionText() { return data.get(6); }

    public String getHeightSelectionText() { return data.get(7); }

    public String getSaveLevelText() { return data.get(8); }

    public String getTypeYourNameText() { return  data.get(9); }

    public String getSavingHighScoreText() { return  data.get(10); }

    public String getSaveHighScoreText() { return  data.get(11); }



    /**
     * The common language variable for all phases of the application
     */
    public static Language selectedLanguage;
}
