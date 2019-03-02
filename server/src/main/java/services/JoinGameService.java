package services;

import java.util.ArrayList;
import java.util.List;

import responses.JoinGameResponse;
import server.ClientCommandManager;
import servermodel.Game;
import servermodel.ModelRoot;
import servermodel.Player;

public class JoinGameService
{
    public JoinGameResponse joinGame(int gameNum, String username)
    {
        JoinGameResponse response = new JoinGameResponse();
        ModelRoot model = ModelRoot.getModel();
        List<Game> gameList = model.getGameList();
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            if(game.getGameNum() == gameNum)
            {
                if(game.getMaxPlayers() > game.getCurrentPlayers())
                {
                    Player player = new Player();
                    player.setName(username);
                    game.addPlayer(player);
                    ClientCommandManager manager = ClientCommandManager.getCommandManager();
                    manager.join(username, gameNum);
                }
                else
                {
                    response.setErrorMessage("Game is full.");
                }
            }
        }
        return null;
    }
}
