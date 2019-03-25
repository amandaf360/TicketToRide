package commands;

import responses.BaseResponse;

public class EndTurnCommand implements ICommand
{
    private String username;

    public EndTurnCommand(String username)
    {
        this.username = username;
    }

    public BaseResponse execute()
    {

        return null;
    }
}
