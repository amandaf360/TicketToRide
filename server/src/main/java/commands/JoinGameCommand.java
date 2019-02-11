package commands;

import responses.BaseResponse;
import services.JoinGameService;

public class JoinGameCommand implements ICommand
{
    private int gameNum;
    private String username;

    public BaseResponse execute()
    {
        JoinGameService service = new JoinGameService();

        return service.joinGame(gameNum, username);
    }

    public JoinGameCommand(int gameNum, String username) {
        this.gameNum = gameNum;
        this.username = username;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
