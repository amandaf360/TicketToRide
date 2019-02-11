package servermodel;

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
        players = new ArrayList<Player>();
    }

    public Game(Game game)
    {
        this.name = game.name;
        this.maxPlayers = game.maxPlayers;
        players = new ArrayList<>();
        for(int i = 0; i < game.players.size(); i++)
        {
            players.add(new Player(game.players.get(i)));
        }
        this.gameNum = game.gameNum;
        this.currentPlayers = game.currentPlayers;
        this.creator = game.creator;
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
        currentPlayers++;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }
}
