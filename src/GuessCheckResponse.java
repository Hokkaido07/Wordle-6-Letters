public record GuessCheckResponse(boolean correct, LetterState[] states){

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
}
