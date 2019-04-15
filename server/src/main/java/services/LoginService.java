package services;

import java.util.List;

import responses.BaseResponse;
import responses.LoginResponse;
import server.ClientCommandManager;
import servermodel.ModelRoot;
import servermodel.User;
import java.util.UUID;

public class LoginService
{
    public BaseResponse login(String username, String password)
    {
        ModelRoot model = ModelRoot.getModel();
        List<User> userList = model.getUserList();
        boolean userExists = false;
        boolean passwordCorrect = false;

        for(int i = 0; i < userList.size(); i ++)
        {
            if(userList.get(i).getUsername().equals(username))
            {
                userExists = true;
                passwordCorrect = password.equals(userList.get(i).getPassword());
                i = userList.size();
            }
        }

        LoginResponse response = new LoginResponse();

        if(!userExists)
        {
            response.setErrorMessage("Username does not exist.");
            return response;
        }

        if(!passwordCorrect)
        {
            response.setErrorMessage("Incorrect password");
            return response;
        }

        String authToken = UUID.randomUUID().toString();
        response.setUsername(username);
        response.setAuthToken(authToken);
        ClientCommandManager manager = ClientCommandManager.getCommandManager();
        manager.addUser(authToken);
        model.addAuthTokenToDatabase(authToken);

        return response;
    }
}
