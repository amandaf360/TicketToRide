package servermodel;

import java.util.ArrayList;

public class ActiveGame
{
    private DestCardDeck destinationDeck;
    private TrainCarDeck trainDeck;
    private ArrayList<Player> players;
    private ArrayList<Message> chatHistory;
    private ArrayList<Message> gameHistory;
    private int gameNum;
    private FaceUpCards faceUpCards;

    public ActiveGame()
    {
        destinationDeck = new DestCardDeck();
        trainDeck = new TrainCarDeck();
        players = new ArrayList<>();
        chatHistory = new ArrayList<>();
        gameHistory = new ArrayList<>();
        faceUpCards = new FaceUpCards();
        for(int i = 0; i < 5; i ++)
        {

        }
    }

    /*
        draw dest card, discard dest card
        drawfaceup, draw from deck, discard traincards,
        chat send message,

     */

    public DestCardDeck getDestinationDeck() {
        return destinationDeck;
    }

    public void setDestinationDeck(DestCardDeck destinationDeck) {
        this.destinationDeck = destinationDeck;
    }

    public TrainCarDeck getTrainDeck() {
        return trainDeck;
    }

    public void setTrainDeck(TrainCarDeck trainDeck) {
        this.trainDeck = trainDeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Message> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ArrayList<Message> getGameHistory() {
        return gameHistory;
    }

    public void setGameHistory(ArrayList<Message> gameHistory) {
        this.gameHistory = gameHistory;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public FaceUpCards getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(FaceUpCards faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public boolean containsUser(String username)
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getName().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    public Player getPlayerByUsername(String username)
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getName().equals(username))
            {
                return players.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> getAllUsernames()
    {
        ArrayList<String> usernameList = new ArrayList<>();
        for(int i = 0; i < players.size(); i++)
        {
            usernameList.add(players.get(i).getName());
        }
        return usernameList;
    }
}
