package servermodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mapgraph.Graph;

public class ActiveGame implements Serializable
{
    private DestCardDeck destinationDeck;
    private TrainCarDeck trainDeck;
    private TrainCarDiscard trainCarDiscard;
    private ArrayList<Player> players;
    private ArrayList<Message> chatHistory;
    private ArrayList<Message> gameHistory;
    private int gameNum;
    private FaceUpCards faceUpCards;
    private ArrayList<Route> routes;
    private int currentTurnIndex = 0;
    private boolean lastTurn;
    private boolean gameOver;
    private boolean actuallyLastTurn;
    private String lastTurnPlayer;
    private Graph graph;

    public ActiveGame()
    {
        destinationDeck = new DestCardDeck();
        trainDeck = new TrainCarDeck();
        players = new ArrayList<>();
        chatHistory = new ArrayList<>();
        gameHistory = new ArrayList<>();
        faceUpCards = new FaceUpCards();
        routes = new ArrayList<>();
        trainCarDiscard = new TrainCarDiscard();
        faceUpCards.setDiscardPile(trainCarDiscard);
        trainDeck.setFaceUpCards(faceUpCards);
        trainDeck.setDiscardPile(trainCarDiscard);
        lastTurn = false;
        gameOver = false;
        actuallyLastTurn = false;
        graph = new Graph();

        initializeRoutes();
    }

    public void advanceTurn()
    {
        currentTurnIndex = (currentTurnIndex + 1) % players.size();
    }


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

    public void addMessage(Message message)
    {
        chatHistory.add(message);
    }

    public TrainCarDiscard getTrainCarDiscard() {
        return trainCarDiscard;
    }

    public void setTrainCarDiscard(TrainCarDiscard trainCarDiscard) {
        this.trainCarDiscard = trainCarDiscard;
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

    public boolean containsToken(String authToken)
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getAuthToken().equals(authToken))
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

    public ArrayList<String> getAllAuthTokens()
    {
        ArrayList<String> authTokens = new ArrayList<>();
        for(int i = 0; i < players.size(); i++)
        {
            authTokens.add(players.get(i).getAuthToken());
        }
        return authTokens;
    }

    public void claimRoute(int index, String name, List<String> cards)
    {
        if(index == -1)
        {
            return;
        }

        Player player =  getPlayerByName(name);
        routes.get(index).setClaimedBy(player);
        player.addRoute();
    }

    public Player getPlayerByName(String name)
    {
        for(Player player : players)
        {
            if(player.getName().equals(name))
            {
                return player;
            }
        }

        return null;
    }


    public boolean isLastTurn()
    {
        return lastTurn;
    }

    public void setLastTurn(boolean lastTurn)
    {
        this.lastTurn = lastTurn;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public String getLastTurnPlayer() {
        return lastTurnPlayer;
    }

    public void setLastTurnPlayer(String lastTurnPlayer) {
        this.lastTurnPlayer = lastTurnPlayer;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<DestPointsInfo> calculateRoutePoints()
    {
        ArrayList<DestPointsInfo> info = new ArrayList<>();

        for(Player player: players)
        {
            int negativePoints = 0;
            int positivePoints = 0;

            ArrayList<DestCard> destinationCards = player.getDestCards();
            for(DestCard card : destinationCards)
            {
                if(graph.completedRoute(player.getName(), card.getCityOne(), card.getCityTwo()))
                {
                    positivePoints += card.getPoints();
                }
                else
                {
                    negativePoints -= card.getPoints();
                }
            }
            info.add(new DestPointsInfo(player.getName(), positivePoints, negativePoints));
        }

        return info;
    }

    public boolean isActuallyLastTurn() {
        return actuallyLastTurn;
    }

    public void setActuallyLastTurn(boolean actuallyLastTurn) {
        this.actuallyLastTurn = actuallyLastTurn;
    }

    private void initializeRoutes()
    {
        if(routes.size() == 0)
        {
            //Route route = new Route()
            Route route = new Route("Helena", "Omaha", "red", 5);
            routes.add(route);
            route = new Route("SLC", "Denver", "red", 3);
            routes.add(route);
            route = new Route("SLC", "Denver", "yellow", 3);
            routes.add(route);
            route = new Route("Denver", "Oklahoma City", "red", 4);
            routes.add(route);
            routes.add(new Route(960, 1310, 88, 4, "red", "none", "ElPaso-Dallas"));
            routes.add(new Route(1760, 1560, 114, 6, "red", "none", "NewOrleans-Miami"));
            routes.add(new Route(1490, 600, 130, 3, "red", "none", "Duluth-Chicago"));
            routes.add(new Route(2240, 560, 40, 2, "red", "yellow", "Boston-NYC"));
            routes.add(new Route(2240, 560, 40, 2, "yellow", "yellow", "Boston-NYC"));

            //yellow routes (total 5)
            routes.add(new Route(500, 300, 114, 6, "yellow", "none", "Seattle-Helena"));
            routes.add(new Route(160, 1000, 152, 3, "purple", "yellow", "SanFrancisco-LA"));
            routes.add(new Route(160, 1000, 152, 3, "yellow", "yellow", "SanFrancisco-LA"));
            routes.add(new Route(960, 1180, 60, 5, "yellow", "none", "ElPaso-OklahomaCity"));
            routes.add(new Route(1640, 1300, 44, 4, "yellow", "orange", "NewOrleans-Atlanta"));
            routes.add(new Route(1640, 1300, 44, 4, "orange", "orange", "NewOrleans-Atlanta"));
            routes.add(new Route(1820, 900, 36, 4, "yellow", "none", "Nashville-Pittsburgh"));

            //blue routes (total 7)
            routes.add(new Route(370, 530, 137, 6, "blue", "none", "Portland-SLC"));
            routes.add(new Route(950, 250, 45, 4, "blue", "none", "Helena-Winnipeg"));
            routes.add(new Route(950, 1080, 85, 3, "blue", "none", "SantaFe-OklahomaCity"));
            routes.add(new Route(1420, 720, 88, 4, "blue", "none", "Omaha-Chicago"));
            routes.add(new Route(1380, 880, 101, 2, "blue", "purple", "KansasCity-SaintLouis"));
            routes.add(new Route(1380, 880, 101, 2, "purple", "purple", "KansasCity-SaintLouis"));
            routes.add(new Route(1900, 1410, 157, 5, "blue", "none", "Atlanta-Miami"));
            routes.add(new Route(2160, 450, 2, 3, "blue", "none", "NYC-Montreal"));

            //green routes (total 7)
            routes.add(new Route(130, 570, 7, 5, "green", "purple", "Portland-SanFrancisco"));
            routes.add(new Route(130, 570, 7, 5, "purple", "purple", "Portland-SanFrancisco"));
            routes.add(new Route(800, 630, 170, 4, "green", "none", "Helena-Denver"));
            routes.add(new Route(1020, 1400, 104, 6, "green", "none", "ElPaso-Houston"));
            routes.add(new Route(1560, 820, 35, 2, "green", "white", "SaintLouis-Chicago"));
            routes.add(new Route(1560, 820, 35, 2, "white", "white", "SaintLouis-Chicago"));
            routes.add(new Route(1710, 830, 63, 4, "green", "none", "SaintLouis-Pittsburgh"));
            routes.add(new Route(2040, 670, 67, 2, "green", "white", "Pittsburgh-NYC"));
            routes.add(new Route(2040, 670, 67, 2, "white", "white", "Pittsburgh-NYC"));
            routes.add(new Route(1450, 1280, 350, 3, "green", "none", "LittleRock-NewOrleans"));

            //orange routes (total 6)
            routes.add(new Route(1060, 440, 95, 6, "orange", "none", "Helena-Duluth"));
            routes.add(new Route(330, 790, 80, 5, "orange", "white", "SanFrancisco-SLC"));
            routes.add(new Route(330, 790, 80, 5, "white", "white", "SanFrancisco-SLC"));
            routes.add(new Route(500, 880, 31, 3, "orange", "none", "LasVegas-SLC"));
            routes.add(new Route(1050, 850, 93, 4, "orange", "black", "Denver-KansasCity"));
            routes.add(new Route(1050, 850, 93, 4, "black", "black", "Denver-KansasCity"));

            routes.add(new Route(1790, 700, 92, 3, "orange", "black", "Chicago-Pittsburgh"));
            routes.add(new Route(1790, 700, 92, 3, "black", "black", "Chicago-Pittsburgh"));

            routes.add(new Route(2150, 750, 7, 2, "orange", "black", "NY-Washington"));
            routes.add(new Route(2150, 750, 7, 2, "black", "black", "NY-Washington"));

            //purple routes (total 4)
            routes.add(new Route(670, 580, 26, 3, "purple", "none", "Helena-SLC"));
            routes.add(new Route(1020, 750, 78, 4, "purple", "none", "Denver-Omaha"));
            routes.add(new Route(1640, 480, 89, 6, "purple", "none", "Duluth-Toronto"));
            routes.add(new Route(2020, 1460, 355, 4, "purple", "none", "Charleston-Miami"));

            //White routes (total 4)
            routes.add(new Route(870, 60, 91, 6, "white", "none", "Calgary-Winnipeg"));
            routes.add(new Route(680, 1020, 43, 5, "white", "none", "Denver-Phoenix"));
            routes.add(new Route(1780, 580, 58, 4, "white", "none", "Toronto-Chicago"));
            routes.add(new Route(1550, 1090, 80, 3, "white", "none", "LittleRock-Nashville"));

            //Black routes (total 4)
            routes.add(new Route(1260, 280, 155, 4, "black", "none", "Winnipeg-Duluth"));
            routes.add(new Route(1890, 320, 75, 5, "black", "none", "SaultStMarie-Montreal"));
            routes.add(new Route(1850, 1040, 86, 3, "black", "none", "Nashville-Raleigh"));
            routes.add(new Route(490, 1270, 107, 6, "black", "none", "LA-ElPaso"));

            //Gray routes (total...34?)
            routes.add(new Route(1400, 220, 125, 6, "gray", "none", "Winnipeg-SaultStMarie"));
            routes.add(new Route(270, 120, 30, 1, "gray", "gray", "Vancouver-Seattle"));
            routes.add(new Route(270, 120, 30, 1, "gray", "gray", "Vancouver-Seattle"));

            routes.add(new Route(200, 250, 30, 1, "gray", "gray", "Seattle-Portland"));
            routes.add(new Route(200, 250, 30, 1, "gray", "gray", "Seattle-Portland"));
            routes.add(new Route(450, 60, 88, 3, "gray", "none", "vancouver-Calgary"));
            routes.add(new Route(440, 130, 74, 4, "gray", "none", "Seattle-Calgary"));
            routes.add(new Route(680, 230, 155, 4, "gray", "none", "Calgary-Helena"));
            routes.add(new Route(1490, 430, 70, 3, "gray", "none", "SaultStMarie-Duluth"));
            routes.add(new Route(1780, 430, 105, 2, "gray", "none", "SaultStMarie-Toronto"));
            routes.add(new Route(2050, 370, 45, 3, "gray", "none", "Toronto-Montreal"));
            routes.add(new Route(2240, 360, 150, 2, "gray", "gray", "Montreal-Boston"));

            routes.add(new Route(1930, 600, 0, 2, "gray", "none", "Toronto-Pittsburgh"));
            routes.add(new Route(2060, 950, 36, 2, "gray", "gray", "Washington-Raleigh"));
            routes.add(new Route(2060, 950, 36, 2, "gray", "gray", "Washington-Raleigh"));
            routes.add(new Route(1960, 890, 174, 2, "gray", "none", "Pittsburgh-Raleigh"));
            routes.add(new Route(2030, 800, 125, 2, "gray", "none", "Pittsburgh-Washington"));
            routes.add(new Route(1890, 1110, 60, 2, "gray", "gray", "Atlanta-Raleigh"));
            routes.add(new Route(1890, 1110, 60, 2, "gray", "gray", "Atlanta-Raleigh"));
            routes.add(new Route(2020, 1130, 1, 2, "gray", "none", "Raleigh-Charleston"));
            routes.add(new Route(1890, 1210, 105, 2, "gray", "none", "Atlanta-Charleston"));
            routes.add(new Route(1740, 1110, 145, 1, "gray", "none", "Atlanta-Nashville"));
            routes.add(new Route(1590, 980, 123, 2, "gray", "gray", "SaintLouis-Nashville"));
            routes.add(new Route(1440, 1020, 22, 2, "gray", "none", "LittleRock-SaintLouis"));

            routes.add(new Route(1290, 1100, 100, 2, "gray", "none", "LittleRock-OklahomaCity"));
            routes.add(new Route(1290, 1220, 51, 2, "gray", "none", "Dallas-LittleRock"));
            routes.add(new Route(1250, 1380, 140, 1, "gray", "gray", "Dallas-Houston"));
            routes.add(new Route(1250, 1380, 140, 1, "gray", "gray", "Dallas-Houston"));
            routes.add(new Route(1400, 1480, 87, 2, "gray", "none", "Houston-NewOrleans"));
            routes.add(new Route(1180, 1190, 185, 2, "gray", "gray", "Dallas-OklahomaCity"));
            routes.add(new Route(1180, 1190, 185, 2, "gray", "gray", "Dallas-OklahomaCity"));

            routes.add(new Route(1230, 970, 20, 2, "gray", "gray", "OklahomaCity-KansasCity"));
            routes.add(new Route(1240, 780, 165, 1, "gray", "gray", "KansasCity-Omaha"));
            routes.add(new Route(1270, 580, 30, 2, "gray", "gray", "Omaha-Duluth"));
            routes.add(new Route(1230, 970, 20, 2, "gray", "gray", "OklahomaCity-KansasCity"));
            routes.add(new Route(1240, 780, 165, 1, "gray", "gray", "KansasCity-Omaha"));
            routes.add(new Route(1270, 580, 30, 2, "gray", "gray", "Omaha-Duluth"));
            routes.add(new Route(820, 980, 10, 2, "gray", "none", "SantaFe-Denver"));
            routes.add(new Route(770, 1200, 10, 2, "gray", "none", "SantaFe-ElPaso"));

            routes.add(new Route(650, 1140, 60, 3, "gray", "none", "SantaFe-Phoenix"));
            routes.add(new Route(620, 1260, 110, 3, "gray", "none", "Phoenix-ElPaso"));
            routes.add(new Route(360, 1180, 92, 3, "gray", "none", "LA-Phoenix"));
            routes.add(new Route(310, 1080, 49, 2, "gray", "none", "LA-Las Vegas"));
        }
    }
}
