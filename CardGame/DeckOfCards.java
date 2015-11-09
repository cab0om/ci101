/**
 * A class to define the card number and the suit.
 * 
 * @author Mate Barna
 * 
 */

public class DeckOfCards
{
    private int suit;
    private int card;
    
    public DeckOfCards()
    {
        // Gives a random card and suit.
        shuffleDeck();
    }
    public void shuffleDeck() 
    {
        // Creates a random number between 1 and 13.
        card = 1 + (int)(Math.random() * 12);
        // Creates a random number between 1 and 4.
        suit = 1 + (int)(Math.random() * 3);
    }
    
    //Accessor to return card value.
    public int getCard()
    {
        return card;
    }
    
    // Accessor to return suit value.
    public int getSuit()
    {
        return suit;
    }
}
