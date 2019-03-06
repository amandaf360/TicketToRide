package services;

import ClientModel.ClientModel;
import ClientModel.Game;

public class SetActiveGameService
{
    private ClientModel model;

    public SetActiveGameService()
    {
        model = ClientModel.getInstance();
    }

    public void setActiveGame(Game game)
    {
        model.setActiveGame(game);
    }
}
