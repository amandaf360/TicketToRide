package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

public interface ITrainCardDeckFragmentView {
    void setCardValues();
    void onCardClicked(int index);
    void onExitClicked();
    void setExitEnabled(boolean enabled);
    void popFragment(boolean exitPressed);
    void showToast(String toastString);
}
