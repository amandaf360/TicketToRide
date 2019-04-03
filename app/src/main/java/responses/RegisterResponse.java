package responses;

public class RegisterResponse
{
    private String username;
    private String errorMessage;
    private String authToken;

    public RegisterResponse(String username, String errorMessage) {
        this.username = username;
        this.errorMessage = errorMessage;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
