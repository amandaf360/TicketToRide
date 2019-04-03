package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DestPointsInfo;
import servermodel.ModelRoot;

public class EndTurnService
{
    private String username;
    private String authToken;

    public EndTurnService(String username, String authToken)
    {
        this.username = username;
        this.authToken = authToken;
    }

    public void endTurn()
    {
        ModelRoot model = ModelRoot.getModel();
        ActiveGame game = model.getGameByAuthToken(authToken);
        game.advanceTurn();
        ArrayList<String> allAuthTokens = game.getAllAuthTokens();
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<DestPointsInfo> info = null;
        boolean gameOver = false;
        if(game.isLastTurn())
        {
            if(game.isActuallyLastTurn())
            {
                if (username.equals(game.getLastTurnPlayer())) {
                    gameOver = true;
                    info = game.calculateRoutePoints();
                }
            }
            else
            {
                game.setActuallyLastTurn(true);
            }
        }
        for(String token: allAuthTokens)
        {
            manager.advanceTurn(token);
            if(gameOver)
            {
                manager.setGameOver(token);
                manager.setDestPointsInfo(token, info);
            }
        }
    }
}
