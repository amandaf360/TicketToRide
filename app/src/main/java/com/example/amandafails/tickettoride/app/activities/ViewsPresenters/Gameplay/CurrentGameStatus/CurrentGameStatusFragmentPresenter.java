package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.CurrentGameStatus;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;

public class CurrentGameStatusFragmentPresenter implements ICurrentGameStatusFragmentPresenter, Observer {

    private final int MAX_NUM_PLAYERS = 5;
    private ClientModel clientModel = ClientModel.getInstance();
    private ICurrentGameStatusFragmentView view;

    public CurrentGameStatusFragmentPresenter(ICurrentGameStatusFragmentView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.populateEverything();
    }

    @Override
    public void exit() {
        deleteObserver();
        view.popFragment();
    }

    @Override
    public void deleteObserver() {
        clientModel.deleteObserver(this);
    }

    @Override
    public List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            String name;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                name = clientModel.getActiveGame().getPlayers().get(i).getName();
            }
            else {
                name = null;
            }

            names.add(name);
        }
        return names;
    }

    @Override
    public List<String> getPlayerPoints() {
        List<String> points = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            int pointsVal;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                pointsVal = clientModel.getActiveGame().getPlayers().get(i).getPoints();
            }
            else {
                pointsVal = -1;
            }
            points.add(Integer.toString(pointsVal));
        }
        return points;
    }

    @Override
    public List<String> getPlayerTrains() {
        List<String> trains = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            int numTrains;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                numTrains = clientModel.getActiveGame().getPlayers().get(i).getNumTrains();
            }
            else {
                numTrains = -1;
            }
            trains.add(Integer.toString(numTrains));
        }
        return trains;
    }

    @Override
    public List<String> getPlayerRoutes() {
        List<String> routes = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            int numRoutes;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                numRoutes = clientModel.getActiveGame().getPlayers().get(i).getNumRoutes();
            }
            else {
                numRoutes = -1;
            }
            routes.add(Integer.toString(numRoutes));
        }

        return routes;
    }

    @Override
    public List<String> getPlayerCards() {
        List<String> cards = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            int numCards;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                numCards = clientModel.getActiveGame().getPlayers().get(i).getNumCards();
            }
            else {
                numCards = -1;
            }
            cards.add(Integer.toString(numCards));
        }
        return cards;
    }

    @Override
    public List<String> getPlayerDests() {
        List<String> destCards = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_PLAYERS; i++) {
            int numDestinations;
            if(clientModel.getActiveGame().getPlayers().size() > i) {
                numDestinations = clientModel.getActiveGame().getPlayers().get(i).getNumDestCards();
            }
            else {
                numDestinations = -1;
            }
            destCards.add(Integer.toString(numDestinations));
        }
        return destCards;
    }

    @Override
    public List<String> getPlayerNumEachCard() {
        List<String> numCards = new ArrayList<>();

        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumRed()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumBlue()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumGreen()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumYellow()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumWhite()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumBlack()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumLocomotives()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumOrange()));
        numCards.add(Integer.toString(clientModel.getMainPlayer().getPlayerHandTrains().getNumPurple()));

        return numCards;
    }

    @Override
    public void setPlayerNameColors(List<TextView> names) {
        for(int i = 0; i < clientModel.getActiveGame().getCurrentPlayers(); i++) {
            names.get(i).setTextColor(Color.parseColor(stringToHex(clientModel.getActiveGame().getPlayers().get(i).getColor())));
        }
    }

    private String stringToHex(String color) {
        String hexColor = "";
        switch (color) {
            case "blue":
                hexColor = "#039BE5";
                break;
            case "red":
                hexColor = "#F44336";
                break;
            case "green":
                hexColor = "#8BC34A";
                break;
            case "yellow":
                hexColor = "#FFEB3B";
                break;
            case "black":
                hexColor = "#000000";
                break;
        }
        return hexColor;
    }
}
