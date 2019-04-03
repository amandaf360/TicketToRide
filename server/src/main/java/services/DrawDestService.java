package services;

import java.util.ArrayList;

import responses.BaseResponse;
import responses.DrawDestResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DecksStateData;
import servermodel.DestCard;
import servermodel.DestCardDeck;
import servermodel.ModelRoot;
import servermodel.Player;
import servermodel.TrainCarCard;

public class DrawDestService
{
    public DrawDestService()
    {

    }

    public DrawDestResponse drawCards(int numCards, String username, String authToken)
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByAuthToken(authToken);
        ArrayList<DestCard> cardsDrawn = new ArrayList<>();
        DestCardDeck deck = game.getDestinationDeck();
        Player player = game.getPlayerByUsername(username);

        for(int i = 0; i < numCards; i++)
        {
            DestCard drawnCard = deck.draw();
            if(drawnCard != null)
            {
                cardsDrawn.add(drawnCard);
                player.addDestCard(drawnCard);
            }
        }

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> authTokens = game.getAllAuthTokens();
        for(int i = 0; i < authTokens.size(); i++)
        {
            if(!authTokens.get(i).equals(authToken))
            {
                manager.addCardsDrawn(numCards, username, authTokens.get(i));
            }
            manager.setDeckState(new DecksStateData(game), authToken);
        }

        DrawDestResponse response = new DrawDestResponse(cardsDrawn);
        return response;
    }
}
