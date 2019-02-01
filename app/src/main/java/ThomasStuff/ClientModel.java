package ThomasStuff;

import java.util.ArrayList;

public class ClientModel
{
    private ArrayList<String> gameList;
    private Game game;

    ClientModel()
    {

    }

    public ArrayList<String> getGameList()
    {
        return gameList;
    }

    public void setGameList(ArrayList<String> gameList)
    {
        this.gameList = gameList;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }
}
