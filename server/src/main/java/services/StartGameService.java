package services;

import responses.StartGameResponse;
import server.ClientCommandManager;
import servermodel.Game;
import servermodel.ModelRoot;

public class StartGameService
{
    private String gameName;
    private String numPlayers;
    private String username;


    public StartGameResponse startGame()
    {
        Game game = new Game();
        game.setCreator(username);
        game.setMaxPlayers(Integer.parseInt(numPlayers));
        game.setName(gameName);

        ModelRoot.getModel().addGame(game);
        ClientCommandManager commandManager = ClientCommandManager.getCommandManager();
        commandManager.addGame(game);
        return null;
    }

    public StartGameService(String gameName, String numPlayers, String username) {
        this.gameName = gameName;
        this.numPlayers = numPlayers;
        this.username = username;
    }
}
