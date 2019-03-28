package responses;

public class ClaimRouteResponse extends BaseResponse
{
    private int index;
    private String name;
    private int howMany;

    public ClaimRouteResponse(int index, String name, int howMany)
    {
        this.index = index;
        this.name = name;
        this.howMany = howMany;
    }


    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHowMany()
    {
        return howMany;
    }

    public void setHowMany(int howMany)
    {
        this.howMany = howMany;
    }
}
