package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;

public interface GameplayState
{
    // called when the state is entered
    void enter(GameplayPresenter presenter);

    // called when the state is exited
    void exit(GameplayPresenter presenter);
}
