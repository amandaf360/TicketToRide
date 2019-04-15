package commands;

import java.io.Serializable;

import responses.BaseResponse;
import responses.DrawTrainResponse;
import servermodel.ModelRoot;
import services.DrawTrainService;

public class DrawTrainCommand implements ICommand, Serializable
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


        int gameNum = ModelRoot.getModel().getGameByAuthToken(authToken).getGameNum();
        ModelRoot.getModel().addGameCommandToDataBase(gameNum, this);
        return response;
    }
}
