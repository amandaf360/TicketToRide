package services;

import responses.PollResponse;
import server.ClientCommandManager;

public class PollService
{
    public PollResponse poll(String username)
    {
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        return manager.poll(username);
    }
}
