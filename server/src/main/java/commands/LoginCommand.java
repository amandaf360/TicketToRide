package commands;

import responses.BaseResponse;
import services.LoginService;

public class LoginCommand implements ICommand
{

    private String username;
    private String password;

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public BaseResponse execute()
    {
        return new LoginService().login(username, password);
    }
}
