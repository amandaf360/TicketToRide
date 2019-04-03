package commands;

import responses.BaseResponse;
import responses.DrawDestResponse;
import services.DrawDestService;

public class DrawDestCommand implements ICommand
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
        return response;
    }
}
