package mapgraph;

public class RouteNode
{
    private CityNode cityOne;
    private CityNode cityTwo;
    private String owner;

    public RouteNode()
    {

    }

    public RouteNode(CityNode cityOne, CityNode cityTwo, String owner)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.owner = owner;
    }

    public CityNode getCityOne()
    {
        return cityOne;
    }

    public void setCityOne(CityNode cityOne)
    {
        this.cityOne = cityOne;
    }

    public CityNode getCityTwo() {
        return cityTwo;
    }

    public void setCityTwo(CityNode cityTwo) {
        this.cityTwo = cityTwo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
