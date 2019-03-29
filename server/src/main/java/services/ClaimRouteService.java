package services;

import java.util.ArrayList;
import java.util.List;

import responses.ClaimRouteResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.ModelRoot;
import servermodel.Player;
import servermodel.TrainCarDiscard;

public class ClaimRouteService
{
    public ClaimRouteService()
    {

    }

    public ClaimRouteResponse claimRoute(int index, String name, List<String> cards)
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(name);
        game.claimRoute(index, name, cards);
        TrainCarDiscard discardPile = game.getTrainCarDiscard();

        Player player = game.getPlayerByUsername(name);
        for(int i = 0; i < cards.size(); i++)
        {
            discardPile.discard(player.removeTrainCarCard(cards.get(i)));
        }



        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> usernames = game.getAllUsernames();
        for(int i = 0; i < usernames.size(); i++)
        {
            if(!usernames.get(i).equals(name))
            {
                manager.claimRoute(index, name, cards.size(), usernames.get(i));
            }
            manager.addTrainsUsed(usernames.get(i), name, cards.size());
        }

        ClaimRouteResponse response = new ClaimRouteResponse(index, name);
        return response;
    }
}
