package responses;

public class CreateGameResponse extends BaseResponse
{
    private String gameName;
    private String user;
    private int numPlayers;

    public CreateGameResponse(String gameName, String user, int numPlayers) {
        this.gameName = gameName;
        this.user = user;
        this.numPlayers = numPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
