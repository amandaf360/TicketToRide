package servermodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TrainCarDeck implements Serializable
{
    private ArrayList<TrainCarCard> deck;
    private TrainCarDiscard discardPile;
    private FaceUpCards faceUpCards;

    public TrainCarDeck()
    {
        deck = new ArrayList<>();
        for(int i = 0; i < 11; i++)
        {
            deck.add(new TrainCarCard("red"));
        }
        for(int i = 0; i < 12; i++)
        {
            deck.add(new TrainCarCard("orange"));
        }
        /*for(int i = 0; i < 12; i++)
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
        }*/
        shuffle();
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    public TrainCarCard draw()
    {
        TrainCarCard drawnCard = null;
        if(deck.size() == 0)
        {
            combineWithDiscard();
        }

        if(deck.size() != 0)
        {
            drawnCard = deck.get(0);
            deck.remove(0);
        }

        return drawnCard;
    }

    public void combineWithDiscard()
    {
        deck.addAll(discardPile.getDiscardPile());
        discardPile.clearPile();
        faceUpCards.checkNulls(this);
        shuffle();
    }

    public void setDiscardPile(TrainCarDiscard discardPile) {
        this.discardPile = discardPile;
    }

    public int size()
    {
        return deck.size();
    }

    public void setFaceUpCards(FaceUpCards faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
