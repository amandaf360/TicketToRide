package services;

import java.util.ArrayList;

import ClientModel.Route;
import ClientModel.TrainCarCard;
import proxy.ServerProxy;

public class ClaimRouteService
{
    ServerProxy proxy;

    public void ClaimRouteService()
    {

    }

    public void claimRoute(String name, int indexOfRouteToClaim, ArrayList<TrainCarCard> cardsForPayment)
    {
        ServerProxy proxy = new ServerProxy();
        proxy.claimRoute(indexOfRouteToClaim, name, cardsForPayment);
    }
}
