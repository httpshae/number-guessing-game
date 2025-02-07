# Number Guessing Game

# Author: Shae Ferguson

## Description
This is a simple command-line game developed in Java where the player must guess a randomly generated number between 1-100.

This program implements the number guessing game found at: https://roadmap.sh/projects/number-guessing-game

# Game Logic 
- Display welcome message and game rules
- Prompt user to select difficulty level
    - Set the number of guesses based on selected difficulty level
- The game generates a random number between 1-100
- The game validates the number of guesses a player has
    - If the player has 1 or more remaining guesses,
        - Prompt the user to guess the number
        - Evaluate the guess
            - If the user guesses correctly,
                - Display congratulatory message
                - Evaluate if score is eligible to be added to high score board
                    - If so, add it
                - Display high scores
            - Else (if the user does not guess correctly)
                - Decrement number of remaining guesses
                - Give the user a hint indicating if the number is higher or lower than their guess
    - Else (if the player has no remaining guesses)
        - Display end of game message
        - Display high scores
- Prompt the player to play again
    - If yes,
        - Reset config variables 
        - Run game again
    - Else
        - Display "Thank you for playing!"