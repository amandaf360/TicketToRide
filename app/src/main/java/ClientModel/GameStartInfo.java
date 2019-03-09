package servermodel;

import java.util.ArrayList;

public class GameStartInfo
{
    private ArrayList<String> playersAndColors;
    private ArrayList<TrainCarCard> cardsInHand;

    public GameStartInfo()
    {

    }

    public ArrayList<String> getPlayersAndColors() {
        return playersAndColors;
    }

    public void setPlayersAndColors(ArrayList<String> playersAndColors) {
        this.playersAndColors = playersAndColors;
    }

    public ArrayList<TrainCarCard> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<TrainCarCard> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
}
