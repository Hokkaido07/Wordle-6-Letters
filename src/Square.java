public class Square {

    private char letter;
    private LetterState state;

    public Square(char letter, LetterState state) {
        this.letter = letter;
        this.state = state;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public void setState(LetterState state) {
        this.state = state;
    }

    public LetterState getState() {
        return state;
    }

}

