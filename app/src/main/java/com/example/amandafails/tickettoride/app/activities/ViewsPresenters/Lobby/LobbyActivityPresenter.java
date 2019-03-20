package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;

public class LobbyActivityPresenter implements ILobbyPresenter, Observer {

    private ILobbyView view;
    private ClientModel clientModel = ClientModel.getInstance();

    public LobbyActivityPresenter(ILobbyView view) {
        this.view = view;
        this.clientModel.addObserver(this);
        clientModel.getMainPlayer();
    }

    public void disconnectObserver() {
        clientModel.deleteObserver(this);
    }

    @Override
    public void startGame() {
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
        else if(arg.getClass() == Boolean.class)
        {
            if((Boolean) arg)
            {
                clientModel.deleteObserver(this);
                startGame();
            }
        }
    }

    @Override
    public void setNumCurrentPlayers() {
        view.setCurrentNumPlayers(clientModel.getActiveGame().getCurrentPlayers());
    }

    @Override
    public List<Player> getCurrentPlayers() {
        return clientModel.getActiveGame().getPlayers();
    }

    @Override
    public void getMaxNumPlayers() {
        view.setMaxNumPlayers(clientModel.getActiveGame().getMaxPlayers());
    }
}
