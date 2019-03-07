package services;
import ClientModel.ClientModel;
import ClientModel.User;

public class SetUserService
{
    private ClientModel model;

    public SetUserService()
    {
        model = ClientModel.getInstance();
    }

    public void setUser(String username)
    {
        User newUser = new User();
        newUser.setUserName(username);
        model.setUser(newUser);
    }
}
