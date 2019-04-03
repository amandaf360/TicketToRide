package commands;

import services.*;

public class LoginCommand implements ICommand
{
    private String username;
    private String authToken;
    private String errorMessage;

    public LoginCommand(String name, String error, String authToken)
    {
        username = name;
        errorMessage = error;
        this.authToken = authToken;
    }

    public void execute()
    {
        if(username != null)
        {
            SetUserService userService = new SetUserService();
            userService.setUser(username, authToken);
        }
        else
        {
            SetMessageService messageService = new SetMessageService();
            messageService.setMessage(errorMessage);
        }
    }
}
