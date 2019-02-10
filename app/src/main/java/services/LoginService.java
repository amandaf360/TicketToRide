package services;
import proxy.ServerProxy;

public class LoginService
{
    private ServerProxy proxy;

    public LoginService()
    {
        proxy = new ServerProxy();
    }

    public void login(String username, String password)
    {
        proxy.login(username, password);
    }
}
