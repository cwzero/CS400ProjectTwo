// --== CS400 File Header Information ==--
// Name: Eric Gjerde
// Email: egjerde@wisc.edu
// Team: DE blue
// Role: Frontend Developer
// TA: Dan Kiel
// Lecturer: Florian Heimerl

import java.util.Scanner;

/*
    The frontend class is the interface used to run the app.
 */
public class Frontend {
        /*
        The main method is the entrypoint, it constructs a backend and calls the run method.
         */
    public static void main(String[] args) {
        System.out.println("Welcome to the video game high score manager app.");
        BackendInterface backendInterface = new BackendDummy();
        Frontend frontend = new Frontend();
        frontend.init();
        frontend.run(backendInterface);
    }

    private Scanner input;

    /*
    This was a quick fix for needing to create the scanner after the System.setIn in the tests
     */
    public void init() {
        input = new Scanner(System.in);
    }

    /*
    The core of the frontend.  Prompts for commands to run, calls the appropriate method, recursive.
     */
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

    /*
    Part of the output format, creates headers for the table the scores will be shown in
     */
    public void printHeaders(BackendInterface backendInterface) {
        System.out.format("%-16s\t%-8s\t%-8s\t%-16s\t%-28s\n",
                "Player",
                "Time",
                "Verified",
                "Platform",
                "Date Completed");
    }

    /*
    Prints an individual row in the table for a given record
     */
    public void printScore(BackendInterface backendInterface, HighScoreInterface highScore) {
        System.out.format("%-16s\t%-8.2f\t%-8b\t%-16s\t%-28s\n",
                highScore.getPlayer(),
                highScore.getTime(),
                highScore.isVerified(),
                highScore.getPlatform(),
                highScore.getDateRunCompleted().toString());
    }

    /*
    Command method: a = get all scores
     */
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

    /*
    Command method: h = get high scores(under a given time)
     */
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

    /*
    Command method: f = get fastest score
     */
    public void getFastestScore(BackendInterface backendInterface) {
        System.out.println("Fastest Score:");

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getFastestScore());
        System.out.println();

        run(backendInterface);
    }

    /*
    Command method: s = get slowest score
     */
    public void getSlowestScore(BackendInterface backendInterface) {
        System.out.println("Slowest Score:");

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getSlowestScore());
        System.out.println();

        run(backendInterface);
    }

    /*
    Command method: s = get slowest score
     */
    public void searchByUser(BackendInterface backendInterface) {
        System.out.print("Enter User: ");
        String user = input.nextLine().trim().toLowerCase();
        System.out.println("Searching by User (" + user + "):");

        System.out.println();
        printHeaders(backendInterface);
        printScore(backendInterface, backendInterface.getPlayerScore(user));
        System.out.println();

        run(backendInterface);
    }

    /*
    Command method: x = exit
     */
    public void exit() {
        input.close();
    }
}
