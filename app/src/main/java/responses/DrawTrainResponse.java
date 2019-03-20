package responses;

public class DrawTrainResponse
{
    private String cardColor;

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
