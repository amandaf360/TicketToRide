package services;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import proxy.ServerProxy;

public class DiscardDestCardService
{
    public void discardCard(DestinationCards card)
    {
        ClientModel model = ClientModel.getInstance();
        ServerProxy proxy = new ServerProxy();
        proxy.discardDestCard(card, model.getUser().getUserName(), model.getUser().getAuthToken());
    }
}

