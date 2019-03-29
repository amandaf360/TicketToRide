package mapgraph;

public class RouteNode
{
    private CityNode cityOne;
    private CityNode cityTwo;
    private String owner;
    private boolean visited;

    public RouteNode()
    {
        visited = false;
    }

    public RouteNode(CityNode cityOne, CityNode cityTwo, String owner)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.owner = owner;
        visited = false;
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

    public boolean beenVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public boolean nextCity(String username, String comingFrom, String lookingFor)
    {
        visited = true;
        if(cityOne.getName().equals(comingFrom))
        {
            if(!cityTwo.beenVisited())
            {
                return cityTwo.traverseRoutes(username, lookingFor);
            }
        }
        else
        {
            if(!cityOne.beenVisited())
            {
                return cityOne.traverseRoutes(username, lookingFor);
            }
        }
        return false;
    }
}
