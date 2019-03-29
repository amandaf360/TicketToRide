package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.GameOver;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;

public class GameOverPresenter implements Observer, IGameOverPresenter {

    GameOverView view;
    ClientModel clientModel;

    final int MAX_NUM_PLAYERS = 5;

    GameOverPresenter(GameOverView view)
    {
        this.view = view;
        clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        clientModel.initializeRoutes();
    }


    @Override
    public void setDisplay() {
        view.updateGameOverDisplay();
    }

    @Override
    public void calculateFinalPoints() {

    }

    @Override
    public String getWinnerName() {
        return null;
    }

    @Override
    public String getLongestRouteWinnerName() {
        return null;
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
    public List<String> getPlayerDestPoints() {
        // TODO: grab this from model
        return null;
    }

    @Override
    public List<String> getPlayerDestPointsLost() {
        // TODO: grab this from model
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        // if something is updated in the model, reset the display
        // shouldn't really ever be called
        setDisplay();
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
