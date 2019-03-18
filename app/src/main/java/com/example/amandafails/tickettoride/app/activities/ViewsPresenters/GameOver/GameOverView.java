package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.amandafails.tickettoride.R;

public class GameOverView extends AppCompatActivity implements IGameOverView {

    private GameOverPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        presenter = new GameOverPresenter(this);
    }


    @Override
    public void updateGameOverDisplay() {

    }
}
