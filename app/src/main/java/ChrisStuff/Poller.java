package ChrisStuff;

public class Poller
{
    //ServerProxy proxy;

    Poller()
    {
        //initialize proxy
    }

    void poll()
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
