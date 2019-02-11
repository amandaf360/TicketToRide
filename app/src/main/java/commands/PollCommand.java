package commands;

import java.util.ArrayList;

import ThomasStuff.ClientModel;
import ThomasStuff.Game;
import proxy.ServerProxy;
import responses.PollResponse;
import services.SetGamelistService;

public class PollCommand implements ICommand
{
    private PollResponse response;

    public void execute()
    {
        if(response.getGamesCreated().size() != 0 || response.getGamesDeleted().size() != 0 ||
            response.getPlayersJoined().size() != 0 || response.getPlayersLeft().size() != 0)
        {
            ServerProxy proxy = new ServerProxy();
            proxy.clearPoll(response.getUsername());
            SetGamelistService listService = new SetGamelistService();
            ArrayList<Game> gameList = response.getGamesCreated();
            for (int i = 0; i < gameList.size(); i++)
            {
                listService.addGame(gameList.get(i));
            }

            /*String currentUser = ClientModel.getInstance().getUser().getUserName();
            ArrayList<String> joinedList = response.getPlayersJoined();
            for(int i = 0; i < joinedList.size(); i += 2)
            {
                String username = joinedList.get(i);
                int gameNum = Integer.parseInt(joinedList.get(i + 1));


                if(username.equals(currentUser))
                {
                    //
                }
            }*/

        }

        //updates players in a given game
    }

    public PollCommand(PollResponse response)
    {
        this.response = response;
    }
}
