package servermodel;

import java.util.ArrayList;

public class FaceUpCards
{
    private ArrayList<TrainCarCard> cards;

    public FaceUpCards()
    {
        cards = new ArrayList<>();
    }

    public TrainCarCard draw(int index, TrainCarDeck deck)
    {
        TrainCarCard drawnCard = cards.get(index);
        cards.set(index, deck.draw());
        return drawnCard;
    }

    public void checkValidity(TrainCarDiscard discardPile, TrainCarDeck deck)//
    {
        int numLocomotives = 0;
        for(int i = 0; i < 5; i++)
        {
            if(cards.get(i).getColor().equals("locomotive"))
            {
                numLocomotives++;
            }
        }
        if(numLocomotives >= 3)
        {
            for(TrainCarCard card : cards)
            {
                discardPile.discard(card);
            }
            cards.clear();
            for(int i = 0; i < 5; i++)
            {
                TrainCarCard drawnCard = deck.draw();
                cards.add(drawnCard);
            }
            checkValidity(discardPile, deck);
        }
    }

    public ArrayList<String> getColors()
    {
        ArrayList<String> colors = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++)
        {
            colors.add(cards.get(i).getColor());
        }
        return colors;
    }

    public ArrayList<TrainCarCard> getCards()
    {
        return cards;
    }
}
