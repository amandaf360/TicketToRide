package commands;

import java.io.Serializable;

import responses.BaseResponse;
import servermodel.ModelRoot;
import services.SendMessageService;

public class SendMessageCommand implements ICommand, Serializable
{

    private String message;
    private String username;
    private String color;
    private int gameNum;

    public BaseResponse execute()
    {
        SendMessageService service = new SendMessageService(message, username, color, gameNum);
        service.sendMessage();
        ModelRoot.getModel().addGameCommandToDataBase(gameNum, this);
        return null;
    }

    public SendMessageCommand(String username, String message, String color, int gameNum) {
        this.message = message;
        this.username = username;
        this.color = color;
        this.gameNum = gameNum;
    }
}
