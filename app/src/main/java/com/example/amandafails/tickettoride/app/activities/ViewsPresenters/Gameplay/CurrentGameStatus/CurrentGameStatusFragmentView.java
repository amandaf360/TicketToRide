package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.CurrentGameStatus;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.ViewPagerAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;

public class CurrentGameStatusFragmentView extends Fragment implements ICurrentGameStatusFragmentView
{

    private final int MAX_NUM_PLAYERS = 5;
    private final int NUM_TYPES_OF_CARDS = 9;

    private Button exitButton;

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

    // all player trains
    private TextView player1Trains;
    private TextView player2Trains;
    private TextView player3Trains;
    private TextView player4Trains;
    private TextView player5Trains;

    // all player cards
    private TextView player1Cards;
    private TextView player2Cards;
    private TextView player3Cards;
    private TextView player4Cards;
    private TextView player5Cards;

    // all player routes
    private TextView player1Routes;
    private TextView player2Routes;
    private TextView player3Routes;
    private TextView player4Routes;
    private TextView player5Routes;

    private TextView player1Destinations;
    private TextView player2Destinations;
    private TextView player3Destinations;
    private TextView player4Destinations;
    private TextView player5Destinations;

    private TextView numRed;
    private TextView numBlue;
    private TextView numGreen;
    private TextView numYellow;
    private TextView numWhite;
    private TextView numBlack;
    private TextView numWild;
    private TextView numOrange;
    private TextView numPurple;

    //private ClientModel clientModel = ClientModel.getInstance();
    // Presenter
    ICurrentGameStatusFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_game_status, container, false);

        presenter = new CurrentGameStatusFragmentPresenter(this);

        exitButton = v.findViewById(R.id.button_game_status_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExitButtonClicked();
            }
        });
        player1Name = v.findViewById(R.id.player1_name_text);
        player2Name = v.findViewById(R.id.player2_name_text);
        player3Name = v.findViewById(R.id.player3_name_text);
        player4Name = v.findViewById(R.id.player4_name_text);
        player5Name = v.findViewById(R.id.player5_name_text);

        populatePlayerNames();

        player1Points = v.findViewById(R.id.player1_points_text);
        player2Points = v.findViewById(R.id.player2_points_text);
        player3Points = v.findViewById(R.id.player3_points_text);
        player4Points = v.findViewById(R.id.player4_points_text);
        player5Points = v.findViewById(R.id.player5_points_text);

        populatePlayerPoints();

        player1Trains = v.findViewById(R.id.player1_trains_text);
        player2Trains = v.findViewById(R.id.player2_trains_text);
        player3Trains = v.findViewById(R.id.player3_trains_text);
        player4Trains = v.findViewById(R.id.player4_trains_text);
        player5Trains = v.findViewById(R.id.player5_trains_text);

        populatePlayerTrains();

        player1Routes = v.findViewById(R.id.player1_routes_text);
        player2Routes = v.findViewById(R.id.player2_routes_text);
        player3Routes = v.findViewById(R.id.player3_routes_text);
        player4Routes = v.findViewById(R.id.player4_routes_text);
        player5Routes = v.findViewById(R.id.player5_routes_text);

        populatePlayerRoutes();

        player1Cards = v.findViewById(R.id.player1_cards_text);
        player2Cards = v.findViewById(R.id.player2_cards_text);
        player3Cards = v.findViewById(R.id.player3_cards_text);
        player4Cards = v.findViewById(R.id.player4_cards_text);
        player5Cards = v.findViewById(R.id.player5_cards_text);

        populatePlayerCards();

        player1Destinations = v.findViewById(R.id.player1_destinations_text);
        player2Destinations = v.findViewById(R.id.player2_destinations_text);
        player3Destinations = v.findViewById(R.id.player3_destinations_text);
        player4Destinations = v.findViewById(R.id.player4_destinations_text);
        player5Destinations = v.findViewById(R.id.player5_destinations_text);

        populatePlayerDestinations();

        numRed = v.findViewById(R.id.red_cards_value);
        numBlue = v.findViewById(R.id.blue_cards_value);
        numGreen = v.findViewById(R.id.green_cards_value);
        numYellow = v.findViewById(R.id.yellow_cards_value);
        numWhite = v.findViewById(R.id.white_cards_value);
        numBlack = v.findViewById(R.id.black_cards_value);
        numWild = v.findViewById(R.id.wild_cards_value);
        numOrange = v.findViewById(R.id.orange_cards_value);
        numPurple = v.findViewById(R.id.purple_cards_value);

        populatePlayerCardsValues();

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = v.findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        ViewPagerAdaptor adapter = new ViewPagerAdaptor(getContext(), getChildFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = v.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onExitButtonClicked() {
        presenter.exit();
    }

    @Override
    public void popFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }

    @Override
    public void populateEverything() {
        populatePlayerNames();
        populatePlayerCards();
        populatePlayerPoints();
        populatePlayerRoutes();
        populatePlayerTrains();
        populatePlayerDestinations();
        populatePlayerCardsValues();
    }

    private void populatePlayerNames() {
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

    private void populatePlayerPoints() {
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

    private void populatePlayerTrains() {
        List<String> trains = presenter.getPlayerTrains();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Trains);
        texts.add(player2Trains);
        texts.add(player3Trains);
        texts.add(player4Trains);
        texts.add(player5Trains);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(!trains.get(i).equals("-1")) {
                texts.get(i).setText(trains.get(i)); }
            else {
                texts.get(i).setText("");
            }
        }
    }

    private void populatePlayerRoutes() {
        List<String> routes = presenter.getPlayerRoutes();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Routes);
        texts.add(player2Routes);
        texts.add(player3Routes);
        texts.add(player4Routes);
        texts.add(player5Routes);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(!routes.get(i).equals("-1")) {
                texts.get(i).setText(routes.get(i)); }
            else {
                texts.get(i).setText("");
            }
        }
    }

    private void populatePlayerCards() {
        List<String> cards = presenter.getPlayerCards();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Cards);
        texts.add(player2Cards);
        texts.add(player3Cards);
        texts.add(player4Cards);
        texts.add(player5Cards);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(!cards.get(i).equals("-1")) {
                texts.get(i).setText(cards.get(i));
            }
            else {
                texts.get(i).setText("");
            }
        }
    }

    private void populatePlayerDestinations() {
        List<String> destCards = presenter.getPlayerDests();

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(player1Destinations);
        texts.add(player2Destinations);
        texts.add(player3Destinations);
        texts.add(player4Destinations);
        texts.add(player5Destinations);

        for(int i = 0; i < MAX_NUM_PLAYERS; i++) {
            if(!destCards.get(i).equals("-1")) {
                texts.get(i).setText(destCards.get(i));
            }
            else {
                texts.get(i).setText("");
            }
        }
    }

    private void populatePlayerCardsValues() {
        List<String> playerCardVals = presenter.getPlayerNumEachCard();

        ArrayList<TextView> texts = new ArrayList<>();

        texts.add(numRed);
        texts.add(numBlue);
        texts.add(numGreen);
        texts.add(numYellow);
        texts.add(numWhite);
        texts.add(numBlack);
        texts.add(numWild);
        texts.add(numOrange);
        texts.add(numPurple);

        for(int i = 0; i < NUM_TYPES_OF_CARDS; i++) {
            texts.get(i).setText(playerCardVals.get(i));
        }
    }
}
