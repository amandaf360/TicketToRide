package commands;

import services.SetMessageService;

public class StartGameCommand implements ICommand
{
    private String message;

    public StartGameCommand(String message) {
        this.message = message;
    }

    public void execute()
    {
        SetMessageService messageService = new SetMessageService();
        messageService.setMessage(message);
    }
}
