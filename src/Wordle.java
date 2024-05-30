import java.time.Duration;

public class Wordle {

    private Guess guess = null;
    public WordOfTheDay wordOfTheDay;
    private Time time = new Time();

    public void start() {
        guess = new Guess();
        wordOfTheDay = new WordOfTheDay();
        System.out.println("Answer Word: " + wordOfTheDay.getWord());
        time.start();
    }

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
            } else {
                state = LetterState.INCORRECT;
                hasIncorrect = true;
            }
            Square square = new Square(guessChar, state);
            squares[i] = square;
        }

        return new GuessCheckResponse(!hasIncorrect, squares);
    }


    public GuessCheckResponse processGuess(String guess) {
        GuessCheckResponse isCorrect = isGuessCorrect(guess);
        this.guess.addGuess(guess);
        return isCorrect;

    }

    public Duration getTimeSinceStarted(){
        return time.getDurationOnTimer();
    }

    public Guess getGuess(){
        return guess;
    }

    public WordOfTheDay getWordOfTheDay() {
        return wordOfTheDay;
    }

    public Time getTime() {
        return time;
    }
}


