package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.Message;
import servermodel.ModelRoot;

public class GameHistoryService
{
    public void sendGameHistoryMessage(Message message, String username)
    {
        ModelRoot model = ModelRoot.getModel();
        ArrayList<String> allUsers = model.getGameByUser(username).getAllUsernames();
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for(String user: allUsers)
        {
            manager.addGameHistoryMessage(user, message);
        }
    }
}
