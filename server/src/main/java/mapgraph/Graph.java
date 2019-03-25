package mapgraph;

import java.util.ArrayList;

public class Graph
{
    private ArrayList<CityNode> cityList;
    private ArrayList<RouteNode> routeList;

    public Graph()
    {
        cityList = new ArrayList<>();
        cityList.add(new CityNode("Vancouver"));//0
        cityList.add(new CityNode("Seattle"));//1
        cityList.add(new CityNode("Portland"));//2
        cityList.add(new CityNode("San Francisco"));//3
        cityList.add(new CityNode("Los Angeles"));//4
        cityList.add(new CityNode("Calgary"));//5
        cityList.add(new CityNode("Helena"));//6
        cityList.add(new CityNode("Salt Lake City"));//7
        cityList.add(new CityNode("Las Vegas"));//8
        cityList.add(new CityNode("Phoenix"));//9
        cityList.add(new CityNode("Denver"));//10
        cityList.add(new CityNode("Santa Fe"));//11
        cityList.add(new CityNode("El Paso"));//12
        cityList.add(new CityNode("Winnipeg"));//13
        cityList.add(new CityNode("Duluth"));//14
        cityList.add(new CityNode("Omaha"));//15
        cityList.add(new CityNode("Kansas City"));//16
        cityList.add(new CityNode("Oklahoma City"));//17
        cityList.add(new CityNode("Dallas"));//18
        cityList.add(new CityNode("Houston"));//19
        cityList.add(new CityNode("Sault St. Marie"));//20
        cityList.add(new CityNode("Toronto"));//21
        cityList.add(new CityNode("Chicago"));//22
        cityList.add(new CityNode("Saint Louis"));//23
        cityList.add(new CityNode("Little Rock"));//24
        cityList.add(new CityNode("New Orleans"));//25
        cityList.add(new CityNode("Montreal"));//26
        cityList.add(new CityNode("Toronto"));//27
        cityList.add(new CityNode("Pittsburgh"));//28
        cityList.add(new CityNode("Nashville"));//29
        cityList.add(new CityNode("Atlanta"));//30
        cityList.add(new CityNode("Charleston"));//31
        cityList.add(new CityNode("Miami"));//32
        cityList.add(new CityNode("Boston"));//33
        cityList.add(new CityNode("New York"));//34
        cityList.add(new CityNode("Washington"));//35
        cityList.add(new CityNode("Raleigh"));//36


    }

    public boolean completedRoute(String username, String cityOne, String cityTwo)
    {
        return false;
    }


}
