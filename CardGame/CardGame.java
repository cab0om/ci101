/**
 * A class to play the actual game.
 * Game description to be found in README.
 * 
 * @author Mate Barna
 */
public class CardGame
{
    // instance variables
    private SimpleInput input;
    private HandOfCards handOfCards;
    
    // Giving value to the variables.
    public CardGame()
    {
        input = new SimpleInput();
        handOfCards = new HandOfCards();
    }
    
    // Mutator to play the actual game.
    public void playGame()
    {
        // Ask player for a username to play with.
        String name;
        name = input.getString("Username:");
        // do Loop.
        do
        {
            // Greeting the user.
            System.out.println("Hi " + name + "! \n");
            // Showing first hand.
            System.out.println(handOfCards.showHand() + "\n");
            // Telling the score of first round.
            System.out.println("Your score is: " + handOfCards.getFirstPoints() + "\n");
            // Player must change card 3 times.
            for (int count = 0; count < 3; count ++)
            {
                int changeCards;
                changeCards = input.getInt("Change a card: 1,2 or 3.");
                handOfCards.changeCard(changeCards);
                // Showing the hand after the change.
                System.out.println(handOfCards.showHand() + "\n");
                // Telling the score of second and third rounds.
                System.out.println("Your score is: " + handOfCards.getPoints() + "\n");
            }
        // If the player choose so, they can play again.
        }while(input.getBoolean("Do you want to play again?"));
    }
}
