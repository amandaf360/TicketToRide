package commands;


import responses.BaseResponse;
import responses.ClaimRouteResponse;
import services.ClaimRouteService;

public class ClaimRouteCommand implements ICommand
{

    private int index;
    private String name;

    public ClaimRouteCommand(int index, String name)
    {
        this.index = index;
        this.name = name;
    }

    public BaseResponse execute()
    {
        ClaimRouteService service = new ClaimRouteService();
        ClaimRouteResponse response = service.claimRoute(index, name);
        return response;
    }
}
