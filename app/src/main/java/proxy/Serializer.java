package proxy;

import com.google.gson.Gson;

import requests.RequestWrapper;
import responses.*;

public class Serializer
{
    Gson gson;

    public Serializer(){
        gson = new Gson();
    }

    public String serializeRequest(RequestWrapper request)
    {
        return gson.toJson(request);
    }

    public LoginResponse deserializeLoginResponse(String response)
    {
        return gson.fromJson(response, LoginResponse.class);
    }

    public RegisterResponse deserializeRegisterResponse(String response)
    {
        return gson.fromJson(response, RegisterResponse.class);
    }

}