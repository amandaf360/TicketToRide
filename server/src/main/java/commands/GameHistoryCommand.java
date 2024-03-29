package commands;

import java.io.Serializable;

import responses.BaseResponse;
import servermodel.Message;
import servermodel.ModelRoot;
import services.GameHistoryService;

public class GameHistoryCommand implements ICommand, Serializable
{
    private Message message;
    private String user;
    private String authToken;

    public BaseResponse execute()
    {
        GameHistoryService service = new GameHistoryService();
        service.sendGameHistoryMessage(message, user, authToken);

        int gameNum = ModelRoot.getModel().getGameByAuthToken(authToken).getGameNum();
        ModelRoot.getModel().addGameCommandToDataBase(gameNum, this);
        return null;
    }

    public GameHistoryCommand(Message message, String user, String authToken)
    {
        this.message = message;
        this.user = user;
        this.authToken = authToken;
    }
}
