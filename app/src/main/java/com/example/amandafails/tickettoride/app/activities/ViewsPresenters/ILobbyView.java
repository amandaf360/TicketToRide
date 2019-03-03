package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import ThomasStuff.Player;

public interface ILobbyView {
    void onStartClicked();
    void setStartEnabled(boolean enabled);
    void displayPlayer(Player player);
    void displayErrorMessage(String error);
    void switchActivity();
}
