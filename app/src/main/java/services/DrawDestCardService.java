package services;

import ClientModel.ClientModel;
import proxy.ServerProxy;

public class DrawDestCardService
{
    public void drawCards(int numCards)
    {
        ClientModel model = ClientModel.getInstance();
        ServerProxy proxy = new ServerProxy();
        proxy.drawDestCards(numCards, model.getUser().getUserName());
    }
}
