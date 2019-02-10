package ThomasStuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/*
 * to get a ClientModel object do something like this where needed
 * e.x.
 * public class Dummy
 * {
 *      private ClientModel clientModel
 *      public Dummy()
 *      {
 *          this.clientModel = ClientModel.getInstance()
 *      }
 * }
 *
 */

public class ClientModel extends Observable
{
    private ArrayList<Game> gameList;
    private Game game;
    private ArrayList<String> messages;
    private static ClientModel instance;
    private User user;
    private List<Observer> observers = new ArrayList<Observer>();


    public ClientModel()
    {
        this.instance = this;
    }

    public static ClientModel getInstance() {
        if (instance == null)
            instance = new ClientModel();
        return instance;
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
        gameList.add(newGame);
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

    public Game getGame()
    {
        return game;
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
        game.addPlayer(player);

        setChanged();
        notifyObservers(this.gameList);
    }

    public void setGame(Game game)
    {

        this.game = game;
        setChanged();
        notifyObservers(this.game);

    }

    public int getGameNum(Game game)
    {
        if(gameList.contains(game))
        {
            return gameList.indexOf(game);
        }
        return -1;
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
}
