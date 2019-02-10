package services;
import ThomasStuff.ClientModel;
import ThomasStuff.User;

public class SetUserService
{
    ClientModel model;

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
