import java.util.List;
import java.util.Random;

/**
 * This class is used to generate the Word of the Day by randomly choosing a word from the Word bank.
 */
public class WordOfTheDay {
    private String word;

    public WordOfTheDay() {
        FileParse fileParse = new FileParse();
        List<String> words = fileParse.getAllLinesFromFile("WordBank");
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
