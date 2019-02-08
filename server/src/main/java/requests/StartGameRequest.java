package requests;

public class StartGameRequest extends BaseRequest
{
    private String name;
    private int gameNum;
    private int numPlayers;

    public StartGameRequest(String name, int gameNum, int numPlayers) {
        this.name = name;
        this.gameNum = gameNum;
        this.numPlayers = numPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
