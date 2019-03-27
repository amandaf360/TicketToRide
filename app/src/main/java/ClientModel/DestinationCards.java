package ClientModel;

import java.util.StringTokenizer;

public class DestinationCards
{
    private String cityOne;
    private String cityTwo;
    private int points;

    public DestinationCards(String cityOne, String cityTwo, int points)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.points = points;
    }

    public DestinationCards(String toString)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(toString, "-");
        if(stringTokenizer.hasMoreElements())
        {
            this.cityOne = stringTokenizer.nextToken().trim();
        }
        if(stringTokenizer.hasMoreElements())
        {
            toString = stringTokenizer.nextToken().trim();
        }
        stringTokenizer = new StringTokenizer(toString, ":");

        if(stringTokenizer.hasMoreElements())
        {
            this.cityTwo = stringTokenizer.nextToken().trim();
        }
        if(stringTokenizer.hasMoreElements())
        {
            toString = stringTokenizer.nextToken().trim();
        }

        this.points = Integer.parseInt(toString);


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

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public String toString()
    {
        return cityOne + " - " + cityTwo + ":" + Integer.toString(points);
    }


}
