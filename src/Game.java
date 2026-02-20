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
        System.out.println("Wähle einen Schwierigkeitsgrad:");
        System.out.println("1 = leicht (1-50)");
        System.out.println("2 = mittel (1-100)");
        System.out.println("3 = schwer (1-500)");
        System.out.println("Deine Wahl: ");

        int choice = scanner.nextInt();

        int max;
        if (choice == 1){
            max = 50;
        } else if (choice == 3){
            max = 500;
        } else {
            max = 100;
        }

        secretNumber = new Random().nextInt(max) +1;

        System.out.println("Rate eine Zahl zwischen 1 und "+max+".");


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
