package services;

import java.util.ArrayList;
import java.util.List;

import responses.ClaimRouteResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DecksStateData;
import servermodel.Message;
import servermodel.ModelRoot;
import servermodel.Player;
import servermodel.TrainCarCard;
import servermodel.TrainCarDeck;
import servermodel.TrainCarDiscard;

public class ClaimRouteService
{
    public ClaimRouteService()
    {

    }

    public ClaimRouteResponse claimRoute(int index, String name, List<String> cards, String authToken)
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByAuthToken(authToken);
        game.claimRoute(index, name, cards);
        int numPoints = calculatePoints(cards.size());
        Player player = game.getPlayerByUsername(name);
        TrainCarDiscard discardPile = game.getTrainCarDiscard();
        for(int i = 0; i < cards.size(); i++)
        {
            TrainCarCard discardedCard = player.removeTrainCarCard(cards.get(i));
            if(discardedCard != null)
            {
                discardPile.discard(discardedCard);
            }
        }
        TrainCarDeck deck = game.getTrainDeck();
        if(deck.size() == 0)
        {
            deck.combineWithDiscard();
        }

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> authTokens = game.getAllAuthTokens();
        player.setNumTrains(player.getNumTrains() - cards.size());
        boolean lastTurn = false;
        if(!game.isLastTurn())
        {
            lastTurn = (player.getNumTrains() <= 2);
            if(lastTurn)
            {
                game.setLastTurnPlayer(name);
                game.setLastTurn(true);
            }
        }


        for (int i = 0; i < authTokens.size(); i++)
        {
            manager.setDeckState(new DecksStateData(game), authTokens.get(i));
            if (!authTokens.get(i).equals(authToken))
            {
                manager.claimRoute(index, name, cards.size(), authTokens.get(i), numPoints);
            }
            else
            {
                manager.claimRoute(numPoints, authToken);
            }
            manager.addTrainsUsed(authTokens.get(i), name, cards.size());
            if(lastTurn)
            {
                manager.addGameHistoryMessage(authTokens.get(i), new Message("Not important", "You have one turn remaining."));
            }

        }

        game.getGraph().claimRoute(name, index);
        ClaimRouteResponse response = new ClaimRouteResponse(index, name);
        return response;
    }


    private int calculatePoints(int length)
    {
        switch(length)
        {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                return length;
        }
    }
}







