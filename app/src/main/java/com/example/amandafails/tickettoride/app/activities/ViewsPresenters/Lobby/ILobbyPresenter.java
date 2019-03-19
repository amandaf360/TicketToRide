package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby;

import java.util.List;

import ClientModel.Player;

public interface ILobbyPresenter {
    void startGame();
    String getGameName();
    void getMaxNumPlayers();
    void setNumCurrentPlayers();
    List<Player> getCurrentPlayers();
}
