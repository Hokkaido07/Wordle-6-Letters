public class Display {
    private Square[][] grid;
    private static final Wordle wordle = new Wordle();

    public Display() {
        grid = new Square[6][6];
        initializeGrid();
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
        for (int i = 0; i < grid.length; i++) {
            System.out.print("|");
            for (int j = 0; j < grid[i].length; j++) {
                Square square = grid[i][j];
                String letter = String.valueOf(square.getLetter());
                LetterState state = square.getState();
                var color = getColorForState(state);
                System.out.println(" " + color + letter + "|");
            }
        }
        System.out.println("\n+------+------+------+------+------+------+");
    }


    public String getColorForState(LetterState state) {
        return switch (state) {
            case CORRECT -> "\u001B[32m"; // Green color
            case WRONG_PLACE -> "\u001B[33m"; // Yellow color
            case INCORRECT -> "\u001B[37m"; // Grey color
        };
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

