package services;
import proxy.ServerProxy;

public class CreateGameService
{
    private ServerProxy proxy;

    public CreateGameService()
    {
        proxy = new ServerProxy();
    }

    public void createGame(String username, int numPlayers, String gamename, String authToken)
    {
        proxy.createGame(username, numPlayers, gamename, authToken);
    }

}
