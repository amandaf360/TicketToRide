package commands;

import java.io.Serializable;

import responses.BaseResponse;
import services.RegisterService;

public class RegisterCommand implements ICommand, Serializable
{
    private String username;
    private String password;

    @Override
    public BaseResponse execute()
    {
        RegisterService service = new RegisterService();
        return service.register(username, password);
    }

    public RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
