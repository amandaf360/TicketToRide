package services;

import ClientModel.ClientModel;
import ClientModel.Message;
import proxy.ServerProxy;

public class CreateChatMessageService
{
    private Message message;
    public CreateChatMessageService(String string)
    {
        this.message = new Message(ClientModel.getInstance().getMainPlayer().getColor(), string);
    }

    public void sendMessage()
    {
        ClientModel model = ClientModel.getInstance();
        ServerProxy proxy = new ServerProxy();
        proxy.sendChatMessage(model.getUser().getAuthToken(), message, model.getGameNum(model.getActiveGame()));
    }
}
