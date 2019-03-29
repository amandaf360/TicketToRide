package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.CurrentGameStatus;

public interface ICurrentGameStatusFragmentView {
    void populateEverything();
    void onExitButtonClicked();
    void popFragment(boolean exitPressed);
}
