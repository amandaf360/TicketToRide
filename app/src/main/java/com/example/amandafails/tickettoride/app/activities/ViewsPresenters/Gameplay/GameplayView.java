package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.amandafails.tickettoride.R;

import java.util.List;

import ClientModel.ClientModel;

public class GameplayView extends FragmentActivity implements IGameplayView
{
    private GameplayPresenter presenter;

    private Button displayGameStatusButton;
    private Button drawTrainsButton;
    private Button drawRoutesButton;
    private Button placeTrainsButton;
    private boolean firstCreate = true;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        presenter = new GameplayPresenter(this);

        displayGameStatusButton = findViewById(R.id.display_game_status);
        displayGameStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDisplayGameStatusClicked();
            }
        });

        drawTrainsButton = findViewById(R.id.button_drawTrains);
        drawTrainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDrawCardsClicked();
            }
        });
        drawRoutesButton = findViewById(R.id.button_drawRoute);
        drawRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDrawRoutesClicked();
            }
        });
        placeTrainsButton = findViewById(R.id.button_placeTrains);
        placeTrainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlaceTrainsClicked();
            }
        });
        ClientModel.getInstance().initializeRoutes();

        if(firstCreate)
        {
            presenter.chooseDestinationCards();
            firstCreate = false;
        }
        firstCreate = false;

    }

    //define all these buttons inside of onCreate
    public void onDrawCardsClicked()
    {
        // set fragment to draw card fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        TrainCardDeckFragment trainCardDeckFragment = new TrainCardDeckFragment();
        transaction.replace(R.id.frame, trainCardDeckFragment)
                .addToBackStack(null)
                .commit();

        // call draw cards method in train card deck fragment
        //trainCardDeckFragment.drawCards();
    }

    public void onDisplayGameStatusClicked() {
        // set fragment to draw card fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        CurrentGameStatusFragment currentGameStatusFragment = new CurrentGameStatusFragment();
        transaction.replace(R.id.frame, currentGameStatusFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onDrawRoutesClicked()
    {
        presenter.drawRoute();
    }

    public void onPlaceTrainsClicked()
    {
        presenter.placeTrains();
    }

    public void onTrainCardDrawerExpanded()
    {

    }

    public void onGameStatusDrawerExpanded()
    {

    }

    public void createStartDialog()
    {

    }

    public void onTrainCarClicked()
    {

    }

    public void onTrainDeckClicked()
    {

    }

    public void onDestinationCardSelected()
    {

    }

    public void onDiscardClicked()
    {

    }

    public void onChatTabClicked()
    {

    }

    public void onGameHistoryTabClicked()
    {

    }

    public void onDestCardTabClicked()
    {

    }

    public void display_chat_message(String message)
    {

    }

    public void set_chat_enabled(boolean isEnabled)
    {

    }

    public void display_error_message(String message)
    {

    }

    public GameplayPresenter getPresenter() {
        return presenter;
    }

    public boolean isFirstCreate()
    {
        return firstCreate;
    }

    public void setFirstCreateToFalse()
    {
        firstCreate = false;
    }
}
