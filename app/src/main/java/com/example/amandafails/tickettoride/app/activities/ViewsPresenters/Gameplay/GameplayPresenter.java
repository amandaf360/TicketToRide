package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.amandafails.tickettoride.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import services.DrawDestCardService;
import ClientModel.PlayerHandDestinations;

public class GameplayPresenter implements IGameplayPresenter, Observer
{
    GameplayView view;
    ClientModel clientModel;

    public GameplayPresenter(GameplayView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        clientModel.addObserver(this);
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
        if(o.getClass() == PlayerHandDestinations.class)
        {
            if(view.isFirstCreate())
            {
                PlayerHandDestinations hand = (PlayerHandDestinations)o;
                ArrayList<DestinationCards> destList = hand.getCardList();
                String zeroth = "Do Not Discard";
                String first = destList.get(0).getCityOne() + " to " + destList.get(0).getCityTwo();
                String second = destList.get(1).getCityOne() + " to " + destList.get(1).getCityTwo();
                String third = destList.get(2).getCityOne() + " to " + destList.get(2).getCityTwo();
                String[] passer = {zeroth, first, second, third};
                view.setFirstCreateToFalse();
                showDialog(passer);
            }
        }
    }


    /*
     * This is the section for creating the dialog to choose the destination cards to get rid of.
     * it's kinda messy.
     */

    public void chooseDestinationCards()
    {
        //DrawDestCardService drawDestCardService = new DrawDestCardService();
        //drawDestCardService.drawCards(3);
    }


    private boolean showDialog(String[] destCards)
    {
        final String dialogTitle = "Choose a Destination Card to discard!";


        final String[] singleChoiceItems = {"Do Not Discard","Dest1","Dest2","Dest3"};

        // singleChoiceItems = destCards;
        final int itemSelected = 0;
        new AlertDialog.Builder(view)   //AlertDialog.Builder(view. R.whatever.dialog)
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
        /*

        if(destChoiceValue == 0)
        {
            // do nothing
        }
        else
        {
            ClientModel.deleteMainPlayersDestinationCardFromHand(
                    ClientModel.getMainPlayer().getPlayerHandDestinations().getCardList().get(destChoiceValue - 1));
        }

        */

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



        //Player player = model.getCurrentPlayer();
        //player.setDestCards(arrayList);


    }
}
