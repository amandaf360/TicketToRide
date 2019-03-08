package ClientModel;

public class Route
{
    String cityOne;
    String cityTwo;
    int length;
    Player claimedBy;

    public Route(String cityOne, String cityTwo, int length)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = length;
        claimedBy = null;
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

    public Player getClaimedBy()
    {
        return claimedBy;
    }

    public void setClaimedBy(Player claimedBy)
    {
        this.claimedBy = claimedBy;
    }
}
