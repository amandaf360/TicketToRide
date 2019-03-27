package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.GameplayState;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.MyTurnState;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.NotMyTurnState;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import services.ClaimRouteService;
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
    GameplayState currentState;

    public GameplayPresenter(GameplayView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        clientModel.initializeRoutes();
        if(currentPlayerTurn().equals(clientModel.getMainPlayer().getName()))
        {
            System.out.println("It is my turn!");
            setState(MyTurnState.getInstance());
        }
        else
        {
            System.out.println("It is not my turn!");
            setState(NotMyTurnState.getInstance());
        }
    }

    public void setState(GameplayState newState)
    {
        if(currentState != null)
        {
            currentState.exit(this);
        }
        currentState = newState;
        if(currentState != null)
        {
            currentState.enter(this);
        }
    }


    public void drawCards()
    {
        // is this the function to choose your first destination cards??
    }



    public void placeTrains()
    {
        setState(NotMyTurnState.getInstance());
        view.setRoutesClaimable(true);
    }

    public void claimRouteByTap(ArrayList<Route> routes)
    {
        int numRoutes = routes.size();

        switch(numRoutes)
        {
            case 0:
                //if the array of routes has nothing in it, there are no valid selections, meaning the route is taken. Display a message and
                //resume their turn
                view.showToast("Sorry, this route is already taken");
                setState(MyTurnState.getInstance());
                break;
            case 1:
                //if there is one element, then either it is a double route with only 1 option remaining, or else it is an unclaimed single
                int routeIndex = ClientModel.getInstance().getIndexOfMatchingUnclaimedRoute(routes.get(0));
                int numRelevantColor  = 0;
                String relevantColor = routes.get(0).getColor();
                switch(relevantColor)
                {
                    case "blue":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumBlue();
                        break;
                    case "red":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumRed();
                        break;
                    case "yellow":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumYellow();
                        break;
                    case "white":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumWhite();
                        break;
                    case "black":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumBlack();
                        break;
                    case "green":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumGreen();
                        break;
                    case "purple":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumPurple();
                        break;
                    case "orange":
                         numRelevantColor = clientModel.getMainPlayer().getPlayerHandTrains().getNumOrange();
                        break;
                    case "gray":
                        //TODO: Display window asking which one they want to use, collect answer, change relevant color to chosen color
                        break;
                    default: break;
                }

                //checks if they have enough of the color itself
                if (numRelevantColor >= routes.get(0).getLength())
                {
                    //TODO: end turn
                    ArrayList<TrainCarCard> cards = new ArrayList<>();
                    for(int i = 0; i < routes.get(0).getLength(); i++)
                    {
                        cards.add(new TrainCarCard(relevantColor));
                        clientModel.getMainPlayer().getPlayerHandTrains().discardCard(relevantColor);
                    }
                    ClaimRouteService service = new ClaimRouteService();
                    service.claimRoute(clientModel.getMainPlayer().getName(), routeIndex, cards);
                }
                //or if they have enough with locomotives
                else if(clientModel.getMainPlayer().getPlayerHandTrains().getNumLocomotives() >= routes.get(0).getLength() - numRelevantColor)
                {
                    //TODO: end turn
                    ArrayList<TrainCarCard> cards = new ArrayList<>();
                    for(int i = 0; i < numRelevantColor; i++)
                    {
                        cards.add(new TrainCarCard(relevantColor));
                        clientModel.getMainPlayer().getPlayerHandTrains().discardCard(relevantColor);
                    }
                    for(int i = 0; i < routes.get(0).getLength() - numRelevantColor; i++)
                    {
                        cards.add(new TrainCarCard("locomotive"));
                        clientModel.getMainPlayer().getPlayerHandTrains().discardCard("locomotive");
                    }
                    ClaimRouteService service = new ClaimRouteService();
                    service.claimRoute(clientModel.getMainPlayer().getName(), routeIndex, cards);
                }
                //or if they just don't have enough at all
                else
                {
                    view.showToast("You don't have the resources to claim this route");
                    setState(MyTurnState.getInstance());
                }

                break;
            case 2:
                //if there is more than one element, than it is a double route where both options are unclaimed
                //TODO: display some kind of message asking the person which route to take
                break;
            default:break;
        }

        setState(MyTurnState.getInstance());
        //System.out.println("You claimed the routes from " + routes.get(0).getCityOne() + " to " + routes.get(0).getCityTwo());

    }

    public String currentPlayerTurn() {
        return clientModel.getActiveGame().getCurrentPlayersTurn();
    }

    public String currentTurn()
    {
        String turn = clientModel.getActiveGame().getCurrentPlayersTurn() + "'s Turn";
        return turn;
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
            else if (drawingRoute == true)
            {
                //drawRoutesNormal();
                //addNumDestCardsAdded();
            }

        }

        if(o.getClass() == Route.class)
        {
            view.drawRoutetoScreen((Route)o);
        }

        if(o.getClass() == Game.class)
        {
            view.changeTurnName(clientModel.getActiveGame().getCurrentPlayersTurn() + "'s Turn");
            if(currentPlayerTurn().equals(clientModel.getMainPlayer().getName()))
            {
                System.out.println("It is my turn!");
                setState(MyTurnState.getInstance());
            }
            else
            {
                System.out.println("It is not my turn!");
                setState(NotMyTurnState.getInstance());
            }
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

    public void setDrawTrainCardsEnabled(boolean enabled)
    {
        view.setDrawTrainCardsEnabled(enabled);
    }

    public void setDrawDestCardsEnabled(boolean enabled)
    {
        view.setDrawDestCardsEnabled(enabled);
    }

    public void setClaimRouteEnabled(boolean enabled)
    {
        view.setClaimRouteEnabled(enabled);
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
    }

    @Override
    public void setPlayerCards() {
        final int CARDS_TO_ADD = 4;
        for(int i = 0; i < clientModel.getActiveGame().getCurrentPlayers(); i++) {
            if(clientModel.getMainPlayer() != clientModel.getActiveGame().getPlayers().get(i)) {
                for(int j = 0; j < CARDS_TO_ADD; j++) {
                    TrainCarCard trainCarCard = new TrainCarCard("locomotive");
                    clientModel.getActiveGame().getPlayers().get(i).addTrainCardToHand(trainCarCard);
                }

            }
        }
    }



    private int numDestCardsAdded = 0;

    private void addNumDestCardsAdded()
    {
        numDestCardsAdded++;
    }

    private boolean isNumDestCardsMoreThanThree()
    {
        if(numDestCardsAdded >= 3)
        {
            numDestCardsAdded = 0;
            return true;
        }
        else
        {
            return false;
        }
    }


    private boolean drawingRoute = false;

    public void drawRoute()
    {



    }




}
