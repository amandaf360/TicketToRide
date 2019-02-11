package services;

import responses.PollResponse;
import server.ClientCommandManager;

public class PollService
{
    public PollResponse poll(String username)
    {
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        PollResponse response = manager.poll(username);
        response.setUsername(username);
        return response;
    }
}
