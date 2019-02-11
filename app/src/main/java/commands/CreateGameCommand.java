package commands;

import services.SetGamelistService;

public class CreateGameCommand implements ICommand
{
    private String errorMessage;


    @Override
    public void execute()
    {
        if(errorMessage != null)
        {
            return;
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
