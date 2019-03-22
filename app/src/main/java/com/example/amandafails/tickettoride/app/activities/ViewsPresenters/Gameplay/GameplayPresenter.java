package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.amandafails.tickettoride.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import PossiblyHelpful.ClaimRouteHelper;
import services.DiscardDestCardService;
import services.DrawDestCardService;
import ClientModel.PlayerHandDestinations;
import ClientModel.TrainCarCard;
import ClientModel.Route;
import ClientModel.Game;
import ClientModel.AsyncDemo;

public class GameplayPresenter implements IGameplayPresenter, Observer
{
    GameplayView view;
    ClientModel clientModel;

    public GameplayPresenter(GameplayView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        clientModel.initializeRoutes();
    }

    public void drawCards()
    {
        // is this the function to choose your first destination cards??

    }

    public void drawRoute()
    {
        view.setDrawRoutesClickable(false);
        DrawDestCardService drawDestCardService = new DrawDestCardService();
        drawDestCardService.drawCards(3);
        ArrayList<DestinationCards> cards = clientModel.getNewlyAddedDestinationCardsFromMainPlayer();

        String zeroth = "Do Not Discard";
        String first = cards.get(0).toString();
        String second = cards.get(1).toString();
        String third = cards.get(2).toString();
        String[] options = {zeroth, first, second, third};
        String dialogTitle = "Choose a route to discard.";
        int itemSelected = 0;



    }

    public void placeTrains()
    {

    }

    public String currentTurn()
    {
        return clientModel.getActiveGame().getCurrentPlayersTurn();
    }


    @Override
    public void update(Observable observable, Object o)
    {
        if(o.getClass() == PlayerHandDestinations.class)
        {
            if(view.isFirstCreate())
            {
                PlayerHandDestinations hand = (PlayerHandDestinations)o;
                ArrayList<DestinationCards> destList = hand.getCardList();
                String zeroth = "Do Not Discard";
                String first = destList.get(0).toString();
                String second = destList.get(1).toString();
                String third = destList.get(2).toString();
                String[] passer = {zeroth, first, second, third};
                view.setFirstCreateToFalse();
                showDialog(passer);
            }
        }

        if(o.getClass() == Route.class)
        {
            view.drawRoutetoScreen((Route)o);
        }

        if(o.getClass() == Game.class)
        {
            view.changeTurnName(clientModel.getActiveGame().getCurrentPlayersTurn());
        }
        view.setDiscardNumber(ClientModel.getInstance().getActiveGame().getNumDestCardsInDeck());
    }


    private int numDemoClicks = 0;
    public void demo()
    {
        AsyncDemo demo = new AsyncDemo(this);
        demo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        switch (numDemoClicks)
        {
            case 0:
                clientModel.getMainPlayer().addPoints(1000);
                //
                break;
            case 1:
                clientModel.getMainPlayer().addTrainCardToHand(new TrainCarCard("locomotive"));
                break;
            case 2:
                clientModel.claimRouteByIndex(3, clientModel.getActiveGame().getPlayers().get(0).getName());

                break;
            case 3:
                clientModel.getActiveGame().getPlayers().get(0).addTrainCardToHand(new TrainCarCard("locomotive"));
                if(clientModel.getActiveGame().getPlayers().size() > 1)
                {
                    clientModel.getActiveGame().getPlayers().get(1).addTrainCardToHand(new TrainCarCard("locomotive"));
                }
                clientModel.getActiveGame().getPlayers().get(0).addTrainCardToHand(new TrainCarCard("locomotive"));
                if(clientModel.getActiveGame().getPlayers().size() > 1)
                {
                    clientModel.getActiveGame().getPlayers().get(1).addTrainCardToHand(new TrainCarCard("locomotive"));
                }
                break;
        }
        numDemoClicks++;
    }

    public void displayToast(String toastString)
    {
        view.showToast(toastString);
    }


    /*
     * This is the section for creating the dialog to choose the destination cards to get rid of.
     * it's kinda messy.
     */

    public void chooseDestinationCards()
    {
        DrawDestCardService drawDestCardService = new DrawDestCardService();
        drawDestCardService.drawCards(3);
    }


    private boolean showDialog(final String[] destCards)
    {
        final String dialogTitle = "Choose a Destination Card to discard!";


        final String[] singleChoiceItems = {"Do Not Discard","Dest1","Dest2","Dest3"};

        //singleChoiceItems = destCards;
        final int itemSelected = 0;
        new AlertDialog.Builder(view)   //AlertDialog.Builder(view. R.whatever.dialog)
                .setTitle(dialogTitle)
                .setCancelable(false)
                //.setCanceledOnTouchOutside(false)
                //.setMessage(joinGameName)
                .setSingleChoiceItems(destCards, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setChoice(selectedIndex);
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        doit(destCards);
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

    private int getDestChoiceValue()
    {
        return destChoiceValue;
    }

    private void doit(String[] selection)
    {


        if(getDestChoiceValue() == 0)
        {
            // do nothing
        }
        else
        {
            DiscardDestCardService discardDestCardService = new DiscardDestCardService();
            discardDestCardService.discardCard(clientModel.getMainPlayer().getPlayerHandDestinations()
                    .getCardList().get(destChoiceValue - 1));
            clientModel.deleteMainPlayersDestinationCardFromHand(
                    clientModel.getMainPlayer().getPlayerHandDestinations().getCardList().get(destChoiceValue - 1));
        }

        /*
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


        */

        //Player player = model.getCurrentPlayer();
        //player.setDestCards(arrayList);


    }
}
