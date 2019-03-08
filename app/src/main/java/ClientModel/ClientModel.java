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
    private ArrayList<Message> gameChat;
    private ArrayList<Message> gameHistory;
    private List<Route> routes;
    private List<Observer> observers = new ArrayList<Observer>();



    public ClientModel()
    {
        gameChat = new ArrayList<>();
        gameHistory = new ArrayList<>();
        instance = this;
        gameList = new ArrayList<>();
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

    public void setFaceUpCardByIndex(int index, TrainCards trainCards)
    {
        activeGame.changeCardByIndex(index, trainCards);
        setChanged();
        notifyObservers();
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


    public void addDestinationCardToActivePlayersHand(DestinationCards destinationCards)
    {
        mainPlayer.addDestinationCardToPlayerHand(destinationCards);
        activeGame.getPlayerByName(mainPlayer.getName()).addDestinationCardToPlayerHand(destinationCards);
        setChanged();
        notifyObservers(this.mainPlayer.getPlayerHandDestinations());
    }

    public void addTrainCardToActivePlayerHand(TrainCards trainCards)
    {
        mainPlayer.addTrainCardToHand(trainCards);
        activeGame.getPlayerByName(mainPlayer.getName()).addTrainCardToHand(trainCards);
        setChanged();
        notifyObservers(this.mainPlayer.getPlayerHandTrains());

    }

    public void deleteMainPlayersDestinationCardFromHand(DestinationCards destinationCards)
    {
        mainPlayer.getPlayerHandDestinations().deleteCard(destinationCards);
        activeGame.getPlayerByName(mainPlayer.getName()).getPlayerHandDestinations().deleteCard(destinationCards);
        setChanged();
        notifyObservers(this.mainPlayer.getPlayerHandDestinations());
    }

    public void addMessageToChat(Message message)
    {
        gameChat.add(message);
        setChanged();
        notifyObservers(this.gameChat);
    }

    public void addMessageToHistory(Message message)
    {
        gameHistory.add(message);
        setChanged();
        notifyObservers(this.gameHistory);
    }

    public ArrayList<Message> getGameChat()
    {
        return gameChat;
    }

    public ArrayList<Message> getGameHistory()
    {
        return gameHistory;
    }

    public void initializeRoutes()
    {
        //Route route = new Route()
    }


}
