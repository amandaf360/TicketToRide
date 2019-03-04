package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amandafails.tickettoride.R;

public class GameplayView extends AppCompatActivity implements IGameplayView
{
    private GameplayPresenter presenter;

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
        presenter.drawCards();
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
}
