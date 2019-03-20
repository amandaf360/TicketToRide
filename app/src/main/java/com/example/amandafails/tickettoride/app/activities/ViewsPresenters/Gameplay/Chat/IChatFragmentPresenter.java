package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.Chat;

import java.util.List;

import ClientModel.Message;

public interface IChatFragmentPresenter {
    List<Message> getChatMessages();
    void sendMessage();
}
