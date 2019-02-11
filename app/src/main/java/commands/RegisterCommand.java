package commands;
import services.*;

public class RegisterCommand implements ICommand
{
    private String username;
    private String errorMessage;

    public void execute()
    {
        System.out.println("In register command");
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

    public RegisterCommand(String username, String errorMessage)
    {
        this.username = username;
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
