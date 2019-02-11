package services;
import android.os.AsyncTask;

import proxy.ServerProxy;

public class Poller extends AsyncTask
{
    private String user;

    public Poller(String user)
    {
        this.user = user;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);
                ServerProxy proxy = new ServerProxy();
                proxy.poll(user);
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
