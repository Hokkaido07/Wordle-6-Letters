public class Display {
    private final Square[][] grid;
    private Wordle wordle;


    public Display() {
        grid = new Square[6][6];
        initializeGrid();
    }

    public void show(Wordle wordle) {
        this.wordle = wordle;

            printGrid();
            int guess = 0;
            while (true){
                var response = getPlayerGuess();
                int end = processGuess(response, guess);
                if (end == 1) {
                    var durationOnTimer = wordle.getTimeSinceStarted();
                    System.out.println("You have guessed the word!\nTime taken was: " + durationOnTimer.toMinutes() + " minutes and " + durationOnTimer.toSecondsPart() + " seconds.");
                    return;

                } else if (end == -1) {
                    System.out.println("Sorry, you have run out of guesses.\nThe answer word was: " + wordle.getWordOfTheDay());
                    return;
                } else if (end == -2) {
                    System.out.println(Main.ANSI_RED + Main.ANSI_BOLD + "Your word is either invalid or already guessed. Please try again. " + Main.ANSI_RESET);
                    continue;
                }
                guess++;
    }

}

    /**
     * This method initializes a grid of [6][6] using a 2D array.
     */
    private void initializeGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = null;
            }
        }
    }

    /**
     * This method takes a guess from the player.
     * @return input from user
     */
    public String getPlayerGuess() {
        System.out.println("Please enter a word: ");
        return Main.scanner.nextLine();
    }

    /**
     * This method processes the guess and checks whether the user has entered an invalid word, correctly guessed the word or run out of tries.
     * @param playersGuess user input guess
     * @param index grid index
     * @return integer for whether the guess is correct
     */
    public int processGuess(String playersGuess, int index) {
        var response = wordle.processGuess(playersGuess);
        if (response.guessAgain()) {
            return -2;
        }
        var rowForGuess = grid[index];
        for (int i = 0; i < response.squares().length; i++) {
            rowForGuess[i] = response.squares()[i];
        }
        printGrid();
        if (response.correct()) {
            return 1;
        }
        if (wordle.getGuess().getTotalGuesses() == 6) {
            return -1;
        }
        return 0;
    }

    /**
     * This method prints the Wordle grid.
     */
    public void printGrid() {
        clearScreen();
        System.out.println(
                """
                        __      __          _ _    \s
                        \\ \\    / /__ _ _ __| | |___\s
                         \\ \\/\\/ / _ \\ '_/ _` | / -_)
                          \\_/\\_/\\___/_| \\__,_|_\\___|""".indent(1)
        );

        System.out.println("+-+-+-+-+-+-+");
        for (Square[] squares : grid) {
            System.out.print("|");
            for (Square square : squares) {
                if (square == null) {
                    System.out.print(" |");
                } else {
                    String letter = String.valueOf(square.getLetter());
                    LetterState state = square.getState();
                    var color = getColorForState(state);
                    System.out.print(color + letter + Main.ANSI_RESET + "|");
                }
            }
            System.out.println();
            System.out.println("+-+-+-+-+-+-+");
        }
        var durationOnTimer = wordle.getTimeSinceStarted();
        System.out.println("Time: " + durationOnTimer.toMinutes() + " minutes and " + durationOnTimer.toSecondsPart() + " seconds.");
    }

    /**
     * This method gets the colors for the different states of a letter
     * @param state State of the letter
     * @return String of corresponding color code
     */
    public String getColorForState(LetterState state) {
        return switch (state) {
            case CORRECT -> "\u001B[32m"; // Green color code
            case WRONG_PLACE -> "\u001B[33m"; // Yellow color code
            case INCORRECT -> "\u001B[37m"; // Grey color code
        };
    }

    /**
     * This method clears the screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}

