package services;
import proxy.ServerProxy;

public class RegisterService
{
    private ServerProxy proxy;

    public RegisterService(String host, String port)
    {
        proxy = new ServerProxy(host, port);
    }

    public void register(String username, String password)
    {
        System.out.println("In Register Service");
        proxy.register(username, password);

    }
}
