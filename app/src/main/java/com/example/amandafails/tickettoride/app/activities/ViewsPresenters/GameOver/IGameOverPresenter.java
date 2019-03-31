package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

import android.widget.TextView;

import java.util.List;

public interface IGameOverPresenter {
    void setDisplay();
    List<String> calculateFinalPoints();
    String getWinnerName();
    String getLongestRouteWinnerName();
    List<String> getPlayerNames();
    List<String> getPlayerPoints();
    List<String> getPlayerDestPoints();
    List<String> getPlayerDestPointsLost();
    void setPlayerNameColors(List<TextView> names);
    int getPlayersInGame();

}
