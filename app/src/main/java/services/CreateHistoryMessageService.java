package services;

import ClientModel.ClientModel;
import ClientModel.Message;

public class CreateHistoryMessageService
{
    Message message;
    public CreateHistoryMessageService(String string)
    {
        this.message = new Message(ClientModel.getInstance().getMainPlayer().getColor(), string);
    }
}
