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
        game.setName("Ya boi");
        game.setMaxPlayers(5);
        game.setCreator("Ya boi");

        Game game2 = new Game();
        game2.setName("THORSTY boi");
        game2.setMaxPlayers(5);
        game2.setCreator("THORSTY boi's game");

        Game game4 = new Game();
        game4.setName("STRONK boi");
        game4.setMaxPlayers(5);
        game4.setCreator("STRONK BOI'S GAME TOO STRONK");

        Player player = new Player();
        player.setName("Superman");

        game4.addPlayer(player);

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

    public void addPlayerToCurrentGame(Player player)
    {
        game.addPlayer(player);

        setChanged();
        notifyObservers(this.game);
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

    public void popMessage()
    {
        messages.remove(messages.size() - 1);
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
