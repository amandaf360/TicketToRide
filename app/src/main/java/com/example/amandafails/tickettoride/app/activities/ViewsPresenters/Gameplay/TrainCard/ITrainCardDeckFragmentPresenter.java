package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import java.util.List;

import ClientModel.TrainCarCard;

public interface ITrainCardDeckFragmentPresenter {
    List<TrainCarCard> getTrainCarCards();
    int getNumCardsLeftInDeck();
    void drawCard();
    void demoFunction();
    void exit();
}