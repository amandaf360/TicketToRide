package commands;

import java.util.ArrayList;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;

public class ClaimRouteCommand implements ICommand
{
    int index;
    String name;
    int numCards;

    public ClaimRouteCommand(int index, String name)
    {
        this.index = index;
        this.name = name;
    }



    public void execute()
    {
        ClientModel model = ClientModel.getInstance();
        model.claimRouteByIndex(index, name);
    }
}
