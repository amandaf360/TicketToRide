package servermodel;

import java.io.Serializable;
import java.util.ArrayList;

public class DecksStateData implements Serializable
{
    private int destDeckSize;
    private int trainDeckSize;
    private int trainDiscardSize;
    private ArrayList<TrainCarCard> faceUpCards;

    public DecksStateData(int dSize, int tSize, int tDSize, ArrayList<TrainCarCard> faceUpCards)
    {
        destDeckSize = dSize;
        trainDeckSize = tSize;
        trainDiscardSize = tDSize;
        this.faceUpCards = faceUpCards;
    }

    public DecksStateData()
    {}

    public DecksStateData(ActiveGame game)
    {
        destDeckSize = game.getDestinationDeck().size();
        trainDiscardSize = game.getTrainCarDiscard().size();
        trainDeckSize = game.getTrainDeck().size();
        faceUpCards = game.getFaceUpCards().getCards();
    }


    public int getDestDeckSize() {
        return destDeckSize;
    }

    public void setDestDeckSize(int destDeckSize) {
        this.destDeckSize = destDeckSize;
    }

    public int getTrainDeckSize() {
        return trainDeckSize;
    }

    public void setTrainDeckSize(int trainDeckSize) {
        this.trainDeckSize = trainDeckSize;
    }

    public int getTrainDiscardSize() {
        return trainDiscardSize;
    }

    public void setTrainDiscardSize(int trainDiscardSize) {
        this.trainDiscardSize = trainDiscardSize;
    }

    public ArrayList<TrainCarCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(ArrayList<TrainCarCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
