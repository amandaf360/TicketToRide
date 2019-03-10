package PossiblyHelpful;

import android.widget.TextView;

import com.example.amandafails.tickettoride.app.subviews.TrainView;
import com.example.amandafails.tickettoride.app.subviews.TrainView;

import ClientModel.ClientModel;
import ClientModel.Route;

public class ClaimRouteHelper
{
    int indexOfRouteClaimed;
    public ClaimRouteHelper(TrainView.MapRoute mapRoute)
    {
        Route route = new Route(mapRoute.getLength(), mapRoute.getLength(), mapRoute.getLength(),
                mapRoute.getLength(), mapRoute.getPaint(), mapRoute.getPaint2(), mapRoute.getName());

        indexOfRouteClaimed = ClientModel.getInstance().getIndexOfMatchingUnclaimedRoute(route);
    }

    public ClaimRouteHelper()
    {

    }


    public void claimRoute()    // make sure to use this claimRoute if you use the route constructor
    {

        PossibleWayToDoClaimRouteService claimRouteService;
        claimRouteService = new PossibleWayToDoClaimRouteService();
        claimRouteService.claimRoute(ClientModel.getInstance().getMainPlayer(), indexOfRouteClaimed);
    }

    public void claimRoute(TrainView.MapRoute mapRoute) // use this function if you use the empty constructor
    {
        Route route = new Route(mapRoute.getLength(), mapRoute.getLength(), mapRoute.getLength(),
                mapRoute.getLength(), mapRoute.getPaint(), mapRoute.getPaint2(), mapRoute.getName());

        indexOfRouteClaimed = ClientModel.getInstance().getIndexOfMatchingUnclaimedRoute(route);

        PossibleWayToDoClaimRouteService claimRouteService;
        claimRouteService = new PossibleWayToDoClaimRouteService();
        claimRouteService.claimRoute(ClientModel.getInstance().getMainPlayer(), indexOfRouteClaimed);
    }
}
