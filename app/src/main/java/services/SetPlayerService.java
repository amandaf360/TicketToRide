package services;
import java.util.ArrayList;
import ThomasStuff.ClientModel;
import ThomasStuff.Player;

public class SetPlayerService
{
    private ClientModel model;

    SetPlayerService()
    {
        model = ClientModel.getInstance();
    }

    public void setPlayer(ArrayList<Player> players)
    {
        //model.setPlayers(players);
    }
}
