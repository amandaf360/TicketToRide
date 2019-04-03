package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.Message;
import servermodel.ModelRoot;

public class GameHistoryService
{
    public void sendGameHistoryMessage(Message message, String username, String authToken)
    {
        ModelRoot model = ModelRoot.getModel();
        ArrayList<String> allAuthTokens = model.getGameByAuthToken(authToken).getAllAuthTokens();
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for(String token: allAuthTokens)
        {
            manager.addGameHistoryMessage(token, message);
        }
    }
}
