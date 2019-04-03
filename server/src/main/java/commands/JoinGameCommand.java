package commands;

import responses.BaseResponse;
import services.JoinGameService;

public class JoinGameCommand implements ICommand
{
    private int gameNum;
    private String username;
    private String authToken;

    public BaseResponse execute()
    {
        JoinGameService service = new JoinGameService();

        return service.joinGame(gameNum, username, authToken);
    }

    public JoinGameCommand(int gameNum, String username, String authToken)
    {
        this.gameNum = gameNum;
        this.username = username;
        this.authToken = authToken;
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
