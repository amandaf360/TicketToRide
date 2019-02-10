package services;

import ThomasStuff.ClientModel;
import ThomasStuff.Game;

public class SetActiveGameService
{
    ClientModel model;

    public SetActiveGameService()
    {
        model = ClientModel.getInstance();
    }

    public void setActiveGame(Game game)
    {
        model.setGame(game);
    }
}