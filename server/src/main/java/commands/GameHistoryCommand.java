package commands;

import responses.BaseResponse;
import servermodel.Message;
import services.GameHistoryService;

public class GameHistoryCommand implements ICommand
{
    private Message message;
    private String user;

    public BaseResponse execute()
    {
        GameHistoryService service = new GameHistoryService();
        service.sendGameHistoryMessage(message, user);
        return null;
    }

    public GameHistoryCommand(Message message, String user)
    {
        this.message = message;
        this.user = user;
    }
}
