import java.util.Scanner;

// Frontend class - currently a stub
public class Frontend {
    public static void main(String[] args) {
        System.out.println("Welcome to the video game high score manager app.");
        BackendInterface backendInterface = new BackendDummy();
        new Frontend().run(backendInterface);
    }

    private Scanner input;

    public Frontend() {
        input = new Scanner(System.in);
    }

    public void run(BackendInterface backendInterface) {
        System.out.println("Commands:");
        System.out.println("a: Get all scores");
        System.out.println("h: Get high scores");
        System.out.println("f: Get fastest score");
        System.out.println("s: Get slowest score");
        System.out.println("u: Search by user");
        System.out.println("x: Exit");
        System.out.print("Enter a command: ");

        String command = input.nextLine();

        switch(command.trim().toLowerCase().charAt(0)) {
            case 'a':
                getAllScores(backendInterface);
                break;
            case 'h':
                getHighScores(backendInterface);
                break;
            case 'f':
                getFastestScore(backendInterface);
                break;
            case 's':
                getSlowestScore(backendInterface);
                break;
            case 'u':
                searchByUser(backendInterface);
                break;
            case 'x':
                exit();
                break;
            default:
                System.out.println("Command not recognized.");
                run(backendInterface);
                break;
        }
    }

    public void printHeaders(BackendInterface backendInterface) {
        System.out.format("%-16s\t%-8s\t%-8s\t%-16s\t%-28s\n",
                "Player",
                "Time",
                "Verified",
                "Platform",
                "Date Completed");
    }

    public void printScore(BackendInterface backendInterface, HighScoreInterface highScore) {
        System.out.format("%-16s\t%-8.2f\t%-8b\t%-16s\t%-28s\n",
                highScore.getPlayer(),
                highScore.getTime(),
                highScore.isVerified(),
                highScore.getPlatform(),
                highScore.getDateRunCompleted().toString());
    }

    public void getAllScores(BackendInterface backendInterface) {
        System.out.println("All Scores:");
        System.out.println();
        printHeaders(backendInterface);
        for (HighScoreInterface highScore : backendInterface.getAllScores()) {
            printScore(backendInterface, highScore);
        }
        System.out.println();
        run(backendInterface);
    }

    public void getHighScores(BackendInterface backendInterface) {
        System.out.print("Enter time to request: ");
        double time = Double.parseDouble(input.nextLine().trim());
        System.out.println("High Scores:");
        System.out.println();
        printHeaders(backendInterface);
        for (HighScoreInterface highScore : backendInterface.getHighScores(time)) {
            printScore(backendInterface, highScore);
        }
        System.out.println();
        run(backendInterface);
    }

    public void getFastestScore(BackendInterface backendInterface) {
        System.out.println("Fastest Score:");

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getFastestScore());
        System.out.println();

        run(backendInterface);
    }

    public void getSlowestScore(BackendInterface backendInterface) {
        System.out.println("Slowest Score:");

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getSlowestScore());
        System.out.println();

        run(backendInterface);
    }

    public void searchByUser(BackendInterface backendInterface) {
        System.out.print("Enter User: ");
        String user = input.nextLine().trim().toLowerCase();

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getPlayerScore(user));
        System.out.println();

        run(backendInterface);
    }

    public void exit() {
        input.close();
    }
}
