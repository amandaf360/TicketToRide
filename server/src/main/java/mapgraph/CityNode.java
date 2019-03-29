package mapgraph;

import java.util.ArrayList;

public class CityNode
{
    private String name;
    private boolean visited;
    private ArrayList<RouteNode> adjacentRoutes;

    public CityNode(String name)
    {
        this.name = name;

    }

    public void addRoute(RouteNode route)
    {
        adjacentRoutes.add(route);
    }

    public String getName()
    {
        return name;
    }

    public boolean traverseRoutes(String username, String cityTwo)
    {
        if(name.equals(cityTwo))
        {
            return true;
        }

        visited = true;
        for(RouteNode route: adjacentRoutes)
        {
            if(route.getOwner().equals(username) && !route.beenVisited())
            {
                boolean foundCityTwo = route.nextCity(username, name, cityTwo);
                if(foundCityTwo)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean beenVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }
}
