package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby;

import ClientModel.Player;

public interface ILobbyView {
    void displayPlayer(Player player);
    void displayErrorMessage(String error);
    void switchActivity();
    void setMaxNumPlayers(int num);
    void setCurrentNumPlayers(int currentNumPlayers);
}
