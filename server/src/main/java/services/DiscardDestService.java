package services;

import java.util.ArrayList;

import responses.BaseResponse;
import responses.DrawDestResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DecksStateData;
import servermodel.DestCard;
import servermodel.ModelRoot;
import servermodel.Player;

public class DiscardDestService
{
    private String cityOne;
    private String cityTwo;
    private int length;
    private String username;
    private String authToken;

    public DiscardDestService(String cityOne, String cityTwo, int length, String username, String authToken)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
        this.username = username;
        this.authToken = authToken;
    }

    public BaseResponse discardCard()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByAuthToken(authToken);
        Player player = game.getPlayerByUsername(username);
        DestCard card = player.discardDestCard(cityOne, cityTwo);
        game.getDestinationDeck().putOnBottom(card);

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> allAuthTokens = game.getAllAuthTokens();
        for(int i = 0; i < allAuthTokens.size(); i++)
        {
            if(!allAuthTokens.get(i).equals(authToken))
            {
                manager.addCardDiscarded(username, allAuthTokens.get(i));
            }
            manager.setDeckState(new DecksStateData(game), authToken);
        }
        return null;
    }
}
