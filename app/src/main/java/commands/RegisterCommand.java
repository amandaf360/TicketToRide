package commands;

public class RegisterCommand implements ICommand
{
    private String username;
    private String errorMessage;

    public void execute()
    {
        /*
        if(username != null)
        {
            SetPlayerService playerService = new SetPlayerService();
            playerService.setPlayer(username);
        }
        else
        {
            ShowMessageService messageService = new ShowMessageService();
            messageService.show(errorMessage);
        }

        */
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
