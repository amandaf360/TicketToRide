package server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import commands.*;
import responses.PollResponse;
import servermodel.ActiveGame;
import servermodel.DecksStateData;
import servermodel.Game;
import servermodel.GameStartInfo;
import servermodel.Message;
import servermodel.ModelRoot;
import servermodel.TrainCarCard;
import servermodel.User;

public class ClientCommandManager
{


    private Map<String, ArrayList<Game>> gamesCreated;
    private Map<String, ArrayList<String>> gamesDeleted;
    private Map<String, ArrayList<String>> playersJoined;
    private Map<String, ArrayList<String>> playersLeft;
    private Map<String, ArrayList<String>> gameStarted;
    private Map<String, ArrayList<Message>> chatHistory;
    private Map<String, ArrayList<String>> destinationCardsDrawn;
    private Map<String, ArrayList<String>> destinationCardsDiscarded;
    private Map<String, DecksStateData> deckStateUpdate;
    private Map<String, GameStartInfo> startGameInfo;
    private Map<String, ArrayList<String>> routesClaimed;
    private Map<String, ArrayList<String>> trainCardsDrawn;
    private Map<String, ArrayList<Message>> gameHistory;
    private Map<String, Integer> turnsEnded;
    private Map<String, ArrayList<String>> trainsUsed;
    private Map<String, ArrayList<String>> pointsGained;


    private static ClientCommandManager commandManager = new ClientCommandManager();

    public static ClientCommandManager getCommandManager()
    {
        return commandManager;
    }

    private ClientCommandManager()
    {
        gamesCreated = new HashMap<>();
        gamesDeleted = new HashMap<>();
        playersJoined = new HashMap<>();
        playersLeft = new HashMap<>();
        gameStarted = new HashMap<>();
        chatHistory = new HashMap<>();
        destinationCardsDrawn = new HashMap<>();
        destinationCardsDiscarded = new HashMap<>();
        deckStateUpdate = new HashMap<>();
        startGameInfo = new HashMap<>();
        routesClaimed = new HashMap<>();
        trainCardsDrawn = new HashMap<>();
        gameHistory = new HashMap<>();
        turnsEnded = new HashMap<>();
        trainsUsed = new HashMap<>();
        pointsGained = new HashMap<>();

    }

    public void addUser(String username)
    {
        gamesCreated.put(username, new ArrayList<Game>());
        gamesDeleted.put(username, new ArrayList<String>());
        playersJoined.put(username, new ArrayList<String>());
        playersLeft.put(username, new ArrayList<String>());
        gameStarted.put(username, new ArrayList<String>());
        chatHistory.put(username, new ArrayList<Message>());
        destinationCardsDrawn.put(username, new ArrayList<String>());
        destinationCardsDiscarded.put(username, new ArrayList<String>());
        deckStateUpdate.put(username, null);
        startGameInfo.put(username, null);
        routesClaimed.put(username, new ArrayList<String>());
        trainCardsDrawn.put(username, new ArrayList<String>());
        gameHistory.put(username, new ArrayList<Message>());
        turnsEnded.put(username, 0);
        trainsUsed.put(username, new ArrayList<String>());
        pointsGained.put(username, new ArrayList<String>());
    }

    public PollResponse firstPoll()
    {
        PollResponse response = new PollResponse();
        ModelRoot modelRoot = ModelRoot.getModel();
        response.setGamesCreated(modelRoot.getGameList());

        return response;
    }

    public PollResponse poll(String username)
    {
        PollResponse response = new PollResponse();

        response.setGamesCreated(gamesCreated.get(username));
        response.setGamesDeleted(gamesDeleted.get(username));
        response.setPlayersJoined(playersJoined.get(username));
        response.setPlayersLeft(playersLeft.get(username));
        response.setChatHistory(chatHistory.get(username));
        response.setDiscardedDestCards(destinationCardsDiscarded.get(username));
        response.setDeckData(deckStateUpdate.get(username));
        response.setGameStartInfo(startGameInfo.get(username));
        response.setDestinationCardsDrawn(destinationCardsDrawn.get(username));
        response.setRoutesClaimed(routesClaimed.get(username));
        response.setTrainCardsDrawn(trainCardsDrawn.get(username));
        response.setGameHistory(gameHistory.get(username));
        response.setTurnsEnded(turnsEnded.get(username));
        response.setTrainsUsed(trainsUsed.get(username));
        response.setPointsGained(pointsGained.get(username));

        return response;
    }

    public void pollClear(String username)
    {
        gamesCreated.get(username).clear();
        gamesDeleted.get(username).clear();
        playersJoined.get(username).clear();
        playersLeft.get(username).clear();
        chatHistory.get(username).clear();
        deckStateUpdate.put(username, null);
        destinationCardsDiscarded.get(username).clear();
        destinationCardsDrawn.get(username).clear();
        startGameInfo.put(username, null);
        routesClaimed.put(username, null);
        trainCardsDrawn.get(username).clear();
        gameHistory.get(username).clear();
        turnsEnded.put(username, 0);
        trainsUsed.get(username).clear();
        pointsGained.get(username).clear();
    }

    public void addGame(Game game)
    {
        Set<Map.Entry<String, ArrayList<Game>>> gameSet = gamesCreated.entrySet();
        Iterator<Map.Entry<String, ArrayList<Game>>> iter = gameSet.iterator();
        while(iter.hasNext())
        {
            Map.Entry<String, ArrayList<Game>> entry = iter.next();
            entry.getValue().add(game);
        }
    }

    public void deleteGame(String gamename)
    {
        Set<Map.Entry<String, ArrayList<String>>> joinedSet = playersJoined.entrySet();
        Iterator<Map.Entry<String, ArrayList<String>>> iter = joinedSet.iterator();
        while(iter.hasNext())
        {
            iter.next().getValue().add(gamename);
        }
    }

    public void join(String username, int gameNum) {
        Set<Map.Entry<String, ArrayList<String>>> joinedSet = playersJoined.entrySet();
        Iterator<Map.Entry<String, ArrayList<String>>> iter = joinedSet.iterator();
        while (iter.hasNext())
        {
            ArrayList<String> joined = iter.next().getValue();
            joined.add(username);
            joined.add(Integer.toString(gameNum));
        }
    }

    public void leaveGame(String username, String gameName)
    {
        Set<Map.Entry<String, ArrayList<String>>> joinedSet = playersLeft.entrySet();
        Iterator<Map.Entry<String, ArrayList<String>>> iter = joinedSet.iterator();
        while (iter.hasNext())
        {
            ArrayList<String> left = iter.next().getValue();
            left.add(username);
            left.add(gameName);
        }
    }

    public void addChatMessage(String username, Message message)
    {
        chatHistory.get(username).add(message);
    }

    public void addCardsDrawn(int numCards, String thisUserDrew, String sendingTo)
    {
        destinationCardsDrawn.get(sendingTo).add(Integer.toString(numCards));
        destinationCardsDrawn.get(sendingTo).add(thisUserDrew);
    }

    public void addCardDiscarded(String userDiscarded, String otherUser)
    {
        destinationCardsDiscarded.get(otherUser).add(userDiscarded);
    }

    public void setDeckState(DecksStateData data, String username)
    {
        deckStateUpdate.put(username, data);
    }

    public void startGame(String username, GameStartInfo info)
    {
        startGameInfo.put(username, info);
    }

    public void claimRoute(int index, String userClaiming, int howMany, String otherUserName)
    {

        routesClaimed.put(otherUserName, new ArrayList<String>());

        routesClaimed.get(otherUserName).add(Integer.toString(index));
        routesClaimed.get(otherUserName).add(userClaiming);
        routesClaimed.get(otherUserName).add(Integer.toString(howMany));
    }

    public void addTrainCardDrawn(String userDrew, String sendingTo)
    {
        trainCardsDrawn.get(sendingTo).add(userDrew);
    }

    public void addGameHistoryMessage(String username, Message message)
    {
        gameHistory.get(username).add(message);
    }

    public void advanceTurn(String username)
    {
        turnsEnded.put(username, turnsEnded.get(username) + 1);
    }

    public void addTrainsUsed(String sendingTo, String playerDrew, int numberUsed)
    {
        trainsUsed.get(sendingTo).add(playerDrew);
        trainsUsed.get(sendingTo).add(Integer.toString(numberUsed));
    }

}