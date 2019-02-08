package services;
import proxy.ServerProxy;

public class Poller
{
    ServerProxy proxy;

    public Poller()
    {
        proxy = new ServerProxy();
    }

    public void poll()
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                proxy.poll();
            }
        }
    }
}
