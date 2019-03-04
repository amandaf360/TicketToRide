package services;

public class SendMessageService
{
    private String message;
    private String user;
    private int gameNum;

    public SendMessageService(String message, String user, int gameNum)
    {
        this.message = message;
        this.user = user;
        this.gameNum = gameNum;
    }
}
