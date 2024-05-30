public class Display {
    private Square[][] grid;
    private Wordle wordle;


    public Display() {
        grid = new Square[6][6];
        initializeGrid();
    }

    public void show (Wordle wordle){
        this.wordle = wordle;
        printGrid();
        int guess = 0;
        while(true){
            var response = getPlayerGuess();
            processGuess(response, guess);
            guess++;
        }
    }

    private void initializeGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = null;
            }
        }
    }

    public String getPlayerGuess() {
        System.out.println("Please enter a word: ");
        String guess = Main.scanner.nextLine();
        return guess;
    }

    public void processGuess(String playersGuess, int index) {
        var response = wordle.processGuess(playersGuess);
        var rowForGuess = grid[index];
        for (int i = 0; i < response.squares().length; i++) {
            rowForGuess[i] = response.squares()[i];
        }
        printGrid();
    }

    public void printGrid() {
        clearScreen();
        System.out.println(
                " __      __          _ _     \n" +
                        " \\ \\    / /__ _ _ __| | |___ \n" +
                        "  \\ \\/\\/ / _ \\ '_/ _` | / -_)\n" +
                        "   \\_/\\_/\\___/_| \\__,_|_\\___|"
        );

        System.out.println("+------+------+------+------+------+------+");
        for (Square[] squares : grid) {
            System.out.print("|");
            for (Square square : squares) {
                String letter = String.valueOf(square.getLetter());
                LetterState state = square.getState();
                var color = getColorForState(state);
                System.out.print(color + letter + "|");
            }
            System.out.println();
            System.out.println("+------+------+------+------+------+------+");
        }
        var durationOnTimer = wordle.getTimeSinceStarted();
        System.out.println("Time: " + durationOnTimer.toMinutes() + " minutes and " + durationOnTimer.toSecondsPart());
    }


    public String getColorForState(LetterState state) {
        return switch (state) {
            case CORRECT -> "\u001B[32m"; // Green color code
            case WRONG_PLACE -> "\u001B[33m"; // Yellow color code
            case INCORRECT -> "\u001B[37m"; // Grey color code
        };
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void endGame(Square[] square){
        while (wordle.getGuess().getTotalGuesses() < 8) {
            String guess = getUserInput();
            var response = wordle.processGuess();
            // Process the response
            printGrid();
            if (conditionToCheckIfAnswerWasCorrect == true) {
                // Correct answer, so escape the loop!
                break;
            }
        }
    }
}

