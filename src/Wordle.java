import java.util.ArrayList;

public class Wordle {

    private Guess guess = null;
    private WordOfTheDay wordOfTheDay;
    private ArrayList<String> guesses;

    public void start(){
        guess = new Guess();
        wordOfTheDay = new WordOfTheDay();
    }

    private record GuessCheckResponse(boolean correct, LetterState[] states){

    }

    public GuessCheckResponse isGuessCorrect(String guess){
        String answer = wordOfTheDay.getWord();
        if(guess.length() !=answer.length()){
            throw new IllegalArgumentException("Guess is not the same length as answer");
        }

        boolean hasIncorrect = false;
        LetterState[] states = new LetterState[guess.length()];

        for(int i = 0; i<answer.length(); i++){
            char answerChar = answer.charAt(i);
            char guessChar = guess.charAt(i);
            if(answerChar == guessChar){
                states[i] = LetterState.CORRECT;
            } else if (answer.contains(guessChar + "")){
                states[i] = LetterState.WRONG_PLACE;
            } else {
                states[i] = LetterState.INCORRECT;
                hasIncorrect = true;
            }
        }
        return new GuessCheckResponse(hasIncorrect, states);
    }

    public void processGuess(String guess){
        GuessCheckResponse isCorrect = isGuessCorrect(guess);
        guesses.add(guess);
        ArrayList<Character> word = Guess.getGuesses();
        ArrayList<Boolean> correctPositions = getCorrectPositions(guess);

    }





}


