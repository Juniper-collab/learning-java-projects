import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class HighscoreManager {

    private static final String FILE_NAME = "highscores.properties";
    private final Properties properties = new Properties();

    public HighscoreManager() {
        load();
    }

    private void load() {
        try(FileInputStream in = new FileInputStream(FILE_NAME)) {
            properties.load(in);
        } catch (IOException e){
            //Datei existiert beim ersten Start noch nicht.
        }
    }

    private void save() {
        try(FileOutputStream out = new FileOutputStream(FILE_NAME)) {
            properties.store(out, "Highscores for Number Guess Game");
        } catch (IOException e) {
            System.out.println("Konnte Highscores nicht speichern: " + e.getMessage());
        }
    }

    public List<Integer> getHighscores(String difficultyKey) {
        String value = properties.getProperty(difficultyKey);
        List<Integer> scores = new ArrayList<>();

        if (value != null && !value.isEmpty()) {
            String[] parts = value.split(",");
            for (String p : parts) {
                try {
                    scores.add(Integer.parseInt(p.trim()));
                } catch (NumberFormatException ignored){

                }
            }
        }

        Collections.sort(scores);
        return scores;

    }

    public void updateHighscores(String difficultyKey, int attempts) {
        List<Integer> scores = getHighscores(difficultyKey);

        scores.add(attempts);
        Collections.sort(scores);

        while (scores.size() > 5) {
            scores.remove(scores.size() - 1);
        }

        properties.setProperty(difficultyKey, join(scores));
        save();

        System.out.println("Aktuelle Top 5: " + scores);
    }

        private String join(List<Integer> scores) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                sb.append(scores.get(i));
                if (i < scores.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
}
