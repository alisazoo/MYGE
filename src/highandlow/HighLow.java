package highandlow;
import textio.TextIO;
/**
 * This program lets the user play HighLow, a simple card game that is described in the output statements
 * at the beginning of the main() routine. After the user plays several games, the user's average score
 * is reported.
 */
public class HighLow {

    public static void main(String[] args) {
        System.out.println("This program lets you play the simple card game,");
        System.out.println("HighLow. A card is dealt from a deck of cards.");
        System.out.println("You have to predict whether the next card will be");
        System.out.println("higher or lower. Your score in the game is the");
        System.out.println("number of correct preditions you make before");
        System.out.println("you guess wrong\n");

        int gamesPlayed = 0;    // Number of games user has played.
        int sumOfscores = 0;    // The sum of all the scores from all the games played.
        double averageScore;    // Average score, computed by dividing sumOfScores by gamesPlayed.
        boolean playAgain;      // Record user's response when user is asked whether he wants to play another game.

        do{
            int scoreThisGame;  // Score for one game.
            scoreThisGame = play();
            sumOfscores += scoreThisGame;
            gamesPlayed++;
            System.out.println("Play again?");
            playAgain = TextIO.getlnBoolean();
        } while(playAgain);
    } // end: main()

    private static int play(){
        Deck deck = new Deck();     // Get a new deck of cards, and store a reference to it in the variable, deck.
        Card currentCard;           // The current card, which the user sees.
        Card nextCard;              // The next card in the deck. The user tries to predict whether this is higher
                                    //  or lower than the current card.
        int correctGuesses;         // The number of correct predictions the user has made.
                                    //  At the end of the game, this will be the user's score.
        char guess;                 // The user's guess. 'H' if the user predicts that the next card will be higher,
                                    //  'L' if the user predicts that it will be lower.
        deck.shuffle();             // Shuffle the deck into a random order before starting the game.

        correctGuesses = 0;
        currentCard = deck.dealCard();
        System.out.println("The first card is the " + currentCard);

        while(true){
            System.out.println("Will the next card be higher(H) or lower(L)? ");

            // Get the user's prediciton
            do{
                guess = TextIO.getlnChar();
                guess = Character.toUpperCase(guess);
                if(guess != 'H' && guess != 'L')
                    System.out.println("Please respond with H or L: ");
            } while(guess != 'H' && guess != 'L');

            // Get the next card and show it to the user
            nextCard = deck.dealCard();
            System.out.println("The next card is " + nextCard);

            // Check the user's prediciton
            if(nextCard.getValue() == currentCard.getValue()){
                System.out.println("The value is the same as the previous card.");
                System.out.println("You lose on ties. Sorry!");
                break;  // end the game.
            }
            else if(nextCard.getValue() > currentCard.getValue()){
                if(guess == 'H'){
                    System.out.println("Your prediction was correct.");
                    correctGuesses++;
                }else{
                    System.out.println("Your prediction was incorrect.");
                    break; // end the game
                }
            }
            else{   // next card is lower
                if(guess == 'L'){
                    System.out.println("Your prediction was correct.");
                    correctGuesses++;
                }else{
                    System.out.println("Your prediction was incorrect.");
                    break;  // end the game
                }
            }

            // To set up for the next iteration of the loop, the nextCard becomes the currentCard,
            //  since the currentCard has to be the card that the user sees, and the nextCard will
            //  be set to the next card in the deck after the user makes his prediction.
            currentCard = nextCard;
            System.out.println("\nThe card is " + currentCard);
        } // end of while loop

        System.out.println("\nThe game is over.");
        System.out.println("You made " + correctGuesses + " correct predictions.\n");
        return correctGuesses;
    }   // end play()
}
