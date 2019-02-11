package commands;

import responses.BaseResponse;

public class StartGameCommand implements ICommand
{
    private String gameName;
    private String numPlayers;
    private String username;

    public BaseResponse execute()
    {
        return null;
    }
}
