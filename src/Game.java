import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int secretNumber;
    private Scanner scanner;

    public Game() {
        Random random = new Random();
        secretNumber = random.nextInt(100) +1;
        scanner = new Scanner(System.in);
    }

    public void start() {


        System.out.println("Willkommen zum Zahlenratespiel! Kannst du das Spiel gewinnen?");

        boolean playAgain = false;
        do {

            int attempts = 0;

            System.out.println("Wähle einen Schwierigkeitsgrad:");
            System.out.println("1 = leicht (1-50)");
            System.out.println("2 = mittel (1-100)");
            System.out.println("3 = schwer (1-500)");
            System.out.println("Deine Wahl: ");

            int choice = scanner.nextInt();

            int max;

            String difficultyKey;

            if (choice == 1) {
                max = 50;
                difficultyKey = "easy";
            } else if (choice == 3) {
                max = 500;
                difficultyKey = "hard";
            } else {
                max = 100;
                difficultyKey = "medium";
            }

            List<Integer> highscores = highscoreManager.getHighscores(difficultyKey);

            if (highscores.isEmpty()) {
                System.out.println("Noch keine Highsores vorhanden.");
            } else {
                System.out.println("Top 5 Highscores in der Kategorie "+difficultyKey+": "+ highscores);
            }

            secretNumber = new Random().nextInt(max) + 1;

            System.out.println("Rate eine Zahl zwischen 1 und " + max + ".");


            int guess = 0;

            while (guess != secretNumber) {

                attempts++;

                System.out.println("An welche Zahl denkst du?: ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Bitte eine Zahl eingeben: ");
                    scanner.next();
                }
                guess = scanner.nextInt();

                if (guess > max || guess < 0) {
                    System.out.print("Bitte eine Zahl zwischen 1 und " + max + " wählen! ");
                } else if (guess == 0) {
                    System.out.print("Null gilt nicht! :P ");
                } else if (guess < secretNumber) {
                    System.out.println("Zu klein! ");
                } else if (guess > secretNumber) {
                    System.out.println("Zu groß! ");
                } else {
                    System.out.println("Richtig! Omedeto! ");
                    System.out.println("Du hast " + attempts + " Versuche gebraucht. ");
                    highscoreManager.updateHighscores(difficultyKey, attempts);

                    playAgain = askToPlayAgain();
                }
            }
        }while (playAgain);

            System.out.println("Danke fürs Spielen! ");

    }

    private boolean askToPlayAgain(){
        System.out.print("Nochmal spielen? (j/n): ");
        String input = scanner.next().trim().toLowerCase();
        return input.equals("j") || input.equals("ja") || input.equals("y") || input.equals("yes");
    }

    private HighscoreManager highscoreManager = new HighscoreManager();
}
