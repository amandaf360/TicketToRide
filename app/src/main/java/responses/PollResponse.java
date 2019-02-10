package responses;

import java.util.ArrayList;
import servermodel.Game;

public class PollResponse extends BaseResponse
{
    private ArrayList<Game> gamesCreated;
    private ArrayList<String> gamesDeleted;
    private ArrayList<String> playersJoined;
    private ArrayList<String> playersLeft;

    public PollResponse() {
    }

    public ArrayList<Game> getGamesCreated() {
        return gamesCreated;
    }

    public void setGamesCreated(ArrayList<Game> gamesCreated) {
        this.gamesCreated = gamesCreated;
    }

    public ArrayList<String> getGamesDeleted() {
        return gamesDeleted;
    }

    public void setGamesDeleted(ArrayList<String> gamesDeleted) {
        this.gamesDeleted = gamesDeleted;
    }

    public ArrayList<String> getPlayersJoined() {
        return playersJoined;
    }

    public void setPlayersJoined(ArrayList<String> playersJoined) {
        this.playersJoined = playersJoined;
    }

    public ArrayList<String> getPlayersLeft() {
        return playersLeft;
    }

    public void setPlayersLeft(ArrayList<String> playerLeft) {
        this.playersLeft = playerLeft;
    }
}
