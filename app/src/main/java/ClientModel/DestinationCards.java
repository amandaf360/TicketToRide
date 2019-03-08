package ClientModel;

public class DestinationCards
{
    private String cityOne;
    private String cityTwo;
    private int length;

    public DestinationCards(String cityOne, String cityTwo, int length)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
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

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
}
