package commands;
import services.*;

public class RegisterCommand implements ICommand
{
    private String username;
    private String errorMessage;
    private String authToken;

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

    public RegisterCommand(String username, String errorMessage, String authToken)
    {
        this.username = username;
        this.errorMessage = errorMessage;
        this.authToken = authToken;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
