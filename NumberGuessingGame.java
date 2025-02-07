import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

public class NumberGuessingGame {

    private static int magicNumber;
    private static DifficultyLevel selectedLevel;
    private static int chances;
    private static int attempts = 0;
    private static boolean gameOver = false;
    private static EndGameCondition endCondition;
    private static Instant startTime;
    private static Instant endTime;
    private static final HashMap<DifficultyLevel, Integer> highScores = new HashMap<>();

    public static int getAttempts() {
        return attempts;
    }

    public static void setAttempts(int attempts) {
        NumberGuessingGame.attempts = attempts;
    }
    
    public static void incrementAttempts() {
        NumberGuessingGame.attempts++;
    }

    public static int getChances() {
        return chances;
    }

    public static void setChances(int chances) {
        NumberGuessingGame.chances = chances;
    }

    public static void decrementChances() {
        NumberGuessingGame.chances--;
    }

    public static int getMagicNumber() {
        return magicNumber;
    }

    public static void setMagicNumber(int magicNumber) {
        NumberGuessingGame.magicNumber = magicNumber;
    }

    public static DifficultyLevel getSelectedLevel() {
        return selectedLevel;
    }

    public static void setSelectedLevel(DifficultyLevel selectedLevel) {
        NumberGuessingGame.selectedLevel = selectedLevel;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        NumberGuessingGame.gameOver = gameOver;
    }

    public static Instant getStartTime() {
        return startTime;
    }

    public static void setStartTime() {
        NumberGuessingGame.startTime = Instant.now();
    }

    public static Instant getEndTime() {
        return endTime;
    }

    public static void setEndTime() {
        NumberGuessingGame.endTime = Instant.now();
    }

    public static long getSolveTime() {
        long timeElapsed = Duration.between(NumberGuessingGame.getStartTime(), NumberGuessingGame.getEndTime()).toSeconds();
        return timeElapsed;
    }

    public static HashMap<DifficultyLevel, Integer> getHighScores() {
        return highScores;
    }

    public static void addHighScore(DifficultyLevel level, Integer attempts) {
        NumberGuessingGame.highScores.put(level, attempts);
    }

    public static EndGameCondition getEndCondition() {
        return endCondition;
    }

    public static void setEndCondition(EndGameCondition endCondition) {
        NumberGuessingGame.endCondition = endCondition;
    }

}