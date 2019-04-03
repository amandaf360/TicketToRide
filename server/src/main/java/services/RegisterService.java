package services;

import java.util.List;

import responses.RegisterResponse;
import server.ClientCommandManager;
import servermodel.ModelRoot;
import servermodel.User;
import java.util.UUID;

public class RegisterService
{
    public RegisterResponse register(String username, String password)
    {
        ModelRoot model = ModelRoot.getModel();
        boolean nameAlreadyExists = false;
        List<User> userList = model.getUserList();

        for(int i = 0; i < userList.size(); i++)
        {
            if(userList.get(i).getUsername().equals(username))
            {
                nameAlreadyExists = true;
                i = userList.size();
            }
        }
        RegisterResponse response = new RegisterResponse();

        if(password == null || password.equals(""))
        {
            response.setErrorMessage("Password must be at least 1 character.");
            return response;
        }

        if(!nameAlreadyExists)
        {
            String authToken = UUID.randomUUID().toString();
            User user = new User(username, password, authToken);
            model.addUser(user);
            response.setUsername(username);
            response.setAuthToken(authToken);
            ClientCommandManager commandManager = ClientCommandManager.getCommandManager();
            commandManager.addUser(authToken);
            return response;
        }
        else
        {
            response.setErrorMessage("Username already exists.");
            return response;
        }

    }
}
