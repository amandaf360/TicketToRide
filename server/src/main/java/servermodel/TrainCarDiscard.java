package servermodel;

import java.io.Serializable;
import java.util.ArrayList;

public class TrainCarDiscard implements Serializable
{
    ArrayList<TrainCarCard> discardPile;

    public TrainCarDiscard()
    {
        discardPile = new ArrayList<>();
    }

    public void discard(TrainCarCard discardedCard)
    {
        discardPile.add(discardedCard);
    }

    public ArrayList<TrainCarCard> getDiscardPile()
    {
        return discardPile;
    }

    public void clearPile()
    {
        discardPile.clear();
    }

    public int size()
    {
        return discardPile.size();
    }

}