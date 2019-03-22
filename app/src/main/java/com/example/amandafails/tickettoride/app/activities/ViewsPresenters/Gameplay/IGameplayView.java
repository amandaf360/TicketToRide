package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

public interface IGameplayView
{
    void onDrawCardsClicked();
    void onDrawRoutesClicked();
    void onPlaceTrainsClicked();

    void showToast(String message);

    void setDrawTrainCardsEnabled(boolean enabled);
    void setDrawDestCardsEnabled(boolean enabled);
    void setClaimRouteEnabled(boolean enabled);
}
