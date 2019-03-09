package ClientModel;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    private int gameNum;
    private int currentPlayers;
    private String creator;
    private int indexOfCurrentPlayer;
    private List<TrainCarCard> faceUpCards;
    private int numCardsInDeck;

    public Game()
    {
        players = new ArrayList<Player>();
        indexOfCurrentPlayer = 0;
        faceUpCards = new ArrayList<>();
        numCardsInDeck = 0;
        TrainCarCard card1 = new TrainCarCard("blue");
        faceUpCards.add(card1);
        TrainCarCard card2 = new TrainCarCard("also blue");
        faceUpCards.add(card2);
        TrainCarCard card3 = new TrainCarCard("slightly less blue");
        faceUpCards.add(card3);
        TrainCarCard card4 = new TrainCarCard("teal..? nah that's blue");
        faceUpCards.add(card4);
        TrainCarCard card5 = new TrainCarCard("chartreuse");
        faceUpCards.add(card5);
    }


    public String getPlayerColor(Player player)
    {
        return player.getColor();
    }

    public String getPlayerAuthToken(Player player)
    {
        return player.getAuthToken();
    }

    public int getCurrentPlayers()
    {
        currentPlayers = players.size();
        return currentPlayers;
    }

    public String getName()
    {
        return name;
    }

    public String getCreator()
    {
        if(creator == null || creator == "")
        {
            return "Games have creators now";
        }
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers)
    {
        this.maxPlayers = maxPlayers;
    }

    public void setCurrentPlayers(int currentPlayers)
    {
        this.currentPlayers = currentPlayers;
    }

    public void addPlayer(Player player) /// THIS SHOULD NEVER BE CALLED
    {
        players.add(player);
        int i = getCurrentPlayers();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public int getGameNum() {
        return gameNum;
    }

    public int getIndexOfCurrentPlayer()
    {
        return indexOfCurrentPlayer;
    }

    public void updateTurnIndex()
    {
        indexOfCurrentPlayer += 1;
        if(indexOfCurrentPlayer >= players.size())
        {
            indexOfCurrentPlayer = 0;
        }
    }

    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    public void setGameNum(int gameNum)
    {
        this.gameNum = gameNum;
    }

    public void setIndexOfCurrentPlayer(int indexOfCurrentPlayer)
    {
        this.indexOfCurrentPlayer = indexOfCurrentPlayer;
    }

    public List<TrainCarCard> getFaceUpCards()
    {
        return faceUpCards;
    }

    public void setFaceUpCards(List<TrainCarCard> faceUpCards)
    {
        this.faceUpCards = faceUpCards;
    }

    public void changeCardByIndex(int index, TrainCarCard trainCard)
    {
        faceUpCards.set(index, trainCard);
    }

    public void setNumCardsInDeck(int numCardsInDeck) {
        this.numCardsInDeck = numCardsInDeck;
    }

    public int getNumCardsInDeck() {
        return numCardsInDeck;
    }

    public Player getPlayerByName(String name)
    {
        for(Player player : players)
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }
        return null;
    }
}
