package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.amandafails.tickettoride.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameplayPresenter implements IGameplayPresenter, Observer
{
    GameplayView view;

    public GameplayPresenter(GameplayView view)
    {
        this.view = view;
    }

    public void drawCards()
    {
        // is this the function to choose your first destination cards??

    }

    public void drawRoute()
    {

    }

    public void placeTrains()
    {

    }

    public void update(Observable observable, Object o)
    {

    }


    /*
     * This is the section for creating the dialog to choose the destination cards to get rid of.
     * it's kinda messy.
     */

    public boolean chooseDestinationCards()
    {
        final String dialogTitle = "Choose a Destination Card to discard!";
        final String[] singleChoiceItems = {"Do Not Discard","Dest1","Dest2","Dest3"};
        // GetDestCardsService getDestCardsService = new GetDestCardsService();
        // String[] destCards = getDestCardsService.getCardChoices
        // singleChoiceItems[1] = destCards[0];
        // singleChoiceItems[2] = destCards[1];
        // singleChoiceItems[3] = destCards[2];
        final int itemSelected = 0;
        new AlertDialog.Builder(view)
                .setTitle(dialogTitle)
                .setCancelable(false)
                //.setCanceledOnTouchOutside(false)
                //.setMessage(joinGameName)
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setChoice(selectedIndex);
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        doit(singleChoiceItems);
                    }
                })

                .show();
        return true;
    }

    private static int destChoiceValue = 0;

    private void setChoice(int index)
    {
        destChoiceValue = index;
    }

    private void doit(String[] selection)
    {

        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0; i < 4; i++)
        {
            arrayList.add(selection[i]);
        }
        if(destChoiceValue == 0)
        {
            arrayList.remove(0);
        }
        else
        {
            arrayList.remove(destChoiceValue);
            arrayList.remove(0);
        }


        //ClientModel model = ClientModel.getInstance();
        //Player player = model.getCurrentPlayer();
        //player.setDestCards(arrayList);

        
    }
}
