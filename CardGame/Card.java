/**
 * A class to define Ace, Jack, Queen and King and the suits.
 * It also allows the player to change cards.
 * 
 * @author Mate Barna
 */

public class Card
{
    //instance variables
    private DeckOfCards myDeck;
    private int card;
    private int suit;
    private String cardName;
    
    //Handing out cards.
    public Card()
    {
        myDeck = new DeckOfCards();
        suit = myDeck.getSuit();
        card = myDeck.getCard();
        setCardName();
    }
    // Accessor to return name of the card.
    public String showCard()
    {
       return cardName; 
    }
    
    //Changing cards.
    public void changeCard()
    {
        // Getting a random card from DeckOfCards class.
        myDeck.shuffleDeck();
        card = myDeck.getCard();
        suit = myDeck.getSuit();
        setCardName();
    }
    
    //Tells us the cardname.
    public String getCardName()
    {
        return cardName + "\n";
    }
    
    //In case our card value is 1, 11, 12 or 13, define the names as follows.
    //If its anything else, stay with the number.
    public void setCardName()
    {
        switch (card)
        {
           case 1 : cardName = "Ace of " + getSuitName();
              break;
           case 11 : cardName = "Jack of " + getSuitName();
              break;
           case 12 : cardName = "Queen of " + getSuitName();
              break;
           case 13 : cardName = "King of " + getSuitName();
              break;
           default : cardName = card + " of " + getSuitName();
        }
    }
    
    //Defines the suit names for the cards.
    public String getSuitName()
    {
       switch(suit)
       {
           case 1 : return "Spades";
           case 2 : return "Diamonds";
           case 3 : return "Hearts";
           case 4 : return "Clubs";
           default : return "";
       }
    }
    
    //Accessor that returns card value.
    public int getCard()
    {
        return card;
    }
    
    //Accessor that returns suit value
    public int getSuit()
    {
       return suit;
    }
    
}
