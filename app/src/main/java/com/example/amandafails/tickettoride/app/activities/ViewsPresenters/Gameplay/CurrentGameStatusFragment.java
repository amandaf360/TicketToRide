package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

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

public class CurrentGameStatusFragment extends Fragment {

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

    public CurrentGameStatusFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        // Find the view pager that will allow the user to swipe between fragments
//        ViewPager viewPager = (ViewPager)getView().findViewById(R.id.viewpager);
//
//        // Create an adapter that knows which fragment should be shown on each page
//        ViewPagerAdaptor adapter = new ViewPagerAdaptor(getContext(), getFragmentManager());
//
//        // Set the adapter onto the view pager
//        viewPager.setAdapter(adapter);
//
//        // Give the TabLayout the ViewPager
//        TabLayout tabLayout = (TabLayout)getView().findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_game_status, container, false);

        exitButton = getView().findViewById(R.id.display_game_status);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExitButtonClicked();
            }
        });

        return rootView;
    }

    public void onExitButtonClicked() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }
}
