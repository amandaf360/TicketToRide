package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.amandafails.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

public class GameOverView extends AppCompatActivity implements IGameOverView {

    private GameOverPresenter presenter;
    private TextView winnerName;

    final int MAX_NUM_PLAYERS = 5;

    // all player names
    private TextView player1Name;
    private TextView player2Name;
    private TextView player3Name;
    private TextView player4Name;
    private TextView player5Name;

    // all player points
    private TextView player1Points;
    private TextView player2Points;
    private TextView player3Points;
    private TextView player4Points;
    private TextView player5Points;

    // all player route points
    private TextView player1RoutePoints;
    private TextView player2RoutePoints;
    private TextView player3RoutePoints;
    private TextView player4RoutePoints;
    private TextView player5RoutePoints;

    // all player dest. card points
    private TextView player1DestCardPoints;
    private TextView player2DestCardPoints;
    private TextView player3DestCardPoints;
    private TextView player4DestCardPoints;
    private TextView player5DestCardPoints;

    // all player dest. card points lost
    private TextView player1DestCardPointsLost;
    private TextView player2DestCardPointsLost;
    private TextView player3DestCardPointsLost;
    private TextView player4DestCardPointsLost;
    private TextView player5DestCardPointsLost;

    private TextView longestPathWinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        presenter = new GameOverPresenter(this);

        winnerName = findViewById(R.id.winnerName);

        player1Name = findViewById(R.id.player1_name_text);
        player2Name = findViewById(R.id.player2_name_text);
        player3Name = findViewById(R.id.player3_name_text);
        player4Name = findViewById(R.id.player4_name_text);
        player5Name = findViewById(R.id.player5_name_text);

        player1Points = findViewById(R.id.player1_points_text);
        player2Points = findViewById(R.id.player2_points_text);
        player3Points = findViewById(R.id.player3_points_text);
        player4Points = findViewById(R.id.player4_points_text);
        player5Points = findViewById(R.id.player5_points_text);

        player1DestCardPoints = findViewById(R.id.player1_dest_points_text);
        player2DestCardPoints = findViewById(R.id.player2_dest_points_text);
        player3DestCardPoints = findViewById(R.id.player3_dest_points_text);
        player4DestCardPoints = findViewById(R.id.player4_dest_points_text);
        player5DestCardPoints = findViewById(R.id.player5_dest_points_text);

        player1DestCardPointsLost = findViewById(R.id.player1_dest_points_lost_text);
        player2DestCardPointsLost = findViewById(R.id.player2_dest_points_lost_text);
        player3DestCardPointsLost = findViewById(R.id.player3_dest_points_lost_text);
        player4DestCardPointsLost = findViewById(R.id.player4_dest_points_lost_text);
        player5DestCardPointsLost = findViewById(R.id.player5_dest_points_lost_text);

        updateGameOverDisplay();
    }


    @Override
    public void updateGameOverDisplay() {
        showWinner();
        showPlayers();
        showPoints();
        showRoutePoints();
        showDestPoints();
        showDestPointsLost();
        showLongestPathWinner();
    }

    private void showPlayers() {
        List<String> names = presenter.getPlayerNames();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Name);
        texts.add(player2Name);
        texts.add(player3Name);
        texts.add(player4Name);
        texts.add(player5Name);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(names.get(i) != null) {
                texts.get(i).setText(names.get(i));
            }
            else {
                texts.get(i).setText("");
            }
        }
        presenter.setPlayerNameColors(texts);
    }

    private void showPoints() {
        presenter.calculateFinalPoints();
    }

    private void showRoutePoints() {
        List<String> points = presenter.getPlayerPoints();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Points);
        texts.add(player2Points);
        texts.add(player3Points);
        texts.add(player4Points);
        texts.add(player5Points);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(!points.get(i).equals("-1")) {
                texts.get(i).setText(points.get(i));
            }
            else {
                texts.get(i).setText("");
            }
        }
    }

    private void showDestPoints() {

    }

    private void showDestPointsLost() {

    }

    private void showWinner() {
        winnerName.setText(presenter.getWinnerName());
    }

    private void showLongestPathWinner() {
        longestPathWinner.setText(presenter.getLongestRouteWinnerName());
    }

}
