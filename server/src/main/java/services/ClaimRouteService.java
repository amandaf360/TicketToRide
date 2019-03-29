package services;

import java.util.ArrayList;
import java.util.List;

import responses.ClaimRouteResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.ModelRoot;
import servermodel.Player;

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

        int numPoints = calculatePoints(cards.size());


        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> usernames = game.getAllUsernames();


        for (int i = 0; i < usernames.size(); i++)
        {
            if (!usernames.get(i).equals(name))
            {
                manager.claimRoute(index, name, cards.size(), usernames.get(i), numPoints);
            } else
            {
                calculatePoints(cards.size());
            }
            manager.addTrainsUsed(usernames.get(i), name, cards.size());
        }

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




