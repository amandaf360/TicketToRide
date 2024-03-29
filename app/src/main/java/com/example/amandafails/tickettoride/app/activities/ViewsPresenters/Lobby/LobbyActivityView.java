package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayView;
import com.example.amandafails.tickettoride.app.adaptors.LobbyRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ClientModel.ClientModel;
import ClientModel.Player;
import proxy.ServerProxy;

public class LobbyActivityView extends AppCompatActivity implements ILobbyView {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> lines;
    int maxNumPlayers;
    int currentNumPlayers;

    private LobbyActivityPresenter presenter;

    private TextView gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        presenter = new LobbyActivityPresenter(this);
        lines = new ArrayList<>();
        mAdapter = new LobbyRecyclerViewAdaptor(lines);

        gameName = findViewById(R.id.game_name_text);
        String gameText = presenter.getGameName(); // + "'s "
        gameName.setText(gameText);

        mRecyclerView = findViewById(R.id.my_lobby_recycler_view);

        presenter.setNumCurrentPlayers();
        presenter.getMaxNumPlayers();

        // display the players already in the game
        List<Player> players = presenter.getCurrentPlayers();
        for(int i = 0; i < currentNumPlayers; i++) {
            displayPlayer(players.get(i));
        }

        if(lines.size() == maxNumPlayers) {
            ServerProxy proxy = new ServerProxy();
            proxy.beginGame(ClientModel.getInstance().getActiveGame().getGameNum());
        }

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                if(lines.size() == maxNumPlayers) {
                    System.out.println("HERE");
                    presenter.disconnectObserver();
                    switchActivity();
                }
            }

            @Override
            public void onChanged() {
                if(lines.size() == maxNumPlayers) {
                    presenter.disconnectObserver();
                    switchActivity();
                }
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void switchActivity()
    {
        Intent intent = new Intent(this, GameplayView.class);
        startActivity(intent);
    }

    @Override
    public void displayPlayer(Player player) {
        String toAdd = player.getName() + " has entered the game.";
        lines.add(toAdd);

        mAdapter = new LobbyRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        if(lines.size() == maxNumPlayers) {
            presenter.disconnectObserver();
            switchActivity();
        }
    }

    @Override
    public void displayErrorMessage(String error) {
        // give a toast displaying error message
        Context context = Objects.requireNonNull(this).getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, error, duration);
        toast.show();
    }

    @Override
    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    @Override
    public void setCurrentNumPlayers(int currentNumPlayers) {
        this.currentNumPlayers = currentNumPlayers;
    }
}
