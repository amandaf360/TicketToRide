package services;

import java.util.List;

import responses.BaseResponse;
import responses.LoginResponse;
import servermodel.ModelRoot;
import servermodel.User;

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

        response.setUsername(username);
        return response;
    }
}
