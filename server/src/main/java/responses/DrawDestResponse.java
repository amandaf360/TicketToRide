package responses;

import java.util.ArrayList;

import servermodel.DestCard;

public class DrawDestResponse extends BaseResponse
{
    private ArrayList<DestCard> cardsDrawn;

    public DrawDestResponse(ArrayList<DestCard> cardsDrawn)
    {
        this.cardsDrawn = cardsDrawn;
    }

    public ArrayList<DestCard> getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(ArrayList<DestCard> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }
}
