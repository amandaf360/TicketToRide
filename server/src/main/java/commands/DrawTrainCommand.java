package commands;

import responses.BaseResponse;
import responses.DrawTrainResponse;
import services.DrawTrainService;

public class DrawTrainCommand implements ICommand
{

    private String username;
    private int index;

    public DrawTrainCommand(String username, int index)
    {
        this.username = username;
        this.index = index;
    }
    public BaseResponse execute()
    {
        DrawTrainService service = new DrawTrainService(username, index);
        DrawTrainResponse response = service.draw();
        return response;
    }
}
