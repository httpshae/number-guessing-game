import java.util.Random;

public class GameRunner {
    private static final GameUtils gameUtils = new GameUtils();
    
    public static void runGame() {
        printWelcomeMessage();
        printGameRules();
        setDifficultyLevel();
        generateMagicNumber();
        runGuessingLogic();
        
        if(promptPlayAgain()) {
            restartGame();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    private static void runGuessingLogic(){
        while(!NumberGuessingGame.isGameOver()) {
            if(NumberGuessingGame.getChances() == 0) {
                endGame(EndGameCondition.LOSER);
            } else {
                evaluateGuess(promptGuess());
            }
        }
    }

    private static void printWelcomeMessage() {
        gameUtils.printStarDivider();
        System.out.println("Welcome to the Number Guessing Game!");
    }

    private static void printGameRules() {
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Select a difficulty level to determine the number of chances you will get to guess the correct number.\n");
    }

    private static void generateMagicNumber() {
        Random randomGenerator = new Random();
        NumberGuessingGame.setMagicNumber(randomGenerator.nextInt(100)+1);
        System.out.println("~ Magic number generated ~\n");
        // Debugging log: System.out.println("Magic number: " + NumberGuessingGame.getMagicNumber());
        NumberGuessingGame.setStartTime();
    }

    private static void promptLevelSelection() {
        System.out.println("Please select the difficulty level:");
        System.out.println("1. Easy (10 chances)");
        System.out.println("2. Medium (5 chances)");
        System.out.println("3. Hard (3 chances)");
        System.out.print("\nEnter your choice:\t");
    }

    private static void setDifficultyLevel() {
        int level;
        boolean validLevel = false;

        // level validation
        do {
            promptLevelSelection();
            level = gameUtils.getUserInputAsInteger();
                switch (level) {
                    case 1 -> {
                        validLevel = true;
                        NumberGuessingGame.setSelectedLevel(DifficultyLevel.EASY);
                        NumberGuessingGame.setChances(10);
                        System.out.println("\nGreat! You have selected the Easy difficulty level. ");
                        System.out.print("You have " + NumberGuessingGame.getChances() + " chances.");
                    }
                    case 2 -> {
                        validLevel = true;
                        NumberGuessingGame.setSelectedLevel(DifficultyLevel.MEDIUM);
                        NumberGuessingGame.setChances(5);
                        System.out.println("\nGreat! You have selected the Medium difficulty level. ");
                        System.out.print("You have " + NumberGuessingGame.getChances() + " chances.");
                    }
                    case 3 -> {
                        validLevel = true;
                        NumberGuessingGame.setSelectedLevel(DifficultyLevel.HARD);
                        NumberGuessingGame.setChances(3);
                        System.out.println("\nGreat! You have selected the Hard difficulty level ");
                        System.out.println("You have " + NumberGuessingGame.getChances() + " chances.");
                    }
                    default -> {
                        System.out.println("\nInvalid selection. Type 1 for easy, 2 for medium, or 3 for hard.\n");
                    }
                }
        } while(!validLevel);

        System.out.println("\nLet's start the game!\n");
    }

    private static int promptGuess() {
        System.out.print("Enter your guess: ");
        int guess = gameUtils.getUserInputAsInteger();
        return guess;
    }

    private static void evaluateGuess(int guess) {
        final int magicNumber = NumberGuessingGame.getMagicNumber();
        NumberGuessingGame.incrementAttempts();
        NumberGuessingGame.decrementChances();

        if(guess == magicNumber) {
            endGame(EndGameCondition.WINNER);
        } else {
            System.out.print("\nIncorrect!\t(Attempts made: " + NumberGuessingGame.getAttempts() + "\tChances remaining: " + NumberGuessingGame.getChances() + ")\n");
            giveHint(guess, magicNumber);
        }
    }

    private static void endGame(EndGameCondition condition) {
        NumberGuessingGame.setGameOver(true);

        switch (condition) {
            case LOSER -> {
                printEndOfGameMessage();
                printHighScores();
            }
            case WINNER -> {
                printCongratulatoryMessage(); 
                evaluateScore(NumberGuessingGame.getSelectedLevel(), NumberGuessingGame.getAttempts());
                printHighScores();
            }
        }
        
    }

    private static void printCongratulatoryMessage() {
        NumberGuessingGame.setEndTime();
        long solveTime = NumberGuessingGame.getSolveTime();
        if(NumberGuessingGame.getAttempts() == 1) {
            System.out.println("\nCongratulations! You guessed the correct number in 1 attempt.");
        } else {
            System.out.println("\nCongratulations! You guessed the correct number in " + NumberGuessingGame.getAttempts() + " attempts. ");
        }
        System.out.print("It took you " + solveTime + " seconds.");
    }

    private static void giveHint(int guess, int magicNumber) {
        if(magicNumber < guess) {
            System.out.println("Hint: The number is less than " + guess + "\n");
        } else {
            System.out.println("Hint: The number is greater than " + guess + "\n");
        }
    }

    private static void printEndOfGameMessage() {
        System.out.println("\nSorry, you lost. Thank you for playing.");
        System.out.println("The magic number was " + NumberGuessingGame.getMagicNumber());
        gameUtils.printStarDivider();
    }

    private static boolean promptPlayAgain() {
        System.out.print("\nWould you like to play again?\tY - yes\tN - no\t\t");
        String input = gameUtils.getUserInputAsString();
        switch (input) {
            case "Y", "y", "Yes", "yes" -> {
                return true;
            }
            case "N", "n", "No", "no" -> {
                return false;
            }
            default -> {
                System.out.println("Invalid input. Please enter Y or N.");
                return promptPlayAgain();
            }
        }
    }

    private static void restartGame() {
        // reset config variables
        NumberGuessingGame.setGameOver(false);
        NumberGuessingGame.setEndCondition(null);
        NumberGuessingGame.setAttempts(0);
        runGame();
    }

    private static void evaluateScore(DifficultyLevel level, Integer attempts) {
        // LOG: System.out.println("\nEvaluating high score...");
        if(NumberGuessingGame.getHighScores().isEmpty() || !NumberGuessingGame.getHighScores().containsKey(level)) {
            NumberGuessingGame.addHighScore(level, attempts);
        } 
        int highScore = NumberGuessingGame.getHighScores().get(level);
        if(attempts < highScore) {
            NumberGuessingGame.addHighScore(level, attempts);
        }
    }

    private static void printHighScores() {
       if(NumberGuessingGame.getHighScores().isEmpty()) {
            System.out.println("\nHigh Scores:");
           System.out.println("No high scores yet.");
       } else {
           System.out.println("\nHigh Scores:");
           for(DifficultyLevel level : NumberGuessingGame.getHighScores().keySet()) {
               System.out.println(level + ": " + NumberGuessingGame.getHighScores().get(level));
           }
       }
    }
}
