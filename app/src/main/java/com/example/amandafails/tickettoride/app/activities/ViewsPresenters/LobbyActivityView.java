package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.LobbyRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ThomasStuff.ClientModel;
import ThomasStuff.Player;

public class LobbyActivityView extends AppCompatActivity implements ILobbyView {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> lines;
    int maxNumPlayers;
    private LobbyActivityPresenter presenter;
    private ClientModel clientModel = ClientModel.getInstance();

    private TextView gameName;

    private Button startButton;

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
        startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartClicked();
            }
        });

        mRecyclerView = findViewById(R.id.my_lobby_recycler_view);

        // display the players already in the game
        int numPlayers = clientModel.getActiveGame().getCurrentPlayers();
        List<Player> players = clientModel.getActiveGame().getPlayers();
        for(int i = 0; i < numPlayers; i++) {
            displayPlayer(players.get(i));
        }

        maxNumPlayers = clientModel.getActiveGame().getMaxPlayers();
        // print out max number of people allowed in the game
        System.out.println("Max players in game: " + maxNumPlayers);

        // print out current number of people in the game
        System.out.println("Players in game: " + numPlayers);

        // enable start button if number of players in game is at it's max
        if(numPlayers == maxNumPlayers) {
            startButton.setEnabled(true);
        }
        else {
            startButton.setEnabled(false);
        }

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                int numPlayers = clientModel.getActiveGame().getCurrentPlayers();
                int sizeOfLines = lines.size();
                maxNumPlayers = clientModel.getActiveGame().getMaxPlayers();
                // print out max number of people allowed in the game
                System.out.println("Max players in game: " + maxNumPlayers);

                // print out current number of people in the game
                System.out.println("Players in game: " + numPlayers);

                // print out lines size
                System.out.println("Lines size: " + sizeOfLines);
                if(lines.size() != maxNumPlayers) {
                    startButton.setEnabled(false);
                }
                else {
                    startButton.setEnabled(true);
                }
            }

            @Override
            public void onChanged() {
                int numPlayers = clientModel.getActiveGame().getCurrentPlayers();
                int sizeOfLines = lines.size();
                maxNumPlayers = clientModel.getActiveGame().getMaxPlayers();
                // print out max number of people allowed in the game
                System.out.println("Max players in game: " + maxNumPlayers);

                // print out current number of people in the game
                System.out.println("Players in game: " + numPlayers);

                // print out lines size
                System.out.println("Lines size: " + sizeOfLines);
                if(lines.size() != maxNumPlayers) {
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

        // call start game in presenter
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
        // print out lines size
        System.out.println("Lines size: " + lines.size());

        mAdapter = new LobbyRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
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
