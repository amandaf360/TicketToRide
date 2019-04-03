package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.GameplayState;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.MyTurnState;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.State.NotMyTurnState;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import services.ClaimRouteService;
import services.CreateHistoryMessageService;
import services.DiscardDestCardService;
import services.DrawDestCardService;
import ClientModel.PlayerHandDestinations;
import ClientModel.TrainCarCard;
import ClientModel.Route;
import ClientModel.Game;
import ClientModel.AsyncDemo;
import services.EndTurnService;
import ClientModel.PlayerHandTrains;

public class GameplayPresenter implements IGameplayPresenter, Observer
{
    GameplayView view;
    ClientModel clientModel;
    GameplayState currentState;
    boolean lastTurn;

    public GameplayPresenter(GameplayView view) {
        this.view = view;
        clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        clientModel.initializeRoutes();
        if(currentPlayerTurn().equals(clientModel.getMainPlayer().getName())) {
            System.out.println("It is my turn!");
            setState(MyTurnState.getInstance());
        }
        else {
            System.out.println("It is not my turn!");
            setState(NotMyTurnState.getInstance());
        }
        lastTurn = false;
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


    public void createDoubleRouteDialog(String colorOne, String colorTwo, List<Route> routes)
    {
        final String dialogTitle = "Which route do you want to claim?";

        //final String[] singleChoiceItems = {(colorOne.substring(0,1).toUpperCase() + colorOne.substring(1)), (colorTwo.substring(0,1).toUpperCase() + colorTwo.substring(1))};
        final String[] singleChoiceItems = {colorOne, colorTwo};
        final int selection = 0;
        final List<Route> finalRoutes = new ArrayList<Route>();
        for(Route route : routes)
        {
            finalRoutes.add(route);
        }
        setDoubleSelection(0);
        new AlertDialog.Builder(view)   //AlertDialog.Builder(view. R.whatever.dialog)
                .setTitle(dialogTitle)
                .setCancelable(false)
                .setSingleChoiceItems(singleChoiceItems, selection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setDoubleSelection(selectedIndex);
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        claimRouteHelper(finalRoutes.get(doubleSelection));
                        System.out.println("This is doubleSelection: " + doubleSelection + "\n");
                        System.out.println("This is the route'sColor: " + finalRoutes.get(doubleSelection).getColor() + "\n");
                        System.out.println("This is the routes index in the model: " +
                                            clientModel.getIndexOfMatchingUnclaimedRoute(finalRoutes.get(doubleSelection)) +
                                            "\n");
                    }
                })

                .show();

        return;
    }

    private void  findGrayColor(Route route)
    {
        List<String> colors = new ArrayList<>();
        colors.add("black");
        colors.add("blue");
        colors.add("green");
        colors.add("locomotive");
        colors.add("orange");
        colors.add("purple");
        colors.add("red");
        colors.add("yellow");
        colors.add("white");

        showColorSelectionDialog(colors, route);

    }

    private void showColorSelectionDialog(List<String> colors, Route route)
    {
        final String dialogTitle = "Which color do you want to use?";
        final Route route1 = route;
//
//        for(int i = 0; i < colors.size(); i++) {
//            colors.set(i, (colors.get(i).substring(0,1).toUpperCase() + colors.get(i).substring(1)));
//        }

        setGrayRouteColor("black");
        String[] singleChoiceItems = new String[colors.size()];
        singleChoiceItems = colors.toArray(singleChoiceItems);
        final String[] finalSingleChoiceItems = singleChoiceItems;

        setDoubleSelection(0);
        new AlertDialog.Builder(view)   //AlertDialog.Builder(view. R.whatever.dialog)
                .setTitle(dialogTitle)
                .setCancelable(false)
                .setSingleChoiceItems(finalSingleChoiceItems, doubleSelection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setGrayRouteColor(finalSingleChoiceItems[selectedIndex]);
                        System.out.println("Selected this color: " + grayRouteColor + " for now...\n\n");
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doItClaimRoute(route1);
                        System.out.println("This is the colorChosen: " + grayRouteColor + "\n\n");
                    }
                })
                .show();

        return;
    }

    private void doItClaimRoute(Route route)
    {
        int numRelevantColor = 0;
        switch(grayRouteColor)
        {
            case "blue":
                numRelevantColor = ((clientModel.getMainPlayer().getPlayerHandTrains().getNumBlue()));
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

            default:
                break;
        }

        claimRouteHelpersHelper(route, numRelevantColor, grayRouteColor);
    }

    private String grayRouteColor = "locomotive";


    private void setGrayRouteColor(String selection)
    {
        grayRouteColor = selection;
    }


    private int doubleSelection = 0;

    private void setDoubleSelection(int i)
    {
        doubleSelection = i;
    }


    public void claimRouteByTap(ArrayList<Route> routes)
    {
        int numRoutes = routes.size();
        boolean routeClaimed = false;

        switch(numRoutes)
        {
            case 0:
                //if the array of routes has nothing in it, there are no valid selections, meaning the route is taken. Display a message and
                //resume their turn
                view.showToast("Route is taken OR not enough trains");
                setState(MyTurnState.getInstance());
                routeClaimed = false;
                break;
            case 1:
                //if there is one element, then either it is a double route with only 1 option remaining, or else it is an unclaimed single
                claimRouteHelper(routes.get(0));
                break;
            case 2:
                //if there is more than one element, than it is a double route where both options are unclaimed
                String colorOne = routes.get(0).getColor();
                String colorTwo = routes.get(1).getColor();
                if(colorOne.equals(colorTwo))
                {
                    claimRouteHelper(routes.get(0));
                    break;
                }

                createDoubleRouteDialog(colorOne, colorTwo, routes);
                break;
            default:break;
        }
        view.setRoutesClaimable(false);
    }

    private void claimRouteHelper(Route route)
    {
        int routeIndex = ClientModel.getInstance().getIndexOfMatchingUnclaimedRoute(route);
        int numRelevantColor  = 0;
        String relevantColor = route.getColor();
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
                //THOMAS set RelevantColor to whatever they chose;
                //THOMAS set numRelevantColor to howMany they have of that.

                findGrayColor(route);
                //TODO: Display window asking which one they want to use, collect answer, change relevant color to chosen color
                return;
            default: break;
        }

        claimRouteHelpersHelper(route, numRelevantColor, relevantColor);
        //checks if they have enough of the color itself
    }

    private void claimRouteHelpersHelper(Route route, int numRelevantColor, String relevantColor)
    {


        int routeIndex = clientModel.getIndexOfMatchingUnclaimedRoute(route);
        if(routeIndex == -1)
        {
            System.out.println("THIS ROUTE IS BROKEN IN THE MODEL. FIX IT!\nHERE IS THE ROUTE DATA: " + route.toString() + "\n");
            return;
        }
        if (numRelevantColor >= route.getLength())
        {
            ArrayList<TrainCarCard> cards = new ArrayList<>();
            for(int i = 0; i < route.getLength(); i++)
            {
                cards.add(new TrainCarCard(relevantColor));
                clientModel.getMainPlayer().getPlayerHandTrains().discardCard(relevantColor);
            }
            ClaimRouteService service = new ClaimRouteService();
            service.claimRoute(clientModel.getMainPlayer().getName(), routeIndex, cards, clientModel.getMainPlayer().getAuthToken());

            CreateHistoryMessageService historyService = new CreateHistoryMessageService();
            historyService.sendMessage("Claimed the route from " + route.getCityOne() + " to " + route.getCityTwo()
                    + " using " + route.getLength() + " " + relevantColor + "s");

            EndTurnService endService = new EndTurnService();
            endService.endTurn();
            return;
        }
        //or if they have enough with locomotives
        else if(clientModel.getMainPlayer().getPlayerHandTrains().getNumLocomotives() >= route.getLength() - numRelevantColor)
        {
            ArrayList<TrainCarCard> cards = new ArrayList<>();
            for(int i = 0; i < numRelevantColor; i++)
            {
                cards.add(new TrainCarCard(relevantColor));
                clientModel.getMainPlayer().getPlayerHandTrains().discardCard(relevantColor);
            }
            for(int i = 0; i < route.getLength() - numRelevantColor; i++)
            {
                cards.add(new TrainCarCard("locomotive"));
                clientModel.getMainPlayer().getPlayerHandTrains().discardCard("locomotive");
            }
            ClaimRouteService service = new ClaimRouteService();
            service.claimRoute(clientModel.getMainPlayer().getName(), routeIndex, cards, clientModel.getMainPlayer().getAuthToken());

            CreateHistoryMessageService historyService = new CreateHistoryMessageService();
            historyService.sendMessage("Claimed the route from " + route.getCityOne() + " to " + route.getCityTwo()
                    + " using " + numRelevantColor + " " + relevantColor + "s" + " and " + (route.getLength() - numRelevantColor) + " locomotives");

            EndTurnService endService = new EndTurnService();
            endService.endTurn();
            return;
        }
        //or if they just don't have enough at all
        else
        {
            view.showToast("You don't have the resources to claim this route");
            setState(MyTurnState.getInstance());
            return;
        }
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

        if(o.getClass() == Boolean.class) {
            if(clientModel.getActiveGame().isGameOver()) {
                view.switchActivities();
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


    private void showDialog(final String[] destCards)
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
        return;
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
