package services;

import ClientModel.ClientModel;
import proxy.ServerProxy;

public class DrawTrainCardService
{
    public void drawCard(int faceUpIndex)//index should be -1 if drawing from the deck and not the face up cards
    {
        ClientModel model = ClientModel.getInstance();
        ServerProxy proxy = new ServerProxy();
        proxy.drawTrainCarCard(model.getUser().getUserName(), faceUpIndex);
    }
}
