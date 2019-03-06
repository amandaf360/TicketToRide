package services;

import ClientModel.ClientModel;

public class SetMessageService
{
    private ClientModel model;

    public SetMessageService()
    {
        model = ClientModel.getInstance();
    }

    public void setMessage(String message)
    {
        model.setMessage(message);
    }
}
