package commands;

import responses.CreateGameResponse;
import services.GameCreateService;

public class CreateGameCommand implements ICommand
{
    private String gameName;
    private String user;
    private int numPlayers;


    @Override
    public CreateGameResponse execute()
    {
        GameCreateService service = new GameCreateService(gameName, numPlayers, user);
        return service.startGame();
    }

    public CreateGameCommand(String user, int numPlayers, String gameName) {
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
