package services;

import java.util.List;

import communicationdata.RegisterResponse;
import servermodel.ModelRoot;
import servermodel.User;

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
            User user = new User(username, password);
            model.addUser(user);
            response.setUsername(username);
            return response;
        }
        else
        {
            response.setErrorMessage("Username already exists.");
            return response;
        }
    }
}
