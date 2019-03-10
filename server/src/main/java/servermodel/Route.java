package servermodel;



import java.util.StringTokenizer;

public class Route
{
    String cityOne;
    String cityTwo;
    String color;
    int length;
    Player claimedBy;


    public Route(String cityOne, String cityTwo, String color, int length)
    {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.color = color;
        this.length = length;
        claimedBy = null;
    }

    public Route(int bad, int dontUse, int almost, int length, String colorOne, String colorTwo, String city)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(city, "-");
        String cityOne = stringTokenizer.nextToken();
        String cityTwo = stringTokenizer.nextToken();
        cityOne = stringLogic(cityOne);
        cityTwo = stringLogic(cityTwo);

        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.color = colorOne;
        this.length = length;
        claimedBy = null;

    }

    private String stringLogic(String cityString)
    {
        if( !Character.isLowerCase( cityString.charAt(1) ) )
        {
            return cityString;
        }

        StringBuilder sb = new StringBuilder();
        char[] charArrayOfOriginal = cityString.toCharArray();
        for(int i = 0 ; i < charArrayOfOriginal.length ; i++)
        {
            if (!Character.isLowerCase(charArrayOfOriginal[i]))
            {
                sb.append(' ');
                sb.append(charArrayOfOriginal[i]);
            }
            else
            {
                sb.append(charArrayOfOriginal[i]);
            }
        }
        return sb.toString();
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

    public boolean isClaimed()
    {
        if (claimedBy == null)
        {
            return false;
        }
        return true;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public boolean almostEquals(Route route)
    {
        if(this.getCityOne().equals(route.getCityOne()) &&
                this.getCityTwo().equals(route.getCityTwo()) &&
                this.getCityTwo().equals(route.getColor()) )
        {
            return true;
        }
        return false;
    }
}

