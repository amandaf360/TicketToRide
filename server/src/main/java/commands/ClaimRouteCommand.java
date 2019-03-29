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

    public ClaimRouteCommand(int index, String name, List<String> cards)
    {
        this.index = index;
        this.name = name;
        this.cards = cards;
    }

    public BaseResponse execute()
    {
        ClaimRouteService service = new ClaimRouteService();
        ClaimRouteResponse response = service.claimRoute(index, name, cards);
        return response;
    }
}
