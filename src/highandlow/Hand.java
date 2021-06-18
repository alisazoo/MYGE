package highandlow;


import java.util.ArrayList;

/**
 * An object of type Hand represents a hand of cards.
 * The cards belong to the class Card.
 * A hand is empty when it is created, and any number of cards can be added to it.
 */
public class Hand {

    private ArrayList<Card> hand;   // The cards in the hand.

    /**
     * Constructor. Create a Hand object that is initially empty.
     */
    public Hand(){
        hand = new ArrayList<Card>();
    }

    /**
     * Discard all cards from the hand, making the hand empty.
     */
    public void clear(){
        hand.clear();
    }

    /**
     * Add the card c to the hand. c should be non-null.
     *
     * the new card is added at the end of the current hand.
     * @param c the non-null card to be added.
     * @throws NullPointerException if c is null.
     */
    public void addCard(Card c){
        if(c== null)
            throw new NullPointerException("Can't add a null card to a hand");
        hand.add(c);
    }

    /**
     * If the specified card is in the hand, it is removed.
     *
     * Remove a card from the hand, if present.
     * @param c the card to be removed. If c is null or if the card is not in the hand, then nothing is done.
     */
    public void removeCard(Card c){
        hand.remove(c);
    }

    /**
     * Remove the card in the specified position from the hand.
     * Cards are numbered counting from zero.

     * @param position the position of the card that is to be removed, where positions are starting from zero.
     * @throws IllegalArgumentException if the specified position does not exist in the hand. That is,
     * if the positoin is less than 0 or greater than or equal to the number of cards in the hand.
     */
    public void removeCard(int position){
        if(position <0|| position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: " + position);
        hand.remove(position);
    }

    /**
     * @return the number of cards in the hand.
     */
    public int getCardCount(){
        return hand.size();
    }

    /**
     * Get the card from the hand in given position, where positions are numbered starting from 0.
     * @param position the position of the card that is to be returned.
     * @throws IllegalArgumentException if the specified position does note exist in the hand.
     */
    public Card getCard(int position){
        if(position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: " + position);
        return hand.get(position);
    }

    /**
     * Sorts the cards in the hand so that cards of the same suit are grouped together,
     * and within a suit the cards are sorted by value.
     * Note: the aces are considered to have the lowest value, 1.
     */
    public void sortBySuit(){
        ArrayList<Card> newHand = new ArrayList<Card>();
        while(hand.size() > 0){
            int pos = 0;            // Position of minimal card.
            Card c = hand.get(0);   // Minimal card.
            for(int i = 1; i < hand.size(); i++){
                Card c1 = hand.get(i);
                //TODO understand the logic for sorting after coding Card class.
                if( c1.getSuit() < c.getSuit() || (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ){
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    /**
     * Sorts the cards in the hand so that cards are sorted into order of increasing value.
     * Cards with the same value are sorted by suit.
     * Note: the aces are considered to have the lowest value, 1.
     */
    public void sortByValue(){
        ArrayList<Card> newHand = new ArrayList<Card>();
        while(hand.size() > 0){
            int pos = 0;            // Position of minimal card.
            Card c = hand.get(0);   // Minimal card.
            for(int i = 1; i < hand.size(); i++){
                Card c1 = hand.get(i);
                //TODO understand the logic for sorting after coding Card class.
                if( c1.getValue() < c.getValue() || (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ){
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }
}
