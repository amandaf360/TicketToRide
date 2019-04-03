package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayView;

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
    private Button exit;

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
                onCardClicked(0);
            }
        });

        card2 = v.findViewById(R.id.train_card2_button);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardClicked(1);
            }
        });

        card3 = v.findViewById(R.id.train_card3_button);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardClicked(2);
            }
        });

        card4 = v.findViewById(R.id.train_card4_button);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardClicked(3);
            }
        });

        card5 = v.findViewById(R.id.train_card5_button);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCardClicked(4);
            }
        });

        deck = v.findViewById(R.id.train_deck_button);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getNumCardsLeftInDeck() <= 0) {
                    showToast("Deck is empty, can't draw cards from here");
                }
                else {
                    onCardClicked(-1);
                }
            }
        });

        exit = v.findViewById(R.id.train_card_exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onExitClicked();
            }
        });

        setCardValues();

        return v;
    }

    @Override
    public void onCardClicked(int cardIndex) {
        presenter.drawCard(cardIndex);
    }

    @Override
    public void onExitClicked() {
        presenter.exit();
    }

    @Override
    public void popFragment(boolean exitPressed) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Activity activity = getActivity();
        if(exitPressed) {
            ((GameplayView)activity).enableAllButtons();
        }
        else {
            ((GameplayView)activity).setDisplayGameStatusButtonEnabled(true);
        }
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setExitEnabled(boolean enabled) {
        exit.setEnabled(enabled);
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
