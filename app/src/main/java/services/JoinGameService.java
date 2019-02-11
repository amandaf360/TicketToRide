package services;
import proxy.ServerProxy;

public class JoinGameService
{
    private ServerProxy proxy;

    public JoinGameService()
    {
        proxy = new ServerProxy();
    }

    public void joinGame(int gameNumber, String username)
    {
        proxy.joinGame(gameNumber, username);
    }
}
