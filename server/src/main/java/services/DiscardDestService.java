package services;

import java.util.ArrayList;

import responses.BaseResponse;
import responses.DrawDestResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DestCard;
import servermodel.ModelRoot;
import servermodel.Player;

public class DiscardDestService
{
    private String cityOne;
    private String cityTwo;
    private int length;
    private String username;

    public DiscardDestService(String cityOne, String cityTwo, int length, String username)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
        this.username = username;
    }

    public BaseResponse discardCard()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(username);
        Player player = game.getPlayerByUsername(username);
        DestCard card = player.discardDestCard(cityOne, cityTwo);
        game.getDestinationDeck().putOnBottom(card);

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> allUsers = game.getAllUsernames();
        for(int i = 0; i < allUsers.size(); i++)
        {
            if(!allUsers.get(i).equals(username))
            {
                manager.addCardDiscarded(username, allUsers.get(i));
            }
            manager.setDeckState(username);
        }
        return null;
    }
}
