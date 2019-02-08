package communicationdata;

public class LoginResponse extends BaseResponse
{
    private String username;
    private String errorMessage;

    public LoginResponse()
    {
        username = null;
        errorMessage = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
