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

    public void setUser(String username, String authToken)
    {
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setAuthToken(authToken);
        model.setUser(newUser);
    }
}
