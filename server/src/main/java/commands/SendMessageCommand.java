package commands;

import responses.BaseResponse;
import services.SendMessageService;

public class SendMessageCommand implements ICommand
{

    private String message;
    private String username;
    private int gameNum;

    public BaseResponse execute()
    {
        SendMessageService service = new SendMessageService(message, username, gameNum);
        return null;
    }

    public SendMessageCommand(String message, String username, int gameNum) {
        this.message = message;
        this.username = username;
        this.gameNum = gameNum;
    }
}
