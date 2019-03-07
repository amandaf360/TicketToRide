package ClientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ClientModel extends Observable
{
    private ArrayList<Game> gameList;
    private Game activeGame;
    private String message;
    private static ClientModel instance;
    private User user;
    private Player mainPlayer;
    private List<Observer> observers = new ArrayList<Observer>();


    public ClientModel()
    {
        instance = this;
        gameList = new ArrayList<>();
    }

    public static ClientModel getInstance() {
        if (instance == null)
            instance = new ClientModel();
        return instance;
    }

    public void errorChecking()
    {
        Game game = new Game();
        game.setName("Ya boi's Game");
        game.setMaxPlayers(5);
        game.setCreator("Ya boi");

        Game game2 = new Game();
        game2.setName("THORSTY boi's activeGame");
        game2.setMaxPlayers(5);
        game2.setCreator("THORSTY boi");

        Game game4 = new Game();
        game4.setName("STRONK BOI'S GAME TOO STRONK");
        game4.setMaxPlayers(5);
        game4.setCreator("STRONK boi");

        Player player = new Player();
        player.setName("Superman");
        Player player2 = new Player();
        player2.setName("My name is");
        Player player1 = new Player();
        player1.setName("Yo it's me");

        game4.addPlayer(player);
        game4.addPlayer(player1);
        game4.addPlayer(player2);

        gameList.add(game);
        gameList.add(game2);
        gameList.add(game4);



    }

    public ArrayList<Game> getGameList()
    {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList)
    {

        this.gameList = gameList;
        setChanged();
        notifyObservers(this.gameList);
    }

    public void addGame(Game newGame)
    {

        boolean isPresent = false;
        int check = newGame.getGameNum();
        for(Game game : gameList)
        {
            if(game.getGameNum() == check)
            {
                isPresent = true;
            }
        }
        if(!isPresent)
        {
            gameList.add(newGame);
        }
        setChanged();
        notifyObservers(gameList);
    }

    public void deleteGame(Game goneGame)
    {
        if(gameList.contains(goneGame))
        {
            gameList.remove(goneGame);
            setChanged();
            notifyObservers(gameList);
        }
    }

    public Game getActiveGame()
    {
        return activeGame;
    }

    public Game getGameByNumber(int gameNum) {
        for(Game game : gameList) {
            if(game.getGameNum() == gameNum) {
                return game;
            }
        }
        return null;
    }

    public Game getGame(String gameName)
    {
        for(Game game : gameList)
        {
            if(game.getName().equals(gameName))
            {
                return game;
            }
        }
        return null;
    }

    public void addPlayerToGame(Game game, Player player)
    {
        ArrayList<Player> players = game.getPlayers();
        boolean playIsPresent = false;
        String playerName = player.getName();
        for(Player playerCheck : players)
        {
            if(playerCheck.getName().equals(playerName))
            {
                playIsPresent = true;
            }
        }
        if(!playIsPresent)
        {
            if (activeGame != null)
            {
                if (game.getGameNum() == activeGame.getGameNum())
                {
                    activeGame.addPlayer(player);
                }
            }
            else
            {
                game.addPlayer(player);
            }

            setChanged();
            notifyObservers(this.gameList);
        }
    }

    public void addPlayerToCurrentGame(Player player)
    {
        activeGame.addPlayer(player);

        setChanged();
        notifyObservers(this.activeGame);
    }

    public void setActiveGame(Game activeGame)
    {
        this.activeGame = activeGame;
        setChanged();
        notifyObservers(this.activeGame);
    }

    public int getGameNum(Game game)
    {
        return game.getGameNum();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers(this.message);
    }

    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setChanged();
        notifyObservers(this.user);
    }

    public Player getMainPlayer()
    {
        return mainPlayer;
    }

    public void setMainPlayer(Player mainPlayer)
    {
        this.mainPlayer = mainPlayer;
    }

    public void endCurrentTurn()
    {
        activeGame.updateTurnIndex();
        setChanged();
        notifyObservers(this.activeGame);
    }
}