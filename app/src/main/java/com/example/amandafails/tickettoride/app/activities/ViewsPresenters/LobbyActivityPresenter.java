package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import java.util.Observable;
import java.util.Observer;

import ThomasStuff.ClientModel;
import ThomasStuff.Game;
import ThomasStuff.Player;
import ThomasStuff.User;

public class LobbyActivityPresenter implements ILobbyPresenter, Observer {

    private ILobbyView view;
    private ClientModel clientModel = ClientModel.getInstance();

    public LobbyActivityPresenter(ILobbyView view) {
        this.view = view;
        this.clientModel.addObserver(this);

    }

    @Override
    public void startGame() {
        // disable the start game button
        //view.setStartEnabled(false);
        Game game = new Game();
        game.setMaxPlayers(3);
        game.setCreator("creator");
        game.setCurrentPlayers(0);
        clientModel.setGame(game);

        Player player = new Player();
        player.setName("player1");
        player.setAuthToken("auth1");
        player.setColor("red");
        clientModel.addPlayerToGame(game, player);

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
        if(arg.getClass() == Game.class) {
            if(clientModel.getGame().getCurrentPlayers() != 0) {
                view.displayPlayer(clientModel.getGame().getPlayers().get(clientModel.getGame().getCurrentPlayers() - 1));
            }

        }
        else {
            view.displayErrorMessage(arg.toString());
            //clientModel.popMessage(arg.toString());
        }
        // BEFORE SWITCHING ACTIVITIES, DELETE OBSERVER!!!
    }


    // create a method called "Update" implemented from observer to see when new players join
    // call displayPlayer(Player player) in view when this occurs


}
