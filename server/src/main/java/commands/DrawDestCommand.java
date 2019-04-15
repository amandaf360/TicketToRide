package commands;

import java.io.Serializable;

import responses.BaseResponse;
import responses.DrawDestResponse;
import servermodel.ModelRoot;
import services.DrawDestService;

public class DrawDestCommand implements ICommand, Serializable
{
    private int numCards;
    private String username;
    private String authToken;

    public DrawDestCommand(int numCards, String username, String authToken)
    {
        this.numCards = numCards;
        this.username = username;
        this.authToken = authToken;
    }

    public BaseResponse execute()
    {
        DrawDestService service = new DrawDestService();
        DrawDestResponse response = service.drawCards(numCards, username, authToken);


        int gameNum = ModelRoot.getModel().getGameByAuthToken(authToken).getGameNum();
        ModelRoot.getModel().addGameCommandToDataBase(gameNum, this);
        return response;
    }
}
