package ClientModel;

public class DestinationCards
{
    private String cityOne;
    private String cityTwo;
    private int points;

    public DestinationCards(String cityOne, String cityTwo, int points)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.points = points;
    }

    public String getCityOne()
    {
        return cityOne;
    }

    public void setCityOne(String cityOne)
    {
        this.cityOne = cityOne;
    }

    public String getCityTwo()
    {
        return cityTwo;
    }

    public void setCityTwo(String cityTwo)
    {
        this.cityTwo = cityTwo;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}
