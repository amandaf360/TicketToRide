package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

public interface ITrainCardDeckFragmentView {
    void setCardValues();
    void onDeckClicked();
    void onExitClicked();
    void setExitEnabled(boolean enabled);
    void popFragment();
}
