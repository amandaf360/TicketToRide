package responses;

import java.util.ArrayList;

public class DrawTrainResponse extends BaseResponse
{
    String cardColor;

    public DrawTrainResponse(String cardColor) {
        this.cardColor = cardColor;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }
}
