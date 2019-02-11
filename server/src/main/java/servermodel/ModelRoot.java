package servermodel;

import java.util.ArrayList;
import java.util.List;

public class ModelRoot
{
    private static ModelRoot model = new ModelRoot();

    private ModelRoot()
    {
        gameCounter = 5;
        userList = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    private List<User> userList;
    private int gameCounter;
    private List<Game> gameList;

    public void addUser(User user)
    {
        userList.add(user);
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public static ModelRoot getModel()
    {
        return model;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
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
