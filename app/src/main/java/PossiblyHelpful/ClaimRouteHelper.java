package PossiblyHelpful;

import android.widget.TextView;

import com.example.amandafails.tickettoride.app.subviews.TrainView;
import com.example.amandafails.tickettoride.app.subviews.TrainView;

import ClientModel.ClientModel;
import ClientModel.Route;

public class ClaimRouteHelper
{
    int indexOfRouteClaimed;

    // All you have to do is pass in the mapRoute you want to claim into the constructor.
    // Or if you don't want to you could just pass it into the claimRoute function.
    // Up to you.
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



    // So this func takes the mapRoute and turns it into the type of route used by the client model.
    // Then it finds an unclaimed route in the client model that matches it.
    // It takes the index of that route in the list of Routes in the model and makes my weak
    // claimRoute service. The service does the rest.
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
