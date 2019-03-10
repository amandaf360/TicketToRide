package ClientModel;

import java.util.ArrayList;

public class GameStartInfo
{
    private ArrayList<String> playersAndColors;
    private ArrayList<TrainCarCard> cardsInHand;
    private String currentTurnPlayer;

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

    public String getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public void setCurrentTurnPlayer(String currentTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
    }
}