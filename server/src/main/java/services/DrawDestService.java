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

    public DrawDestResponse drawCards(int numCards, String username)
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(username);
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
        ArrayList<String> usernames = game.getAllUsernames();
        for(int i = 0; i < usernames.size(); i++)
        {
            if(!usernames.get(i).equals(username))
            {
                manager.addCardsDrawn(numCards, username, usernames.get(i));
            }
            manager.setDeckState(new DecksStateData(game), username);
        }

        DrawDestResponse response = new DrawDestResponse(cardsDrawn);
        return response;
    }
}
