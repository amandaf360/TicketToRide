package commands;

import java.io.Serializable;

import responses.BaseResponse;
import responses.PollResponse;
import services.PollService;

public class PollCommand implements ICommand, Serializable
{
    private String username;
    private boolean firstPoll;

    public BaseResponse execute()
    {
            PollService service = new PollService();
            return service.poll(username, firstPoll);
    }

    public PollCommand(String username, boolean firstPoll) {
        this.username = username;
        this.firstPoll = firstPoll;
    }


}
