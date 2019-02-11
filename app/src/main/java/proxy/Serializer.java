package proxy;

import com.google.gson.Gson;

import requests.RequestWrapper;
import responses.*;

public class Serializer
{
    private Gson gson;

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

    public CreateGameResponse deserializeCreateGameResponse(String response)
    {
        return gson.fromJson(response, CreateGameResponse.class);
    }

    public PollResponse deserializePollResponse(String response)
    {
        return gson.fromJson(response, PollResponse.class);
    }

}