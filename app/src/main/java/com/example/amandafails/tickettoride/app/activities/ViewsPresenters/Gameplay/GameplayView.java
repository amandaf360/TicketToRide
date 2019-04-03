package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver.GameOverView;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.CurrentGameStatus.CurrentGameStatusFragmentView;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DrawDestCard.DrawDestFragmentView;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.NotMyTurnState;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard.TrainCardDeckFragmentView;
import com.example.amandafails.tickettoride.app.subviews.TrainView;

import java.util.ArrayList;

import ClientModel.Route;

import ClientModel.ClientModel;

public class GameplayView extends FragmentActivity implements IGameplayView
{
    private GameplayPresenter presenter;

    private Button displayGameStatusButton;
    private Button drawTrainsButton;
    private Button drawRoutesButton;
    private Button placeTrainsButton;
    //private Button demoButton;
    private TextView currentTurn;
    private boolean firstCreate = true;
    private TrainView trainView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

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

        trainView = findViewById(R.id.view_trains);
        trainView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        trainView.setParentView(this);

        ClientModel.getInstance().initializeRoutes();
        presenter = new GameplayPresenter(this);

        currentTurn = findViewById(R.id.turn_text_indicator);
        currentTurn.setText(presenter.currentTurn());


        if(firstCreate) {
            presenter.chooseDestinationCards();
            presenter.setPlayerCards();
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    public void claimRouteByTap(ArrayList<Route> routes)
    {
        presenter.claimRouteByTap(routes);
    }


    public void changeTurnName(String name)
    {
        currentTurn.setText(name);
    }


    public void onDemoClicked()
    {
        presenter.demo();
    }

    public void enableAllButtons() {
        setDrawTrainCardsEnabled(true);
        setDrawDestCardsEnabled(true);
        setClaimRouteEnabled(true);
        setDisplayGameStatusButtonEnabled(true);
    }

    public void disableAllButtons() {
        setDrawTrainCardsEnabled(false);
        setDrawDestCardsEnabled(false);
        setClaimRouteEnabled(false);
        setDisplayGameStatusButtonEnabled(false);
    }

    public void onDrawCardsClicked() {
        // disable all buttons
        disableAllButtons();
        // set fragment to draw card fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        TrainCardDeckFragmentView trainCardDeckFragment = new TrainCardDeckFragmentView();
        transaction.replace(R.id.frame, trainCardDeckFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onDisplayGameStatusClicked() {
        // disable all buttons
        disableAllButtons();
        // make routes not claimable
        setRoutesClaimable(false);
        // set fragment to draw card fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        CurrentGameStatusFragmentView currentGameStatusFragment = new CurrentGameStatusFragmentView();
        transaction.replace(R.id.frame, currentGameStatusFragment)
                .addToBackStack(null)
                .commit();
    }

    public void switchActivities() {
        Intent intent = new Intent(this, GameOverView.class);
        startActivity(intent);
    }

    public void setRoutesClaimable(boolean enabled)
    {
        trainView.setRoutesClaimable(enabled);
    }

    public void onDrawRoutesClicked()
    {
        if(!presenter.canDrawDestCards()) {
            showToast("Not enough destination cards left in the deck to draw 3");
        }
        else {
            presenter.setState(NotMyTurnState.getInstance());

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction  = manager.beginTransaction();
            DrawDestFragmentView drawDestFragmentView = new DrawDestFragmentView();
            transaction.replace(R.id.frame, drawDestFragmentView)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onPlaceTrainsClicked()
    {
        presenter.placeTrains();
    }

    public void drawRoutetoScreen(Route route)
    {
        trainView.claimRoute(route);
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

    public void setDiscardNumber(int num)
    {
        String display = "Draw Dest Card (" + Integer.toString(num) + ")";
        drawRoutesButton.setText(display);
    }

    public void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void setDrawTrainCardsEnabled(boolean enabled)
    {
        drawTrainsButton.setEnabled(enabled);
    }

    public void setDrawDestCardsEnabled(boolean enabled)
    {
        drawRoutesButton.setEnabled(enabled);
    }

    public void setClaimRouteEnabled(boolean enabled)
    {
        placeTrainsButton.setEnabled(enabled);
    }

    public void setDisplayGameStatusButtonEnabled(boolean enabled) {
        displayGameStatusButton.setEnabled(enabled);
    }
}
