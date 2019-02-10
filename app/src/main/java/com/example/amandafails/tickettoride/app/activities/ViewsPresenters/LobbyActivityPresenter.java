package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import java.util.Observable;
import java.util.Observer;

import ThomasStuff.Player;
import ThomasStuff.User;

public class LobbyActivityPresenter implements ILobbyPresenter, Observer {

    private ILobbyView view;

    public LobbyActivityPresenter(ILobbyView view) {
        this.view = view;
    }

    @Override
    public void startGame() {
        // disable the start game button
        view.setStartEnabled(false);

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

    @Override
    public void update(Observable o, Object arg) {
        // if new player object is created, display that this player has joined game
        if(arg.getClass() == Player.class) {
            view.displayPlayer((Player)arg);
        }
        else {
            view.displayErrorMessage(arg.toString());
            //clientModel.popMessage(arg.toString());
        }
    }


    // create a method called "Update" implemented from observer to see when new players join
    // call displayPlayer(Player player) in view when this occurs


}
