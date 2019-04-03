package services;

import java.util.ArrayList;

import responses.DrawTrainResponse;
import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.DecksStateData;
import servermodel.FaceUpCards;
import servermodel.ModelRoot;
import servermodel.TrainCarCard;
import servermodel.TrainCarDeck;

public class DrawTrainService
{
    private String username;
    private int index;
    private String authToken;

    public DrawTrainService(String username, int index, String authToken)
    {
        this.username = username;
        this.index = index;
        this.authToken = authToken;
    }

    public DrawTrainResponse draw()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByAuthToken(authToken);
        TrainCarCard cardDrawn;
        TrainCarDeck deck = game.getTrainDeck();

        if(index == -1)//drawing from the deck
        {
            cardDrawn = deck.draw();
        }
        else//drawing from the face up pile.
        {
            FaceUpCards faceUpCards = game.getFaceUpCards();
            cardDrawn = faceUpCards.draw(index, deck);
        }
        ArrayList<String> allAuthTokens = game.getAllAuthTokens();
        DecksStateData data = new DecksStateData(game);
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for(String token: allAuthTokens)
        {
            manager.setDeckState(data, token);
            if(!token.equals(authToken))
            {
                manager.addTrainCardDrawn(authToken, token);
            }
        }
        game.getPlayerByUsername(username).addTrainCarCard(cardDrawn);
        DrawTrainResponse response = new DrawTrainResponse(cardDrawn.getColor());

        return response;
    }
}
