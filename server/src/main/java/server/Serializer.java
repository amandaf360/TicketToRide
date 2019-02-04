package server;

import com.google.gson.*;

import communicationdata.BaseResponse;
import communicationdata.Request;

public class Serializer
{
    Gson gson;
    public Serializer()
    {
        gson = new Gson();
    }

    public Request deserializeRequest(String serializedString)
    {
        return gson.fromJson(serializedString, Request.class);
    }

    public String serializeResponse(BaseResponse response)
    {
        return gson.toJson(response);
    }
}
