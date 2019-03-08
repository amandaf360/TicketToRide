package commands;

import responses.BaseResponse;
import services.DiscardDestService;

public class DiscardDestCommand implements ICommand
{

    private String cityOne;
    private String cityTwo;
    private int length;
    private String username;

    public DiscardDestCommand(String cityOne, String cityTwo, int length, String username)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
        this.username = username;
    }

    public BaseResponse execute()
    {
        DiscardDestService service = new DiscardDestService(cityOne, cityTwo, length, username);
        service.discardCard();
        return null;
    }
}
