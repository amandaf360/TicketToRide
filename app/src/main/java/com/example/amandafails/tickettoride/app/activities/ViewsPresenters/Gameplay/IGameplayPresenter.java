package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import java.util.ArrayList;

import ClientModel.Route;

public interface IGameplayPresenter
{
    void drawCards();
    void drawRoute();
    void claimRouteByTap(ArrayList<Route> routes);
    void placeTrains();
    void setPlayerCards();
    boolean canDrawDestCards();
}
