package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameHistory;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;

public class GameHistoryFragmentPresenter implements IGameHistoryFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private IGameHistoryFragmentView view;

    public GameHistoryFragmentPresenter(IGameHistoryFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateGameHistory();
    }

    @Override
    public List<Message> getGameHistory() {
        return clientModel.getGameHistory();
    }
}
