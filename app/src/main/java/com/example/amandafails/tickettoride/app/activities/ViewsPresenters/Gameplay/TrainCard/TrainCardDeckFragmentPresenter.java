package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;
import services.DrawTrainCardService;

public class TrainCardDeckFragmentPresenter implements ITrainCardDeckFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private ITrainCardDeckFragmentView view;

    public TrainCardDeckFragmentPresenter(ITrainCardDeckFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public List<TrainCarCard> getTrainCarCards() {
        return clientModel.getActiveGame().getFaceUpCards();
    }

    @Override
    public int getNumCardsLeftInDeck() {
        return clientModel.getActiveGame().getNumCardsInDeck();
    }

    @Override
    public void deleteObserver() {
        clientModel.deleteObserver(this);
    }

    @Override
    public void drawCard(int cardIndex) {
        // check to see if it's a valid draw
        // don't call the service if they've already drawn a card and try to draw a wild
        // maybe set a bool??

        // call draw card service
        DrawTrainCardService drawTrainCardService = new DrawTrainCardService();
        drawTrainCardService.drawCard(cardIndex);
    }

    @Override
    public void exit() {
        deleteObserver();
        view.popFragment();
    }


    @Override
    public void demoFunction() {
        TrainCarCard card1 = new TrainCarCard("yellow");
        TrainCarCard card2 = new TrainCarCard("pink");
        TrainCarCard card3 = new TrainCarCard("purple");
        TrainCarCard card4 = new TrainCarCard("indigo");
        TrainCarCard card5 = new TrainCarCard("orange");

        clientModel.setFaceUpCardByIndex(0, card1);
        clientModel.setFaceUpCardByIndex(1, card2);
        clientModel.setFaceUpCardByIndex(2, card3);
        clientModel.setFaceUpCardByIndex(3, card4);
        clientModel.setFaceUpCardByIndex(4, card5);


        clientModel.getActiveGame().setNumCardsInDeck(101);

    }

    @Override
    public void update(Observable o, Object arg) {
        view.setCardValues();
        // check to see what they've drawn
        // if only 1 card has been drawn (not a locomotive), then disable the exit button
        // if it was a locomotive,
    }
}
