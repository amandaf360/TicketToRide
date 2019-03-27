package services;
import proxy.ServerProxy;

public class LoginService
{
    private ServerProxy proxy;

    public LoginService(String host, String port)
    {
        proxy = new ServerProxy(host, port);
    }

    public void login(String username, String password)
    {
        proxy.login(username, password);
    }
}
