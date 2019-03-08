package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amandafails.tickettoride.R;

import java.util.List;

import ClientModel.ClientModel;

public class TrainCardDeckFragment extends Fragment {

    // client model
    ClientModel clientModel = ClientModel.getInstance();

    // presenter
    GameplayPresenter presenter;

    // buttons
    private Button card1;
    private Button card2;
    private Button card3;
    private Button card4;
    private Button card5;
    private Button deck;

    //methods
    public TrainCardDeckFragment() {} //empty public constructor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_train_card_deck, container, false);

        card1 = v.findViewById(R.id.train_card1_button);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginClicked();
            }
        });

        card2 = v.findViewById(R.id.train_card2_button);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginClicked();
            }
        });

        card3 = v.findViewById(R.id.train_card3_button);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginClicked();
            }
        });

        card4 = v.findViewById(R.id.train_card4_button);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginClicked();
            }
        });

        card5 = v.findViewById(R.id.train_card5_button);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onLoginClicked();
            }
        });

        deck = v.findViewById(R.id.train_deck_button);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeckClicked();
            }
        });

        //List<TrainCards>
        //for(int i )
        // set what cards are shown - grab from model!
        card1.setText("Blue");
        card2.setText("Green");
        card3.setText("Black");
        card4.setText("White");
        card5.setText("Yellow");

        // set how many cards are left in deck - grab from model!
        deck.setText("50");

        return v;
    }

    public void onDeckClicked() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }

    public void drawCards() {
        // call draw card service?
        // once 2 cards are drawn, switch fragments?
//        Intent i = new Intent(getActivity(), GameplayView.class);
//        startActivity(i);
    }

}
