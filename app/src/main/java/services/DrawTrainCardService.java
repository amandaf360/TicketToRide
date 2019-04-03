package services;

import ClientModel.ClientModel;
import ClientModel.Message;
import proxy.ServerProxy;

public class DrawTrainCardService
{
    public void drawCard(int faceUpIndex)//index should be -1 if drawing from the deck and not the face up cards
    {
        ClientModel model = ClientModel.getInstance();
        ServerProxy proxy = new ServerProxy();
        proxy.drawTrainCarCard(model.getUser().getUserName(), faceUpIndex, model.getUser().getAuthToken());
        ServerProxy messageProxy = new ServerProxy();
        if(faceUpIndex == -1)
        {
            messageProxy.sendGameHistoryMessage(model.getUser().getUserName(), new Message(model.getMainPlayer().getColor(),
                    "Drew a face down train car card"), model.getUser().getAuthToken());
        }
        else
        {
            String indefArticle = "a";
            String color = model.getActiveGame().getFaceUpCards().get(faceUpIndex).getColor();
            if(color.equals("orange"))
            {
                indefArticle += "n";
            }
            indefArticle += " ";
            messageProxy.sendGameHistoryMessage(model.getUser().getUserName(), new Message(model.getMainPlayer().getColor(),
                    "Drew " + indefArticle + color + " face up train car card"), model.getUser().getAuthToken());
        }
    }
}
