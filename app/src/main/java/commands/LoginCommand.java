package commands;

import services.*;

public class LoginCommand implements ICommand
{
    String username;
    String errorMessage;

    public LoginCommand(String name, String error)
    {
        username = name;
        errorMessage = error;
    }

    public void execute()
    {

        if(username != null)
        {
            SetUserService userService = new SetUserService();
            userService.setUser(username);
        }
        else
        {
            SetMessageService messageService = new SetMessageService();
            messageService.setMessage(errorMessage);
        }
    }
}
