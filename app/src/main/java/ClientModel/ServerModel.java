package ClientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ServerModel extends Observable
{
    private ArrayList<Game> gameList;
    private List<Observer> observers = new ArrayList<>();
    private ArrayList<User> users;
    private static ServerModel instance;

    public ServerModel()
    {
        instance = this;
    }

    public static ServerModel getInstance() {
        if (instance == null)
            instance = new ServerModel();
        return instance;
    }


    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }
}
