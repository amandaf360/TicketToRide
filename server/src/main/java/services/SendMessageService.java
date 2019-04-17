package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.Message;
import servermodel.ModelRoot;

public class SendMessageService
{
    private String message;
    private String authToken;
    private String color;
    private int gameNum;

    public SendMessageService(String message, String authToken, String color, int gameNum)
    {
        this.message = message;
        this.authToken = authToken;
        this.color = color;
        this.gameNum = gameNum;
    }

    public void sendMessage()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByAuthToken(authToken);
        game.addMessage(new Message(color, message));


        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> allAuthTokens = game.getAllAuthTokens();
        for(String authToken : allAuthTokens)
        {
            Message formedMessage = new Message(color, message);
            manager.addChatMessage(authToken, formedMessage);
        }

    }
}

