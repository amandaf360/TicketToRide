package commands;

import responses.BaseResponse;
import services.PollService;

public class PollCommand implements ICommand
{
    private String username;

    public BaseResponse execute()
    {
        PollService service = new PollService();
        return service.poll(username);
    }

    public PollCommand(String username) {
        this.username = username;
    }


}
