package commands;

import services.SetMessageService;

public class CreateGameCommand implements ICommand
{
    private String errorMessage;


    @Override
    public void execute()
    {
        if(errorMessage != null)
        {
            SetMessageService service = new SetMessageService();
            service.setMessage(errorMessage);
        }
    }

    public CreateGameCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
