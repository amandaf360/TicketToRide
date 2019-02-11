package commands;

import responses.BaseResponse;
import services.StartGameService;

public class StartGameCommand implements ICommand
{
    private int gameNum;

    public StartGameCommand(int gameNum) {
        this.gameNum = gameNum;
    }

    public BaseResponse execute()
    {
        StartGameService service = new StartGameService();
        return service.startGame(gameNum);
    }
}
