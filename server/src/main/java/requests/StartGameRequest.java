package requests;

public class StartGameRequest extends BaseRequest
{
    private int gameNum;

    public StartGameRequest(int gameNum) {
        this.gameNum = gameNum;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }
}
