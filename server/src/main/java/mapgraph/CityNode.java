package mapgraph;

import java.util.ArrayList;

public class CityNode
{
    private String name;
    private ArrayList<RouteNode> adjacentRoutes;

    public CityNode(String name)
    {
        this.name = name;
    }

    public void addRoute(RouteNode route)
    {
        adjacentRoutes.add(route);
    }

}
