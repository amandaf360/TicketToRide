package services;
import android.os.AsyncTask;

import proxy.ServerProxy;

public class Poller extends AsyncTask
{
    private ServerProxy proxy;

    public Poller()
    {
        proxy = new ServerProxy();
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);
                proxy.poll();
            }
            catch (InterruptedException ex)
            {
                System.out.println("Hello there! An InterruptedException has been thrown!");
            }
        }
    }

    public void poll()
    {
        this.execute();
    }
}
