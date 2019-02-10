package ThomasStuff;

import java.util.ArrayList;

public class Game
{
    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    private int gameNum;
    private int currentPlayers;
    private String creator;


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

    public String getCreator()
    {
        if(creator == null || creator == "")
        {
            return "Games have creators now";
        }
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers)
    {
        this.maxPlayers = maxPlayers;
    }

    public void setCurrentPlayers(int currentPlayers)
    {
        this.currentPlayers = currentPlayers;
    }

    public void addPlayer(Player player) /// THIS SHOULD NEVER BE CALLED
    {
        players.add(player);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }
}
