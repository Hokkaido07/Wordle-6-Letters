import java.util.Arrays;

public class Guess {
    private final Character[][] letters = new Character[6][6];
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

    public static boolean isRealWord() {
        StringBuilder wordBuilder = new StringBuilder();
        for (Character[] row : letters) {
            for (Character letter : row) {
                if (letter != null) {
                    wordBuilder.append(letter);
                }
            }
        }
        String word = wordBuilder.toString();

        FileParse fileParse = new FileParse();
        return fileParse.isWordInFile(word, "WordBank");
    }

    public boolean isDuplicateWord(Guess[] previousGuesses) {
        for (Guess guess : previousGuesses) {
            if (Arrays.deepEquals(guess.letters, this.letters)) {
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
