package services;
import proxy.ServerProxy;

public class CreateGameService
{
    ServerProxy proxy;

    public CreateGameService()
    {
        proxy = new ServerProxy();
    }

    public void createGame(String name, /*, User creator */ int numPlayers)
    {
        proxy.createGame(name, USer, numPlayers);
    }
}
