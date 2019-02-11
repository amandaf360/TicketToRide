package responses;

public class JoinGameResponse extends BaseResponse
{
    private String errorMessage;

    public JoinGameResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public JoinGameResponse() {
    }
}
