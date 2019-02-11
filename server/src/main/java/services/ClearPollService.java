package services;

import server.ClientCommandManager;

public class ClearPollService
{
    public void clearPoll(String username)
    {
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        manager.pollClear(username);
    }
}
