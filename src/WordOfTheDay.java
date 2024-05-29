import java.util.List;
import java.util.Random;

public class WordOfTheDay {
    private String word;

    public WordOfTheDay() {
        FileParse fileParse = new FileParse();
        List<String> words = fileParse.getAllLinesFromFile("WordBank.txt");
        if (!words.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(words.size());
            word = words.get(randomIndex);
        }
    }

    public String getWord() {
        return word;
    }
}
