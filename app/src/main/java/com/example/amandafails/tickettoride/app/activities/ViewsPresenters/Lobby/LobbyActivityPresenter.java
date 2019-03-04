package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ThomasStuff.ClientModel;

public class LobbyActivityPresenter implements ILobbyPresenter, Observer {

    private ILobbyView view;
    private ClientModel clientModel = ClientModel.getInstance();

    public LobbyActivityPresenter(ILobbyView view) {
        this.view = view;
        this.clientModel.addObserver(this);

    }

    @Override
    public void startGame() {
        // disable the start game button
        view.setStartEnabled(false);

        //call activity switcher in View?
        view.switchActivity();

        // will call the "start game service" once it's created
        // similar to this below...
        // will want to return a result from start game service??
        /*
        // call login service connected to model??
        LoginService loginService = new LoginService();
        // will get both username and password and attempt to login
        loginService.login(view.getLoginUsername(), view.getLoginPassword());
        */

        // once it is ready to start, change the activity
        view.switchActivity();
    }

    @Override
    public String getGameName() {

        return clientModel.getActiveGame().getName();
    }


    @Override
    public void update(Observable o, Object arg) {
        // if new player object is created, display that this player has joined game
        if(arg.getClass() == ArrayList.class) {
            if(clientModel.getActiveGame().getCurrentPlayers() != 0) {
                view.displayPlayer(clientModel.getActiveGame().getPlayers().get(clientModel.getActiveGame().getCurrentPlayers() - 1));
            }
        }
        else if(arg.getClass() == String.class) {
            // display the most recent error message
            view.displayErrorMessage(clientModel.getMessage());
        }
        // BEFORE SWITCHING ACTIVITIES, DELETE OBSERVER!!!
    }

}
