package commands;

import responses.BaseResponse;
import services.StartGameService;

public class StartGameCommand implements ICommand
{
    private String gameName;
    private String numPlayers;
    private String username;

    public BaseResponse execute()
    {
        StartGameService service = new StartGameService(gameName, numPlayers, username);
        service.startGame();
        return null;
    }
}
