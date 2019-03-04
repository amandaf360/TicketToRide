package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

public interface IGameplayView
{
    void onDrawCardsClicked();
    void onDrawRoutesClicked();
    void onPlaceTrainsClicked();
    void onTrainCardDrawerExpanded();
    void onGameStatusDrawerExpanded();
    void createStartDialog();

    void onTrainCarClicked();
    void onTrainDeckClicked();

    void onDestinationCardSelected();
    void onDiscardClicked();

    void onChatTabClicked();
    void onGameHistoryTabClicked();
    void onDestCardTabClicked();
    void display_chat_message(String message);
    void set_chat_enabled(boolean isEnabled);
    void display_error_message(String message);
}
