/**
 * A class to hand out cards to the player making sure they are not the same.
 * It also lets the user to change the cards.
 * 
 * @author Mate Barna
 */

public class HandOfCards
{
    //instance variables
    private Card cardOne;
    private Card cardTwo;
    private Card cardThree;
    private int firstPoints;
    private int points;
    
    //Constructor to hand out cards.
    public HandOfCards()
    {
        // Define first card.
        cardOne = new Card();
        // Define second card. Make sure it is not the same as the first one.
        cardTwo = new Card();
        while (cardTwo.getCard() == cardOne.getCard() &&
               cardTwo.getSuit() == cardOne.getSuit())
        { cardTwo.changeCard();}
        // Define third card. Make sure it is not the same as before (card and suit).
        cardThree = new Card();
        while (cardThree.getCard() == cardOne.getCard() &&
               cardThree.getSuit() == cardOne.getSuit() ||
               cardThree.getCard() == cardTwo.getCard() &&
               cardThree.getSuit() == cardTwo.getSuit())
        { cardThree.changeCard();}
        //Show the user how many points they have from first round.
        firstPoints = cardOne.getCard() + cardTwo.getCard() + cardThree.getCard();
        //if 2 suits match, give 5 extra points.
        if (cardOne.getSuit() == cardTwo.getSuit() ||
            cardOne.getSuit() == cardThree.getSuit() ||
            cardTwo.getSuit() == cardThree.getSuit())
        {
            firstPoints += 5;
        }
        //if 3 suits match, give 10 extra points.
        if (cardOne.getSuit() == cardTwo.getSuit() &&
            cardOne.getSuit() == cardThree.getSuit())
        {
            firstPoints += 10;
        }
    }
    
    //Mutator to change cards.
    public void changeCard(int cardToChange)
    {
       // Because we only have 3 cards, the IF makes sure that the number we enter
       // is between 1 and 3 (the number of cards we have).
       if (cardToChange < 1 || cardToChange >3)
       {
            System.out.println("You have to choose between 1-3.");
       }
       
       // Switching/changing cards. The program continues changing the value,
       // while they are not the same, because we only have 1 deck of cards.
       switch(cardToChange)
       {
        case 1 : do {cardOne.changeCard();}
                 while(
                    cardOne.getCard() == cardTwo.getCard() &&
                    cardOne.getSuit() == cardTwo.getSuit() ||
                    cardOne.getCard() == cardThree.getCard() &&
                    cardOne.getSuit() == cardThree.getSuit());
                    break;
        case 2 : do {cardTwo.changeCard();}
                 while(
                    cardTwo.getCard() == cardOne.getCard() &&
                    cardTwo.getSuit() == cardOne.getSuit() ||
                    cardTwo.getCard() == cardThree.getCard() &&
                    cardTwo.getSuit() == cardThree.getSuit());
                    break;
        case 3 : do {cardThree.changeCard();}
                 while(
                    cardThree.getCard() == cardOne.getCard() &&
                    cardThree.getSuit() == cardOne.getSuit() ||
                    cardThree.getCard() == cardTwo.getCard() &&
                    cardThree.getSuit() == cardTwo.getSuit());
                    break;
       }
       // Tells the player how many points they have from second and third round.
       points = cardOne.getCard() + cardTwo.getCard() + cardThree.getCard();
       // if we have 2 matching suits, earn 5 extra points.
       if (cardOne.getSuit() == cardTwo.getSuit() ||
           cardOne.getSuit() == cardThree.getSuit() ||
           cardTwo.getSuit() == cardThree.getSuit())
       {
            points += 5;
       }
       // if we have 3 matching suits, earn 10 extra points.
       if (cardOne.getSuit() == cardTwo.getSuit() &&
           cardOne.getSuit() == cardThree.getSuit())
       {
           points += 10;
       }
    }
    
    // Accessor to show a hand.
    public String showHand()
    {
        String handToShow = "Your 3 cards are: \n";
        handToShow += cardOne.getCardName();
        handToShow += cardTwo.getCardName();
        handToShow += cardThree.getCardName();
        return handToShow;
    }
    
    // Accessor to get the points of first round.
    public int getFirstPoints()
    {
        return firstPoints;
    }
    
    // Accessor to get the points of the second and third round.
    public int getPoints()
    {
        return points;
    }
 }
