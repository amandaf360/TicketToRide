package servermodel;

import java.util.ArrayList;
import java.util.List;

public class ModelRoot
{
    private static ModelRoot model = new ModelRoot();

    private ModelRoot()
    {
        gameCounter = 0;
        userList = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    private ArrayList<User> userList;
    private int gameCounter;
    private ArrayList<Game> gameList;

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

    public int assignNumber()
    {
        gameCounter++;
        return gameCounter;
    }
}
