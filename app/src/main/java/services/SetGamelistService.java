package services;
import java.util.ArrayList;

import ClientModel.ClientModel;
import ClientModel.Game;

public class SetGamelistService
{
    private ClientModel model;

    public SetGamelistService()
    {
        model = ClientModel.getInstance();
    }

    public void setGameList(ArrayList<Game> games)
    {
        model.setGameList(games);
    }

    public void addGame(Game game)
    {
        model.addGame(game);
    }
}
