package servermodel;

import java.util.ArrayList;
import java.util.List;
import mapgraph.Graph;

public class ModelRoot
{
    private static ModelRoot model = new ModelRoot();

    private ModelRoot()
    {
        gameCounter = 0;
        userList = new ArrayList<>();
        gameList = new ArrayList<>();
        activeGameList = new ArrayList<>();
    }

    private Graph mapGraph;
    private ArrayList<User> userList;
    private int gameCounter;
    private ArrayList<Game> gameList;
    private ArrayList<ActiveGame> activeGameList;

    public void addUser(User user)
    {
        userList.add(user);
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
        gameList.add(game);
    }

    public ArrayList<ActiveGame> getActiveGameList()
    {
        return activeGameList;
    }

    public void addActiveGame(ActiveGame game)
    {
        activeGameList.add(game);
    }

    public int assignNumber()
    {
        gameCounter++;
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
