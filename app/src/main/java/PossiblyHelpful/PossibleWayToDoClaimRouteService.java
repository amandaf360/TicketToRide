package PossiblyHelpful;


import java.lang.reflect.Proxy;

import ClientModel.ClientModel;
import ClientModel.Player;
import proxy.ServerProxy;

//FEEL FREE TO DELETE THESE CLASSES IF YOU DECIDE THERE IS A BETTER/EASIER WAY TO DO THIS.
public class PossibleWayToDoClaimRouteService
{

    public PossibleWayToDoClaimRouteService()
    {

    }

    //This function doesn't go through the server yet, but it shows what the poller ought to do
    // to all client models.
    // The way this function claims the route is by going directly to the route in the model
    // finding a player in the activeGame of the model that matches the player you passed in,
    // and says the route is claimed by that player. The player's numRoutes are then incremented.
    public void claimRoute(String name, int indexOfRouteToClaim)
    {
        ServerProxy proxy = new ServerProxy();
        proxy.claimRoute(indexOfRouteToClaim, name);
        //ClientModel.getInstance().claimRouteByIndex(indexOfRouteToClaim, name);

    }
}
