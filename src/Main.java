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
    public static void main(String[] args) {

    }

    public static void welcomeBanner(){
        System.out.print("   __   _        _   _            __      __          _ _     \n" +
                "  / /  | |   ___| |_| |_ ___ _ _  \\ \\    / /__ _ _ __| | |___ \n" +
                " / _ \\ | |__/ -_)  _|  _/ -_) '_|  \\ \\/\\/ / _ \\ '_/ _` | / -_)\n" +
                " \\___/ |____\\___|\\__|\\__\\___|_|     \\_/\\_/\\___/_| \\__,_|_\\___|");

    }
}
