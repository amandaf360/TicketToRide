package commands;

import responses.BaseResponse;
import services.EndTurnService;

public class EndTurnCommand implements ICommand
{
    private String username;

    public EndTurnCommand(String username)
    {
        this.username = username;
    }

    public BaseResponse execute()
    {
        EndTurnService service = new EndTurnService(username);
        service.endTurn();
        return null;
    }
}
