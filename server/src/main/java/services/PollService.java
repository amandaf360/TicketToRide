package services;

import java.util.ArrayList;

import responses.PollResponse;
import server.ClientCommandManager;
import servermodel.Game;
import servermodel.ModelRoot;

public class PollService
{
    public PollResponse poll(String username, boolean firstPoll)
    {
        PollResponse response = new PollResponse();
        if(!firstPoll)
        {
            ClientCommandManager manager = ClientCommandManager.getCommandManager();
            response = manager.poll(username);
            response.setUsername(username);
            return response;
        }
        else
        {
            ModelRoot model = ModelRoot.getModel();
            response.setGamesCreated(model.getGameList());
            if(response.getGamesCreated() == null)
            {
                response.setGamesCreated(new ArrayList<Game>());
            }
            return response;
        }
    }
}
