package services;
import java.util.ArrayList;

import ThomasStuff.ClientModel;
import ThomasStuff.Game;

public class SetGamelistService
{
    ClientModel model;

    public SetGamelistService()
    {
        model = ClientModel.getInstance();
    }

    public void setGameList(ArrayList<Game> games)
    {
        model.setGameList(games);
    }
}
