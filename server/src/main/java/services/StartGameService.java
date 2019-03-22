package services;

import java.util.ArrayList;

import responses.StartGameResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.ColorAssigner;
import servermodel.DecksStateData;
import servermodel.FaceUpCards;
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
            if(currentGame.getGameNum() == gameNum && !currentGame.isHasStarted())
            {
                currentGame.setHasStarted(true);
                gameFound = true;
                currentGame.assignColors();
                ArrayList<String> playersAndColors = currentGame.getPlayersAndColors();
                ActiveGame activeGame = new ActiveGame();
                activeGame.setPlayers(currentGame.getPlayers());
                activeGame.setGameNum(currentGame.getGameNum());
                ClientCommandManager manager = ClientCommandManager.getCommandManager();
                ArrayList<String> usernames = activeGame.getAllUsernames();
                TrainCarDeck deck = activeGame.getTrainDeck();
                ArrayList<TrainCarCard> faceUps = new ArrayList<>();
                for(int j = 0; j < 5; j++)
                {
                    faceUps.add(deck.draw());
                }
                activeGame.getFaceUpCards().setCards(faceUps);
                for(String username: usernames)
                {

                    ArrayList<TrainCarCard> playerHand = new ArrayList<>();
                    for(int j = 0; j < 4; j++)
                    {
                        playerHand.add(deck.draw());
                    }
                    ArrayList<TrainCarCard> personalFaceUps = new ArrayList<>();//don't want to risk pointing to the same stuff
                    for(int j = 0; j < 5; j++)
                    {
                        personalFaceUps.add(new TrainCarCard(faceUps.get(j).getColor()));
                    }

                    GameStartInfo info = new GameStartInfo();
                    info.setCardsInHand(playerHand);
                    info.setCurrentTurnPlayer(activeGame.getPlayers().get(0).getName());
                    info.setPlayersAndColors(playersAndColors);
                    info.setStartingFaceUps(personalFaceUps);

                    manager.startGame(username, info);
                }


                if(currentGame.getPlayers().size() == currentGame.getMaxPlayers())
                {
                    response.setErrorMessage("Starting game");
                }
                else
                {
                    response.setErrorMessage("Not enough players");
                }
                model.addActiveGame(activeGame);
            }
        }
        if(!gameFound)
        {
            response.setErrorMessage("Game does not exist! (Don't know how the heck you got this to happen)");
        }
        return response;
    }
}
