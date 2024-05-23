import java.util.ArrayList;

public class Display {
    private char[][] grid;
    private final String GREEN_CHAR = '\u001B' + "[32m";     // Green color code
    private final String YELLOW_CHAR = '\u001B' + "[33m";    // Yellow color code
    private final String GREY_CHAR = '\u001B' + "[37m";      // Grey color code
    private final String RESET_CHAR = '\u001B' + "[0m";      // Reset color code

    public Display() {
        grid = new char[6][6];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = ' ';
            }
        }
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
        for (int i = 0; i < 6; i++) {
            System.out.print("|");
            for (int j = 0; j < 6; j++) {
                System.out.print("  " + grid[i][j] + "   |");
            }
            System.out.println("\n+------+------+------+------+------+------+");
        }
    }

    public void updateGrid(ArrayList<Character> word, ArrayList<Boolean> correctPositions) {
        for (int i = 0; i < word.size(); i++) {
            String colorChar = GREY_CHAR;

            for (int j = 0; j < word.size(); j++) {
                if (correctPositions.get(i)) {
                    if (word.get(i) == grid[i][j]) {
                        colorChar = GREEN_CHAR;
                    } else {
                        colorChar = YELLOW_CHAR;
                    }
                }

                //grid[i][j] = colorChar.charAt(0) + word.get(i) + RESET_CHAR;
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

