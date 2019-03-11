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
        if(response != null)
        {
            if (response.getGamesCreated().size() != 0 || response.getGamesDeleted().size() != 0 ||
                    response.getPlayersJoined().size() != 0 || response.getPlayersLeft().size() != 0
                    || response.getChatHistory().size() != 0 || response.getGameStarted().size() != 0
                    || response.getGameStartInfo() != null || response.getDestinationCardsDrawn().size() != 0
                    || response.getDeckData() != null)
            {
                //ServerProxy proxy = new ServerProxy();
                //proxy.clearPoll(response.getUsername());
                joinPlayers(response.getPlayersJoined());
                addGames(response.getGamesCreated());
                startGame(response.getGameStarted());
                updateChat(response.getChatHistory());
                updateDestCardsDrawn(response.getDestinationCardsDrawn());
                if(response.getGameStartInfo() != null)
                {
                    int i = 0;
                }
                startMyGame(response.getGameStartInfo());
                updateDeckData(response.getDeckData());
            }
        }
        //updates players in a given game
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
            String mainPlayerName = model.getMainPlayer().getName();
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
        ClientModel model = ClientModel.getInstance();
        for(String user : usersDiscarded)
        {
            model.decrementPlayerDestCardNum(user);
        }
    }


    private void joinPlayers(ArrayList<String> joinedList)
    {
        String currentUser = ClientModel.getInstance().getUser().getUserName();
        ClientModel model = ClientModel.getInstance();
        for(int i = 0; i < joinedList.size(); i += 2)
        {
            String username = joinedList.get(i);
            int gameNum = Integer.parseInt(joinedList.get(i + 1));

            Player player = new Player();
            player.setName(username);
            player.setAuthToken(username);
            player.setColor("blue");

            Game game = model.getGameByNumber(gameNum);
            model.addPlayerToGame(game, player);
            if(username.equals(currentUser))
            {
                SetActiveGameService service = new SetActiveGameService();
                service.setActiveGame(game);
                model.setMainPlayer(player);
            }
        }
    }


    private void addGames(ArrayList<Game> gameList)
    {
        SetGamelistService listService = new SetGamelistService();
        for (int i = 0; i < gameList.size(); i++)
        {
            listService.addGame(gameList.get(i));
        }
    }

    private void updateChat(ArrayList<Message> chatHistory)
    {
        if(chatHistory.size() != 0)
        {
            ClientModel model = ClientModel.getInstance();
            for (Message message : chatHistory)
            {
                model.addMessageToChat(message);
            }
        }
    }

    private void startGame(ArrayList<String> gameStarted)
    {
        if(gameStarted != null && gameStarted.size() != 0)
        {
            ArrayList<Player> playerList = ClientModel.getInstance().getActiveGame().getPlayers();
            for(int i = 0; i < gameStarted.size(); i+=2)
            {
                for(int j = 0; j < playerList.size(); j++)
                {
                    if(playerList.get(j).getName().equals(gameStarted.get(i)))
                    {
                        playerList.get(j).setColor(gameStarted.get(i + 1));
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
