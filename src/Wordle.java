import java.util.ArrayList;

public class Wordle {

    private Guess guess = null;
    public WordOfTheDay wordOfTheDay;
    private ArrayList<String> guesses;

    public void start(){
        guess = new Guess();
        wordOfTheDay = new WordOfTheDay();
    }



    public GuessCheckResponse processGuess(String guess){
        GuessCheckResponse isCorrect = isGuessCorrect(guess);
        guesses.add(guess);
        return isCorrect;

    }





}


