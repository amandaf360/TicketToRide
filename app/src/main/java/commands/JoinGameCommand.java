package commands;

import services.SetMessageService;

public class JoinGameCommand implements ICommand
{
    private String errorMessage;
    public void execute()
    {
        if(errorMessage != null && !(errorMessage.equals("")))
        {
            SetMessageService service = new SetMessageService();
            service.setMessage(errorMessage);
        }
    }

    public JoinGameCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
