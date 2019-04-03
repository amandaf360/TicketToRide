package commands;

import responses.BaseResponse;
import services.EndTurnService;

public class EndTurnCommand implements ICommand
{
    private String username;
    private String authToken;

    public EndTurnCommand(String username, String authToken)
    {
        this.username = username;
        this.authToken = authToken;
    }

    public BaseResponse execute()
    {
        EndTurnService service = new EndTurnService(username, authToken);
        service.endTurn();
        return null;
    }
}
