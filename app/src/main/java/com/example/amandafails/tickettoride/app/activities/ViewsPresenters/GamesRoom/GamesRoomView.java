package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GamesRoom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Lobby.LobbyActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ClientModel.Game;

public class GamesRoomView extends AppCompatActivity implements IGamesRoomView
{

    private RecyclerView gameListRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private List<Game> games;
    int numPlayers;
    private GamesRoomPresenter presenter;
    private GamesRoomView gamesRoomView = this;
    private GamesAdapter gameAdapter;

    private Button createGame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        presenter = new GamesRoomPresenter(this);

        createGame = findViewById(R.id.create_game);
        createGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onCreateGameClicked();
            }
        });

        //createGame.setEnabled(false);



        gameListRecycler = findViewById(R.id.my_games_recycler_view);

        layoutManager = new LinearLayoutManager(this);

        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        gameListRecycler.setLayoutManager(layoutManager);

        setGames(); // initializes games
        upDateGameList();
    }

    @Override
    public void onCreateGameClicked()
    {
        // for now, just show a toast
        //Context context = Objects.requireNonNull(this).getApplicationContext();
        //CharSequence text = "Create Game pressed!";
        //int duration = Toast.LENGTH_LONG;

        //Toast toast = Toast.makeText(context, text, duration);
        presenter.createGame(gamesRoomView);
        gameAdapter.notifyDataSetChanged();
        updateView();
        //toast.show();

        // call register in presenter
        //presenter.startGame();
    }

    @Override
    public void switchActivity() {
        Intent intent = new Intent(gamesRoomView, LobbyActivityView.class);
        startActivity(intent);
    }

    @Override
    public void displayErrorMessage(String error)
    {
        // give a toast displaying error message
        Context context = Objects.requireNonNull(this).getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, error, duration);
        toast.show();
    }

    public void setGames()
    {
        games = presenter.getGameListFromModel();
    }

    /*
     * DON"T KNOW IF THIS WILL WORK
     */

    @Override
    public void updateView()
    {
        setGames();
        upDateGameList();
    }

    private void upDateGameList()
    {
        gameAdapter = new GamesRoomView.GamesAdapter(games);
        gameListRecycler.setAdapter(gameAdapter);
    }


    public class GamesAdapter extends RecyclerView.Adapter<GameListHolder>
    {
        private List<Game> gameList;

        public GamesAdapter(List<Game> gameList) //this creates a list of games given
        {
            ArrayList<Game> arrayList = new ArrayList<>();
            for(Game game : gameList)
            {
                if(game.getMaxPlayers() <= game.getCurrentPlayers())
                {
                    // don't add!
                }
                else
                {
                    arrayList.add(game);
                }
            }
            this.gameList = arrayList;
            //show parents
            //also show events
            //make that array list
        }

        @Override
        public GameListHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {


            LayoutInflater layoutInflater = LayoutInflater.from(gamesRoomView);
            View view = layoutInflater.inflate(R.layout.game_list_object, parent, false);
            return new GameListHolder(view);
        }

        @Override
        public void onBindViewHolder(GameListHolder holder, int position) // passes my object to the bind.
        {
            holder.bind(gameList.get(position));
        }

        @Override
        public int getItemCount()
        {
            return gameList.size();
        }
    }

    public class GameListHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView gameNum;
        private TextView gameName;
        private TextView gameCreator;
        private TextView inGame;
        private Game list;

        public GameListHolder(View view)
        {
            super(view);
            gameNum = view.findViewById(R.id.game_list_game_number);
            gameName = view.findViewById(R.id.game_list_game_name);
            gameCreator = view.findViewById(R.id.game_list_creator);
            inGame = view.findViewById(R.id.game_list_in_game);

            itemView.setOnClickListener(this);

        }



        public void bind(final Game list)
        {

            this.list = list;
            gameName.setText(list.getName());
            gameNum.setText(Integer.toString(presenter.getGameNumber(list)));
            gameCreator.setText(list.getCreator());
            int numPlayers = list.getCurrentPlayers();
            int maxPlayers = list.getMaxPlayers();
            String str = numPlayers + "/" + maxPlayers;
            inGame.setText(str);

        }

        @Override
        public void onClick(View view)
        {
            presenter.joinGame(list, gamesRoomView);
        }
    }
}
