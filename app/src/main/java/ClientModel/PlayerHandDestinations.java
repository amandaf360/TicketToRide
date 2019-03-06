package ClientModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandDestinations
{
    private List<DestinationCards> cardList;            // p.s. it's an array list

    public PlayerHandDestinations()
    {
        cardList = new ArrayList<>();
    }

    public PlayerHandDestinations(List<DestinationCards> cardList)
    {
        this.cardList = cardList;
    }

}
