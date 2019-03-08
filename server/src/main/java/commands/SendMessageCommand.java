package commands;

import responses.BaseResponse;
import services.SendMessageService;

public class SendMessageCommand implements ICommand
{

    private String message;
    private String username;
    private String color;
    private int gameNum;

    public BaseResponse execute()
    {
        SendMessageService service = new SendMessageService(message, username, color, gameNum);
        return null;
    }

    public SendMessageCommand(String message, String username, String color, int gameNum) {
        this.message = message;
        this.username = username;
        this.color = color;
        this.gameNum = gameNum;
    }
}