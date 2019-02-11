package servermodel;

public class Player
{
    private String color;
    private String authToken;
    private String name;

    public Player()
    {

    }

    public Player(Player player)
    {
        this.color = player.color;
        this.authToken = player.authToken;
        this.name = player.name;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
