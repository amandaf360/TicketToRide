package services;

import ClientModel.ClientModel;
import ClientModel.Message;

public class CreateChatMessageService
{
    Message message;
    public CreateChatMessageService(String string)
    {
        this.message = new Message(ClientModel.getInstance().getMainPlayer().getColor(), string);
    }
}
