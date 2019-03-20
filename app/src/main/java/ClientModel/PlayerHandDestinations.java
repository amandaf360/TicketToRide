package ClientModel;

import java.util.ArrayList;
import java.util.List;

/**
 * PlayerHandDestinations is a wrapper class for a list of destination cards.
 * It provides added functionality that just a list of Strings would not have.
 *
 * @invariant getSize() >= 0
 *
 */

public class PlayerHandDestinations
{

    /**
     * private list of object containing objects of type DestinationCards
     */
    private ArrayList<DestinationCards> cardList;


    /**
     * Default constructor
     */
    public PlayerHandDestinations()
    {
        cardList = new ArrayList<>();
    }

    /**
     * constructs a new PlayerHandDestinations object with cards already in
     * the list.
     * @param cardList this is the list of cards in the hand
     * @pre cardList must be non-null
     *
     * @post getSize() = cardList.size()
     *
     *
     */
    public PlayerHandDestinations(ArrayList<DestinationCards> cardList)
    {
        this.cardList = cardList;
    }


    /**
     * adds a card to the list.
     * @param destinationCards
     * @pre card must be non-null
     * @post getSize() = preGetSize() + 1;
     */
    public void addCard(DestinationCards destinationCards)
    {
        cardList.add(destinationCards);
    }

    /**
     * Deletes a matching card in the hand if it exists. Doesn't need to be
     * the exact same object that is in the hand, just a matching one.
     * @param destinationCards
     *
     * @pre destinationCards must be non-null
     * @pre card must exist in hand already
     * @pre getSize() > 0
     *
     * @post getSize() = preGetSize() - 1;
     * @post the hand will not have the card given inside of it.
     */
    public void deleteCard(DestinationCards destinationCards)
    {
        DestinationCards cardToRemove = null;
        for(DestinationCards card : cardList)
        {
            if(card.getCityOne().equals(destinationCards.getCityOne()) && card.getCityTwo().equals(destinationCards.getCityTwo()))
            {
                cardToRemove = card;
                break;
            }
        }
        cardList.remove(cardToRemove);
    }

    /**
     * Returns how many cards are currently in the hand.
     *
     * @pre none
     * @post retval == the amount of cards i th
     *
     * @return the amount of cards in the hand.
     */
    public int getSize()
    {
        return cardList.size();
    }


    public ArrayList<DestinationCards> getCardList() {
        return cardList;
    }
}
