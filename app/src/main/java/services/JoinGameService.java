package services;
import proxy.ServerProxy;

public class JoinGameService
{
    ServerProxy proxy;

    public JoinGameService()
    {
        proxy = new ServerProxy();
    }

    public void joinGame(int gameNumber)
    {
        proxy.joinGame(gameNumber);
    }
}
