package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.amandafails.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ThomasStuff.ClientModel;
import ThomasStuff.Game;
import services.CreateGameService;
import services.JoinGameService;

public class GamesRoomPresenter implements ILobbyPresenter, Observer
{

    private IGamesRoomView view;
    private ClientModel clientModel;
    private static boolean joinGame;
    private GamesRoomPresenter gamesRoomPresenter = this;


    public GamesRoomPresenter(IGamesRoomView view) {
        this.view = view;
        clientModel = ClientModel.getInstance();
        clientModel.addObserver(this);
    }

    @Override
    public void startGame() {
        // disable the start game button
        //view.setStartEnabled(false);

        // will call the "start game service" once it's created
        // similar to this below...
        // will want to return a result from start game service??
        /*
        // call login service connected to model??
        LoginService loginService = new LoginService();
        // will get both username and password and attempt to login
        loginService.login(view.getLoginUsername(), view.getLoginPassword());
        */

        // once it is ready to start, change the activity
        /* TO START A NEW ACTIVITY!!
        Intent i = new Intent(context, EventActivity.class);
                    i.putExtra("event", eventID);
                    context.startActivity(i);
         */
    }

    public List<Game> getGameListFromModel()
    {
        return clientModel.getGameList();
    }

    public int getGameNumber(Game game)
    {
        return clientModel.getGameNum(game);
    }

    public boolean joinGame(Game game, Context context)
    {
        String joinGameName = "Are you sure you want to join " + game.getName() + "?";
        new AlertDialog.Builder(context)
                .setTitle("Join Game?")
                .setMessage(joinGameName)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinGameYes();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        joinGameNo();
                    }
                })
                .show();

        if(joinGame == true)
        {
            JoinGameService joinGameService = new JoinGameService();
            joinGameService.joinGame(clientModel.getGameNum(game));

            return true;
        }

        else
        {
            return false;
        }
    }

    private static void joinGameYes()
    {
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
            createGameService.createGame(clientModel.getUser().getUserName(), value + 2, makeGameName());
            //joinGameService.joinGame(clientModel.getGameNum(game));
            createGameNo();
        }
    }

    private static void createGameNo()
    {
        createGame = true;
    }


    private static CharSequence selected;

    public boolean createGame(Context context)
    {


        final String joinGameName = "pick the amount of players you want in your game";
        String[] singleChoiceItems = {"2","3","4","5"};
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
        view.update();
    }
                  // create a method called "Update" implemented from observer to see when new players join
    // call displayPlayer(Player player) in view when this occurs



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

    public void stopObserving()
    {
        clientModel.deleteObserver(this);
    }


}