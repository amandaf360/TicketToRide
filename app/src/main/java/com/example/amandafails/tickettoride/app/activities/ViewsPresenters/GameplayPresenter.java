package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;
import java.util.Observable;
import java.util.Observer;

public class GameplayPresenter implements IGameplayPresenter, Observer
{
    GameplayView view;

    public GameplayPresenter(GameplayView view)
    {
        this.view = view;
    }

    public void drawCards()
    {

    }

    public void drawRoute()
    {

    }

    public void placeTrains()
    {

    }

    public void update(Observable observable, Object o)
    {

    }
}
