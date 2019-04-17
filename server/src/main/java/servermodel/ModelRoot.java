package servermodel;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PluginInterfaces.IGameDAO;
import PluginInterfaces.IPersistanceProvider;
import PluginInterfaces.IUserDAO;
import PluginInterfaces.gameWrapper;
import commands.ICommand;
import commands.JoinGameCommand;
import mapgraph.Graph;
import server.ClientCommandManager;

public class ModelRoot
{
    private static ModelRoot model = new ModelRoot();

    private ModelRoot()
    {
        gameCounter = 0;
        userList = new ArrayList<>();
        gameList = new ArrayList<>();
        activeGameList = new ArrayList<>();
        haveLoaded = false;
    }


    public void loadState()
    {
        List<String> allTokens = userDAO.getAllTokens();
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for (String token : allTokens)
        {
            manager.addUser(token);
        }


        List<byte[]> usersSerialized = userDAO.getAllUsers();

        for(byte[] serializedObj : usersSerialized)
        {
           User newUser = (User) deserialize(serializedObj);
           addUser(newUser);
        }


        List<gameWrapper> games = gameDAO.getAllGames();

        for(gameWrapper game: games)
        {
            if(game.getLabel().equals("activeGame"))
            {
                addActiveGame((ActiveGame) deserialize(game.getObject()));
            }
            else
            {
                addGame((Game) deserialize(game.getObject()));
            }
        }


        for(Game game: gameList)
        {
            List<byte[]> commandList = gameDAO.getAllGameCommands(game.getGameNum());
            for(byte[] command : commandList)
            {
                ICommand Icommand = (ICommand) deserialize(command);
                Icommand.execute();
            }
        }

        for(ActiveGame game: activeGameList)
        {
            List<byte[]> commandList = gameDAO.getAllGameCommands(game.getGameNum());
            for(byte[] command : commandList)
            {
                ICommand Icommand = (ICommand) deserialize(command);
                Icommand.execute();
            }
        }

        haveLoaded = true;
    }

    private Graph mapGraph;
    private IPersistanceProvider dataBase;
    private IUserDAO userDAO;
    private IGameDAO gameDAO;
    private ArrayList<User> userList;
    private int gameCounter;
    private ArrayList<Game> gameList;
    private ArrayList<ActiveGame> activeGameList;
    private int gameUpdateLimit;
    private Map<Integer, Integer> gameUpdates;
    private boolean haveLoaded;


    public IPersistanceProvider getDataBase()
    {
        return dataBase;
    }

    public void setDataBase(IPersistanceProvider dataBase)
    {
        this.dataBase = dataBase;
        this.gameDAO = dataBase.getGameDAO();
        this.userDAO = dataBase.getUserDAO();
    }

    public void setGameUpdateLimit(int limit)
    {
        gameUpdateLimit = limit;
    }

    public Game getGameByGameNum(int gameNum)
    {
        for(Game game : gameList)
        {
            if (game.getGameNum() == gameNum)
            {
                return game;
            }
        }
        return null;
    }


    public ActiveGame getActiveGameByGameNum(int gameNum)
    {
        for(ActiveGame game : activeGameList)
        {
            if (game.getGameNum() == gameNum)
            {
                return game;
            }
        }
        return null;
    }

    public void addGameCommandToDataBase(int gameNum, ICommand command)
    {
        if(haveLoaded)
        {
            if (gameUpdates == null)
            {
                gameUpdates = new HashMap<Integer, Integer>();
                for (Game game : gameList)
                {
                    gameUpdates.put(game.getGameNum(), 0);
                }

            }

            if (gameUpdates.get(gameNum) >= gameUpdateLimit)
            {
                if (command.getClass() == JoinGameCommand.class)
                {
                    gameDAO.setGameState(serialize(getGameByGameNum(gameNum)), gameNum, "game");
                    gameUpdates.put(gameNum, 0);
                } else
                {
                    gameDAO.setGameState(serialize(getActiveGameByGameNum(gameNum)), gameNum, "activeGame");
                    gameUpdates.put(gameNum, 0);
                }
            }

            gameDAO.addCommand(serialize(command), gameNum);
            gameUpdates.put(gameNum, gameUpdates.get(gameNum) + 1);
        }

    }

    public byte[] serialize(Serializable obj)
    {
        byte[] stream = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             ObjectOutputStream OS = new ObjectOutputStream(output))
        {
            OS.writeObject(obj);
            stream = output.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stream;
    }

    public byte[] serialize(ICommand obj)
    {
        byte[] stream = null;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             ObjectOutputStream OS = new ObjectOutputStream(output))
        {
            OS.writeObject(obj);
            stream = output.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stream;
    }

    public Object deserialize(byte[] serialized)
    {
        Object obj = null;
        try(ByteArrayInputStream iStream = new ByteArrayInputStream(serialized);
            ObjectInputStream objStream = new ObjectInputStream(iStream);)
        {


            obj = objStream.readObject();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not serialize an object!\n");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return obj;

    }

    public void addUser(User user)
    {
        if(haveLoaded)
        {
            userDAO.addUser(serialize(user));
        }
        userList.add(user);
    }

    public void addAuthTokenToDatabase(String str)
    {
        if(haveLoaded)
        {
            userDAO.addAuthToken(str);
        }
    }

    public ArrayList<User> getUserList()
    {
        return userList;
    }

    public static ModelRoot getModel()
    {
        return model;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public void addGame(Game game)
    {
        if(haveLoaded)
        {
            gameDAO.setGameState(serialize(game), game.getGameNum(), "game");
        }
        gameList.add(game);
    }

    public ArrayList<ActiveGame> getActiveGameList()
    {
        return activeGameList;
    }

    public void addActiveGame(ActiveGame game)
    {
        if(haveLoaded)
        {
            gameDAO.setGameState(serialize(game), game.getGameNum(), "activeGame");
        }
        activeGameList.add(game);
    }

    public int assignNumber()
    {
        gameCounter++;
        if(gameUpdates != null)
        {
            gameUpdates.put(gameCounter, 0);
        }
        return gameCounter;
    }

    public ActiveGame getGameByUser(String username)
    {
        for(int i = 0; i < activeGameList.size(); i++)
        {
            if(activeGameList.get(i).containsUser(username))
            {
                return activeGameList.get(i);
            }
        }
        return null;
    }

    public ActiveGame getGameByAuthToken(String authToken)
    {
        for(int i = 0; i < activeGameList.size(); i++)
        {
            if(activeGameList.get(i).containsToken(authToken))
            {
                return activeGameList.get(i);
            }
        }
        return null;
    }

    public Graph getMapGraph() {
        return mapGraph;
    }

    public void setMapGraph(Graph mapGraph) {
        this.mapGraph = mapGraph;
    }
}
