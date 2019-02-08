package services;
import proxy.ServerProxy;

public class RegisterService
{
    ServerProxy proxy;

    public RegisterService()
    {
        proxy = new ServerProxy();
    }

    public void register(String username, String password)
    {
        System.out.println("In Register Service");
        proxy.register(username, password);
    }
}
