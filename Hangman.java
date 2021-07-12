import java.lang.reflect.Array;
import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        System.out.println("Hangman");
        Scanner playerInput = new Scanner(System.in);
        //Make list of words
        ArrayList<String> words = new ArrayList<>();
        words.add("cat");
        words.add("eagle");
        words.add("football");
        words.add("dragon");
        words.add("summer");
        System.out.println(words);

        //Print Random word
        Random rand = new Random();
        //System.out.println(word);

        //Make array to hold players letters and print the word/dashes
        ArrayList<Character> playerGuesses = new ArrayList<>();
        ArrayList<Character> playerMisses = new ArrayList<>();

        //play again
        boolean playing = true;

        //Wrong counter
        int wrongCount = 0;

        // Game
        while (playing) {
            String word = words.get(rand.nextInt(words.size()));
            printWordState(word, playerGuesses);


            // Gameplay
            while (true) {
                printHangedMan(wrongCount, playerMisses);
                if (wrongCount >= 6) {
                    System.out.println("Game Over");
                    System.out.println("Do you want to play again? (y or n)");
                    break;
                }

                if (!getPlayerGuess(playerInput, word, playerGuesses, playerMisses)) {
                    wrongCount++;
                }

                if (printWordState(word, playerGuesses)) {
                    System.out.println("Yes! The secret word is " + word + "! You have won!");
                    System.out.println("Do you want to player again? (y or n)");
                    break;
                }
            }

            // Play again
            String endGame = playerInput.nextLine();
            switch (endGame) {
                case "y":
                    playing = true;
                    wrongCount = 0;
                    playerGuesses.clear();
                    playerMisses.clear();
                    //word = words.get(rand.nextInt(words.size()));
                    //printWordState(word, playerGuesses);
                    //printHangedMan(wrongCount, playerMisses);
                    break;
                case "n":
                    System.out.println("Game over");
                    playing = false;
                    break;
            }
        }

    }

    public static void printHangedMan(Integer wrongCount, ArrayList<Character> playerMisses) {
        //Print Board
        System.out.println(" -------");
        System.out.println(" |      |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println(" \\");
            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Missed Letters: " + playerMisses);
    }

    public static boolean printWordState(String word, ArrayList<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print(" _");
                //System.out.println("Guessed Characters: " + playerGuesses);
            }

        }
        System.out.println();
        return (word.length() == correctCount);
    }

    public static boolean getPlayerGuess(Scanner playerInput, String word, ArrayList<Character> playerGuesses, ArrayList<Character> playerMisses) {
        //Take user's playerInput and add character to playerGuesses array.
        System.out.println("Guess a letter:");
        String letterGuess = playerInput.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        if (!word.contains(letterGuess)) {
            playerMisses.add(letterGuess.charAt(0));
        }
        return word.contains(letterGuess);
    }
}

