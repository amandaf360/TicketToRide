package commands;

import java.io.Serializable;

import responses.BaseResponse;
import services.DiscardDestService;

public class DiscardDestCommand implements ICommand, Serializable
{

    private String cityOne;
    private String cityTwo;
    private int length;
    private String username;
    private String authToken;

    public DiscardDestCommand(String cityOne, String cityTwo, int length, String username, String authToken)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
        this.username = username;
        this.authToken = authToken;
    }

    public BaseResponse execute()
    {
        DiscardDestService service = new DiscardDestService(cityOne, cityTwo, length, username, authToken);
        service.discardCard();
        return null;
    }
}
