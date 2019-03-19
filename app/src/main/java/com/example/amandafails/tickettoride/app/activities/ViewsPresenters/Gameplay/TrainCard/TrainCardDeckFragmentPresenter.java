package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;

public class TrainCardDeckFragmentPresenter implements ITrainCardDeckFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private ITrainCardDeckFragmentView view;

    public TrainCardDeckFragmentPresenter(ITrainCardDeckFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.setCardValues();
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
    public void drawCard() {
        // call draw card service?
        // once 2 cards are drawn, switch fragments?
//        Intent i = new Intent(getActivity(), GameplayView.class);
//        startActivity(i);

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
}
