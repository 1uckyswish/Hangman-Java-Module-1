import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                                  // character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        int randomWordIndex = random.nextInt(words.length) + 1;
        String chosenWord = words[randomWordIndex];
        char[] userGuessPlaceholder = new char[chosenWord.length()];
        for (int i = 0; i < userGuessPlaceholder.length; i++) {
            userGuessPlaceholder[i] = '_';
        }

        int misses = 0;
        char[] missedGuesses = new char[6];
        System.out.println("\nWelcome to Hangman! You have 6 trys to guess the lucky word :)");
        System.out.println("If you want to play, just type anywhere on the keyboard and we can start.");
        scan.nextLine();

        System.out.println("\nShuffling through my lucky bag of words...");
        System.out.println("\nLet me generate a word to start the game: ");

        while (misses < 6) {
            System.out.println(gallows[misses]);
            System.out.print("Word:   ");
            printCharPlaceholder(userGuessPlaceholder);
            System.out.println("\n");

            System.out.print("Misses:   ");
            printCharPlaceholder(missedGuesses);
            System.out.println("\n");

            System.out.print("Guess:   ");
            char userGuess = scan.nextLine().charAt(0);
            System.out.println("\n");

            if (checkIfCharEquals(userGuess, chosenWord)) {
                updateGuessIndexValue(userGuess, chosenWord, userGuessPlaceholder);
            } else {
                missedGuesses[misses] = userGuess;
                misses++;
            }

            if (misses == 6) {
                System.out.print(gallows[6]);
                System.out.println("\nRIP!");
                System.out.println("\nGood Try but your time is over");
                System.out.println("The word was: '" + chosenWord + "'");
                break;
            }

            if (Arrays.equals(userGuessPlaceholder, chosenWord.toCharArray())) {
                System.out.print(gallows[misses]);
                System.out.print("\n Word: ");
                printCharPlaceholder(userGuessPlaceholder);
                System.out.print("\nGOOD WORK!");
                break;
            }

        }

        scan.close();

    }

    public static void printCharPlaceholder(char[] charPlaceholder) {
        for (char c : charPlaceholder) {
            System.out.print(c + " ");
        }
    }

    public static void updateGuessIndexValue(char guess, String chosenWord, char[] userGuessPlaceholder) {
        for (int i = 0; i < userGuessPlaceholder.length; i++) {
            if (chosenWord.charAt(i) == guess) {
                userGuessPlaceholder[i] = guess;
            }
        }
    }

    public static boolean checkIfCharEquals(char guess, String chosenWord) {
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }
}