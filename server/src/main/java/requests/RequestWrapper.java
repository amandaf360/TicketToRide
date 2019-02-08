package requests;

import java.util.ArrayList;

public class RequestWrapper {
    private String requestType;
    private ArrayList<String> stringList;

    public RequestWrapper(String requestType, ArrayList<String> stringList) {
        this.requestType = requestType;
        this.stringList = stringList;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ArrayList<String> getStringList() {
        return stringList;
    }

    public void setStringList(ArrayList<String> stringList) {
        this.stringList = stringList;
    }
}
