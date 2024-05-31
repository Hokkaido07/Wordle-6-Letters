import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    //Declaring all ANSI color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private static final String FILE_PATH = "LastPlayedDate";
    public static Scanner scanner = new Scanner(System.in);

    private static final Wordle wordle = new Wordle();
    private static final Display display = new Display();


    public static void main(String[] args) {
        welcomeBanner();
        decideGamemode(gameMenu());
        wordle.start();
        display.show(wordle);

    }

    /**
     * This method is for printing the welcome banner at the start of the game.
     */
    public static void welcomeBanner() {
        System.out.print("""
                  __   _        _   _            __      __          _ _    \s
                 / /  | |   ___| |_| |_ ___ _ _  \\ \\    / /__ _ _ __| | |___\s
                / _ \\ | |__/ -_)  _|  _/ -_) '_|  \\ \\/\\/ / _ \\ '_/ _` | / -_)
                \\___/ |____\\___|\\__|\\__\\___|_|     \\_/\\_/\\___/_| \\__,_|_\\___|""".indent(1));

    }

    /**
     * This method is the game menu with the rules of how to play. setting the username, and asking the user to determine which mode they want to play.
     * @return user input for gamemode
     */
    public static int gameMenu() {
        System.out.println(ANSI_BOLD + "\nWelcome to 6-Letter Wordle!" + ANSI_RESET);
        System.out.println("Rules:");
        System.out.println("- Guess the letters of the 6-letter word.");
        System.out.println("- Use the following colors as hints:");
        System.out.println("  - Green: Correct letter in the correct position");
        System.out.println("  - Yellow: Correct letter in the wrong position");
        System.out.println("  - Grey: Incorrect letter");
        waitSecond();
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Welcome " + ANSI_BOLD + ANSI_CYAN + username + ANSI_RESET + " can choose to play the Once a Day Mode or the Free Play Mode.\nType " + ANSI_BLUE + "[1]" + ANSI_RESET + " for once a day mode\nType " + ANSI_PURPLE + "[2]" + ANSI_RESET + " for free play.");
        String playerAnswer = scanner.nextLine();
        return parseInt(playerAnswer);
    }

    /**
     * Decides the gamemode. If the user picked 1, it will determine whether the user has already played once today.
     * @param n user input for gamemode
     */
    public static void decideGamemode(int n) {
        if (n == 1) {
            if (new PlayedToday().isPlayedToday("LastPlayedDate")) {
                System.out.println("Sorry, you have already played today.");
                System.exit(1);
            }
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = dateFormat.format(currentDate);

            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
                writer.println(formattedDate);
            } catch (IOException e) {
                System.out.println("Error occurred while storing the date.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid input. Date not stored in file.");
        }
    }

    /**
     * This method is to slow down the display, allowing the user enough time to read the message slowly.
     */
    public static void waitSecond() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


}
