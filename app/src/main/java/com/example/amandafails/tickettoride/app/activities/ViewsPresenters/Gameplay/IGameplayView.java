package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import java.util.ArrayList;

import ClientModel.Route;

public interface IGameplayView
{
    void onDrawCardsClicked();
    void onDrawRoutesClicked();
    void onPlaceTrainsClicked();

    void showToast(String message);

    void setDrawTrainCardsEnabled(boolean enabled);
    void setDrawDestCardsEnabled(boolean enabled);
    void setClaimRouteEnabled(boolean enabled);
    void claimRouteByTap(ArrayList<Route> routes);
}
