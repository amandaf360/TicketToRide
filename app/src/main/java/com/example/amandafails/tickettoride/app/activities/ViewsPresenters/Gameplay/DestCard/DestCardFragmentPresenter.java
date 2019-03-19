package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DestCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;

public class DestCardFragmentPresenter implements IDestCardFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private IDestCardFragmentView view;

    public DestCardFragmentPresenter(IDestCardFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateDestCardMessages();
    }

    @Override
    public List<Message> getListDestCardMessages() {
        List<DestinationCards> cards = clientModel.getMainPlayer().getPlayerHandDestinations().getCardList();
        List<Message> destinationMessages = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++)
        {
            Message message = new Message(clientModel.getMainPlayer().getColor(), cards.get(i).toString());
            destinationMessages.add(message);
        }

        return destinationMessages;
    }
}
