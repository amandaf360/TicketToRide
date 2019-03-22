package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;

public class NotMyTurnState implements GameplayState
{
    private static NotMyTurnState instance = new NotMyTurnState();
    private NotMyTurnState(){}

    public static NotMyTurnState getInstance()
    {
        return instance;
    }

    public void enter(GameplayPresenter presenter)
    {
        presenter.setDrawTrainCardsEnabled(false);
        presenter.setDrawDestCardsEnabled(false);
        presenter.setClaimRouteEnabled(false);
    }

    public void exit(GameplayPresenter presenter)
    {
        presenter.setDrawTrainCardsEnabled(true);
        presenter.setDrawDestCardsEnabled(true);
        presenter.setClaimRouteEnabled(true);
    }
}
