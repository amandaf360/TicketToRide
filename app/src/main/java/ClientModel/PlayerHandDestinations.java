package ClientModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandDestinations
{
    private ArrayList<DestinationCards> cardList;

    public PlayerHandDestinations()
    {
        cardList = new ArrayList<>();
    }

    public PlayerHandDestinations(ArrayList<DestinationCards> cardList)
    {
        this.cardList = cardList;
    }

    public ArrayList<DestinationCards> getCardList()
    {
        return cardList;
    }

    public void addCard(DestinationCards destinationCards)
    {
        cardList.add(destinationCards);
    }

    public void deleteCard(DestinationCards destinationCards)
    {
        for(DestinationCards card : cardList)
        {
            if(card.getCityOne().equals(destinationCards.getCityOne()) && card.getCityTwo().equals(destinationCards.getCityTwo()))
            {
                cardList.remove(card);
            }
        }
    }
}
