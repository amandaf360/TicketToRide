package server;

import com.google.gson.*;

import responses.BaseResponse;
import requests.RequestWrapper;

public class Serializer
{
    Gson gson;
    public Serializer()
    {
        gson = new Gson();
    }

    public RequestWrapper deserializeRequestWrapper(String serializedString)
    {
        return gson.fromJson(serializedString, RequestWrapper.class);
    }

    public String serializeResponse(BaseResponse response)
    {
        return gson.toJson(response);
    }
}
