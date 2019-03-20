package commands;

import responses.BaseResponse;
import servermodel.Message;

public class GameHistoryCommand implements ICommand
{
    private Message message;
    private String user;

    public BaseResponse execute()
    {

        return null;
    }

    public GameHistoryCommand(Message message, String user)
    {
        this.message = message;
        this.user = user;
    }
}
