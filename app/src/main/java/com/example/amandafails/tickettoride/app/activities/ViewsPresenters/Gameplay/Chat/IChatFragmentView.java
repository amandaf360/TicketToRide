package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.Chat;

public interface IChatFragmentView {
    void onSendButtonClicked();
    String getChatMessage();
    void clearChatMessage();
    void updateChatMessages();
}
