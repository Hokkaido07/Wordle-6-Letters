import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Wordle class that contains all the logic for the game.
 */

public class Wordle {

    private Guess guess = null;
    public WordOfTheDay wordOfTheDay;
    private final Time time = new Time();

    /**
     * Starts the process for guessing a word.
     */
    public void start() {
        guess = new Guess();
        wordOfTheDay = new WordOfTheDay();
        System.out.println("Answer Word: " + WordOfTheDay.getWord()); //This line is only used for testing purposes to check that the program is correcting matching color codes to letters
        time.start();
    }

    /**
     * This method checks whether the guess letters are equivalent to the answer letters.
     *
     * @param guess user input guess
     * @return GuessCheckResponse for whether the guess is correct, the square on the grid for the character, and whether the user needs to guess again
     */
    public GuessCheckResponse isGuessCorrect(String guess) {
        String answer = WordOfTheDay.getWord();
        if (guess.length() != answer.length()) {
            throw new IllegalArgumentException("Guess is not the same length as answer");
        }
        boolean hasIncorrect = false;
        Square[] squares = new Square[guess.length()];
        List<Integer> correctIndexes = new ArrayList<>();
        List<Character> remainingChars = new ArrayList<>();
        for (int i = 0; i < answer.length(); i++) {
            char answerChar = answer.charAt(i);
            char guessChar = guess.charAt(i);
            if (answerChar == guessChar) {
                squares[i] = new Square(answerChar, LetterState.CORRECT);
                correctIndexes.add(i);
            } else {
                remainingChars.add(answerChar);
            }
        }
        for (int i = 0; i < answer.length(); i++) {
            if (correctIndexes.contains(i)) {
                continue;
            }
            char guessChar = guess.charAt(i);
            if (remainingChars.contains(guessChar)) {
                squares[i] = new Square(guessChar, LetterState.WRONG_PLACE);
                hasIncorrect = true;
            }
        }
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) {
                char guessChar = guess.charAt(i);
                squares[i] = new Square(guessChar, LetterState.INCORRECT);
                hasIncorrect = true;
            }
        }
        return new GuessCheckResponse(!hasIncorrect, squares, false);
    }

    /**
     * This method processes the user's guess.
     *
     * @param guess user input guess
     * @return GuessCheckResponse
     */
    public GuessCheckResponse processGuess(String guess) {
        if (!this.guess.isRealWord(guess) || this.guess.isDuplicateWord(guess)) {
            return new GuessCheckResponse(false, null, true);
        }
        GuessCheckResponse isCorrect = isGuessCorrect(guess);
        this.guess.addGuess(guess);
        return isCorrect;
    }

    /**
     * This method gets the time since the game has started.
     *
     * @return amount of time since game started
     */
    public Duration getTimeSinceStarted() {
        return time.getDurationOnTimer();
    }

    /**
     * Get guess from Guess class
     *
     * @return user input guess
     */
    public Guess getGuess() {
        return guess;
    }


}


