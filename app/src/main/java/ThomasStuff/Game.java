package ThomasStuff;

import java.util.ArrayList;

public class Game
{
    private String name;
    private int numPlayers;
    private ArrayList<Player> players;
    private int gameNum;
    private int currentPlayers;


    public Game()
    {

    }


    public String getPlayerColor(Player player)
    {
        return player.getColor();
    }

    public String getPlayerAuthToken(Player player)
    {
        return player.getAuthToken();
    }

    public int getCurrentPlayers()
    {
        currentPlayers = players.size();
        return currentPlayers;
    }

    public String getName()
    {
        return name;
    }

}
