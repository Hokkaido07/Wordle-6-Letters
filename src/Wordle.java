import java.time.Duration;

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
        System.out.println("Answer Word: " + wordOfTheDay.getWord()); //This line is only used for testing purposes to check that the program is correcting matching color codes to letters
        time.start();
    }

    /**
     * This method checks whether the guess letters are equivalent to the answer letters.
     * @param guess user input guess
     * @return GuessCheckResponse for whether the guess is correct, the square on the grid for the character, and whether the user needs to guess again
     */
    public GuessCheckResponse isGuessCorrect(String guess) {
        String answer = wordOfTheDay.getWord();
        if (guess.length() != answer.length()) {
            throw new IllegalArgumentException("Guess is not the same length as answer");
        }

        boolean hasIncorrect = false;
        Square[] squares = new Square[guess.length()];

        for (int i = 0; i < answer.length(); i++) {
            char answerChar = answer.charAt(i);
            char guessChar = guess.charAt(i);
            LetterState state;
            if (answerChar == guessChar) {
                state = LetterState.CORRECT;
            } else if (answer.contains(guessChar + "")) {
                state = LetterState.WRONG_PLACE;
                hasIncorrect = true;
            } else {
                state = LetterState.INCORRECT;
                hasIncorrect = true;
            }
            Square square = new Square(guessChar, state);
            squares[i] = square;
        }

        return new GuessCheckResponse(!hasIncorrect, squares, false);
    }

    /**
     * This method processes the user's guess.
     * @param guess user input guess
     * @return
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
     * @return amount of time since game started
     */
    public Duration getTimeSinceStarted() {
        return time.getDurationOnTimer();
    }

    /**
     * Get guess from Guess class
     * @return user input guess
     */
    public Guess getGuess() {
        return guess;
    }

    /**
     * Get word of the day from WordOfTheDay class
     * @return String for wordOfTheDay
     */
    public WordOfTheDay getWordOfTheDay() {
        return wordOfTheDay;
    }

}


