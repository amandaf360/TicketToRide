package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;
import proxy.ServerProxy;
import services.DrawTrainCardService;

public class TrainCardDeckFragmentPresenter implements ITrainCardDeckFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private ITrainCardDeckFragmentView view;

    private boolean cardDrawn;

    public TrainCardDeckFragmentPresenter(ITrainCardDeckFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
        cardDrawn = false;
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
        // if the card is labeled "none", then show a toast
        if(clientModel.getActiveGame().getFaceUpCards().get(cardIndex).getColor().equals("none")) {
            view.showToast("Empty slot - can't draw from here");
        }
        // if it's the SECOND draw...
        else if(cardDrawn) {
            if((cardIndex != -1) && clientModel.getActiveGame().getFaceUpCards().get(cardIndex).getColor().equals("locomotive")) {
                // don't let them draw a wild if it's their second card to draw
                view.showToast("Can't draw a wild as the second card");
            }
            // otherwise draw a card and pop
            else {
                // call draw card service
                DrawTrainCardService drawTrainCardService = new DrawTrainCardService();
                drawTrainCardService.drawCard(cardIndex);
                // pop fragment
                ServerProxy proxy = new ServerProxy();
                proxy.endCurrentTurn(clientModel.getMainPlayer().getName(), clientModel.getMainPlayer().getAuthToken());
                view.popFragment(false);
            }
        }
        // if it's the FIRST draw...
        else {
            // if it's a wild, then pop
            if((cardIndex != -1) && clientModel.getActiveGame().getFaceUpCards().get(cardIndex).getColor().equals("locomotive")) {
                DrawTrainCardService drawTrainCardService = new DrawTrainCardService();
                drawTrainCardService.drawCard(cardIndex);
                ServerProxy proxy = new ServerProxy();
                proxy.endCurrentTurn(clientModel.getMainPlayer().getName(), clientModel.getMainPlayer().getAuthToken());
                view.popFragment(false);

            }
            // otherwise draw a card and set cardDrawn
            else {
                // disable the exit button
                view.setExitEnabled(false);
                // acknowledge that they've drawn their first card
                cardDrawn = true;
                // call draw card service
                DrawTrainCardService drawTrainCardService = new DrawTrainCardService();
                drawTrainCardService.drawCard(cardIndex);
            }
        }
    }

    @Override
    public void exit() {
        deleteObserver();
        view.popFragment(true);
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
    }

    @Override
    public void displayToast(String toastString)
    {
        view.showToast(toastString);
    }

    public boolean isCardDrawn() {
        return cardDrawn;
    }

    public void setCardDrawn(boolean cardDrawn) {
        this.cardDrawn = cardDrawn;
    }
}
