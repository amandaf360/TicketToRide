package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

public interface IGameOverPresenter {
    void setDisplay();
    void calculateFinalPoints();
    String getWinnerName();
    String calculateLongestRouteWinnerName();
}
