package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.ModelRoot;

public class EndTurnService
{
    private String username;

    public EndTurnService(String username)
    {
        this.username = username;
    }

    public void endTurn()
    {
        ModelRoot model = ModelRoot.getModel();
        ActiveGame game = model.getGameByUser(username);
        game.advanceTurn();
        ArrayList<String> allUsers = game.getAllUsernames();
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for(String user: allUsers)
        {
            if(!user.equals(username))
            {
                manager.advanceTurn(user);
            }
        }
    }
}
