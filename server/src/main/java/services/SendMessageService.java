package services;

import java.util.ArrayList;

import server.ClientCommandManager;
import servermodel.ActiveGame;
import servermodel.Message;
import servermodel.ModelRoot;

public class SendMessageService
{
    private String message;
    private String user;
    private String color;
    private int gameNum;

    public SendMessageService(String message, String user, String color, int gameNum)
    {
        this.message = message;
        this.user = user;
        this.color = color;
        this.gameNum = gameNum;
    }

    public void sendMessage()
    {
        ModelRoot root = ModelRoot.getModel();
        ActiveGame game = root.getGameByUser(user);
        game.addMessage(new Message(color, message));

        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        ArrayList<String> allUsers = game.getAllUsernames();
        for(String username : allUsers)
        {
            Message formedMessage = new Message(color, message);
            manager.addChatMessage(username, formedMessage);
        }

    }
}

