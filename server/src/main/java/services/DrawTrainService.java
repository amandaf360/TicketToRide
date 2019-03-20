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

    public DrawTrainService(String username, int index) {
        this.username = username;
        this.index = index;
    }

    public DrawTrainResponse draw()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(username);
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
        ArrayList<String> allUsernames = game.getAllUsernames();
        DecksStateData data = new DecksStateData(game);
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        for(String name: allUsernames)
        {
            manager.setDeckState(data, name);
            if(!name.equals(username))
            {
                manager.addTrainCardDrawn(username, name);
            }
        }
        game.getPlayerByUsername(username).addTrainCarCard(cardDrawn);
        DrawTrainResponse response = new DrawTrainResponse(cardDrawn.getColor());

        return response;
    }
}
