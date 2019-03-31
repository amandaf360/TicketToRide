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
    String winner;
    String longestRouteWinner;

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
    public List<String> calculateFinalPoints() {
        List<String> points = new ArrayList<>();
        int greatestRoutes = 0;
        int indexWinnerLongestRoute = 0;

        for (int i = 0; i < getPlayersInGame(); i++) {
            int pointsVal = 0;

            // grab route points
            pointsVal += clientModel.getActiveGame().getPlayers().get(i).getPoints();

            // grab dest card points won
            pointsVal += clientModel.getActiveGame().getPlayers().get(i).getDestCardPoints();

            // grab dest card points lost
            pointsVal -= clientModel.getActiveGame().getPlayers().get(i).getNegativeDestCardPoints();

            // see if they won the longest route card
            if(clientModel.getActiveGame().getPlayers().get(i).getNumRoutes() > greatestRoutes) {
                greatestRoutes = clientModel.getActiveGame().getPlayers().get(i).getNumRoutes();
                indexWinnerLongestRoute = i;
            }

            // add to list
            points.add(Integer.toString(pointsVal));
        }

        // set the longest route winner's name
        setLongestRouteWinnerName(clientModel.getActiveGame().getPlayers().get(indexWinnerLongestRoute).getName());

        // see who has the most points
        int greatestPoints = 0;
        int indexWinner = 0;
        for(int i = 0; i < getPlayersInGame(); i++) {
            if(Integer.parseInt(points.get(i)) > greatestPoints) {
                greatestPoints = Integer.parseInt(points.get(i));
                indexWinner = i;
            }
        }

        // set the winner's name
        setWinnerName(clientModel.getActiveGame().getPlayers().get(indexWinner).getName());
        return points;
    }

    private void setWinnerName(String s) {
        winner = s;
    }

    @Override
    public String getWinnerName() {
        return winner;
    }

    private void setLongestRouteWinnerName(String s) {
        longestRouteWinner = s;
    }

    @Override
    public String getLongestRouteWinnerName() {
        return longestRouteWinner;
    }

    @Override
    public List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < getPlayersInGame(); i++) {
            names.add(clientModel.getActiveGame().getPlayers().get(i).getName());
        }
        return names;
    }

    @Override
    public List<String> getPlayerPoints() {
        List<String> points = new ArrayList<>();
        for (int i = 0; i < getPlayersInGame(); i++) {
            points.add(Integer.toString(clientModel.getActiveGame().getPlayers().get(i).getPoints()));
        }
        return points;
    }

    @Override
    public List<String> getPlayerDestPoints() {
        List<String> destPoints = new ArrayList<>();
        for (int i = 0; i < getPlayersInGame(); i++) {
            destPoints.add(Integer.toString(clientModel.getActiveGame().getPlayers().get(i).getDestCardPoints()));
        }
        return destPoints;
    }

    @Override
    public List<String> getPlayerDestPointsLost() {
        List<String> destPointsLost = new ArrayList<>();
        for (int i = 0; i < getPlayersInGame(); i++) {
            destPointsLost.add(Integer.toString(clientModel.getActiveGame().getPlayers().get(i).getNegativeDestCardPoints()));
        }
        return destPointsLost;
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

    public int getPlayersInGame() {
        return clientModel.getActiveGame().getCurrentPlayers();
    }
}
