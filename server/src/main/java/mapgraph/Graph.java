package mapgraph;

import java.util.ArrayList;

import servermodel.Route;

public class Graph
{
    private ArrayList<CityNode> cityList;
    private ArrayList<RouteNode> routeList;

    public Graph()
    {
        cityList = new ArrayList<>();
        routeList = new ArrayList<>();
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
        cityList.add(new CityNode("IGNORETHIS"));//27
        cityList.add(new CityNode("Pittsburgh"));//28
        cityList.add(new CityNode("Nashville"));//29
        cityList.add(new CityNode("Atlanta"));//30
        cityList.add(new CityNode("Charleston"));//31
        cityList.add(new CityNode("Miami"));//32
        cityList.add(new CityNode("Boston"));//33
        cityList.add(new CityNode("New York"));//34
        cityList.add(new CityNode("Washington"));//35
        cityList.add(new CityNode("Raleigh"));//36

        routeList.add(new RouteNode(cityList.get(6), cityList.get(15), ""));//0
        cityList.get(6).addRoute(routeList.get(0));
        cityList.get(15).addRoute(routeList.get(0));

        routeList.add(new RouteNode(cityList.get(7), cityList.get(10), ""));//1
        cityList.get(7).addRoute(routeList.get(1));
        cityList.get(10).addRoute(routeList.get(1));

        routeList.add(new RouteNode(cityList.get(7), cityList.get(10), ""));//2
        cityList.get(7).addRoute(routeList.get(2));
        cityList.get(10).addRoute(routeList.get(2));

        routeList.add(new RouteNode(cityList.get(10), cityList.get(17), ""));//3
        cityList.get(10).addRoute(routeList.get(3));
        cityList.get(17).addRoute(routeList.get(3));

        routeList.add(new RouteNode(cityList.get(12), cityList.get(18), ""));//4
        cityList.get(12).addRoute(routeList.get(4));
        cityList.get(18).addRoute(routeList.get(4));

        routeList.add(new RouteNode(cityList.get(25), cityList.get(32), ""));//5
        cityList.get(25).addRoute(routeList.get(5));
        cityList.get(32).addRoute(routeList.get(5));

        routeList.add(new RouteNode(cityList.get(14), cityList.get(22), ""));//6
        cityList.get(14).addRoute(routeList.get(6));
        cityList.get(22).addRoute(routeList.get(6));

        routeList.add(new RouteNode(cityList.get(33), cityList.get(34), ""));//7
        cityList.get(33).addRoute(routeList.get(7));
        cityList.get(34).addRoute(routeList.get(7));

        routeList.add(new RouteNode(cityList.get(33), cityList.get(34), ""));//8
        cityList.get(33).addRoute(routeList.get(8));
        cityList.get(34).addRoute(routeList.get(8));

        routeList.add(new RouteNode(cityList.get(1), cityList.get(6), ""));//9
        cityList.get(1).addRoute(routeList.get(9));
        cityList.get(6).addRoute(routeList.get(9));

        routeList.add(new RouteNode(cityList.get(3), cityList.get(4), ""));//10
        cityList.get(3).addRoute(routeList.get(10));
        cityList.get(4).addRoute(routeList.get(10));

        routeList.add(new RouteNode(cityList.get(3), cityList.get(4), ""));//11
        cityList.get(3).addRoute(routeList.get(11));
        cityList.get(4).addRoute(routeList.get(11));

        routeList.add(new RouteNode(cityList.get(12), cityList.get(17), ""));//12
        cityList.get(12).addRoute(routeList.get(12));
        cityList.get(17).addRoute(routeList.get(12));

        routeList.add(new RouteNode(cityList.get(25), cityList.get(30), ""));//13
        cityList.get(25).addRoute(routeList.get(13));
        cityList.get(30).addRoute(routeList.get(13));

        routeList.add(new RouteNode(cityList.get(25), cityList.get(30), ""));//14
        cityList.get(25).addRoute(routeList.get(14));
        cityList.get(30).addRoute(routeList.get(14));

        routeList.add(new RouteNode(cityList.get(29), cityList.get(28), ""));//15
        cityList.get(29).addRoute(routeList.get(15));
        cityList.get(28).addRoute(routeList.get(15));

        routeList.add(new RouteNode(cityList.get(2), cityList.get(7), ""));//16
        cityList.get(2).addRoute(routeList.get(16));
        cityList.get(7).addRoute(routeList.get(16));

        routeList.add(new RouteNode(cityList.get(6), cityList.get(13), ""));//17
        cityList.get(6).addRoute(routeList.get(17));
        cityList.get(13).addRoute(routeList.get(17));

        routeList.add(new RouteNode(cityList.get(11), cityList.get(17), ""));//18
        cityList.get(11).addRoute(routeList.get(18));
        cityList.get(17).addRoute(routeList.get(18));

        routeList.add(new RouteNode(cityList.get(15), cityList.get(22), ""));//19
        cityList.get(15).addRoute(routeList.get(19));
        cityList.get(22).addRoute(routeList.get(19));

        routeList.add(new RouteNode(cityList.get(16), cityList.get(23), ""));//20
        cityList.get(16).addRoute(routeList.get(20));
        cityList.get(23).addRoute(routeList.get(20));

        routeList.add(new RouteNode(cityList.get(16), cityList.get(23), ""));//21
        cityList.get(16).addRoute(routeList.get(21));
        cityList.get(23).addRoute(routeList.get(21));

        routeList.add(new RouteNode(cityList.get(30), cityList.get(32), ""));//22
        cityList.get(30).addRoute(routeList.get(22));
        cityList.get(32).addRoute(routeList.get(22));

        routeList.add(new RouteNode(cityList.get(34), cityList.get(26), ""));//23
        cityList.get(34).addRoute(routeList.get(23));
        cityList.get(26).addRoute(routeList.get(23));

        routeList.add(new RouteNode(cityList.get(2), cityList.get(3), ""));//24
        cityList.get(2).addRoute(routeList.get(24));
        cityList.get(3).addRoute(routeList.get(24));

        routeList.add(new RouteNode(cityList.get(2), cityList.get(3), ""));//25
        cityList.get(2).addRoute(routeList.get(25));
        cityList.get(3).addRoute(routeList.get(25));

        routeList.add(new RouteNode(cityList.get(6), cityList.get(10), ""));//26
        cityList.get(6).addRoute(routeList.get(26));
        cityList.get(10).addRoute(routeList.get(26));

        routeList.add(new RouteNode(cityList.get(12), cityList.get(19), ""));//27
        cityList.get(12).addRoute(routeList.get(27));
        cityList.get(19).addRoute(routeList.get(27));

        routeList.add(new RouteNode(cityList.get(23), cityList.get(22), ""));//28
        cityList.get(23).addRoute(routeList.get(28));
        cityList.get(22).addRoute(routeList.get(28));

        routeList.add(new RouteNode(cityList.get(23), cityList.get(22), ""));//29
        cityList.get(23).addRoute(routeList.get(29));
        cityList.get(22).addRoute(routeList.get(29));

        routeList.add(new RouteNode(cityList.get(23), cityList.get(28), ""));//30
        cityList.get(23).addRoute(routeList.get(30));
        cityList.get(28).addRoute(routeList.get(30));

        routeList.add(new RouteNode(cityList.get(28), cityList.get(34), ""));//31
        cityList.get(28).addRoute(routeList.get(31));
        cityList.get(34).addRoute(routeList.get(31));

        routeList.add(new RouteNode(cityList.get(28), cityList.get(34), ""));//32
        cityList.get(28).addRoute(routeList.get(32));
        cityList.get(34).addRoute(routeList.get(32));

        routeList.add(new RouteNode(cityList.get(24), cityList.get(25), ""));//33
        cityList.get(24).addRoute(routeList.get(33));
        cityList.get(25).addRoute(routeList.get(33));

        routeList.add(new RouteNode(cityList.get(6), cityList.get(14), ""));//34
        cityList.get(6).addRoute(routeList.get(34));
        cityList.get(14).addRoute(routeList.get(34));

        routeList.add(new RouteNode(cityList.get(3), cityList.get(7), ""));//35
        cityList.get(3).addRoute(routeList.get(35));
        cityList.get(7).addRoute(routeList.get(35));

        routeList.add(new RouteNode(cityList.get(3), cityList.get(7), ""));//36
        cityList.get(3).addRoute(routeList.get(36));
        cityList.get(7).addRoute(routeList.get(36));

        routeList.add(new RouteNode(cityList.get(8), cityList.get(7), ""));//37
        cityList.get(8).addRoute(routeList.get(37));
        cityList.get(7).addRoute(routeList.get(37));

        routeList.add(new RouteNode(cityList.get(10), cityList.get(16), ""));//38
        cityList.get(10).addRoute(routeList.get(38));
        cityList.get(16).addRoute(routeList.get(38));

        routeList.add(new RouteNode(cityList.get(10), cityList.get(16), ""));//39
        cityList.get(10).addRoute(routeList.get(39));
        cityList.get(16).addRoute(routeList.get(39));

        routeList.add(new RouteNode(cityList.get(22), cityList.get(28), ""));//40
        cityList.get(22).addRoute(routeList.get(40));
        cityList.get(28).addRoute(routeList.get(40));

        routeList.add(new RouteNode(cityList.get(22), cityList.get(28), ""));//41
        cityList.get(22).addRoute(routeList.get(41));
        cityList.get(28).addRoute(routeList.get(41));

        routeList.add(new RouteNode(cityList.get(34), cityList.get(35), ""));//42
        cityList.get(34).addRoute(routeList.get(42));
        cityList.get(35).addRoute(routeList.get(42));

        routeList.add(new RouteNode(cityList.get(34), cityList.get(35), ""));//43
        cityList.get(34).addRoute(routeList.get(43));
        cityList.get(35).addRoute(routeList.get(43));

        routeList.add(new RouteNode(cityList.get(6), cityList.get(7), ""));//44
        cityList.get(6).addRoute(routeList.get(44));
        cityList.get(7).addRoute(routeList.get(44));

        routeList.add(new RouteNode(cityList.get(10), cityList.get(15), ""));//45
        cityList.get(10).addRoute(routeList.get(45));
        cityList.get(15).addRoute(routeList.get(45));

        routeList.add(new RouteNode(cityList.get(14), cityList.get(21), ""));//46
        cityList.get(14).addRoute(routeList.get(46));
        cityList.get(21).addRoute(routeList.get(46));

        routeList.add(new RouteNode(cityList.get(31), cityList.get(32), ""));//47
        cityList.get(31).addRoute(routeList.get(47));
        cityList.get(32).addRoute(routeList.get(47));

        routeList.add(new RouteNode(cityList.get(5), cityList.get(13), ""));//48
        cityList.get(5).addRoute(routeList.get(48));
        cityList.get(13).addRoute(routeList.get(48));

        routeList.add(new RouteNode(cityList.get(10), cityList.get(9), ""));//49
        cityList.get(10).addRoute(routeList.get(49));
        cityList.get(9).addRoute(routeList.get(49));

        routeList.add(new RouteNode(cityList.get(21), cityList.get(22), ""));//50
        cityList.get(21).addRoute(routeList.get(50));
        cityList.get(22).addRoute(routeList.get(50));

        routeList.add(new RouteNode(cityList.get(24), cityList.get(29), ""));//51
        cityList.get(24).addRoute(routeList.get(51));
        cityList.get(29).addRoute(routeList.get(51));

        routeList.add(new RouteNode(cityList.get(13), cityList.get(14), ""));//52
        cityList.get(13).addRoute(routeList.get(52));
        cityList.get(14).addRoute(routeList.get(52));

        routeList.add(new RouteNode(cityList.get(20), cityList.get(26), ""));//53
        cityList.get(20).addRoute(routeList.get(53));
        cityList.get(26).addRoute(routeList.get(53));

        routeList.add(new RouteNode(cityList.get(29), cityList.get(36), ""));//54
        cityList.get(29).addRoute(routeList.get(54));
        cityList.get(36).addRoute(routeList.get(54));

        routeList.add(new RouteNode(cityList.get(4), cityList.get(12), ""));//55
        cityList.get(4).addRoute(routeList.get(55));
        cityList.get(12).addRoute(routeList.get(55));

        routeList.add(new RouteNode(cityList.get(13), cityList.get(20), ""));//56
        cityList.get(13).addRoute(routeList.get(56));
        cityList.get(20).addRoute(routeList.get(56));

        routeList.add(new RouteNode(cityList.get(0), cityList.get(1), ""));//57
        cityList.get(0).addRoute(routeList.get(57));
        cityList.get(1).addRoute(routeList.get(57));

        routeList.add(new RouteNode(cityList.get(0), cityList.get(1), ""));//58
        cityList.get(0).addRoute(routeList.get(58));
        cityList.get(1).addRoute(routeList.get(58));

        routeList.add(new RouteNode(cityList.get(1), cityList.get(2), ""));//59
        cityList.get(1).addRoute(routeList.get(59));
        cityList.get(2).addRoute(routeList.get(59));

        routeList.add(new RouteNode(cityList.get(1), cityList.get(2), ""));//60
        cityList.get(1).addRoute(routeList.get(60));
        cityList.get(2).addRoute(routeList.get(60));

        routeList.add(new RouteNode(cityList.get(0), cityList.get(5), ""));//61
        cityList.get(0).addRoute(routeList.get(61));
        cityList.get(5).addRoute(routeList.get(61));

        routeList.add(new RouteNode(cityList.get(1), cityList.get(5), ""));//62
        cityList.get(1).addRoute(routeList.get(62));
        cityList.get(5).addRoute(routeList.get(62));

        routeList.add(new RouteNode(cityList.get(5), cityList.get(6), ""));//63
        cityList.get(5).addRoute(routeList.get(63));
        cityList.get(6).addRoute(routeList.get(63));

        routeList.add(new RouteNode(cityList.get(20), cityList.get(14), ""));//64
        cityList.get(20).addRoute(routeList.get(64));
        cityList.get(14).addRoute(routeList.get(64));

        routeList.add(new RouteNode(cityList.get(20), cityList.get(21), ""));//65
        cityList.get(20).addRoute(routeList.get(65));
        cityList.get(21).addRoute(routeList.get(65));

        routeList.add(new RouteNode(cityList.get(21), cityList.get(26), ""));//66
        cityList.get(21).addRoute(routeList.get(66));
        cityList.get(26).addRoute(routeList.get(66));

        routeList.add(new RouteNode(cityList.get(26), cityList.get(33), ""));//67
        cityList.get(26).addRoute(routeList.get(67));
        cityList.get(33).addRoute(routeList.get(67));

        routeList.add(new RouteNode(cityList.get(21), cityList.get(28), ""));//68
        cityList.get(21).addRoute(routeList.get(68));
        cityList.get(28).addRoute(routeList.get(68));

        routeList.add(new RouteNode(cityList.get(35), cityList.get(36), ""));//69
        cityList.get(35).addRoute(routeList.get(69));
        cityList.get(36).addRoute(routeList.get(69));

        routeList.add(new RouteNode(cityList.get(35), cityList.get(36), ""));//70
        cityList.get(35).addRoute(routeList.get(70));
        cityList.get(36).addRoute(routeList.get(70));

        routeList.add(new RouteNode(cityList.get(28), cityList.get(36), ""));//71
        cityList.get(28).addRoute(routeList.get(71));
        cityList.get(36).addRoute(routeList.get(71));

        routeList.add(new RouteNode(cityList.get(28), cityList.get(35), ""));//72
        cityList.get(28).addRoute(routeList.get(72));
        cityList.get(35).addRoute(routeList.get(72));

        routeList.add(new RouteNode(cityList.get(30), cityList.get(36), ""));//73
        cityList.get(30).addRoute(routeList.get(73));
        cityList.get(36).addRoute(routeList.get(73));

        routeList.add(new RouteNode(cityList.get(30), cityList.get(36), ""));//74
        cityList.get(30).addRoute(routeList.get(74));
        cityList.get(36).addRoute(routeList.get(74));

        routeList.add(new RouteNode(cityList.get(36), cityList.get(31), ""));//75
        cityList.get(36).addRoute(routeList.get(75));
        cityList.get(31).addRoute(routeList.get(75));

        routeList.add(new RouteNode(cityList.get(30), cityList.get(31), ""));//76
        cityList.get(30).addRoute(routeList.get(76));
        cityList.get(31).addRoute(routeList.get(76));

        routeList.add(new RouteNode(cityList.get(30), cityList.get(29), ""));//77
        cityList.get(30).addRoute(routeList.get(77));
        cityList.get(29).addRoute(routeList.get(77));

        routeList.add(new RouteNode(cityList.get(23), cityList.get(29), ""));//78
        cityList.get(23).addRoute(routeList.get(78));
        cityList.get(29).addRoute(routeList.get(78));

        routeList.add(new RouteNode(cityList.get(24), cityList.get(23), ""));//79
        cityList.get(24).addRoute(routeList.get(79));
        cityList.get(23).addRoute(routeList.get(79));

        routeList.add(new RouteNode(cityList.get(24), cityList.get(17), ""));//80
        cityList.get(24).addRoute(routeList.get(80));
        cityList.get(17).addRoute(routeList.get(80));

        routeList.add(new RouteNode(cityList.get(18), cityList.get(24), ""));//81
        cityList.get(18).addRoute(routeList.get(81));
        cityList.get(24).addRoute(routeList.get(81));

        routeList.add(new RouteNode(cityList.get(18), cityList.get(19), ""));//82
        cityList.get(18).addRoute(routeList.get(82));
        cityList.get(19).addRoute(routeList.get(82));

        routeList.add(new RouteNode(cityList.get(18), cityList.get(19), ""));//83
        cityList.get(18).addRoute(routeList.get(83));
        cityList.get(19).addRoute(routeList.get(83));

        routeList.add(new RouteNode(cityList.get(19), cityList.get(25), ""));//84
        cityList.get(19).addRoute(routeList.get(84));
        cityList.get(25).addRoute(routeList.get(84));

        routeList.add(new RouteNode(cityList.get(18), cityList.get(17), ""));//85
        cityList.get(18).addRoute(routeList.get(85));
        cityList.get(17).addRoute(routeList.get(85));

        routeList.add(new RouteNode(cityList.get(18), cityList.get(17), ""));//86
        cityList.get(18).addRoute(routeList.get(86));
        cityList.get(17).addRoute(routeList.get(86));

        routeList.add(new RouteNode(cityList.get(17), cityList.get(16), ""));//87
        cityList.get(17).addRoute(routeList.get(87));
        cityList.get(16).addRoute(routeList.get(87));

        routeList.add(new RouteNode(cityList.get(16), cityList.get(15), ""));//88
        cityList.get(16).addRoute(routeList.get(88));
        cityList.get(15).addRoute(routeList.get(88));

        routeList.add(new RouteNode(cityList.get(15), cityList.get(14), ""));//89
        cityList.get(15).addRoute(routeList.get(89));
        cityList.get(14).addRoute(routeList.get(89));

        routeList.add(new RouteNode(cityList.get(17), cityList.get(16), ""));//90
        cityList.get(17).addRoute(routeList.get(90));
        cityList.get(16).addRoute(routeList.get(90));

        routeList.add(new RouteNode(cityList.get(16), cityList.get(15), ""));//91
        cityList.get(16).addRoute(routeList.get(91));
        cityList.get(15).addRoute(routeList.get(91));

        routeList.add(new RouteNode(cityList.get(15), cityList.get(14), ""));//92
        cityList.get(15).addRoute(routeList.get(92));
        cityList.get(14).addRoute(routeList.get(92));

        routeList.add(new RouteNode(cityList.get(11), cityList.get(10), ""));//93
        cityList.get(11).addRoute(routeList.get(93));
        cityList.get(10).addRoute(routeList.get(93));

        routeList.add(new RouteNode(cityList.get(11), cityList.get(12), ""));//94
        cityList.get(11).addRoute(routeList.get(94));
        cityList.get(12).addRoute(routeList.get(94));

        routeList.add(new RouteNode(cityList.get(11), cityList.get(9), ""));//95
        cityList.get(11).addRoute(routeList.get(95));
        cityList.get(9).addRoute(routeList.get(95));

        routeList.add(new RouteNode(cityList.get(9), cityList.get(12), ""));//96
        cityList.get(9).addRoute(routeList.get(96));
        cityList.get(12).addRoute(routeList.get(96));

        routeList.add(new RouteNode(cityList.get(4), cityList.get(9), ""));//97
        cityList.get(4).addRoute(routeList.get(97));
        cityList.get(9).addRoute(routeList.get(97));

        routeList.add(new RouteNode(cityList.get(4), cityList.get(8), ""));//98
        cityList.get(4).addRoute(routeList.get(98));
        cityList.get(8).addRoute(routeList.get(98));

    }

    public boolean completedRoute(String username, String cityOne, String cityTwo)
    {
        CityNode firstCity = null;
        for(CityNode city: cityList)
        {
            if(city.getName().equals(cityOne))
            {
                firstCity = city;
                break;
            }
        }
        if(firstCity == null)
        {
            System.out.println("City " + cityOne + " not found");
            return false;
        }

        boolean foundIt = firstCity.traverseRoutes(username, cityTwo);
        setAllNotVisited();
        return foundIt;
    }

    public void setAllNotVisited()
    {
        for(CityNode city: cityList)
        {
            city.setVisited(false);
        }
        for(RouteNode route: routeList)
        {
            route.setVisited(false);
        }
    }

    public void claimRoute(String username, int index)
    {
        routeList.get(index).setOwner(username);
    }


}
