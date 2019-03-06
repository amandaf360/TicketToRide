package servermodel;

import java.util.ArrayList;
import java.util.Collections;

public class TrainCarDeck
{
    ArrayList<TrainCarCard> deck;

    public TrainCarDeck()
    {
        deck = new ArrayList<>();
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("red"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("orange"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("yellow"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("green"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("blue"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("purple"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("black"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("white"));
        }
        for(int i = 0; i < 14; i++)
        {
            deck.add(new TrainCarCard("locomotive"));
        }
        shuffle();
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    public TrainCarCard draw()
    {
        TrainCarCard drawnCard;
        if(deck.size() != 0)
        {
            drawnCard = deck.get(0);
            deck.remove(0);
        }
        else
        {
            drawnCard = null;
        }

        return drawnCard;
    }

    public void combineWithDiscard(TrainCarDiscard discardPile)
    {
        ArrayList<TrainCarCard> discardCards = discardPile.getDiscardPile();

    }

}
