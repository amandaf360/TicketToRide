package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.LobbyRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observer;

import ThomasStuff.ClientModel;
import ThomasStuff.Player;

public class LobbyActivityView extends AppCompatActivity implements ILobbyView {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> lines;
    int numPlayers;
    private LobbyActivityPresenter presenter;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        presenter = new LobbyActivityPresenter(this);
        lines = new ArrayList<>();
        mAdapter = new LobbyRecyclerViewAdaptor(lines);

        startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartClicked();
            }
        });

        startButton.setEnabled(false);

        mRecyclerView = findViewById(R.id.my_lobby_recycler_view);

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                if(lines.size() != numPlayers) {
                    startButton.setEnabled(false);
                }
                else {
                    startButton.setEnabled(true);
                }
            }

            @Override
            public void onChanged() {
                if(lines.size() != numPlayers) {
                    startButton.setEnabled(false);
                }
                else {
                    startButton.setEnabled(true);
                }
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        ClientModel clientModel = new ClientModel();
//        // CHANGE THIS LINE TOO
//        // int numPlayers = ClientModel.get().getGame().getMaxPlayers();
//        numPlayers = clientModel.getGame().getMaxPlayers();

        mRecyclerView.setAdapter(mAdapter);
    }

    /* DON'T KNOW IF WE NEED THIS OR NOT
    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        finish();
    }
    */

    @Override
    public void onStartClicked() {
        // for now, just show a toast
        Context context = Objects.requireNonNull(this).getApplicationContext();
        CharSequence text = "Start Game pressed!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // call register in presenter
        presenter.startGame();
    }

    @Override
    public void setStartEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
    }

    @Override
    public void displayPlayer(Player player) {
        String toAdd = player.getName() + " has entered the game.";
        lines.add(toAdd);
        mAdapter.notifyDataSetChanged();

        // don't know if this works in here??
        mAdapter = new LobbyRecyclerViewAdaptor(lines);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void displayErrorMessage(String error) {
        // give a toast displaying error message
        Context context = Objects.requireNonNull(this).getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, error, duration);
        toast.show();
    }
}
