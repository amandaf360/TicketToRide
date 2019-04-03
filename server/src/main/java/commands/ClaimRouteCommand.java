package commands;


import java.util.List;

import responses.BaseResponse;
import responses.ClaimRouteResponse;
import services.ClaimRouteService;

public class ClaimRouteCommand implements ICommand
{

    private int index;
    private String name;
    private List<String> cards;
    private String authToken;

    public ClaimRouteCommand(int index, String name, List<String> cards, String authToken)
    {
        this.index = index;
        this.name = name;
        this.cards = cards;
        this.authToken = authToken;
    }

    public BaseResponse execute()
    {
        ClaimRouteService service = new ClaimRouteService();
        ClaimRouteResponse response = service.claimRoute(index, name, cards, authToken);
        return response;
    }
}
