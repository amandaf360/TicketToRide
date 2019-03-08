package commands;

import responses.BaseResponse;
import responses.DrawDestResponse;
import services.DrawDestService;

public class DrawDestCommand implements ICommand
{
    private int numCards;
    private String username;

    public DrawDestCommand(int numCards, String username) {
        this.numCards = numCards;
        this.username = username;
    }

    public BaseResponse execute()
    {
        DrawDestService service = new DrawDestService();
        DrawDestResponse response = service.drawCards(numCards, username);
        return null;
    }
}
