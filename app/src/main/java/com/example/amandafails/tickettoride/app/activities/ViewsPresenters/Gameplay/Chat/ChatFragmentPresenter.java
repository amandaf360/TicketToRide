package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.Chat;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;
import services.CreateChatMessageService;
import services.CreateHistoryMessageService;

public class ChatFragmentPresenter implements IChatFragmentPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private IChatFragmentView view;

    public ChatFragmentPresenter(IChatFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public List<Message> getChatMessages() {
        return clientModel.getGameChat();
    }

    @Override
    public void sendMessage() {
        String message = view.getChatMessage();
        CreateChatMessageService createChatMessageService = new CreateChatMessageService(message);
        // call method on createChatMessageService object
        createChatMessageService.sendMessage();
        if(message.equals("cheat"))
        {
            clientModel.addTenOfEachTrainCar();
            clientModel.addTenOfEachTrainCar();
            CreateHistoryMessageService create = new CreateHistoryMessageService();
            create.sendMessage(clientModel.getMainPlayer().getName() + " just cheated!\n");
        }
        // clear chat message
        view.clearChatMessage();
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateChatMessages();
    }

}
