package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DrawDestCard;

import java.util.List;

public interface IDrawDestFragmentView
{

    void onDestCardClicked(int cardIndex);



    void popFragment();


    void showToast(String message);


    void setDestCardValues();


    void setCards(List<String> cards);



    void changeCardColor(int index);

    String getViewText(int i);
}
