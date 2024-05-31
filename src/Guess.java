import java.util.Arrays;

public class Guess {
    private static final Character[][] letters = new Character[6][6];
    private int guessCount;

    public boolean addGuess(String word) {
        if (guessCount < 6) {
            letters[guessCount] = convertWordToCharacters(word);
            guessCount++;
            return true;
        } else {
            return false;
        }
    }

    public int getTotalGuesses() {
        return guessCount;
    }

    public Character[][] getGuesses() {
        return letters;
    }

    public boolean isSixLettersLong() {
        return guessCount == 6;
    }

    public boolean isRealWord(String guess) {
        FileParse fileparse  = new FileParse();
        return fileparse.isWordInFile(guess, "WordBank");

    }

    public boolean isDuplicateWord(String guess) {
        var wordCharArray = convertWordToCharacters(guess);
        for (var guesses : getGuesses()) {
            if (Arrays.deepEquals(guesses, wordCharArray)) {
                return true;
            }
        }
        return false;
    }

    private Character[] convertWordToCharacters(String word) {
        Character[] characters = new Character[7];
        for (int i = 0; i < word.length(); i++) {
            characters[i] = word.charAt(i);
        }
        return characters;
    }
}
