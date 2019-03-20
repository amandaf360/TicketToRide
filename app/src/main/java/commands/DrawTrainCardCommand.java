package commands;

import ClientModel.ClientModel;
import ClientModel.TrainCarCard;

public class DrawTrainCardCommand implements ICommand
{
    private String cardColor;

    public DrawTrainCardCommand(String cardColor)
    {
        this.cardColor = cardColor;
    }
    public void execute()
    {
        ClientModel model = ClientModel.getInstance();
        model.addTrainCardToActivePlayerHand(new TrainCarCard(cardColor));
    }

}
