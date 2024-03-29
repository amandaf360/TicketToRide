package commands;

import java.io.Serializable;

import responses.BaseResponse;
import services.ClearPollService;

public class ClearPollCommand implements ICommand, Serializable
{
    String username;
    public BaseResponse execute()
    {
        ClearPollService service = new ClearPollService();
        service.clearPoll(username);
        return null;
    }

    public ClearPollCommand(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
