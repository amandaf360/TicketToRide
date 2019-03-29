package commands;

import java.util.ArrayList;

import ClientModel.ClientModel;
import ClientModel.Game;
import ClientModel.Player;
import responses.PollResponse;
import services.SetActiveGameService;
import services.SetGamelistService;
import ClientModel.Message;
import ClientModel.GameStartInfo;
import ClientModel.TrainCarCard;
import ClientModel.DecksStateData;

public class PollCommand implements ICommand
{
    private PollResponse response;

    public void execute()
    {
        if (response != null)
        {
            joinPlayers(response.getPlayersJoined());
            addGames(response.getGamesCreated());
            startGame(response.getGameStarted());
            updateChat(response.getChatHistory());
            updateDestCardsDrawn(response.getDestinationCardsDrawn());
            updateRoutesClaimed(response.getRoutesClaimed());
            startMyGame(response.getGameStartInfo());
            updateDeckData(response.getDeckData());
            discardDestCards(response.getDiscardedDestCards());
            updateTrainCardsDrawn(response.getTrainCardsDrawn());
            updateGameHistory(response.getGameHistory());
            advanceTurn(response.getTurnsEnded());
            updateTrains(response.getTrainsUsed());
        }
    }

    private void updateTrains(ArrayList<String> trainList)
    {
        if(trainList != null)
        {
            if(trainList.size() != 0)
            {
                ClientModel model = ClientModel.getInstance();
                for (int i = 0; i < trainList.size(); i+=2)
                {
                    model.decrementPlayerTrainsByName(trainList.get(i), Integer.parseInt(trainList.get(i + 1)));
                }
            }
        }
    }

    private void advanceTurn(int numTurns)
    {
        if (numTurns > 0)
        {
            ClientModel model = ClientModel.getInstance();
            for (int i = 0; i < numTurns; i++)
            {
                model.endCurrentTurn();
            }
        }
    }

    private void updateGameHistory(ArrayList<Message> messages)
    {
        if (messages != null)
        {
            if (messages.size() != 0)
            {
                ClientModel model = ClientModel.getInstance();
                for (Message message : messages)
                {
                    model.addMessageToHistory(message);
                }
            }
        }
    }



    private void updateTrainCardsDrawn(ArrayList<String> usersDrew)
    {
        if(usersDrew != null)
        {
            if(usersDrew.size() != 0)
            {
                for(int i = 0; i < usersDrew.size(); i++)
                {
                    ClientModel model = ClientModel.getInstance();
                    /*Ideally, i would just have to increment a number here, but there is no difference
                      between the active player and other player models, so I have to give them an actual
                      train card, but this client shouldn't know what color cards the other players have
                      so im just putting them all as locomotives.
                    */
                    model.addTrainCardToPlayerByHand(usersDrew.get(i), new TrainCarCard("locomotive"));
                }
            }
        }
    }

    private void updateDeckData(DecksStateData data)
    {
        if(data != null)
        {
            ClientModel model = ClientModel.getInstance();
            ArrayList<TrainCarCard> faceUpCards = data.getFaceUpCards();
            if (faceUpCards != null) {
                for (int i = 0; i < faceUpCards.size(); i++) {
                    model.setFaceUpCardByIndex(i, faceUpCards.get(i));
                }
            }
            model.setActiveGameTrainCards(data.getTrainDeckSize());
            model.setActiveGameDestCards(data.getDestDeckSize());
            return;
        }
    }

    private void updateDestCardsDrawn(ArrayList<String> data)
    {
        if(data.size() != 0)
        {
            ClientModel model = ClientModel.getInstance();
            //String mainPlayerName = model.getMainPlayer().getName();
            for(int i = 0; i < data.size(); i+= 2)
            {
                int cardsDrawn = Integer.parseInt(data.get(i));
                model.increaseDestCards(data.get(i + 1), cardsDrawn);
            }
        }
    }

    private void startMyGame(GameStartInfo info)
    {
        if(info != null)
        {
            ClientModel model = ClientModel.getInstance();
            ArrayList<String> playersAndColors = info.getPlayersAndColors();
            Game activeGame = model.getActiveGame();
            ArrayList<Player> players = activeGame.getPlayers();
            for (int i = 0; i < playersAndColors.size(); i += 2) {
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).getName().equals(playersAndColors.get(i))) {
                        players.get(j).setColor(playersAndColors.get(i + 1));
                    }
                }
            }

            ArrayList<TrainCarCard> playerHand = info.getCardsInHand();
            for (TrainCarCard card : playerHand)
            {
                model.addTrainCardToActivePlayerHand(card);
            }

            ArrayList<TrainCarCard> faceUps = info.getStartingFaceUps();
            ClientModel.getInstance().startGame();
            for(int i = 0; i < faceUps.size(); i++)
            {
                model.setFaceUpCardByIndex(i, faceUps.get(i));
            }
        }
    }

    private void discardDestCards(ArrayList<String> usersDiscarded)
    {
        //the list is the names of people who have discarded one card (username repeats possible).
        // subtract 1 dest card from each of these users in the model
        if(usersDiscarded != null)
        {
            if(usersDiscarded.size() != 0)
            {
                ClientModel model = ClientModel.getInstance();
                for (String userToDeleteFrom : usersDiscarded)
                {
                    model.decrementPlayerDestCardNum(userToDeleteFrom);
                }
            }
        }
    }

    private void updateRoutesClaimed(ArrayList<String> routesClaimed)
    {
        if(routesClaimed != null)
        {
            if(routesClaimed.size() != 0)
            {
                int indexOfRoute;
                String userClaiming;
                int howMany;
                int numPoints;
                for (int i = 0; i < routesClaimed.size(); i++)
                {
                    indexOfRoute = Integer.parseInt(routesClaimed.get(i));
                    i++;

                    userClaiming = routesClaimed.get(i);
                    i++;

                    howMany = Integer.parseInt(routesClaimed.get(i));
                    i++;

                    numPoints = Integer.parseInt(routesClaimed.get(i));


                    ClientModel.getInstance().decrementPlayersTrainCardsByName(userClaiming, howMany);

                    ClientModel.getInstance().claimRouteByIndex(indexOfRoute, userClaiming);

                    ClientModel.getInstance().addPointsToPlayerByName(userClaiming, numPoints);

                }
            }
        }
    }


    private void joinPlayers(ArrayList<String> joinedList)
    {
        if(joinedList != null)
        {
            if(joinedList.size() != 0)
            {
                String currentUser = ClientModel.getInstance().getUser().getUserName();
                ClientModel model = ClientModel.getInstance();
                for (int i = 0; i < joinedList.size(); i += 2) {
                    String username = joinedList.get(i);
                    int gameNum = Integer.parseInt(joinedList.get(i + 1));

                    Player player = new Player();
                    player.setName(username);
                    player.setAuthToken(username);
                    player.setColor("blue");

                    Game game = model.getGameByNumber(gameNum);
                    model.addPlayerToGame(game, player);
                    if (username.equals(currentUser))
                    {
                        SetActiveGameService service = new SetActiveGameService();
                        service.setActiveGame(game);
                        model.setMainPlayer(player);
                    }
                }
            }
        }
    }


    private void addGames(ArrayList<Game> gameList)
    {
        if(gameList != null)
        {
            if(gameList.size() != 0)
            {
                SetGamelistService listService = new SetGamelistService();
                for (int i = 0; i < gameList.size(); i++) {
                    listService.addGame(gameList.get(i));
                }
            }
        }
    }

    private void updateChat(ArrayList<Message> chatHistory)
    {
        if(chatHistory != null)
        {
            if (chatHistory.size() != 0)
            {
                ClientModel model = ClientModel.getInstance();
                for (Message message : chatHistory)
                {
                    model.addMessageToChat(message);
                }
            }
        }
    }

    private void startGame(ArrayList<String> gameStarted)
    {
        if(gameStarted != null)
        {
            if(gameStarted.size() != 0)
            {
                ArrayList<Player> playerList = ClientModel.getInstance().getActiveGame().getPlayers();
                for (int i = 0; i < gameStarted.size(); i += 2) {
                    for (int j = 0; j < playerList.size(); j++) {
                        if (playerList.get(j).getName().equals(gameStarted.get(i))) {
                            playerList.get(j).setColor(gameStarted.get(i + 1));
                        }
                    }
                }
            }
        }
    }

    public PollCommand(PollResponse response)
    {
        this.response = response;
    }
}
