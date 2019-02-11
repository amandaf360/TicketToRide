package services;

import ThomasStuff.ClientModel;

public class AddMessageService
{
    private ClientModel model;

    public AddMessageService()
    {
        model = ClientModel.getInstance();
    }

    public void addMessage(String message)
    {
        model.setMessage(message);
    }
}
