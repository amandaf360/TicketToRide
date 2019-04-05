package servermodel;

import java.util.ArrayList;

public class FaceUpCards
{
    private ArrayList<TrainCarCard> cards;
    private TrainCarDiscard discardPile;

    public FaceUpCards()
    {
        cards = new ArrayList<>();
    }

    public TrainCarCard draw(int index, TrainCarDeck deck)
    {
        TrainCarCard drawnCard = cards.get(index);
        cards.set(index, deck.draw());
        if(cards.get(index) == null)
        {
            cards.set(index, new TrainCarCard("none"));
        }
        checkValidity(deck);
        return drawnCard;
    }

    public void checkValidity(TrainCarDeck deck)
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
            checkValidity(deck);
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

    public void setCards(ArrayList<TrainCarCard> cards)
    {
        this.cards = cards;
    }

    public TrainCarDiscard getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(TrainCarDiscard discardPile) {
        this.discardPile = discardPile;
    }

    public void checkNulls(TrainCarDeck deck)
    {
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getColor().equals("none") && deck.size() != 0)
            {
                TrainCarCard cardDrawn = deck.draw();
                if(cardDrawn != null)
                {
                    cards.set(i, cardDrawn);
                }
            }
        }
    }
}
