package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.CurrentGameStatus;

import android.widget.TextView;

import java.util.List;

public interface ICurrentGameStatusFragmentPresenter {
    void deleteObserver();
    void exit();
    List<String> getPlayerNames();
    List<String> getPlayerPoints();
    List<String> getPlayerTrains();
    List<String> getPlayerRoutes();
    List<String> getPlayerCards();
    List<String> getPlayerDests();
    List<String> getPlayerNumEachCard();
    void setPlayerNameColors(List<TextView> names);

}
