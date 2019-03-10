package PossiblyHelpful;


import ClientModel.ClientModel;
import ClientModel.Player;

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
    public void claimRoute(Player player, int indexOfRouteToClaim)
    {
        ClientModel.getInstance().claimRouteByIndex(indexOfRouteToClaim, player);

    }
}
