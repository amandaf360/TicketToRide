package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;

public class MyTurnState implements GameplayState
{
    private static MyTurnState instance = new MyTurnState();
    private MyTurnState(){}

    public static MyTurnState getInstance()
    {
        return instance;
    }

    public void enter(GameplayPresenter presenter)
    {
        presenter.setDrawTrainCardsEnabled(true);
        presenter.setDrawDestCardsEnabled(true);
        presenter.setClaimRouteEnabled(true);
    }

    public void exit(GameplayPresenter presenter)
    {
        presenter.setDrawTrainCardsEnabled(false);
        presenter.setDrawDestCardsEnabled(false);
        presenter.setClaimRouteEnabled(false);
    }
}
