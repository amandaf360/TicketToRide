package commands;

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
}
