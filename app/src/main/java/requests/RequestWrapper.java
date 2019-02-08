package requests;

public class RequestWrapper
{
    String requestType;
    BaseRequest request;

    public RequestWrapper(String requestType, BaseRequest request) {
        this.requestType = requestType;
        this.request = request;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public BaseRequest getRequest() {
        return request;
    }

    public void setRequest(BaseRequest request) {
        this.request = request;
    }
}
