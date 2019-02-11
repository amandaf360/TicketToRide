package commands;

import java.util.ArrayList;

import ThomasStuff.Game;
import responses.PollResponse;
import services.SetGamelistService;

public class PollCommand implements ICommand
{
    private PollResponse response;

    public void execute()
    {
        //updates games
        SetGamelistService listService = new SetGamelistService();
        ArrayList<Game> gameList = response.getGamesCreated();
        for(int i = 0; i < gameList.size(); i++)
        {
            listService.addGame(gameList.get(i));
        }
        //updates players in a given game
    }

    public PollCommand(PollResponse response)
    {
        this.response = response;
    }
}
