package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;

import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.*;

public class TrainCardDeckFragmentView extends Fragment implements ITrainCardDeckFragmentView {

    // presenter
    ITrainCardDeckFragmentPresenter presenter;

    // buttons
    private Button card1;
    private Button card2;
    private Button card3;
    private Button card4;
    private Button card5;
    private Button deck;

    //methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_train_card_deck, container, false);

        presenter = new TrainCardDeckFragmentPresenter(this);

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

        List<TrainCarCard> faceUpCards = presenter.getTrainCarCards();
        // set what cards are shown - grab from model!
        card1.setText(faceUpCards.get(0).getColor());
        card2.setText(faceUpCards.get(1).getColor());
        card3.setText(faceUpCards.get(2).getColor());
        card4.setText(faceUpCards.get(3).getColor());
        card5.setText(faceUpCards.get(4).getColor());

        // set how many cards are left in deck - grab from model!
        deck.setText(String.format(Locale.getDefault(), "%d", presenter.getNumCardsLeftInDeck()));

        return v;
    }

    // TEMPORARY FUNCTIONALITY!!!
    @Override
    public void onDeckClicked() {
        // will eventually just draw a card when the deck is clicked
        // presenter.drawCard();

        // FOR NOW, CALL DEMO FUNCTION
        presenter.demoFunction();

        // FOR NOW, TREAT THIS AS AN "EXIT" BUTTON
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }

    @Override
    public void setCardValues() {
        List<TrainCarCard> faceUpCards = presenter.getTrainCarCards();
        // set what cards are shown - grab from model!
        card1.setText(faceUpCards.get(0).getColor());
        card2.setText(faceUpCards.get(1).getColor());
        card3.setText(faceUpCards.get(2).getColor());
        card4.setText(faceUpCards.get(3).getColor());
        card5.setText(faceUpCards.get(4).getColor());

        // set how many cards are in the deck
        deck.setText(String.format(Locale.getDefault(), "%d", presenter.getNumCardsLeftInDeck()));
    }

}
