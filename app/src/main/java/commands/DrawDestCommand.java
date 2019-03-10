package commands;

import java.util.ArrayList;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import responses.DrawDestResponse;

public class DrawDestCommand implements ICommand
{

    ArrayList<DestinationCards> cardsDrawn;
    public DrawDestCommand(ArrayList<DestinationCards> cardsDrawn)
    {
        this.cardsDrawn = cardsDrawn;
    }

    public void execute()
    {
        ClientModel model = ClientModel.getInstance();
        model.addDestinationCardToActivePlayersHand(cardsDrawn);
    }
}
