package responses;

import java.util.ArrayList;

import ClientModel.DecksStateData;
import ClientModel.DestPointsInfo;
import ClientModel.Game;
import ClientModel.GameStartInfo;
import ClientModel.Message;

public class PollResponse
{
    private String username;
    private ArrayList<Game> gamesCreated;
    private ArrayList<String> gamesDeleted;
    private ArrayList<String> playersJoined;
    private ArrayList<String> playersLeft;
    private ArrayList<String> gameStarted;
    private ArrayList<Message> chatHistory;
    private ArrayList<String> discardedDestCards;
    private ArrayList<String> destinationCardsDrawn;
    private GameStartInfo gameStartInfo;
    private DecksStateData deckData;
    private ArrayList<String> routesClaimed;
    private ArrayList<String> trainCardsDrawn;
    private ArrayList<Message> gameHistory;
    private int turnsEnded;
    private ArrayList<String> trainsUsed;
    private ArrayList<String> pointsGained;
    private boolean gameOver;
    private boolean lastTurn;
    private ArrayList<DestPointsInfo> destPointsInfo;

    public PollResponse() {
    }

    public ArrayList<String> getDestinationCardsDrawn() {
        return destinationCardsDrawn;
    }

    public void setDestinationCardsDrawn(ArrayList<String> destinationCardsDrawn) {
        this.destinationCardsDrawn = destinationCardsDrawn;
    }

    public ArrayList<Game> getGamesCreated() {
        return gamesCreated;
    }

    public void setGamesCreated(ArrayList<Game> gamesCreated) {
        this.gamesCreated = gamesCreated;
    }

    public ArrayList<String> getGamesDeleted() {
        return gamesDeleted;
    }

    public void setGamesDeleted(ArrayList<String> gamesDeleted) {
        this.gamesDeleted = gamesDeleted;
    }

    public ArrayList<String> getPlayersJoined() {
        return playersJoined;
    }

    public void setPlayersJoined(ArrayList<String> playersJoined) {
        this.playersJoined = playersJoined;
    }

    public ArrayList<String> getPlayersLeft() {
        return playersLeft;
    }

    public void setPlayersLeft(ArrayList<String> playerLeft) {
        this.playersLeft = playerLeft;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(ArrayList<String> gameStarted) {
        this.gameStarted = gameStarted;
    }

    public ArrayList<Message> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ArrayList<String> getDiscardedDestCards() {
        return discardedDestCards;
    }

    public void setDiscardedDestCards(ArrayList<String> discardedDestCards) {
        this.discardedDestCards = discardedDestCards;
    }

    public GameStartInfo getGameStartInfo() {
        return gameStartInfo;
    }

    public void setGameStartInfo(GameStartInfo gameStartInfo) {
        this.gameStartInfo = gameStartInfo;
    }

    public DecksStateData getDeckData() {
        return deckData;
    }

    public void setDeckData(DecksStateData deckData) {
        this.deckData = deckData;
    }

    public ArrayList<String> getRoutesClaimed()
    {
        return routesClaimed;
    }

    public void setRoutesClaimed(ArrayList<String> routesClaimed)
    {
        this.routesClaimed = routesClaimed;
    }

    public ArrayList<String> getTrainCardsDrawn() {
        return trainCardsDrawn;
    }

    public void setTrainCardsDrawn(ArrayList<String> trainCardsDrawn) {
        this.trainCardsDrawn = trainCardsDrawn;
    }

    public ArrayList<Message> getGameHistory() {
        return gameHistory;
    }

    public void setGameHistory(ArrayList<Message> gameHistory) {
        this.gameHistory = gameHistory;
    }

    public int getTurnsEnded() {
        return turnsEnded;
    }

    public void setTurnsEnded(int turnsEnded) {
        this.turnsEnded = turnsEnded;
    }

    public ArrayList<String> getTrainsUsed() {
        return trainsUsed;
    }

    public void setTrainsUsed(ArrayList<String> trainsUsed) {
        this.trainsUsed = trainsUsed;
    }

    public ArrayList<String> getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(ArrayList<String> pointsGained) {
        this.pointsGained = pointsGained;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isLastTurn() {
        return lastTurn;
    }

    public void setLastTurn(boolean lastTurn) {
        this.lastTurn = lastTurn;
    }

    public ArrayList<DestPointsInfo> getDestPointsInfo() {
        return destPointsInfo;
    }

    public void setDestPointsInfo(ArrayList<DestPointsInfo> destPointsInfo) {
        this.destPointsInfo = destPointsInfo;
    }
}
