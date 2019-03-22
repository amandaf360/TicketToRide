package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import java.util.List;

import ClientModel.TrainCarCard;

public interface ITrainCardDeckFragmentPresenter {
    List<TrainCarCard> getTrainCarCards();
    int getNumCardsLeftInDeck();
    void drawCard(int cardIndex);
    void demoFunction();
    void exit();
    void deleteObserver();
    void displayToast(String toastString);
}
