package PossiblyHelpful;


import ClientModel.ClientModel;
import ClientModel.Player;

//FEEL FREE TO DELETE THESE CLASSES IF YOU DECIDE THERE IS A BETTER/EASIER WAY TO DO THIS.
public class PossibleWayToDoClaimRouteService
{

    public PossibleWayToDoClaimRouteService()
    {

    }

    //This function doesn't go through the server yet, but it shows what the server ought to do
    // to all client models.
    public void claimRoute(Player player, int indexOfRouteToClaim)
    {
        ClientModel.getInstance().claimRouteByIndex(indexOfRouteToClaim, player);
    }
}
