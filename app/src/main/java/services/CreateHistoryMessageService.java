package services;

import ClientModel.ClientModel;
import ClientModel.Message;
import ClientModel.Player;
import proxy.ServerProxy;

public class CreateHistoryMessageService
{
    public CreateHistoryMessageService()
    {}

    public void sendMessage(String string)
    {
        Player mainPlayer = ClientModel.getInstance().getMainPlayer();
        Message message = new Message(mainPlayer.getColor(), string);
        ServerProxy proxy = new ServerProxy();
        proxy.sendGameHistoryMessage(mainPlayer.getName(), message, mainPlayer.getAuthToken());
    }
}
