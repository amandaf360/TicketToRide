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
import servermodel.Game;
import servermodel.ModelRoot;
import servermodel.User;

public class ClientCommandManager
{


    private Map<String, ArrayList<Game>> gamesCreated;
    private Map<String, ArrayList<String>> gamesDeleted;
    private Map<String, ArrayList<String>> playersJoined;
    private Map<String, ArrayList<String>> playersLeft;

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
    }

    public void addUser(String username)
    {
        gamesCreated.put(username, new ArrayList<Game>());
        gamesDeleted.put(username, new ArrayList<String>());
        playersJoined.put(username, new ArrayList<String>());
        playersLeft.put(username, new ArrayList<String>());

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
        ArrayList<Game> currentGamesCreated = gamesCreated.get(username);
        ArrayList<Game> polledGamesCreated = new ArrayList<>();
        for(int i = 0; i < currentGamesCreated.size(); i++)
        {
            polledGamesCreated.add(new Game(currentGamesCreated.get(i)));
        }

        response.setGamesCreated(gamesCreated.get(username));

        response.setGamesDeleted(gamesDeleted.get(username));
        response.setPlayersJoined(playersJoined.get(username));
        response.setPlayersLeft(playersLeft.get(username));

        return response;
    }

    public void pollClear(String username)
    {
        gamesCreated.get(username).clear();
        gamesDeleted.get(username).clear();
        playersJoined.get(username).clear();
        playersLeft.get(username).clear();
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

}