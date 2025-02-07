import java.util.Scanner;

public class GameUtils {

    private static final Scanner scanner = new Scanner(System.in);

    protected int getUserInputAsInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    protected String getUserInputAsString() {
        return scanner.next();
    }

    protected void printStarDivider() {
        char c = '*';
        for (int i = 1; i <= 50; i++) {
            System.out.print(c);
        }
        System.out.println(); // Move to the next line after printing stars
    }
}