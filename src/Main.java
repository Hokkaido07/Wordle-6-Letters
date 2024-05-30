import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    //Declaring all ANSI color codes

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
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
        decideGamemode(gameRules());
        wordle.start();
        display.show(wordle);

    }

    public static void welcomeBanner() {
        System.out.print("   __   _        _   _            __      __          _ _     \n" +
                "  / /  | |   ___| |_| |_ ___ _ _  \\ \\    / /__ _ _ __| | |___ \n" +
                " / _ \\ | |__/ -_)  _|  _/ -_) '_|  \\ \\/\\/ / _ \\ '_/ _` | / -_)\n" +
                " \\___/ |____\\___|\\__|\\__\\___|_|     \\_/\\_/\\___/_| \\__,_|_\\___|");

    }

    public static int gameRules() {
        System.out.println(ANSI_BOLD + "\nWelcome to 6-Letter Wordle!" + ANSI_RESET);
        System.out.println("Rules:");
        System.out.println("- Guess the letters of the hidden word.");
        System.out.println("- Use the following colors as hints:");
        System.out.println("  - Green: Correct letter in the correct position");
        System.out.println("  - Yellow: Correct letter in the wrong position");
        waitSecond();
        System.out.println("You can choose to play the Once a Day Mode or the Free Play Mode.\nType " + ANSI_BLUE + "[1]" + ANSI_RESET + " for once a day mode\nType " + ANSI_PURPLE + "[2]" + ANSI_RESET + " for free play.");
        return scanner.nextInt();
    }

    public static void decideGamemode(int n) {
        if (n == 1) {
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


    public static void waitSecond() { //This method is used to delay the printing of the next line to allow the user enough time to read the message.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


}
