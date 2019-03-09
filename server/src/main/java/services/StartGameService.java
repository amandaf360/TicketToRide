package services;

import java.util.ArrayList;

import responses.StartGameResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.ColorAssigner;
import servermodel.Game;
import servermodel.GameStartInfo;
import servermodel.ModelRoot;
import servermodel.Player;
import servermodel.TrainCarCard;
import servermodel.TrainCarDeck;

public class StartGameService
{
    public StartGameResponse startGame(int gameNum)
    {
        StartGameResponse response = new StartGameResponse();
        ModelRoot model = ModelRoot.getModel();
        ArrayList<Game> gameList = model.getGameList();
        boolean gameFound = false;
        for(int i = 0; i < gameList.size(); i++)
        {
            Game currentGame = gameList.get(i);
            if(currentGame.getGameNum() == gameNum)
            {
                gameFound = true;
                currentGame.assignColors();
                ArrayList<String> playersAndColors = currentGame.getPlayersAndColors();
                ActiveGame activeGame = new ActiveGame();
                activeGame.setPlayers(currentGame.getPlayers());
                activeGame.setGameNum(currentGame.getGameNum());
                ClientCommandManager manager = ClientCommandManager.getCommandManager();
                ArrayList<String> usernames = activeGame.getAllUsernames();
                TrainCarDeck deck = activeGame.getTrainDeck();
                for(String username: usernames)
                {
                    ArrayList<TrainCarCard> playerHand = new ArrayList<>();
                    for(int j = 0; j < 4; j++)
                    {
                        playerHand.add(deck.draw());
                    }
                    GameStartInfo info = new GameStartInfo();
                    info.setCardsInHand(playerHand);
                    info.setCurrentTurnPlayer(activeGame.getPlayers().get(0).getName());
                    info.setPlayersAndColors(playersAndColors);
                    manager.startGame(username, info);
                }
                //need to do some client command manager stuff here
                if(currentGame.getPlayers().size() == currentGame.getMaxPlayers())
                {
                    response.setErrorMessage("Starting game");
                }
                else
                {
                    response.setErrorMessage("Not enough players");
                }
            }
        }
        if(!gameFound)
        {
            response.setErrorMessage("Game does not exist! (Don't know how the heck you got this to happen)");
        }
        return response;
    }
}
