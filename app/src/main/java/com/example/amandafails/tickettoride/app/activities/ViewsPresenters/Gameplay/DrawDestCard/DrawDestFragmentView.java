package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DrawDestCard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.TrainCard.TrainCardDeckFragmentPresenter;

import java.util.List;
import java.util.Locale;
import java.util.Observer;

import ClientModel.TrainCarCard;

public class DrawDestFragmentView extends Fragment implements IDrawDestFragmentView
{

    private IDrawDestFragmentPresenter presenter;

    private TextView dest1;
    private TextView dest2;
    private TextView dest3;
    private Button doneButton;

    private boolean dest1On;
    private boolean dest2On;
    private boolean dest3On;

    private int onColor = Color.BLUE;
    private int offColor = Color.LTGRAY;

    //methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.draw_dest_card_popup, container, false);

        presenter = new DrawDestFragmentPresenter(this);


        setDestCardValues();


        dest1On = false;
        dest2On = false;
        dest3On = false;



        dest1 = v.findViewById(R.id.first_dest_card_popup);
        dest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestCardClicked(0);
            }
        });

        dest2 = v.findViewById(R.id.second_dest_card_popup);
        dest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestCardClicked(1);
            }
        });

        dest3 = v.findViewById(R.id.third_dest_card_popup);
        dest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestCardClicked(2);
            }
        });

        doneButton = v.findViewById(R.id.dest_card_popup_button);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onDoneButtonClicked();
            }
        });



        return v;
    }

    @Override
    public void onDestCardClicked(int cardIndex) {
        presenter.clickCard(cardIndex);
    }


    @Override
    public void popFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDestCardValues() {
        presenter.drawCards();

    }

    @Override
    public void setCards(List<String> cards)
    {
        dest1.setText(cards.get(0));
        dest1.setBackgroundColor(offColor);
        dest2.setText(cards.get(1));
        dest2.setBackgroundColor(offColor);
        dest3.setText(cards.get(2));
        dest3.setBackgroundColor(offColor);
    }


    @Override
    public void changeCardColor(int index)
    {
        if(index == 0)
        {
            if(dest1On)
            {
                dest1On = false;
                dest1.setBackgroundColor(offColor);
            }
            else
            {
                dest1On = true;
                dest1.setBackgroundColor(onColor);
            }
        }
        if(index == 1)
        {
            if(dest2On)
            {
                dest2On = false;
                dest2.setBackgroundColor(offColor);
            }
            else
            {
                dest2On = true;
                dest2.setBackgroundColor(onColor);
            }
        }
        if(index == 2)
        {
            if(dest3On)
            {
                dest3On = false;
                dest3.setBackgroundColor(offColor);
            }
            else
            {
                dest3On = true;
                dest3.setBackgroundColor(onColor);
            }
        }


    }

    public void onDoneButtonClicked()
    {
        presenter.doneButtonClicked();
    }

    @Override
    public String getViewText(int i)
    {
        if(i == 0)
        {
            return dest1.getText().toString();
        }
        if(i == 1)
        {
            return dest2.getText().toString();
        }
        if(i == 2)
        {
            return dest3.getText().toString();
        }
        return null;
    }

}
