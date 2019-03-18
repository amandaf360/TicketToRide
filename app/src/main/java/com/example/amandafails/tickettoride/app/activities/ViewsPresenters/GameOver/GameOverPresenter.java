package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;

public class GameOverPresenter implements Observer, IGameOverPresenter {

    GameOverView view;
    ClientModel clientModel;

    public GameOverPresenter(GameOverView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        clientModel.initializeRoutes();
    }


    @Override
    public void setDisplay() {

    }

    @Override
    public void calculateFinalPoints() {

    }

    @Override
    public String getWinnerName() {
        return null;
    }

    @Override
    public String calculateLongestRouteWinnerName() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        // if something is updated in the model, reset the display
        // shouldn't really ever be called
        setDisplay();
    }
}
