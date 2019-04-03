package commands;

import responses.BaseResponse;
import responses.DrawTrainResponse;
import services.DrawTrainService;

public class DrawTrainCommand implements ICommand
{

    private String username;
    private int index;
    private String authToken;

    public DrawTrainCommand(String username, int index, String authToken)
    {
        this.username = username;
        this.index = index;
        this.authToken = authToken;
    }
    public BaseResponse execute()
    {
        DrawTrainService service = new DrawTrainService(username, index, authToken);
        DrawTrainResponse response = service.draw();
        return response;
    }
}
