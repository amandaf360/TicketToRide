package servermodel;

import java.util.ArrayList;

public class TrainCarDiscard
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

}