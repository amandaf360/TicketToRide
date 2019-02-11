package responses;

public class StartGameResponse extends BaseResponse
{
    private String errorMessage;

    public StartGameResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
