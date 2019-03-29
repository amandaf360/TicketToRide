package services;

import java.util.ArrayList;

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

    public ClaimRouteResponse claimRoute(int index, String name)
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(name);
        game.claimRoute(index, name);

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> usernames = game.getAllUsernames();
        for(int i = 0; i < usernames.size(); i++)
        {
            if(!usernames.get(i).equals(name))
            {
                manager.claimRoute(index, name, usernames.get(i));
            }
        }

        root.getMapGraph().claimRoute(name, index);
        ClaimRouteResponse response = new ClaimRouteResponse(index, name);
        return response;
    }
}
