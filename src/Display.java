public class Display {
    private Square[][] grid;
    private Wordle wordle;


    public Display() {
        grid = new Square[6][6];
        initializeGrid();
    }

    public void show (Wordle wordle){
        var durationOnTimer = wordle.getTimeSinceStarted();
        this.wordle = wordle;
        printGrid();
        int guess = 0;
        while(true){
            var response = getPlayerGuess();
            int end = processGuess(response, guess);
            if(end == 1){
                System.out.println("You have gotten the word!\nTime taken was: "+ durationOnTimer.toMinutes() + " minutes and " + durationOnTimer.toSecondsPart() + " seconds.");
                break;
            } else if(end == -1){
                System.out.println("Sorry, you have ran out of guesses\n." +
                        "the answer word was: " + wordle.getWordOfTheDay());
                break;
            }
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

    public int processGuess(String playersGuess, int index) {
        var response = wordle.processGuess(playersGuess);
        var rowForGuess = grid[index];
        for (int i = 0; i < response.squares().length; i++) {
            rowForGuess[i] = response.squares()[i];
        }
        printGrid();
        if(response.correct()){
            return 1;
        }
        if(wordle.getGuess().getTotalGuesses() == 7){
            return -1;
        }
        return 0;
    }

    public void printGrid() {
        clearScreen();
        System.out.println(
                " __      __          _ _     \n" +
                        " \\ \\    / /__ _ _ __| | |___ \n" +
                        "  \\ \\/\\/ / _ \\ '_/ _` | / -_)\n" +
                        "   \\_/\\_/\\___/_| \\__,_|_\\___|"
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

}

