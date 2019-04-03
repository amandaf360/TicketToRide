package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GamesRoom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.Game;
import services.CreateGameService;
import services.JoinGameService;
import services.Poller;

public class GamesRoomPresenter implements IGamesRoomPresenter, Observer
{

    private IGamesRoomView view;
    private ClientModel clientModel;
    private static boolean joinGame;
    private GamesRoomPresenter gamesRoomPresenter = this;
    private Poller poller;


    public GamesRoomPresenter(IGamesRoomView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        clientModel.addObserver(this);
        // for error checking purposes only (adding games to game room)
        //clientModel.errorChecking();
        this.poller = new Poller(clientModel.getUser().getUserName());
        poller.poll();
    }

    public List<Game> getGameListFromModel()
    {
        return clientModel.getGameList();
    }

    public int getGameNumber(Game game)
    {
        return clientModel.getGameNum(game);
    }

    // MAYBE PUT THE DIALOG IN THE VIEW??
    public void joinGame(final Game game, Context context)
    {
        String joinGameName = "Are you sure you want to join " + game.getName() + "?";
        new AlertDialog.Builder(context)
                .setTitle("Join Game?")
                .setMessage(joinGameName)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinGameYes(game);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinGameNo();
                    }
                })
                .show();
    }

    private void joinGameYes(Game game)
    {
        JoinGameService joinGameService = new JoinGameService();
        joinGameService.joinGame(clientModel.getGameNum(game), clientModel.getUser().getUserName());

        /*
        // *************** TEST FUNCTIONALITY ***************** //
        clientModel.setActiveGame(game);

        Player player = new Player();
        player.setName("player1");
        player.setAuthToken("auth1");
        player.setColor("red");
        clientModel.addPlayerToCurrentGame(player);

        Player player2 = new Player();
        player2.setName("player2");
        player2.setAuthToken("auth2");
        player2.setColor("green");
        clientModel.addPlayerToCurrentGame(player2);

        // *************** END OF TEST FUNCTIONALITY ********** //
        */
        joinGame = true;
    }

    private static void joinGameNo()
    {
        joinGame = true;
    }

    private static boolean createGame;

    private static int value;

    private static void setChoice(int arg)
    {
        value = arg;
    }

    private static void createGameYes(int which)
    {

        value = which;
        createGame = true;
    }

    private void doit()
    {

        if(createGame)
        {
            CreateGameService createGameService = new CreateGameService();
            createGameService.createGame(clientModel.getUser().getUserName(), value + 2, makeGameName()); // FIX HERE FOR GAME CREATION (value + 2 instead of value + 1)
            //joinGameService.joinGame(clientModel.getGameNum(game));
            createGameNo();
        }
        setChoice(0);
    }

    private static void createGameNo()
    {
        createGame = true;
    }


    //private static CharSequence selected;

    public boolean createGame(Context context)
    {
        setChoice(0);
        //final String joinGameName = "pick the amount of players you want in your game";
        String[] singleChoiceItems = {"2","3","4","5"};                     // FIX HERE FOR GAME CREATION (2,3,4,5 instead of 1,2,3,4)
        final int itemSelected = 0;
        new AlertDialog.Builder(context)
                .setTitle("How many players do you want in your game?")
                //.setMessage(joinGameName)
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        setChoice(selectedIndex);
                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createGameYes(value);
                        doit();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createGameNo();
                    }
                })
                .show();

        createGameNo();


        return true;
    }

    @Override
    public void update(Observable observable, Object o)
    {
        // if game list is updated, (either game added/deleted or person added to a game)
        // then update the game list on screen
        if(o.getClass() == ArrayList.class) {
            view.updateView();
        }
        else if(o.getClass() == Game.class) {
            // delete the observer
            this.clientModel.deleteObserver(this);
            // switch activity
            view.switchActivity();
        }
        // if current game is updated, change activities to go to game lobby
        else if(o.getClass() == String.class) {
            // display the most recent error message
            view.displayErrorMessage(clientModel.getMessage());
        }
    }

    private String makeGameName()
    {
        String userName = clientModel.getUser().getUserName();
        List<Game> gameList = clientModel.getGameList();
        int numGames = 1;
        for(Game game : gameList)
        {
            if(game.getCreator().equals(userName))
            {
                numGames++;
            }
        }

        String gameName = userName + "'s " + ordinal(numGames) + " game";


        return gameName;
    }


    public static String ordinal(int i) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }
}