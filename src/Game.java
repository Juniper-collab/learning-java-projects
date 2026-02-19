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

        int attempts = 0;

        System.out.println("Willkommen zum Zahlenratespiel! Kannst du das Spiel gewinnen?");
        System.out.println("Rate eine Zahl zwischen 1 und 100.");


        int guess = 0;

        while (guess != secretNumber){

            attempts++;

            System.out.println("An welche Zahl denkst du?: ");
            guess = scanner.nextInt();

            if (guess < secretNumber){
                System.out.println("Zu klein!");
            } else if (guess > secretNumber){
                System.out.println("Zu groß!");
            } else {
                System.out.println("Richtig! Omedeto!");
                System.out.println("Du hast " + attempts + " Versuche gebraucht.");
            }
        }
    }
}
