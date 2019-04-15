package commands;


import java.io.Serializable;
import java.util.List;

import responses.BaseResponse;
import responses.ClaimRouteResponse;
import servermodel.ModelRoot;
import services.ClaimRouteService;

public class ClaimRouteCommand implements ICommand, Serializable
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
        int gameNum = ModelRoot.getModel().getGameByAuthToken(authToken).getGameNum();
        ModelRoot.getModel().addGameCommandToDataBase(gameNum, this);
        return response;
    }
}
