package servermodel;

public class Message
{
    private String color;
    private String message;

    public Message(String color, String message)
    {
        this.color = color;
        this.message = message;
    }


    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
