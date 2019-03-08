package servermodel;

import java.util.ArrayList;

public class DecksStateData
{
    private int destDeckSize;
    private int trainDeckSize;
    private int trainDiscardSize;
    private ArrayList<String> faceUpCards;

    public DecksStateData(int dSize, int tSize, int tDSize, ArrayList<String> faceUpCards)
    {
        destDeckSize = dSize;
        trainDeckSize = tSize;
        trainDiscardSize = tDSize;
        this.faceUpCards = faceUpCards;
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

    public ArrayList<String> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(ArrayList<String> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
