package responses;

public class ClaimRouteResponse extends BaseResponse
{
    private int index;
    private String name;

    public ClaimRouteResponse(int index, String name)
    {
        this.index = index;
        this.name = name;
    }


    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }


}
