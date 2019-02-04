package commands;

import communicationdata.BaseResponse;

public class RegisterCommand implements ICommand
{
    private String username;
    private String password;

    @Override
    public BaseResponse execute()
    {
        return null;
    }

    public RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
