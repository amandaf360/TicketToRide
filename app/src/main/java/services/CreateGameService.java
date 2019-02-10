package services;
import proxy.ServerProxy;

public class CreateGameService
{
    ServerProxy proxy;

    public CreateGameService()
    {
        proxy = new ServerProxy();
    }

    public void createGame(String username, int numPlayers, String gamename)
    {
        proxy.createGame(username, numPlayers, gamename);
    }

}
