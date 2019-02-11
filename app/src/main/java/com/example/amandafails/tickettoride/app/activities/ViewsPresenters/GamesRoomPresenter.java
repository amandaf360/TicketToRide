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
import services.Poller;

public class GamesRoomPresenter implements IGamesRoomPresenter, Observer
{

    private IGamesRoomView view;
    private ClientModel clientModel;
    private static boolean joinGame;
    private GamesRoomPresenter gamesRoomPresenter = this;
    private Poller poller;


    public GamesRoomPresenter(IGamesRoomView view) {
        this.view = view;
        this.poller = new Poller();
        poller.poll();
        clientModel = ClientModel.getInstance();
        clientModel.addObserver(this);
        clientModel.errorChecking();
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