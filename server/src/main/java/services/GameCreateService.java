package services;

import responses.CreateGameResponse;
import responses.StartGameResponse;
import server.ClientCommandManager;
import servermodel.Game;
import servermodel.ModelRoot;

public class GameCreateService
{
    private String gameName;
    private int numPlayers;
    private String username;
    private String authToken;


    public CreateGameResponse startGame()
    {
        ModelRoot model = ModelRoot.getModel();
        Game game = new Game();
        game.setCreator(username);
        game.setMaxPlayers(numPlayers);
        game.setName(gameName);
        game.setGameNum(model.assignNumber());



        model.addGame(game);
        ClientCommandManager commandManager = ClientCommandManager.getCommandManager();
        commandManager.addGame(game);
        CreateGameResponse response = new CreateGameResponse(null);
        return response;
    }

    public GameCreateService(String gameName, int numPlayers, String username, String authToken)  {
        this.gameName = gameName;
        this.numPlayers = numPlayers;
        this.username = username;
        this.authToken = authToken;
    }
}
