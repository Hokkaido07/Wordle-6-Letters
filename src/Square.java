/**
 * This class is used to store information for one "square" on the grid, which represents one letter.
 */
public class Square {

    private final char letter;
    private final LetterState state;

    public Square(char letter, LetterState state) {
        this.letter = letter;
        this.state = state;
    }

    public char getLetter() {
        return letter;
    }

    public LetterState getState() {
        return state;
    }

}

