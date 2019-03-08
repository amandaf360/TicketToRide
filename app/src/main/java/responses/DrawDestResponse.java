package responses;

import java.util.ArrayList;

import ClientModel.DestinationCards;

public class DrawDestResponse
{
    private ArrayList<DestinationCards> cardsDrawn;

    public DrawDestResponse(ArrayList<DestinationCards> cardsDrawn)
    {
        this.cardsDrawn = cardsDrawn;
    }

    public ArrayList<DestinationCards> getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(ArrayList<DestinationCards> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }
}
