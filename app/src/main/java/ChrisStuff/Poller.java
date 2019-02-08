package ChrisStuff;

public class Poller
{
    //ServerProxy proxy;

    public Poller()
    {
        //initialize proxy
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
                //server.poll();
            }
        }
    }
}
